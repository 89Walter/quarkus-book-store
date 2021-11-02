package org.relatech.book;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.relatech.bookuser.BookUser;
import org.relatech.bookuser.BookUserId;
import org.relatech.bookuser.BookUserRepository;
import org.relatech.mapper.ApplicationMapper;
import org.relatech.user.User;
import org.relatech.user.UserClient;
import org.relatech.user.UserDTO;
import org.relatech.user.UserRepository;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController implements BookControllerInterface {
	
	@Inject
	BookRepository bookRepository;

	@Inject
	ApplicationMapper mapper;

	@Inject
	UserRepository userRepository;

	@Inject
	BookUserRepository bookUserRepository;

	@Inject
	@RestClient
	UserClient userClient;

	@GET
	@Path("/userInfo/{fiscalCode}")
	@Override
	public Response getUserInfo(@PathParam("fiscalCode") String fiscalCode) {
		return userClient.getUser(fiscalCode);
	}

	@GET
	@Override
	public Response getAllBooks() {
		List<Book> books = bookRepository.listAll();
		List<BookDTO> booksDTO =
				books.stream().map(book -> mapper.convert(book)).collect(Collectors.toList());
		return Response.ok(booksDTO).build();
	}

	@GET
	@Path("/getBook/{id}")
	@Override
	public Response getBookById(@PathParam("id") Long id) {
		return bookRepository
				.findByIdOptional(id)
				.map(book -> Response.ok(mapper.convert(book)).build())
				.orElse(Response.status(Status.NOT_FOUND).build());

		/* PROGRAMMAZIONE TRADIZIONALE */
		//		Book book = bookRepository.findById(id);
		//		if(book != null) {
		//			return Response.ok(book).build();
		//		}
		//		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/title")
	@Override
	public Response getBookByTitle(@QueryParam("title") String title) {
		return bookRepository
				.find("title", title)
				.singleResultOptional()
				.map(book -> Response.ok(mapper.convert(book)).build())
				.orElse(Response.status(Status.NOT_FOUND).build());		
	}

	@POST
	@Path("{bookId}")
	@Transactional
	@Override
	public Response addUser(@PathParam("bookId") Long bookId, UserDTO userDTO) {
		return bookRepository
				.findByIdOptional(bookId)
				.map(
						book -> {
							User user = mapper.convert(userDTO);
							BookUser bookUser = new BookUser();
							bookUser.setId(new BookUserId());
							bookUser.setRating(4);
							book.addBookUser(bookUser);
							user.addBookUser(bookUser);
							bookUserRepository.persist(bookUser);			
							if(bookUserRepository.isPersistent(bookUser)) {
								return Response.ok(mapper.convert(book)).build();				
							}
							return Response.status(Status.BAD_REQUEST).build();
						})
				.orElse(Response.status(Status.NOT_FOUND).build());
	}

	@DELETE
	@Path("/deleteUser/{bookId}/{userId}") 
	@Transactional //questa annotazione, la si usa ogni qual volta che bisogna modificare il DB
	@Override
	public Response deleteUser(@PathParam("bookId") Long bookId, @PathParam("userId") Long userId) {
		return bookUserRepository.listAll().stream()
				.filter(bookUser -> bookUser.getBook().getId().equals(bookId))
				.filter(bookUser -> bookUser.getUser().getId().equals(userId))
				.findFirst()
				.map(
						bookUser -> {
							//Elimino dalla memoria Java
							User user = bookUser.getUser();  
							Book book = bookUser.getBook();      
							user.removeBookUser(bookUser);    
							book.removeBookUser(bookUser);
							//Elimino l'utente dal DB se non ha associato nessun libro
							bookUserRepository.delete(bookUser);
							if(user.getBookUsers().isEmpty()) {
								userRepository.deleteById(userId);
							}
							return Response.ok(mapper.convert(book)).build();
						})
				.orElse(Response.status(Status.NOT_FOUND).build());
	}

	@POST
	@Path("/createBook")
	@Transactional
	@Override
	public Response createNewBook(BookDTO bookDTO) {
		Book book = mapper.convert(bookDTO);
		bookRepository.persist(book); //INSERT INTO BOOK
		return bookRepository.isPersistent(book) //SELECT * FROM BOOK WHERE BOOK.ID = ID
				? Response.created(URI.create("/book/" + book.getId())).build()
						: Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("/updateBook")
	@Transactional
	@Override
	public Response updateBook(BookDTO bookDTO) {
		return bookRepository
				.findByIdOptional(bookDTO.getId())
				.map(
						book -> {
							Book bookUpdated = mapper.convert(bookDTO);
							mapper.merge(book, bookUpdated);
							bookRepository.getEntityManager().merge(book);
							return Response.ok(mapper.convert(book)).build();
						})
				.orElse(Response.status(Status.NOT_FOUND).build());	
	}

	@DELETE
	@Path("/deleteBook/{id}")
	@Transactional
	@Override
	public Response deleteBook(@PathParam("id") Long id) {
		return bookRepository.deleteById(id) 
				? Response.noContent().build()
						: Response.status(Status.NOT_FOUND).build();		
	}

}

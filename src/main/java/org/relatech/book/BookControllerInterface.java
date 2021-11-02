package org.relatech.book;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.relatech.user.UserDTO;

public interface BookControllerInterface {

	@GET
	@Path("/userInfo/{fiscalCode}")
	public Response getUserInfo(
			@PathParam("fiscalCode") String fiscalCode);

	@GET
	public Response getAllBooks();

	@GET
	@Path("/getBook/{id}")
	public Response getBookById(
			@PathParam("id") Long id);

	@GET
	@Path("/title")
	public Response getBookByTitle(
			@QueryParam("title") String title);

	@POST
	@Path("{bookId}")
	@Transactional
	public Response addUser(
			@PathParam("bookId") Long bookId, UserDTO userDTO);

	@DELETE
	@Path("/deleteUser/{bookId}/{userId}") 
	@Transactional
	public Response deleteUser(
			@PathParam("bookId") Long bookId, 
			@PathParam("userId") Long userId);

	@POST
	@Path("/createBook")
	@Transactional
	public Response createNewBook(
			BookDTO bookDTO);

	@PUT
	@Path("/updateBook")
	@Transactional
	public Response updateBook(
			BookDTO bookDTO);

	@DELETE
	@Path("/deleteBook/{id}")
	@Transactional
	public Response deleteBook(
			@PathParam("id") Long id);

}

package org.relatech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.relatech.book.Book;
import org.relatech.book.BookDTO;
import org.relatech.bookself.BookSelf;
import org.relatech.bookself.BookSelfDTO;
import org.relatech.category.Category;
import org.relatech.category.CategoryDTO;
import org.relatech.user.User;
import org.relatech.user.UserDTO;

@Mapper(
		componentModel = "cdi", //cdi = Context Dependency Injection
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApplicationMapper {

	ApplicationMapper MAPPER = Mappers.getMapper(ApplicationMapper.class);

	//DTO to DAO
	@Mapping(source = "name", target = "title")
	@Mapping(source = "writer", target = "author")
	Book convert(BookDTO bookDTO);

	User convert(UserDTO userDTO);

	Category convert(CategoryDTO categoryDTO);

	BookSelf convert(BookSelfDTO bookSelfDTO);

	//DAO to DTO
	@Mapping(source = "title", target = "name")
	@Mapping(source = "author", target = "writer")
	@Mapping(target = "information", expression = "java(book.getTitle() + ' ' + book.getAuthor())")
	@Mapping(target = "users", expression = "java(ApplicationMapperDTO.users(book))")
	BookDTO convert(Book book);

	@Mapping(target = "id", ignore = true)
	UserDTO convert(User user);

	CategoryDTO convert(Category category);

	BookSelfDTO convert(BookSelf bookSelf);
	
	//MERGE
	@Mapping(target = "id", ignore = true) //non aggiornare l'id
	@Mapping(target = "price", ignore = true) //non aggiornare il prezzo
	@Mapping(target = "bookUsers", ignore = true)
	void merge(@MappingTarget Book target, Book source);

}

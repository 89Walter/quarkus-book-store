package org.relatech.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.relatech.book.Book;
import org.relatech.user.UserDTO;

import static org.relatech.mapper.ApplicationMapper.MAPPER;

public class ApplicationMapperDTO {

	private ApplicationMapperDTO() {}

	static Set<UserDTO> users(Book book) {
		if (book.getBookUsers().isEmpty()) {			
			return new HashSet<>();
		}
		return book.getBookUsers().stream()
				.map(bookUser -> MAPPER.convert(bookUser.getUser()))
				.collect(Collectors.toSet());
	}
}

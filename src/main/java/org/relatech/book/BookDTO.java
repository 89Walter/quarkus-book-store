package org.relatech.book;

import java.util.HashSet;
import java.util.Set;

import org.relatech.category.CategoryDTO;
import org.relatech.user.UserDTO;

public class BookDTO {

	private Long id;

	private Long isbn;

	private String name; //title

	private String writer; //author
	
	private Long price;

	private String information; //title + author

	private CategoryDTO category;

	private Set<UserDTO> users = new HashSet<>();
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	} 

}

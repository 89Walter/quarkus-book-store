package org.relatech.category;

import org.relatech.bookself.BookSelfDTO;

public class CategoryDTO {

	private Long id;

	private String name;

	private String description;
	
	private String tag;

	private BookSelfDTO bookSelf;
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BookSelfDTO getBookSelf() {
		return bookSelf;
	}

	public void setBookSelf(BookSelfDTO bookSelf) {
		this.bookSelf = bookSelf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}



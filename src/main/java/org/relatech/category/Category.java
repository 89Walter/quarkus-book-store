package org.relatech.category;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.relatech.bookself.BookSelf;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 100, unique = false, nullable = true)
	private String name;
	
	@Column
	private String description;
	
	private String tag;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookSelf_id", referencedColumnName = "id")
	private BookSelf bookSelf;
	
	public BookSelf getBookSelf() {
		return bookSelf;
	}

	public void setBookSelf(BookSelf bookSelf) {
		this.bookSelf = bookSelf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}

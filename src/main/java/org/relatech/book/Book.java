package org.relatech.book;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.relatech.bookuser.BookUser;
import org.relatech.category.Category;

@Entity
public class Book {
	
	//La relazione tra Book e User ManyToMany avviene tramite la relazione ManyToOne tra User e BookUser e tramite la relazione ManyToOne di Book e BookUser

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, unique = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Una categoria può avere molti libri
	private Category category; 

	private Long price;

	private String author;

	private Long isbn; 

	//un libro può avere più relazioni con più utenti
	@OneToMany(mappedBy = "book")
	private Set<BookUser> bookUsers = new HashSet<>();

	public void addBookUser(BookUser bookUser) {
		boolean added = this.bookUsers.add(bookUser);
		if(added) {
			bookUser.setBook(this);
		}
	}

	public void removeBookUser(BookUser bookUser) {
		boolean removed = this.bookUsers.remove(bookUser);
		if(removed) {
			bookUser.setBook(null);
		}
	}

	public Set<BookUser> getBookUsers() {
		return Collections.unmodifiableSet(bookUsers);
	}

	public void setBookUsers(Set<BookUser> bookUsers) {
		if(bookUsers == null) {
			this.bookUsers = new HashSet<>();
		} else {
			this.bookUsers = new HashSet<>(bookUsers);
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}



}

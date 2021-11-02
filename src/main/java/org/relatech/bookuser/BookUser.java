package org.relatech.bookuser;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.relatech.book.Book;
import org.relatech.user.User;

@Entity
public class BookUser {
	
	//La relazione tra Book e User ManyToMany avviene tramite la relazione ManyToOne tra User e BookUser e tramite la relazione ManyToOne di Book e BookUser
	
	@EmbeddedId // si può definire come id custom ovvero la fusione dei due Id
	private BookUserId id;
	
	//Molti libri possono avere una relazione con BookUser
	@MapsId("bookId")
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	//Molti utenti possono avere una relazione con BookUser
	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private int rating;

	public BookUserId getId() {
		return id;
	}

	public void setId(BookUserId id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	} 
	
	

}

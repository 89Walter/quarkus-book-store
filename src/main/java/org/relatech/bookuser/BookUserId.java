package org.relatech.bookuser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//cioè che deve essere inserito all'interno di BookUser
@Embeddable
public class BookUserId implements Serializable {
	
	@Column(name = "book_id")
	private Long bookId;
	
	@Column(name = "user_id")
	private Long userId;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}

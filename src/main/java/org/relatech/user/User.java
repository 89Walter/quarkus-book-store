package org.relatech.user;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.relatech.bookuser.BookUser;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "fiscal_code")
	private String fiscalCode;
	
	//un utente può avere più associazioni con più libri
	@OneToMany(mappedBy = "user")
	private Set<BookUser> bookUsers = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public void addBookUser(BookUser bookUser) {
		boolean added = this.bookUsers.add(bookUser);
		if(added) {
			bookUser.setUser(this);
		}
	}

	public void removeBookUser(BookUser bookUser) {
		boolean removed = this.bookUsers.remove(bookUser);
		if(removed) {
			bookUser.setUser(null);
		}
	}

	public Set<BookUser> getBookUsers() {
		return Collections.unmodifiableSet(bookUsers);
	}

	public void setBooks(Set<BookUser> bookUsers) {
		if(bookUsers == null) {
			this.bookUsers = new HashSet<>();
		} else {
			this.bookUsers = new HashSet<>(bookUsers);
		}
	}


}

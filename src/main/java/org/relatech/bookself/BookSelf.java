package org.relatech.bookself;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.relatech.category.Category;

@Entity
public class BookSelf {

	@Id
	@GeneratedValue
	private Long id;

	private String typology;

	@OneToOne(mappedBy = "bookSelf")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypology() {
		return typology;
	}

	public void setTypology(String typology) {
		this.typology = typology;
	}



}

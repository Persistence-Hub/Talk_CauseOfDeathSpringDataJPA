package com.thorben.janssen.talk.CauseOfDeathSpringDataJpa.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable
public class Author {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;

	private String firstName;

	private String lastName;

	@ManyToMany(
		mappedBy = "authors" 
//		, fetch = FetchType.EAGER
		, cascade = CascadeType.ALL
	)
	private Set<Book> books = new HashSet<Book>();

	public Long getId() {
		return this.id;
	}

	public int getVersion() {
		return this.version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(final Set<Book> books) {
		this.books = books;
	}

	public void addBook(Book b) {
		this.books.add(b);
		b.getAuthors().add(this);
	}

	public void removeBook(Book b) {
		this.books.remove(b);
		b.getAuthors().remove(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		if (id == null || !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (firstName != null && !firstName.trim().isEmpty())
			result += "firstName: " + firstName;
		if (lastName != null && !lastName.trim().isEmpty())
			result += ", lastName: " + lastName;
		return result;
	}
}
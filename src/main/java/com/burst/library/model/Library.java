package com.burst.library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name_library")
    private String nameLibrary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "libraries_books",
            joinColumns = @JoinColumn(name = "library_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();

    public Library() {
    }

    public Long getId() {
        return id;
    }

    public String getNameLibrary() {
        return nameLibrary;
    }

    public void setNameLibrary(String nameLibrary) {
        this.nameLibrary = nameLibrary;
    }

    public Set<Book> getBooks() { return books; }

    public void setBooks(Set<Book> books) { this.books = books; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(nameLibrary, library.nameLibrary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameLibrary);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", nameLibrary='" + nameLibrary + '\'' +
                ", books=" + books +
                '}';
    }
}

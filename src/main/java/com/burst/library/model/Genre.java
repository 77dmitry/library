package com.burst.library.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name_genre")
    private String nameGenres;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameGenres() {
        return nameGenres;
    }

    public void setNameGenres(String nameGenres) {
        this.nameGenres = nameGenres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(nameGenres, genre.nameGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameGenres);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", nameGenres='" + nameGenres + '\'' +
                '}';
    }
}

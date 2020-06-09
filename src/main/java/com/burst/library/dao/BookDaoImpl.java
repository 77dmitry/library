package com.burst.library.dao;

import com.burst.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl extends AbstractDao implements GeneralDao<Book> {

    @Override
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        entityManager.merge(book);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> list = entityManager.createQuery("SELECT b FROM Book b " +
                "LEFT JOIN FETCH b.authors a " +
                "LEFT JOIN FETCH b.genre g ", Book.class)
                .getResultList();
        return list;
    }

    @Override
    public Book getByName(String name) {
        Book book = entityManager.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE b.title = :name", Book.class)
                    .setParameter("name", name)
                    .getResultStream().findFirst().orElse(new Book());
        return book;
    }

    @Override
    public Book getById(Long id) {
        Book book = entityManager.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE b.id = :id",Book.class)
        .setParameter("id", id)
        .getResultStream().findFirst().orElse(new Book());
        return book;
    }

    @Override
    public int delete(Long id) {
        return entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}

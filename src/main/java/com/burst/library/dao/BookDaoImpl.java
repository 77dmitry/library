package com.burst.library.dao;

import com.burst.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl extends AbstractDao implements GeneralDao<Book> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Book save(Book book) {
        LOGGER.info("Book saved: " + book.getTitle());
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        LOGGER.info("Book update: " + book.getTitle());
        entityManager.merge(book);
        return book;
    }

    @Override
    public List<Book> getAll() {
        LOGGER.info("We get all the books");
        List<Book> list = entityManager.createQuery("SELECT b FROM Book b " +
                "LEFT JOIN FETCH b.authors a " +
                "LEFT JOIN FETCH b.genre g", Book.class)
                .getResultList();
        return list;
    }

    @Override
    public Book getByName(String name) {
        LOGGER.info("Get the book by name - " + name);
        Book book = entityManager.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE b.title = :name", Book.class)
                    .setParameter("name", name)
                    .getResultStream().findFirst().orElse(new Book());
        return book;
    }

    @Override
    public Book getById(Long id) {
        LOGGER.info("Get the book by id - " + id);
        Book book = entityManager.createQuery("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE b.id = :id",Book.class)
        .setParameter("id", id)
        .getResultStream().findFirst().orElse(new Book());
        return book;
    }

    @Override
    public int delete(Long id) {
        LOGGER.info("Delete the book by id - " + id);
        return entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}

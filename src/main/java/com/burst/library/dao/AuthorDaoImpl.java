package com.burst.library.dao;

import com.burst.library.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl extends AbstractDao implements GeneralDao<Author> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Override
    public Author save(Author author) {
        LOGGER.info("Saved author - " + author.getFirstName() + " " + author.getLastName());
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        LOGGER.info("Author update - " + author.getLastName());
        entityManager.merge(author);
        return author;
    }

    @Override
    public List<Author> getAll() {
        LOGGER.info("We get all the authors");
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author getByName(String name) {
        LOGGER.info("Get author by name + " + name);
        Author author = entityManager.createQuery("SELECT a FROM Author a WHERE a.lastName = :name", Author.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst().orElse(new Author());
        return author;
    }

    @Override
    public Author getById(Long id) {
        LOGGER.info("Get by Author by id " + id);
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    @Override
    public int delete(Long id) {
        LOGGER.info("Deleted author by id");
        return entityManager.createQuery("DELETE FROM Author a WHERE a.id = :id", Author.class)
                .setParameter("id", id)
                .executeUpdate();
    }
}

package com.burst.library.dao;

import com.burst.library.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl extends AbstractDao implements GeneralDao<Author> {

    @Override
    public Author save(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        entityManager.merge(author);
        return author;
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author getByName(String name) {
        Author author = entityManager.createQuery("SELECT a FROM Author a WHERE a.lastName = :name", Author.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst().orElse(new Author());
        return author;
    }

    @Override
    public Author getById(Long id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    @Override
    public int delete(Long id) {
        return entityManager.createQuery("DELETE FROM Author a WHERE a.id = :id", Author.class)
                .setParameter("id", id)
                .executeUpdate();
    }
}

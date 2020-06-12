package com.burst.library.dao;

import com.burst.library.model.Library;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryDaoImpl extends AbstractDao implements GeneralDao<Library> {

    @Override
    public Library save(Library library) {
        entityManager.persist(library);
        return library;
    }

    @Override
    public Library update(Library library) {
        entityManager.merge(library);
        return library;
    }

    @Override
    public List<Library> getAll() {
        return entityManager.createQuery("SELECT l FROM Library l LEFT JOIN FETCH l.books b", Library.class)
                .getResultList();
    }

    @Override
    public Library getByName(String name) {
        Library library = entityManager.createQuery("SELECT l FROM Library l LEFT JOIN FETCH l.books b LEFT JOIN FETCH b.authors a WHERE l.nameLibrary = :name", Library.class)
                .setParameter("name", name)
                .getResultStream().findFirst().orElse(new Library());
        return library;
    }

    @Override
    public Library getById(Long id) {
        Library library = entityManager.createQuery("SELECT l FROM Library l LEFT JOIN FETCH l.books b LEFT JOIN FETCH b.authors a WHERE l.id = :id", Library.class)
                .setParameter("id", id)
                .getResultStream().findFirst().orElse(new Library());
        return library;
    }

    @Override
    public int delete(Long id) {
        return entityManager.createQuery("DELETE FROM Library l WHERE l.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}

package com.burst.library.dao;

import com.burst.library.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoImpl extends AbstractDao implements GeneralDao<Genre> {

    @Override
    public Genre save(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Genre update(Genre genre) {
        entityManager.merge(genre);
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public Genre getByName(String name) {
        Genre genre = entityManager.createQuery("SELECT g FROM Genre g WHERE g.nameGenres = :name", Genre.class)
                .setParameter("name", name)
                .getResultStream().findFirst().orElse(new Genre());
        return genre;
    }

    @Override
    public Genre getById(Long id) {
        Genre genre = entityManager.createQuery("SELECT g FROM Genre g WHERE g.id = :id", Genre.class)
                .setParameter("id", id)
                .getResultStream().findFirst().orElse(new Genre());
        return genre;
    }

    @Override
    public int delete(Long id) {
        return entityManager.createQuery("DELETE FROM Genre g WHERE g.id = :id", Genre.class)
                .setParameter("id", id)
                .executeUpdate();
    }
}

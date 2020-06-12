package com.burst.library.service;

import com.burst.library.dao.GeneralDao;
import com.burst.library.model.Genre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GeneralService<Genre> {

    private final GeneralDao<Genre> generalDao;

    public GenreServiceImpl(GeneralDao<Genre> generalDao) {
        this.generalDao = generalDao;
    }

    @Override
    public List<Genre> getAll() {
        return generalDao.getAll();
    }

    @Override
    public Genre getByName(String name) {
        return generalDao.getByName(name);
    }

    @Override
    public Genre getById(Long id) {
        return generalDao.getById(id);
    }

    @Override
    public Genre add(Genre genre) {
        Genre newGenre = generalDao.getByName(genre.getNameGenres());
        if (newGenre.getNameGenres().isEmpty()) {
            newGenre.setNameGenres(genre.getNameGenres());
        }
        return generalDao.save(genre);
    }

    @Override
    public void delete(Long id) {
        generalDao.delete(id);
    }
}

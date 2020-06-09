package com.burst.library.service;

import com.burst.library.dao.GeneralDao;
import com.burst.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements GeneralService<Author> {

    private final GeneralDao<Author> generalDao;

    @Autowired
    public AuthorServiceImpl(GeneralDao<Author> generalDao) {
        this.generalDao = generalDao;
    }

    @Override
    public List<Author> getAll() {
        return generalDao.getAll();
    }

    @Override
    public Author getByName(String name) {
        Author author = generalDao.getByName(name);
        return author;
    }

    @Override
    public Author getById(Long id) {
        Author author = generalDao.getById(id);
        return author;
    }

    @Override
    public Author add(Author author) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(author.getFirstName());
        newAuthor.setLastName(author.getLastName());
        return generalDao.save(author);
    }

    @Override
    public void delete(Long id) {
        generalDao.delete(id);
    }
}

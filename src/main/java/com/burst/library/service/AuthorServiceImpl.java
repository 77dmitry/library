package com.burst.library.service;

import com.burst.library.dao.GeneralDao;
import com.burst.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements GeneralService<Author> {

    private GeneralDao generalDao;

    @Autowired
    public AuthorServiceImpl(@Qualifier(value = "authorDaoImpl") GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Override
    public List<Author> getAll() {
        return generalDao.getAll();
    }

    @Override
    public Object getByName(String name) {
        return generalDao.getByName(name);
    }

    @Override
    public Object getById(Long id) {
        return generalDao.getById(id);
    }

    @Override
    public Author add(Author author) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(author.getFirstName());
        newAuthor.setLastName(author.getLastName());
        return (Author) generalDao.save(author);
    }

    @Override
    public void delete(Long id) {
        generalDao.delete(id);
    }
}

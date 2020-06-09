package com.burst.library.service;

import com.burst.library.dao.GeneralDao;
import com.burst.library.model.Author;
import com.burst.library.model.Book;
import com.burst.library.model.Genre;
import com.burst.library.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class BookServiceImpl implements GeneralService<Book>, CheckEntityValidity {

    private GeneralDao<Book> repository;

    @Autowired
    private GeneralService<Author> authorGeneralService;

    @Autowired
    private GeneralService<Genre> genreGeneralService;

    @Autowired
    private GeneralService<Library> libraryGeneralService;

    @Autowired
    public BookServiceImpl(GeneralDao<Book> repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public Book getByName(String nameBook) {
        Book book = repository.getByName(nameBook);
        return book;
    }

    @Override
    public Book getById(Long id) {
        Book book = repository.getById(id);
        return book;
    }

    @Override
    public Book add(Book newBook) {
        Book book = repository.getByName(newBook.getTitle());
        if (book.getTitle() != null) {
            return book;
        }
        book.setTitle(newBook.getTitle());
        for (Author author : newBook.getAuthors()) {
            if (isValidAuthor(author)) {
                book.getAuthors().add(authorGeneralService.add(author));
            } else {
                book.getAuthors().add(author);
            }
        }
        if (isValidGenre(newBook.getGenre())) {
            book.setGenre(genreGeneralService.add(newBook.getGenre()));
        } else {
            book.setGenre(newBook.getGenre());
        }
        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }


    @Override
    public boolean isValidAuthor(Author author) {
        if (authorGeneralService.getByName(author.getLastName()) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidGenre(Genre genre) {
        if (genreGeneralService.getByName(genre.getNameGenres()) == null) {
            return false;
        }
        return true;
    }
}

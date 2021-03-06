package com.burst.library.service;

import com.burst.library.dao.GeneralDao;
import com.burst.library.model.Author;
import com.burst.library.model.Book;
import com.burst.library.model.Genre;
import com.burst.library.model.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class BookServiceImpl implements GeneralService<Book> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

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
        LOGGER.info("We get all the books");
        return repository.getAll();
    }

    @Override
    public Book getByName(String nameBook) {
        LOGGER.info("Get the book by name - " + nameBook);
        Book book = repository.getByName(nameBook);
        return book;
    }

    @Override
    public Book getById(Long id) {
        LOGGER.info("Get the book by id - " + id);
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
                book.addAuthor(authorGeneralService.getByName(author.getLastName()));
                break;
            } else {
                book.addAuthor(author);
            }
        }
        if (isValidGenre(newBook.getGenre())) {
            book.setGenre(genreGeneralService.getByName(newBook.getGenre().getNameGenres()));
        } else {
            book.setGenre(newBook.getGenre());
        }
        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Delete the book by id - " + id);
        repository.delete(id);
    }

    private boolean isValidAuthor(Author author) {
        Author validAuthor = authorGeneralService.getByName(author.getLastName());
        if (validAuthor.equals(author)) {
            LOGGER.info("Valid name author = " + author.getFirstName() + " " + author.getLastName());
            return true;
        }
        LOGGER.info("author = NULL");
        return false;
    }

    private boolean isValidGenre(Genre genre) {
        Genre validGenre = genreGeneralService.getByName(genre.getNameGenres());
        if (validGenre.equals(genre)) {
            LOGGER.info("Valid name genre = " + genre.getNameGenres());
            return true;
        }
        return false;
    }
}

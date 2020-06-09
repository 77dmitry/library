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
class BookServiceImpl implements GeneralService<Book> {

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
        if (book.getTitle() == null) {
            book.setTitle(newBook.getTitle());
            for (Author author : newBook.getAuthors()) {
                if (authorGeneralService.getByName(author.getLastName()) != null) {
                    book.getAuthors().add(authorGeneralService.add(author));
                }
                book.getAuthors().add(author);
            }
            Genre genre = (Genre) genreGeneralService.getByName(newBook.getGenre().getNameGenres());
            if (genre.getNameGenres() == null) {
                genreGeneralService.add(newBook.getGenre());
            }
            book.setGenre(newBook.getGenre());
        }
        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

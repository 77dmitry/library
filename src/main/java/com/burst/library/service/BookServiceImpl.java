package com.burst.library.service;

import com.burst.library.dao.BookDaoImpl;
import com.burst.library.model.Author;
import com.burst.library.model.Book;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class BookServiceImpl implements GeneralService<Book> {

    private BookDaoImpl repository;
    private final GeneralService<Author> generalService;

    public BookServiceImpl(BookDaoImpl repository, GeneralService<Author> generalService) {
        this.repository = repository;
        this.generalService = generalService;
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
                if (generalService.getByName(author.getLastName()) == null) {
                    generalService.add(author);
                }
                book.getAuthors().add(author);
            }
        }
        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

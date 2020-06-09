package com.burst.library.controller;

import com.burst.library.model.Book;
import com.burst.library.service.GeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/books")
class BookRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    private final GeneralService<Book> generalService;

    public BookRestController(@Qualifier("bookServiceImpl") GeneralService<Book> generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/all")
    protected ResponseEntity<List<Book>> getBooks() {
        LOGGER.info("Получаем список книг");
        List<Book> list = generalService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    protected ResponseEntity<Book> getBookByName(@PathVariable("name") String name) {
        LOGGER.info("Получаем книгу по названию");
        Book book = (Book) generalService.getByName(name);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/id/{id}")
    protected ResponseEntity<Book> getBookByName(@PathVariable("id") Long id) {
        LOGGER.info("Получаем книгу по ID");
        Book book = (Book) generalService.getById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/addBook", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        LOGGER.info("Добавляем новую книгу", book.getTitle());
        return ResponseEntity.ok(generalService.add(book));
    }

    @DeleteMapping("/delete/{id}")
    protected ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        LOGGER.info("Удаляем книгу", id);
        generalService.delete(id);
        return ResponseEntity.notFound().build();
    }
}

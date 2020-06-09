package com.burst.library.controller;

import com.burst.library.model.Author;
import com.burst.library.service.GeneralService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/authors")
class AuthorRestController {

    private final GeneralService<Author> generalService;

    public AuthorRestController(@Qualifier("authorServiceImpl") GeneralService<Author> generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/all")
    protected ResponseEntity<List<Author>> getAuthors() {
        List<Author> list = generalService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/author/name/{name}")
    protected ResponseEntity<Author> getAuthorByLastName(@PathVariable("name") String name) {
        Author author = (Author) generalService.getByName(name);
        return ResponseEntity.ok(author);
    }

    @PostMapping(value = "/author/add",
            consumes = "application/json",
            produces = "application/json")
    protected ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        generalService.add(author);
        return ResponseEntity.ok(author);
    }
}

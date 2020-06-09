package com.burst.library.controller;

import com.burst.library.model.Library;
import com.burst.library.service.GeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/libraries")
class LibraryRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LibraryRestController.class);

    private GeneralService generalService;

    public LibraryRestController(@Qualifier("libraryServiceImpl") GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/all")
    protected ResponseEntity<List<Library>> getLibraries() {
        LOGGER.info("Получаем список библиотек");
        List<Library> list = generalService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name/{name}")
    protected ResponseEntity<Library> getLibraryByName(@PathVariable("name") String nameLibrary) {
        LOGGER.info("Получаем библиотеку по имени");
        Library library = (Library) generalService.getByName(nameLibrary);
        return ResponseEntity.ok(library);
    }

    @PostMapping("/library/add")
    protected ResponseEntity<Library> addLibrary(@RequestBody Library library) {
        LOGGER.info("Создаем библиотеку");
        generalService.add(library);
        return ResponseEntity.ok(library);
    }

    @DeleteMapping("/delete/{id}")
    protected ResponseEntity<Library> delete(@PathVariable("id") Long id) {
        LOGGER.info("Удаляем библиотеку", id);
        generalService.delete(id);
        return ResponseEntity.ok().build();
    }
}

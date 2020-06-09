package com.burst.library.service;

import com.burst.library.dao.LibraryDaoImpl;
import com.burst.library.model.Library;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LibraryServiceImpl implements GeneralService<Library> {

    private LibraryDaoImpl repository;

    public LibraryServiceImpl(LibraryDaoImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Library> getAll() {
        return repository.getAll();
    }

    @Override
    public Library getByName(String nameLibrary) {
        return repository.getByName(nameLibrary);
    }

    @Override
    public Library getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Library add(Library library) {
         return repository.save(library);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

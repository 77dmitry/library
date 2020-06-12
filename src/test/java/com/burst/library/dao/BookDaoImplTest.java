package com.burst.library.dao;

import com.burst.library.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {

    private BookDaoImpl bookDao;

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByName_Return_True() {
        Book book = bookDao.getByName("Темная башня");

    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }
}
package com.burst.library.service;

import com.burst.library.model.Author;
import com.burst.library.model.Genre;

public interface CheckEntityValidity {

    boolean isValidAuthor(Author author);

    boolean isValidGenre(Genre genre);
}

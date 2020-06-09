package com.burst.library.model.dto;

public class BookDto {
    private Long id;
    private String title;
    private String authorFirstName;
    private String authorLastName;

    public BookDto() {}

    public BookDto(String title, String authorFirstName, String authorLastName) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public BookDto(Long id, String title, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
}

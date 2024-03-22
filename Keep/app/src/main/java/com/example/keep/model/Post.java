package com.example.keep.model;

public class Post {
    private String id;
    private String title;

    private String color;

    private String content;
    public Post(){}

    public Post(String id, String title, String content, String color) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

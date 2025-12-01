package com.example.hwnotesgapp;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class NoteItem implements Serializable {
    private String id;
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;

    public NoteItem() {
        // Пустой конструктор для Gson
    }

    public NoteItem(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    // Геттеры
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }

    // Сеттеры
    public void setTitle(String title) {
        this.title = title;
        this.updatedDate = new Date();
    }

    public void setContent(String content) {
        this.content = content;
        this.updatedDate = new Date();
    }

    // Методы для Gson
    public void setId(String id) { this.id = id; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
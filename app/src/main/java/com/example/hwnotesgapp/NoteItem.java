package com.example.hwnotesgapp;

import java.util.Date;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class NoteItem implements Serializable {
    private String id;
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;

    public NoteItem(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreatedDate() { return createdDate; }
    public Date getUpdatedDate() { return updatedDate; }


    public void setTitle(String title) {
        this.title = title;
        this.updatedDate = new Date();
    }

    public void setContent(String content) {
        this.content = content;
        this.updatedDate = new Date();
    }
}
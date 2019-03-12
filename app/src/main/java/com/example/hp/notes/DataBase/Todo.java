package com.example.hp.notes.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Title")
    String title;
    @ColumnInfo(name = "Date")
    String date;
    @ColumnInfo(name = "Content")
    String content;

    public Todo(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }
    @Ignore
    public Todo(int id,String title, String date, String content) {
        this.id=id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        if(title==null)
            return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        if(date==null)
            return "";
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        if(content==null)
            return "";
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

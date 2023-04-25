package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String note_title;

    @ColumnInfo(name = "content")
    private  String note_content;

    Notes(int id,String note_title,String note_content){
        this.id=id;
        this.note_content=note_content;
        this.note_title=note_title;


    }

    @Ignore
    Notes(String note_title,String note_content){
        this.note_content=note_content;
        this.note_title=note_title;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }
}

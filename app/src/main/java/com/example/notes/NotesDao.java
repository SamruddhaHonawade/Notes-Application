package com.example.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes")
    List<Notes> getallnotes();

    @Insert
    void addNote(Notes notes);

    @Delete
    void DleNote(Notes notes);


}

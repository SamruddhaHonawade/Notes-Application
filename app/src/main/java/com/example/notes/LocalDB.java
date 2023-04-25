package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Notes.class,exportSchema = false,version = 1)
public  abstract class LocalDB extends RoomDatabase {

    private static final String DB_NAME="notes";

    private static  LocalDB instance;

    public static synchronized LocalDB getDB(Context context){

        if(instance==null){


            instance= Room.databaseBuilder(context,LocalDB.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;
    }
    public  abstract NotesDao notesDao();
}

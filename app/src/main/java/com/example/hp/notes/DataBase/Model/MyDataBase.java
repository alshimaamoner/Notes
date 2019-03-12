package com.example.hp.notes.DataBase.Model;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hp.notes.DAO.BaseDao;
import com.example.hp.notes.DAO.TodoDAO;
import com.example.hp.notes.DataBase.Todo;

@Database(entities = {Todo.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase db;
    public abstract TodoDAO TodoDao();
    public static MyDataBase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), MyDataBase.class, "TODO-DataBase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return db;
    }
}

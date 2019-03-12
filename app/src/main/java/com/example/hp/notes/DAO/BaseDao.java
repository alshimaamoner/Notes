package com.example.hp.notes.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


public interface BaseDao<T> {
    @Insert
    public void insert(T todo);
    @Update
    public void update(T todo);
    @Delete
    void delete(T todo);
    @Query("Select * From Todo")
    public List<T> selectAllTodo();

}

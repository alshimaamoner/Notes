package com.example.hp.notes.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hp.notes.DataBase.Todo;

import java.util.List;

@Dao
public abstract class TodoDAO  implements BaseDao<Todo>{
    @Insert
    public abstract void insertion(Todo todo);
    @Query("UPDATE Todo SET Title = :Title, Date= :Date,Content=:Content WHERE id=:id")
  public abstract void update( int id,String Title, String Date,String Content);
    @Query("DELETE  from Todo  WHERE id=:id")
    public abstract void deletItem( int id);
    @Query("Select * From Todo Where id = :id")
    public abstract Todo selectTodo(int id);
     @Update
    public abstract void UpdatTask(Todo todo);
}

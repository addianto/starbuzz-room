package com.hfad.starbuzzroom.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DrinkDao {

    @Query("SELECT * FROM drink")
    List<Drink> getDrinks();

    @Query("SELECT * FROM drink WHERE id = :id")
    Drink findById(int id);

    @Insert
    void insert(Drink drink);

    @Insert
    void insertAll(Drink... drinks);

    @Delete
    void delete(Drink drink);
}

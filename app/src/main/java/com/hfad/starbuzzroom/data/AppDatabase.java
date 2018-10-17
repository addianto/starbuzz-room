package com.hfad.starbuzzroom.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Drink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DrinkDao drinkDao();
}

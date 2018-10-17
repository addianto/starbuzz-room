package com.hfad.starbuzzroom.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Drink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Starbuzz.db";
    private static AppDatabase INSTANCE = null;

    public abstract DrinkDao drinkDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }

        return INSTANCE;
    }

    /**
     * Create and pre-populate the database. Based on android-sunflower
     * example.
     *
     * @param context
     * @return an instance of AppDatabase
     */
    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class,
                DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(SupportSQLiteDatabase db) {
                super.onCreate(db);
                // TODO Use AsyncTask to populate DB using worker thread
                // TODO Refactor AsyncTask to use WorkManager?
            }
        }).build();
    }
}

package com.hfad.starbuzzroom.data;

import android.content.Context;
import android.os.AsyncTask;

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
                // TODO Refactor AsyncTask to use WorkManager?
                new SeedDatabaseTask().doInBackground(context);
            }
        }).build();
    }

    private static class SeedDatabaseTask extends AsyncTask<Context, Void, Void> {

        private static final int NEW = 0;

        private static final Drink[] SEED_DATA = new Drink[]{
                new Drink(NEW, "Latte", "Espresso and steamed milk", 1),
                new Drink(NEW, "Cappucinno", "Espresso, hot milk and steamed-milk foam", 2),
                new Drink(NEW, "Filter", "Our best drip coffee", 3)
        };

        @Override
        protected Void doInBackground(Context... contexts) {
            AppDatabase db = AppDatabase.getInstance(contexts[0]);
            DrinkDao dao = db.drinkDao();

            dao.insertAll(SEED_DATA);

            return null;
        }
    }
}

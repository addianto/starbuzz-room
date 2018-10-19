package com.hfad.starbuzzroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hfad.starbuzzroom.data.AppDatabase;
import com.hfad.starbuzzroom.data.Drink;
import com.hfad.starbuzzroom.data.DrinkDao;

import java.util.List;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        ListView listDrinks = findViewById(R.id.list_drinks);
         listDrinks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DrinkCategoryActivity.this,
                    DrinkActivity.class);
            intent.putExtra(DrinkActivity.EXTRA_DRINKID, position + 1);
            startActivity(intent);
        });
        new SetAdapterTask(listDrinks).execute(this);
    }

    private class SetAdapterTask extends AsyncTask<Context, Void, ListAdapter> {

        private ListView listView;

        public SetAdapterTask(ListView listView) {
            this.listView = listView;
        }

        @Override
        protected ListAdapter doInBackground(Context... contexts) {
            Context context = contexts[0];
            AppDatabase db = AppDatabase.getInstance(context);
            DrinkDao dao = db.drinkDao();
            List<Drink> drinks = dao.getDrinks();

            return new ArrayAdapter<>(
                    context,
                    android.R.layout.simple_list_item_1,
                    drinks
            );
        }

        @Override
        protected void onPostExecute(ListAdapter adapter) {
            listView.setAdapter(adapter);
            super.onPostExecute(adapter);
        }
    }
}

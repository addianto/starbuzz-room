package com.hfad.starbuzzroom;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hfad.starbuzzroom.data.AppDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(TopLevelActivity.this,
                        DrinkCategoryActivity.class);
                startActivity(intent);
            }
        });

        new InitDatabaseTask().execute(this);
    }

    private class InitDatabaseTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            Context context = contexts[0];
            AppDatabase db = AppDatabase.getInstance(context);
            return null;
        }
    }
}

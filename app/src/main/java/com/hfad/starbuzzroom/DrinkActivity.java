package com.hfad.starbuzzroom;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.starbuzzroom.data.AppDatabase;
import com.hfad.starbuzzroom.data.Drink;
import com.hfad.starbuzzroom.data.DrinkDao;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int selectedDrinkId = getIntent().getExtras().getInt(EXTRA_DRINKID);
        ImageView photo = findViewById(R.id.photo);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);

        new DisplayDrinkTask(selectedDrinkId, photo, name, description)
                .execute(this);
    }

    private class DisplayDrinkTask extends AsyncTask<Context, Void, Drink> {

        private Integer selectedDrinkId;
        private ImageView photo;
        private TextView name;
        private TextView description;

        public DisplayDrinkTask(Integer selectedDrinkId, ImageView photo,
                                TextView name, TextView description) {
            this.selectedDrinkId = selectedDrinkId;
            this.photo = photo;
            this.name = name;
            this.description = description;
        }

        @Override
        protected Drink doInBackground(Context... contexts) {
            Context context = contexts[0];
            AppDatabase db = AppDatabase.getInstance(context);
            DrinkDao dao = db.drinkDao();

            return dao.findById(selectedDrinkId);
        }

        @Override
        protected void onPostExecute(Drink drink) {
            photo.setImageResource(drink.getImageResourceId());
            photo.setContentDescription(drink.getDescription());
            name.setText(drink.getName());
            description.setText(drink.getDescription());

            super.onPostExecute(drink);
        }
    }
}

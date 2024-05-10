package com.example.foodie;

import static com.example.foodie.FoodImages.foodImages;
import static com.example.foodie.RestaurantImages.restaurantImages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView descriptionTextView;

    ImageView restaurantImage;

    RecyclerView foodsRecyclerView;

    ArrayList<FoodModel> foodModelsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //trying to manipulate views BEFORE setting the content view of the activity WILL NOT WORK

        String restaurantName = getIntent().getStringExtra("name");
        String restaurantDescription = getIntent().getStringExtra("description");
        int restaurantImagePosition = getIntent().getIntExtra("restaurant_image_id", 0);


        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        restaurantImage = findViewById(R.id.restaurant_image);
        foodsRecyclerView = findViewById(R.id.foodsListRecyclerView);

        Picasso.get().load(RestaurantImages.restaurantImages[restaurantImagePosition]).fit().into(restaurantImage);

        nameTextView.setText(restaurantName);
        descriptionTextView.setText(restaurantDescription);

        initFoodModelsList();

        foodsRecyclerView.setAdapter(new F_RecyclerViewAdapter(this, foodModelsList));
        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initFoodModelsList() {
        String[] foodNames = getResources().getStringArray(R.array.foods_names);
        String[] foodDescriptions = getResources().getStringArray(R.array.foods_descriptions);

        for(int i = 0; i < foodNames.length; i++ ) {
            try {
                foodModelsList.add(new FoodModel(foodNames[i], foodDescriptions[i], foodImages[i]));
            } catch(Exception e) {
                Log.e("Restaurant", e.getMessage());
            }
        }
    }
}
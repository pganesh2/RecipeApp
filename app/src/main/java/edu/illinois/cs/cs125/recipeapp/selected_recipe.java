package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class selected_recipe extends AppCompatActivity {

    String[] ingredientArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setContentView(R.layout.selected_recipe);

        ingredientArray = i.getStringArrayExtra("ingredients1");

        StringBuilder builder = new StringBuilder();
        for(String s : ingredientArray) {
            if (s != null) {
                builder.append(s + "\n");
            }
        }
        String str = builder.toString();

        TextView ingredients = findViewById(R.id.ingredients);

        ingredients.setText(str);
        String testRecipe = i.getExtras().getString("title");
        TextView title = findViewById(R.id.Title);
        title.setText(testRecipe);
    }
}

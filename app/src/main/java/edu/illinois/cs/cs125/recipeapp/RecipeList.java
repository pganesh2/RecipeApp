package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class RecipeList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        goToRecipe();

        Intent intent = getIntent();
        String [] recipeArray = intent.getStringArrayExtra("string-array");

        final Button newRecipe = (Button) findViewById(R.id.recipe1);

        //gets random integer for array.
        Random random = new Random();
        int randNum = random.nextInt(3);

        //sets recipe to a random array index.
        newRecipe.setText(recipeArray[0]);

    }


    Button recipeChosen;
    Button recipeChosen2;
    public void goToRecipe() {
        recipeChosen = findViewById(R.id.recipe1);
        recipeChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecipeList.this, selected_recipe.class);
                startActivity(go);
            }
        });
        recipeChosen2 = findViewById(R.id.recipe2);
        recipeChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecipeList.this, selected_recipe.class);
                startActivity(go);
            }
        });

    }
}

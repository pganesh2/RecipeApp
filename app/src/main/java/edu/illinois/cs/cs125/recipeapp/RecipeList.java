package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RecipeList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        goToRecipe();

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

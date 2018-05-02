package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RecipeList extends AppCompatActivity {
    public static final String TAG1 = "RecipeList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        Intent i = getIntent();
        String testRecipe = i.getExtras().getString("key");

        Button displayRecipe = (Button) findViewById(R.id.recipe1);
        displayRecipe.setText(testRecipe);
        goToRecipe();
    }


    Button recipeChosen;
    public void goToRecipe() {
        recipeChosen = findViewById(R.id.recipe1);
        recipeChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecipeList.this, selected_recipe.class);
                startActivity(go);
            }
        });
    }
}

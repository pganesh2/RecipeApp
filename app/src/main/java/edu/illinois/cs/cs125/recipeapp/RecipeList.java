package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecipeList extends AppCompatActivity {
    String[] ingredientArray;
    String[] dietArray;
    public static final String TAG1 = "RecipeList";
    String testRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        Intent i = getIntent();
        testRecipe = i.getExtras().getString("key");

        Button displayRecipe = (Button) findViewById(R.id.recipe1);
        displayRecipe.setText(testRecipe);

        dietArray = i.getStringArrayExtra("diet");
        ingredientArray = i.getStringArrayExtra("ingredients");

        StringBuilder builder = new StringBuilder();
        for(String s : dietArray) {
            if (s != null) {
                builder.append(s + "\n");
            }
        }
        String str = builder.toString();

        TextView dietView = findViewById(R.id.description);
        dietView.setText(str);

        goToRecipe();
    }


    Button recipeChosen;
    public void goToRecipe() {
        recipeChosen = findViewById(R.id.recipe1);
        recipeChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(RecipeList.this, selected_recipe.class);

                go.putExtra("ingredients1", ingredientArray);
                go.putExtra("title", testRecipe);

                startActivity(go);
            }
        });
    }
}

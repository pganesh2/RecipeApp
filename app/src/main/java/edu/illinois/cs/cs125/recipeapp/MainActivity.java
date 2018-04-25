package edu.illinois.cs.cs125.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_selection);

        Spinner proteinSpinner = findViewById(R.id.proteinList);

        ArrayAdapter<String> proteinAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Protein));
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinSpinner.setAdapter(proteinAdapter);


        Spinner veggieSpinner = findViewById(R.id.veggieList);

        ArrayAdapter<String> veggieAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_2, getResources().getStringArray(R.array.Veggies));
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        veggieSpinner.setAdapter(veggieAdapter);
    }

    public void display1(View view) {
        Intent startNewActivity = new Intent(this, selected_recipe.class);
        startActivity(startNewActivity);
    }
    public void display2(View view) {
        Intent startNewActivity = new Intent(this, selected_recipe.class);
        startActivity(startNewActivity);
    }

}

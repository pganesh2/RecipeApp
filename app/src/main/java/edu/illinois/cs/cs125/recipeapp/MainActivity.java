package edu.illinois.cs.cs125.recipeapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    Button ingredientsDone;

    public String recipeName = " ";
    public String[] ingredients = new String[100];


    /**
     * takes user to list of recipes containing selected ingredients.
     */
    public void goToRecipes() {
        Log.i(TAG, "goToRecipes ran");
        ingredientsDone = findViewById(R.id.ingredientsButton);
        ingredientsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(MainActivity.this, RecipeList.class);
                Log.i(TAG, "Button clicked");
                startAPICall();
                startActivity(go);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.ingredient_selection);



        goToRecipes();

        final Spinner proteinSpinner = findViewById(R.id.proteinList);
        final Spinner veggieSpinner = findViewById(R.id.veggieList);
        final Spinner spicesSpinner = findViewById(R.id.spicesList);


        ArrayAdapter<String> proteinAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Protein));
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinSpinner.setAdapter(proteinAdapter);

        proteinSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView changingI1 = findViewById(R.id.first);
                    changingI1.setText(proteinSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<String> veggieAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Veggies));
        veggieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        veggieSpinner.setAdapter(veggieAdapter);

        veggieSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView changingI1 = findViewById(R.id.second);
                changingI1.setText(veggieSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> spicesAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Spices));
        spicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spicesSpinner.setAdapter(spicesAdapter);

        spicesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView changingI1 = findViewById(R.id.third);
                changingI1.setText(spicesSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void display1(View view) {
        Intent startNewActivity = new Intent(this, selected_recipe.class);
        startActivity(startNewActivity);
    }
    public void display2(View view) {
        Intent startNewActivity = new Intent(this, selected_recipe.class);
        startActivity(startNewActivity);
    }


    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.edamam.com/search?q=chicken&app_id=6d0f0a9c&app_key=53b41806efa48f108c049379b3d29920",
//                            ,+ BuildConfig.API_KEY
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                String s = response.toString(2);
                                refine(s);
//                                final TextView stuff = (TextView) findViewById(R.id.textView6);
//                                stuff.setText(recipeName);
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void refine (String output) {
        int counter = 0;
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(output).getAsJsonObject();
        JsonArray hits = result.get("hits").getAsJsonArray();
        for (JsonElement position : hits) {
            JsonObject obj = position.getAsJsonObject();
            JsonObject recipe = obj.get("recipe").getAsJsonObject();
            recipeName = recipe.get("label").getAsString(); // Name of recipe;
//            JsonArray ingredientLines = result.get("ingredientLines").getAsJsonArray();
//            for (JsonElement position2 : ingredientLines) {
//                ingredients[counter] = position2.getAsString();
//                counter++;
//                if (counter == 100) {
//                    break;
//                }
//            }
        }
    }

}

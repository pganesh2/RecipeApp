package edu.illinois.cs.cs125.recipeapp;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /**
     * Request queue for our API requests.
     */
    private static RequestQueue requestQueue;

    Button ingredientsDone;
    RadioGroup radioGroup;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;

    public String protein;
    public String veggie;
    public String spices;
    public String mainIngredient;
    public String recipeName = " ";
    public String dietLabel;
    public String[] recipeArray = new String[100];
    public String[][] dietArray = new String[100][30];
    public String[][] ingredientArray = new String[100][30];



    /**
     * takes user to list of recipes containing selected ingredients.
     */
    public void goToRecipes() {
        Log.i(TAG, "goToRecipes ran");
        ingredientsDone = findViewById(R.id.ingredientsButton);
        ingredientsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAPICall();

//                Intent go = new Intent(MainActivity.this, RecipeList.class);
//
//                Log.i(TAG, "Button clicked");
//                Log.i(TAG, "Start API finished...");
//
//
//                String testRecipe = recipeName;
//                Log.i(TAG, recipeName);
//                go.putExtra("key", testRecipe);
//
//
//                startActivity(go);
            }
        });

    }


    public void setter(String finalRecipe) {
        recipeName = finalRecipe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.ingredient_selection);

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
                protein = changingI1.getText().toString();
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
                veggie = changingI1.getText().toString();
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
                spices = changingI1.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        View.OnClickListener first_radio_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIngredient = protein;
                Log.i(TAG, "protein set");
                Log.i(TAG, mainIngredient);
            }
        };

        View.OnClickListener second_radio_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIngredient = veggie;
            }
        };

        View.OnClickListener third_radio_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIngredient = spices;
            }
        };


        radio1 = (RadioButton) findViewById(R.id.radiobutton1);
        radio1.setOnClickListener(first_radio_listener);

        radio2 = findViewById(R.id.radiobutton2);
        radio2.setOnClickListener(second_radio_listener);

        radio3 = findViewById(R.id.radiobutton3);
        radio3.setOnClickListener(third_radio_listener);

        goToRecipes();



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
                    "https://api.edamam.com/search?q=" + mainIngredient +
                            "&app_id=6d0f0a9c&app_key=8ccf81121ce8b2864bd4141f9e5c1598",
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
                            } catch (JSONException ignored) {
                            }
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


    void refine(String output) {
        int counter = 0;
        int length = 0;
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(output).getAsJsonObject();
        JsonArray hits = result.get("hits").getAsJsonArray();

        for (JsonElement position : hits) {
            JsonObject obj = position.getAsJsonObject();
            JsonObject recipe = obj.get("recipe").getAsJsonObject();
            recipeName = recipe.get("label").getAsString(); // Name of recipe;
            recipeArray[length] = recipeName;


            JsonArray dietLabels = recipe.get("healthLabels").getAsJsonArray();
            for (int i = 0; i < dietLabels.size(); i++) {
                dietArray[length][i] = dietLabels.get(i).getAsString();
            }


            JsonArray ingredients = recipe.get("ingredientLines").getAsJsonArray();
            for (int i = 0; i < ingredients.size(); i++) {
                ingredientArray[length][i] = ingredients.get(i).getAsString();
            }
            length++;

//            JsonArray ingredientLines = result.get("ingredientLines").getAsJsonArray();
//            for (JsonElement position2 : ingredientLines) {
//                ingredients[counter] = position2.getAsString();
//                counter++;
//                if (counter == 100) {
//                    break;
//                }
//            }
        }
        Random random = new Random();
        int selection = random.nextInt(length);
        String temp = recipeArray[selection];
        setter(temp);
        Intent go = new Intent(MainActivity.this, RecipeList.class);

        String testRecipe = recipeName;
        go.putExtra("key", testRecipe);

        String[] tempDiet = new String[30];
        for (int i = 0; i < 30; i++) {
            tempDiet[i] = dietArray[selection][i];
        }
        go.putExtra("diet", tempDiet);


        String[] tempArray = new String[30];
        for (int i = 0; i < 30; i++) {
            tempArray[i] = ingredientArray[selection][i];
        }
        go.putExtra("ingredients", tempArray);


        startActivity(go);
    }
}



package com.crumlabs.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddMealActivity extends AppCompatActivity {

    EditText nameInput, catInput, calInput;
    List<MealItemDB> mealItemDBList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        nameInput = (EditText) findViewById(R.id.mealNameInput);
        catInput = (EditText) findViewById(R.id.categoryInput);
        calInput = (EditText) findViewById(R.id.calInput);
    }

    public void addMeal(View view){

        String name = nameInput.getText().toString();
        String category = catInput.getText().toString();
        String calories = calInput.getText().toString();
        MealItemDB mealItemDB = new MealItemDB();
        mealItemDB.setName(name);
        mealItemDB.setCategory(category);
        mealItemDB.setCalories(calories);
        addMealToDB(mealItemDB);
        mealItemDBList = getAllMeals();
        for(MealItemDB itemDB: mealItemDBList){
            Log.i(TAG,"name: "+itemDB.getName());
        }

    }

    public int addMealToDB(MealItemDB mealItemDB){
        int isSuccess;
        isSuccess = DatabaseManager.getInstance(getApplicationContext()).insertMealItem(mealItemDB,false);
        if(isSuccess == 0){
            Toast.makeText(getApplicationContext(),"Save Meal",Toast.LENGTH_SHORT).show();
        }else if(isSuccess == 1){
            Toast.makeText(getApplicationContext(),"Meal with this id exist", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Meal adding failed",Toast.LENGTH_SHORT).show();
        }
        return isSuccess;
    }
    public List<MealItemDB> getAllMeals(){
        return  DatabaseManager.getInstance(getApplicationContext()).getAllMeals();
    }


}
package com.crumlabs.mealplanner;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    List<MealItemDB> mealItemDBList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context;
    private MealAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        mealItemDBList = getAllMeals();
        mAdapter = new MealAdapter(mealItemDBList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        return root;

    }

    public List<MealItemDB> getAllMeals(){
        return  DatabaseManager.getInstance(getContext()).getAllMeals();
    }



}

package com.crumlabs.mealplanner;

import android.content.Context;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<MealItemDB> mealItemDBList;
    private Context mContext;


    public class MealViewHolder extends RecyclerView.ViewHolder {

        public TextView name, category, calories;
        public LinearLayout editLayout, deleteLayout;

        public MealViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameText);
            category = (TextView) view.findViewById(R.id.categoryText);
            calories = (TextView) view.findViewById(R.id.caloriesText);
        }
    }


    public MealAdapter(List<MealItemDB> mealItemDBList, Context context) {

        this.mealItemDBList = mealItemDBList;
        this.mContext = context;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_meals_view, parent, false);
        return new MealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MealViewHolder holder, int position) {
        final MealItemDB user = mealItemDBList.get(position);

        holder.name.setText(user.getName());
        holder.category.setText(user.getCategory());
        holder.calories.setText(user.getCalories());
    }


    public void updateMealList(List<MealItemDB> newlist) {
        mealItemDBList.clear();
        mealItemDBList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mealItemDBList.size();
    }


}
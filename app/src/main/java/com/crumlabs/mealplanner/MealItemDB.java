package com.crumlabs.mealplanner;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "meals")
public class MealItemDB {
    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private Long id;

    @Getter
    @Setter
    @DatabaseField
    private String name;

    @Getter
    @Setter
    @DatabaseField
    private String category;

    @Getter
    @Setter
    @DatabaseField
    private String calories;

    public MealItemDB() {


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}

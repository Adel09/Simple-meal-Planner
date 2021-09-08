package com.crumlabs.mealplanner;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
//import com.crumlabs.mealplanner.localstorage.UserItemDB;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    private final String TAG = DatabaseManager.this.getClass().getSimpleName();
    private final Context mContext;
    private static DatabaseManager INSTANCE;
    private DatabaseHelper databaseHelper;

    private Dao<MealItemDB, Long> mealItemDao;
    private static String CATEGORY = "category";
    private static String NAME = "name";
    private static String CALORIES = "calories";
    private static String ID = "id";


    public DatabaseManager(Context mContext) {
        Log.i(TAG, "DatabaseManager");
        this.mContext = mContext;
        databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);

        try {
            mealItemDao = databaseHelper.getMealItemDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(Context context){
        if(INSTANCE == null) INSTANCE = new DatabaseManager(context);
        return INSTANCE;
    }

    public void releaseDB(){
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
        }
    }

    public int clearAllData(){
        try {
            if (databaseHelper == null) return -1;
            databaseHelper.clearTable();
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isMealExisting(String id){
        QueryBuilder queryBuilder = mealItemDao.queryBuilder();
        boolean flag = false;
        try {
            if(queryBuilder.where().eq(ID,id).countOf()>0){
                flag = true;
            }else {
                flag = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public int insertMealItem(MealItemDB mealItemDB, boolean isEdit){
        int count = 0;
        try {
            UpdateBuilder updateBuilder = mealItemDao.updateBuilder();
            String name = mealItemDB.getName() != null ? mealItemDB.getName() : "";
            String category = mealItemDB.getCategory() != null ? mealItemDB.getCategory(): "";
            String calories = mealItemDB.getCalories() != null ? mealItemDB.getCalories(): "";

            if(mealItemDao == null) {
                return -1;
            }else {

                count = 0;
                mealItemDao.create(mealItemDB);
                return count;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public int deleteMeal(String id){
        try {
            if(mealItemDao == null) return -1;
            DeleteBuilder deleteBuilder = mealItemDao.deleteBuilder();
            if(id != null || !id.isEmpty()) deleteBuilder.where().eq(ID,id);
            deleteBuilder.delete();
            Log.i(TAG,"meal deleted");
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public List<MealItemDB> getAllMeals(){
        try {
            if (mealItemDao == null)return null;
            return mealItemDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
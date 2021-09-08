package com.crumlabs.mealplanner;

import android.content.Context;
import java.sql.SQLException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "simplemealdb";
    private static final int DATABASE_VERSION = 1;
    Context context;

    private Dao<MealItemDB, Long> mealItemDBS;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, MealItemDB.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            if(checkTableExist(database,"meals"))
                TableUtils.dropTable(connectionSource,MealItemDB.class,false);

            onCreate(database,connectionSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    private boolean checkTableExist(SQLiteDatabase database, String tableName){
        Cursor c = null;
        boolean tableExist = false;
        try {
            c = database.query(tableName, null,null,null,null,null,null);
            tableExist = true;
        }catch (Exception e){

        }
        return tableExist;
    }

    public Dao<MealItemDB, Long> getMealItemDao() throws SQLException{
        if(mealItemDBS == null){
            mealItemDBS = getDao(MealItemDB.class);
        }
        return mealItemDBS;
    }

    @Override
    public void close() {
        mealItemDBS = null;
        super.close();
    }

    public void clearTable() throws SQLException{
        TableUtils.clearTable(getConnectionSource(), MealItemDB.class);
    }

}

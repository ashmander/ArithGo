package com.example.arithgo.model.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.arithgo.app.ArithGoApp;
import com.example.arithgo.model.driver.DBDriver;
import com.example.arithgo.model.entity.Points;


public class CRUDPoints {

    public static void insertPoint() {
        if(getPoints()==null)  {
            DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
            SQLiteDatabase db = driver.getWritableDatabase();
            String sql = "INSERT INTO $TABLE($ID, $MY_POINTS) VALUES('$VID', $VMY_POINTS)";
            sql = sql
                    .replace("$TABLE",DBDriver.TABLE_POINTS)
                    .replace("$ID",DBDriver.POINTS_ID)
                    .replace("$MY_POINTS",DBDriver.POINTS_POINtS)
                    .replace("$VID","1")
                    .replace("$VMY_POINTS",""+60);
            db.execSQL(sql);
            db.close();
        }
    }

    public static Integer getPoints() {
        int points = 0;
        try {
            DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
            SQLiteDatabase db = driver.getReadableDatabase();
            String sql = "SELECT * FROM "+DBDriver.TABLE_POINTS+" WHERE "+DBDriver.POINTS_ID+" = '1'";
            Cursor cursor = db.rawQuery(sql,null);
            cursor.moveToFirst();
            points = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBDriver.POINTS_POINtS)));
            db.close();
        } catch (Exception e) {
            return null;
        }

        return points;
    }

    public static void updatePoints() {
        int points = getPoints() +  1;
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "UPDATE $TABLE SET $POINTS=$VPOINTS WHERE $ID = '1'";
        sql =  sql.replace("$TABLE",DBDriver.TABLE_POINTS)
                .replace("$POINTS",DBDriver.POINTS_POINtS)
                .replace("$VPOINTS",""+points)
                .replace("$ID",DBDriver.POINTS_ID);
        db.execSQL(sql);
        db.close();
    }

    public static void updatePoints(int pointsProduct) {
        int points = getPoints() -  pointsProduct;
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "UPDATE $TABLE SET $POINTS=$VPOINTS WHERE $ID = '1'";
        sql  =  sql.replace("$TABLE",DBDriver.TABLE_POINTS)
                .replace("$POINTS",DBDriver.POINTS_POINtS)
                .replace("$VPOINTS",""+points)
                .replace("$ID",DBDriver.POINTS_ID);
        db.execSQL(sql);
        db.close();
    }

    public static void substractPoint() {
        int points = getPoints() -  1;
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "UPDATE $TABLE SET $POINTS=$VPOINTS WHERE $ID = '1'";
        sql = sql.replace("$TABLE",DBDriver.TABLE_POINTS)
                .replace("$POINTS",DBDriver.POINTS_POINtS)
                .replace("$VPOINTS",""+points)
                .replace("$ID",DBDriver.POINTS_ID);
        db.execSQL(sql);
        db.close();
    }
}

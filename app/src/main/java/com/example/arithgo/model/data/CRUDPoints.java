package com.example.arithgo.model.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arithgo.app.ArithGoApp;
import com.example.arithgo.model.driver.DBDriver;

public class CRUDPoints {

    public static void insertPoin() {
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "INSERT INTO $TABLE($ID, $MY_POINTS) VALUES('$VID', '$VMY_POINTS')";
        sql = sql
                .replace("$TABLE",DBDriver.TABLE_POINTS)
                .replace("$ID",DBDriver.POINTS_ID)
                .replace("$MY_POINTS",DBDriver.POINTS_POINtS)
                .replace("$VID","1")
                .replace("$VMY_POINTS","0");
        db.execSQL(sql);
        db.close();
    }

    public static int getPoints() {
        int points = 0;
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "SELECT * FROM "+DBDriver.TABLE_POINTS+" WHERE $ID = '1'";
        sql = sql
                .replace("$ID",DBDriver.POINTS_ID);
        Cursor cursor = db.rawQuery(sql,null);
        points = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBDriver.POINTS_POINtS)));
        db.close();
        return points;
    }

    public static void updatePoints() {
        int points = getPoints() +  1;
        DBDriver driver = DBDriver.getInstance(ArithGoApp.getContext());
        SQLiteDatabase db = driver.getReadableDatabase();
        String sql = "UPDATE $TABLE SET $POINTS=$VPOINTS WHERE $ID = '1'";
        sql.replace("$TABLE",DBDriver.TABLE_POINTS)
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
        sql.replace("$TABLE",DBDriver.TABLE_POINTS)
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
        sql.replace("$TABLE",DBDriver.TABLE_POINTS)
                .replace("$POINTS",DBDriver.POINTS_POINtS)
                .replace("$VPOINTS",""+points)
                .replace("$ID",DBDriver.POINTS_ID);
        db.execSQL(sql);
        db.close();
    }
}

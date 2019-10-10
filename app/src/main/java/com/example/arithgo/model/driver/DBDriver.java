package com.example.arithgo.model.driver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDriver extends SQLiteOpenHelper {

    private static DBDriver instance;

    public static synchronized DBDriver getInstance(Context context) {
        if(instance==null) {
            instance = new DBDriver(context);
        }
        return instance;
    }

    public static final String DBNAME = "points";
    public static final int DBVERSION = 1;

    //Table Points
    public static final String TABLE_POINTS="points";
    public static final String POINTS_ID="id";
    public static final String POINTS_POINtS="my_points";

    private DBDriver (Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_POINTS = "CREATE TABLE $TABLE($ID TEXT PRIMARY  KEY, $MY_POINTS INTEGER)";
        CREATE_TABLE_POINTS  = CREATE_TABLE_POINTS
                .replace("$TABLE",TABLE_POINTS)
                .replace("$ID",POINTS_ID)
                .replace("$MY_POINTS", POINTS_POINtS);
        db.execSQL(CREATE_TABLE_POINTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_POINTS);
        onCreate(db);

    }
}

package com.example.raj.newbmicalculator;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BmiDB.db";
    public static final String TABLE_PERSON = "person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PERSON_NAME = "person_name";

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PERSON + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSON_NAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSON_NAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PERSON, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String person_Name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PERSON + " WHERE " + COLUMN_PERSON_NAME + "=\"" + person_Name + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PERSON + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("person_name")) != null) {
                dbString += c.getString(c.getColumnIndex("person_name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}

package com.example.tugas_teori_p6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "kontak.db";
    public static final String TABLE_NAME = "kontakhp_table";
    private static final int DATABASE_VERSION = 1;

    public static final String COL_1 = "Id";
    public static final String COL_2 = "FIRST_NAME";
    public static final String COL_3 = "LAST_NAME";
    public static final String COL_4 = "No_HP";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table kontakhp_table(Id integer primary key autoincrement," + "FIRST_NAME text not null," + "LAST_NAME text not null," + "NO_HP integer not null);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Id, String FIRST_NAME, String LAST_NAME, String No_HP) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, FIRST_NAME);
        contentValues.put(COL_3, LAST_NAME);
        contentValues.put(COL_4, No_HP);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from kontakhp_table", null);
        return res;
    }

    public boolean updateDate(String Id, String FIRST_NAME, String LAST_NAME, String No_HP) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, FIRST_NAME);
        contentValues.put(COL_3, LAST_NAME);
        contentValues.put(COL_4, No_HP);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{Id});
        return true;
    }

    public int deleteData(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? ", new String[]{Id});
    }
}


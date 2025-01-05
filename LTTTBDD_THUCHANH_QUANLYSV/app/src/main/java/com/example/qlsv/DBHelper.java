package com.example.qlsv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "quanly_sinhvien.db";
    public static final int DATABASE_VERSION=1;

    public static final String TEN_BANG_SINHVIEN= "SinhVien";
    public static final String COT_ID = "_id";
    public static final String COT_TEN = "ten";
    public static final String COT_LOP = "lop";

    public static final String TABLE_CREATE =
        "CREATE TABLE " + TEN_BANG_SINHVIEN + " (" +
        COT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
        COT_TEN + " TEXT, "+
        COT_LOP + " TEXT); ";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  // Chỉ cần 3 tham số, không cần tham số thừa
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TEN_BANG_SINHVIEN);
        onCreate(sqLiteDatabase);

    }
}

package com.example.qlsv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // Tên cơ sở dữ liệu
    public static final String DATABASE_NAME= "quanly_sinhvien.db";
    // Phiên bản cơ sở dữ liệu
    public static final int DATABASE_VERSION=1;

    // Tên bảng và các cột trong bảng
    public static final String TEN_BANG_SINHVIEN= "SinhVien";
    public static final String COT_ID = "_id";
    public static final String COT_TEN = "ten";
    public static final String COT_LOP = "lop";

    // Câu lệnh SQL tạo bảng SinhVien
    public static final String TABLE_CREATE =
        "CREATE TABLE " + TEN_BANG_SINHVIEN + " (" +
        COT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
        COT_TEN + " TEXT, "+
        COT_LOP + " TEXT); ";

    // Constructor của DBHelper, khởi tạo cơ sở dữ liệu
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  // Chỉ cần 3 tham số, không cần tham số thừa
    }



    // Tạo bảng SinhVien khi cơ sở dữ liệu được tạo lần đầu
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }
    // Cập nhật bảng khi có thay đổi phiên bản cơ sở dữ liệu
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TEN_BANG_SINHVIEN);
        onCreate(sqLiteDatabase);// Tạo lại bảng

    }
}

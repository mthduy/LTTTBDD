package com.example.qlsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    private SQLiteDatabase database;
    private DBHelper helper;

    public MyDatabase(Context context){
        helper= new DBHelper(context);
        database= helper.getWritableDatabase();
    }

    public void close(){  helper.close();  }
    public long them(SinhVien sinhVien){
        ContentValues values= new ContentValues();
        values.put(DBHelper.COT_TEN,sinhVien.get_ten());
        values.put(DBHelper.COT_LOP,sinhVien.get_lop());
        return database.insert(DBHelper.TEN_BANG_SINHVIEN,null,values);
    }

    public long xoa(SinhVien sinhVien){
        return database.delete(DBHelper.TEN_BANG_SINHVIEN, DBHelper.COT_ID + " = ?", new String[]{String.valueOf(sinhVien.get_id())});
    }

    public long sua(SinhVien sinhVien){
        ContentValues values= new ContentValues();
        values.put(DBHelper.COT_TEN,sinhVien.get_ten());
        values.put(DBHelper.COT_LOP,sinhVien.get_lop());
        return database.update(DBHelper.TEN_BANG_SINHVIEN, values, DBHelper.COT_ID+ " = ? ", new String[]{String.valueOf(sinhVien.get_id())});
    }

    public Cursor layTatCaDuLieu(){
        return database.query(DBHelper.TEN_BANG_SINHVIEN, null, null,null,null,null,null,null);
    }

}

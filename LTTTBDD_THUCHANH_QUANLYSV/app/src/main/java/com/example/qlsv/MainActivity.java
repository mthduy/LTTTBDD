package com.example.qlsv;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private static ListView listView;
    public static ArrayList<SinhVien> sinhViens;
    public static MyDatabase database;
    private EditText editTextTen, editTextLop;
    private String ten, lop;
    public static long id=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.list), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView=findViewById(R.id.listView);
        editTextTen= findViewById(R.id.editTextTen);
        editTextLop=findViewById(R.id.editTextLop);

        database= new MyDatabase(this);
        sinhViens= new ArrayList<>();

        capNhapDuLieu();
        thietLapListView();
    }

    private void thietLapListView() {
        listView.setAdapter(new MyAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editTextTen.setText(sinhViens.get(i).get_ten());
                editTextLop.setText(sinhViens.get(i).get_lop());
                MainActivity.id= sinhViens.get(i).get_id();
            }
        });
    }

    @SuppressLint("Range")
    private void capNhapDuLieu() {
        sinhViens.clear();
        Cursor cursor = database.layTatCaDuLieu(); // Lấy dữ liệu từ DB
        if (cursor != null && cursor.moveToFirst()) { // Kiểm tra nếu có dữ liệu
            do {
                SinhVien sinhVien = new SinhVien();
                sinhVien.set_id(cursor.getLong(cursor.getColumnIndex(DBHelper.COT_ID)));
                sinhVien.set_ten(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN)));
                sinhVien.set_lop(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOP)));
                sinhViens.add(sinhVien);
            } while (cursor.moveToNext()); // Di chuyển đến dòng tiếp theo
        }
        if (cursor != null) {
            cursor.close(); // Đóng con trỏ để tránh rò rỉ bộ nhớ
        }

    }



}
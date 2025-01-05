package com.example.qlsv;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private Button btnThem, btnSua, btnXoa;

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

        // Ánh xạ các phần tử giao diện
        listView=findViewById(R.id.listView);
        editTextTen= findViewById(R.id.editTextTen);
        editTextLop=findViewById(R.id.editTextLop);

        // Khởi tạo cơ sở dữ liệu và danh sách sinh viên
        database= new MyDatabase(this);
        sinhViens= new ArrayList<>();

        // Thiết lập sự kiện cho các nút
        btnThem=findViewById(R.id.btnThem);
        if(btnThem!= null){
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ten = editTextTen.getText().toString().trim();
                    lop = editTextLop.getText().toString().trim();

                    if (!ten.isEmpty() && !lop.isEmpty()) {
                        SinhVien sinhVien = new SinhVien(0, ten, lop);  // id sẽ tự động tăng
                        database.them(sinhVien);
                        capNhapDuLieu();  // Cập nhật dữ liệu
                        thietLapListView();  // Thiết lập lại ListView
                        editTextTen.setText("");  // Xóa trường nhập
                        editTextLop.setText("");  // Xóa trường nhập
                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        btnSua=findViewById(R.id.btnSua);
        if(btnSua!=null){
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ten = editTextTen.getText().toString().trim();
                    lop = editTextLop.getText().toString().trim();

                    if (!ten.isEmpty() && !lop.isEmpty() && id != -1) {
                        SinhVien sinhVien = new SinhVien(id, ten, lop);
                        database.sua(sinhVien);  // Cập nhật vào cơ sở dữ liệu
                        capNhapDuLieu();  // Cập nhật dữ liệu
                        thietLapListView();  // Thiết lập lại ListView
                        editTextTen.setText("");  // Xóa trường nhập
                        editTextLop.setText("");  // Xóa trường nhập
                        id = -1;  // Reset id
                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng chọn sinh viên để sửa!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        btnXoa= findViewById(R.id.btnXoa);
        if(btnXoa!=null){
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (id != -1) {
                        database.xoa(new SinhVien(id, null, null));  // Xóa theo id
                        capNhapDuLieu();  // Cập nhật dữ liệu
                        thietLapListView();  // Thiết lập lại ListView
                        editTextTen.setText("");  // Xóa trường nhập
                        editTextLop.setText("");  // Xóa trường nhập
                        id = -1;  // Reset id
                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng chọn sinh viên để xóa!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        // Cập nhật dữ liệu và thiết lập ListView khi vào giao diện
        capNhapDuLieu();
        thietLapListView();
    }

    // Thiết lập ListView để hiển thị danh sách sinh viên
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
    // Cập nhật danh sách sinh viên từ cơ sở dữ liệu
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
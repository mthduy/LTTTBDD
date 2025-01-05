package com.example.qlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    // Constructor của adapter
    public MyAdapter(Context context){
        inflater= LayoutInflater.from(context);
        this.context= context;
    }

    @Override
    public int getCount() {
        return MainActivity.sinhViens.size();// Trả về số lượng sinh viên trong danh sách
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.sinhViens.get(i); // Trả về sinh viên tại vị trí chỉ định
    }

    @Override
    public long getItemId(int i) {
        return MainActivity.sinhViens.get(i).get_id();// Trả về ID của sinh viên
    }

    @Override
    public View getView(int i, View views, ViewGroup viewGroup) {
        // Thiết lập giao diện mỗi mục trong ListView
        View view = inflater.inflate(R.layout.items_list_view, null);

        // Hiển thị tên sinh viên
        TextView textViewTen= view.findViewById(R.id.tvTen);
        textViewTen.setText(MainActivity.sinhViens.get(i).get_ten());

        // Hiển thị lớp sinh viên
        TextView textViewLop= view.findViewById(R.id.tvLop);
        textViewLop.setText(MainActivity.sinhViens.get(i).get_lop());

        // Xử lý sự kiện nhấn nút xóa sinh viên
        ImageView imageViewXoa= view.findViewById(R.id.imageView);
        imageViewXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.xoa(MainActivity.sinhViens.get(i));// Xóa sinh viên
                MainActivity.sinhViens.remove(i);// Loại bỏ sinh viên khỏi danh sách
                notifyDataSetChanged();// Cập nhật lại ListView
            }
        });
        return view;
    }
}

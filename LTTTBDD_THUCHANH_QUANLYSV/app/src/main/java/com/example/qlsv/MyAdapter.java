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

    public MyAdapter(Context context){
        inflater= LayoutInflater.from(context);
        this.context= context;
    }

    @Override
    public int getCount() {
        return MainActivity.sinhViens.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.sinhViens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return MainActivity.sinhViens.get(i).get_id();
    }

    @Override
    public View getView(int i, View views, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.items_list_view, null);

        TextView textViewTen= view.findViewById(R.id.tvTen);
        textViewTen.setText(MainActivity.sinhViens.get(i).get_ten());

        TextView textViewLop= view.findViewById(R.id.tvLop);
        textViewLop.setText(MainActivity.sinhViens.get(i).get_lop());

        ImageView imageViewXoa= view.findViewById(R.id.imageView);
        imageViewXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.xoa(MainActivity.sinhViens.get(i));
                MainActivity.sinhViens.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}

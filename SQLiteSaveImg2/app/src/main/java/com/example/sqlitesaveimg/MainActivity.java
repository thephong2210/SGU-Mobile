package com.example.sqlitesaveimg;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    Button buttonthem;
    ListView lvHinhAnh;
    ArrayList<HinhAnh> arrayHinh;
    HinhAnhAdapter hinhAnhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
        buttonthem = findViewById(R.id.btthem);
        lvHinhAnh = findViewById(R.id.listviewHinhAnh);
        arrayHinh = new ArrayList<>();
        hinhAnhAdapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, arrayHinh);
        lvHinhAnh.setAdapter(hinhAnhAdapter);

        database = new Database(this, "QuanLy.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS ThuVien(Id INTEGER PRIMARY KEY AUTOINCREMENT, HinhAnh BLOB)");

        // get DATA
        Cursor cursor = database.GetData("SELECT * FROM ThuVien");
        while (cursor.moveToNext()) {
            arrayHinh.add(new HinhAnh(
                    cursor.getInt(0),
                    cursor.getBlob(1)
            ));
        }

        hinhAnhAdapter.notifyDataSetChanged();

        buttonthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));
            }
        });

        lvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));

                // Chuyen byte[] --> bitmap
                byte[] hinhAnh = arrayHinh.get(position).getHinh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                ThemDoVatActivity.imageViewCamera.setImageBitmap(bitmap);
            }
        });
    }

}
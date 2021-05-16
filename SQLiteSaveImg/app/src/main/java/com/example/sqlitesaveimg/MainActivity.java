package com.example.sqlitesaveimg;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "123";
    public static Database database;
    Button buttonthem;
    ListView lvHinhAnh;
    ArrayList<HinhAnh> arrayHinh;
    HinhAnhAdapter hinhAnhAdapter;
    Calendar calendar;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Nhắc nhở người dùng chụp ảnh định kì bằng notification
        hengio(17, 25);


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
                ThemHInhAnhActivity.hinhchon = null;

                startActivity(new Intent(MainActivity.this, ThemHInhAnhActivity.class));
            }
        });

        lvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Chuyen byte[] --> bitmap
                byte[] hinhAnh = arrayHinh.get(position).getHinh();
                 ThemHInhAnhActivity.hinhchon = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                Intent intent = new Intent(MainActivity.this, XemHinh.class);


                startActivity(intent);
            }
        });
    }


    private void hengio(int gio, int phut) {
        calendar = Calendar.getInstance();
        Intent intent = new Intent(this, Notification_Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar.set(Calendar.HOUR_OF_DAY, gio);
        calendar.set(Calendar.MINUTE, phut);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }


}


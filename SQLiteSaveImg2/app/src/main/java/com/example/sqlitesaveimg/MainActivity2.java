package com.example.sqlitesaveimg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button btnChupHinh, btnBoSuuTap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnChupHinh = findViewById(R.id.btnChupHinh);
        btnBoSuuTap = findViewById(R.id.btnBoSuuTap);

        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity2.this, ThemDoVatActivity.class);
                startActivity(i);
            }
        });

        btnBoSuuTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
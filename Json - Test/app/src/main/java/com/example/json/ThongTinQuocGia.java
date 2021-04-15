package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ThongTinQuocGia extends AppCompatActivity {

    TextView textNameCountry;
    ImageView imgQuocKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_quoc_gia);

        textNameCountry = findViewById(R.id.textNameCountry);
//        textNameCountry.setText(UserAdapter.nameCountry);

    }
}
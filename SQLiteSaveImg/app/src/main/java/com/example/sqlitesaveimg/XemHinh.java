package com.example.sqlitesaveimg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class XemHinh extends AppCompatActivity {
    static Bitmap hinhchon;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hinh);
        imageView = findViewById(R.id.xem_imageViewCamera);
        imageView.setImageBitmap(ThemHInhAnhActivity.hinhchon);
    }
}
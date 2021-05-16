package com.example.sqlitesaveimg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class ThemHInhAnhActivity extends AppCompatActivity {
    static Bitmap hinhchon;
    Button buttonCamera, buttonThem;
    ImageView imageViewCamera;
    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);
        Intent intent = getIntent();
        anhxa();
        imageViewCamera.setImageBitmap(hinhchon);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        //Lưu trữ các ảnh đã chụp
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hinhchon == null) {
                    Toast.makeText(ThemHInhAnhActivity.this, "Chua chụp hình mà thêm cái gì", Toast.LENGTH_SHORT).show();
                } else {
                    // chuyễn data img --> byte[]
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewCamera.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte[] hinhanh = byteArray.toByteArray();
                    MainActivity.database.INSERT_HINHANH(hinhanh);
                    Toast.makeText(ThemHInhAnhActivity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThemHInhAnhActivity.this, MainActivity.class));
                }

            }
        });
    }

    private void anhxa() {
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonThem = findViewById(R.id.buttonAdd);
        imageViewCamera = findViewById(R.id.imageViewCamera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageViewCamera.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
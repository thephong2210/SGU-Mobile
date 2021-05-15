package com.example.sqlitesaveimg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ThemDoVatActivity extends AppCompatActivity {
    Button buttonCamera,buttonThem;
    public static ImageView imageViewCamera;
    int REQUEST_CODE_CAMERA =123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);
        anhxa();
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyễn data img --> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewCamera.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                byte[] hinhanh = byteArray.toByteArray();
                MainActivity.database.INSERT_HINHANH(hinhanh);
                Toast.makeText(ThemDoVatActivity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivity.this ,MainActivity.class));
            }
        });
    }
    private void anhxa(){
        buttonCamera= findViewById(R.id.buttonCamera);
        buttonThem= findViewById(R.id.buttonAdd);
        imageViewCamera= findViewById(R.id.imageViewCamera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data !=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageViewCamera.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
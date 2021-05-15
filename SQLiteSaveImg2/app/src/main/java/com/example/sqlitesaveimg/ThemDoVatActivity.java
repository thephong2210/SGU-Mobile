package com.example.sqlitesaveimg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private static final int NOTIFICATION_ID=1;

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

        buttonThem.setEnabled(false);
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
            henGio();
            buttonThem.setEnabled(true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void henGio(){
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(7000);
                } catch (Exception e) {

                }
                finally
                {
                    sendNoti();
//                    henGio();
//                    Intent activitymoi=new Intent("com.example.taomanhinhchao.MAINACTIVITY");
//                    startActivity(activitymoi);
                }
            }
        };
        bamgio.start();
    }

    private void sendNoti(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Ting Ting")
                .setContentText("Chụp ảnh nhé!!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager !=null)
            notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID=1;
    int REQUEST_CODE = 123;

    Button btnCamera;
    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHinh = findViewById(R.id.imgHinh);
        btnCamera = findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
            henGio();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void henGio(){
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
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
                .setContentTitle("Hello")
                .setContentText("Chụp ảnh nhé")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager !=null)
            notificationManager.notify(NOTIFICATION_ID, notification);
    }

//    hiển thị nhiều thông báo chồng nhau
//    private int getNotificationId()
//    {
//        return (int) new Date().getTime();
//    }
}
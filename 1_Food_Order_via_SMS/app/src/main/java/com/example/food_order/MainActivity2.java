package com.example.food_order;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textSize, textTortilla, textFillings, textBeverage;
    Button buttonDatHang;
    String s1, s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonDatHang = findViewById(R.id.buttonDatHang);

//        String s = MainActivity.Size;
        textSize = findViewById(R.id.textSize);
        textSize.setText("-Size:  "+ MainActivity.Size);

        textTortilla = findViewById(R.id.textTortilla);
        textTortilla.setText("-Tortilla:  "+ MainActivity.Tortilla);

        textFillings = findViewById(R.id.textFillings);
        textFillings.setText("-Fillings:  "+ MainActivity.Fillings);
        s1 = textFillings.getText().toString() +"\n";

        textBeverage = findViewById(R.id.textBeverage);
        textBeverage.setText("-Beverage:  "+ MainActivity.Beverage);
        s2 = textBeverage.getText().toString() +"\n";

        if(MainActivity.Fillings =="") { //cách 1, nên dùng cách 2
            s1="";
            textFillings.setVisibility(View.GONE); // Khi không có giá trị thì ẩn đi
        }

        if(MainActivity.Beverage.length()==0) {  //cách 2
            s2="";
            textBeverage.setVisibility(View.GONE);

        }

        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity2.this);
                b.setTitle("Thông Báo");
                b.setMessage("Bạn đã đặt đơn hàng Thành Công. Chúng tôi sẽ giao hàng trong ít phút \n\n" +
                        "Vui lòng kiểm tra tin nhắn!");

                b.setPositiveButton("Xác Nhận", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();// Tạo dialog
                al.show();

                //Gửi tin nhắn sms
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("191", null,
                        "I WANT A TACO PRONTO\n\n" +
                                textSize.getText() + "\n"
                        + textTortilla.getText() + "\n"
                        +s1 + s2,
                        null, null);

            }
        });

    }
}
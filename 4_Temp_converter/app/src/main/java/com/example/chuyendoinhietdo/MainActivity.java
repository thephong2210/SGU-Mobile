package com.example.chuyendoinhietdo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textDoC, textDoF, textLichSu;
    EditText editTextDoC, editTextDoF;
    Button buttonHoanDoi, buttonChuyenDoi;
    Integer flag = 1;  // Mặc định ban đầu cho dạng Độ C --> F


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDoC = findViewById(R.id.textDoC);
        textDoF = findViewById(R.id.textDoF);
        textLichSu = findViewById(R.id.textLichSu);
        editTextDoC = findViewById(R.id.editTextDoC);
        editTextDoF = findViewById(R.id.editTextDoF);
        buttonHoanDoi = findViewById(R.id.buttonHoanDoi);
        buttonChuyenDoi = findViewById(R.id.buttonChuyenDoi);
        editTextDoC.setText("");

        buttonHoanDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = flag*(-1);
//              Hoán đổi Tên nhiệt độ
                if(flag == 1)
                {
                    textDoC.setText("Độ C");
                    textDoF.setText("Độ F");
                }
                if(flag == -1)
                {
                    textDoC.setText("Độ F");
                    textDoF.setText("Độ C");
                }


//              Hoán đổi Giá Trị nhiệt độ trong ô
                String a,b,c;
                a = editTextDoC.getText().toString();
                b = editTextDoF.getText().toString();

                c=a;
                a=b;
                b=c;

                editTextDoC.setText(""+a);
                editTextDoF.setText(""+b);

                editTextDoC.setSelection(editTextDoC.getText().length());   //Cho con trỏ cuối dòng
            }
        });
        
        buttonChuyenDoi.setOnClickListener(new View.OnClickListener() {
            String s="", s1, s2;
            int soLanThucHien = 1;

            @Override
            public void onClick(View v) {
                if(editTextDoC.getText().toString().equals(""))
                {
                    AlertDialog.Builder RangBuoc = new AlertDialog.Builder(MainActivity.this);
                    RangBuoc.setTitle("Thông báo!");
                    RangBuoc.setMessage("Bạn chưa nhập số");
                    RangBuoc.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog al = RangBuoc.create();
                    al.show();
//                    return;   // Nếu không dùng Dialog thi chỉ cần dùng return là tắt hàm
                }

                else {
                    float a = Float.parseFloat(editTextDoC.getText().toString());
                    double kq = 0;

                    if(flag==1)  // Độ C --> F
                    {
                        kq = (float) ((float) 1.8*a+32);
                        s1 = "C to F:  ";
                    }
                    if(flag==-1)  // Độ F --> C
                    {
                        kq = (float) ((float) (a-32)/1.8);
                        s1 = "F to C:  ";
                    }

                    kq = (double) Math.round(kq*10)/10;  // Làm tròn
                    editTextDoF.setText(""+kq);

                    s2 = s1 + editTextDoC.getText()+" --> "+editTextDoF.getText();
                    s = s + soLanThucHien++ +". "+ s2 +"\n\n";
                    textLichSu.setText(s);

                    editTextDoC.setSelection(editTextDoC.getText().length());   //Cho con trỏ cuối dòng
                }
            }
        });
    }
}
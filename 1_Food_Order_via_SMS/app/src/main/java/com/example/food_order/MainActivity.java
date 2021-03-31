package com.example.food_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String Size="", Tortilla="", Fillings="", Beverage="";

    TextView icon_vitri, textTenMon;
    RadioGroup radioGroupSize, radioGroupTortilla;
    RadioButton radioButtonSize, radioButtonTortilla, large;
    CheckBox checkbox_Fillings_Beef, checkbox_Fillings_Chicken, checkbox_Fillings_WhiteFish,
            checkbox_Fillings_Chesse, checkbox_Fillings_SeaFood, checkbox_Fillings_Rice, checkbox_Fillings_Bean,
            checkbox_Fillings_PicoDeGallo, checkbox_Fillings_Guacamole, checkbox_Fillings_LBT,
            checkbox_Beverage_Soda, checkbox_Beverage_Cervera, checkbox_Beverage_Margarita, checkbox_Beverage_Tequila;
    Button buttonThem;

    public void clicked_url(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public String catKiTu(String str) {          // Hàm cắt 2 kí tự cuối
        if (str !="" && str.length() > 0) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icon_vitri = findViewById(R.id.icon_vitri);
        textTenMon = findViewById(R.id.textTenMon);

//        checkbox_Fillings_Beef = findViewById(R.id.checkbox_Fillings_Beef);
        checkbox_Fillings_Chicken = findViewById(R.id.checkbox_Fillings_Chicken);
        checkbox_Fillings_WhiteFish = findViewById(R.id.checkbox_Fillings_WhiteFish);
        checkbox_Fillings_Chesse = findViewById(R.id.checkbox_Fillings_Chesse);
        checkbox_Fillings_SeaFood = findViewById(R.id.checkbox_Fillings_SeaFood);
        checkbox_Fillings_Rice = findViewById(R.id.checkbox_Fillings_Rice);
        checkbox_Fillings_Bean = findViewById(R.id.checkbox_Fillings_Bean);
        checkbox_Fillings_PicoDeGallo = findViewById(R.id.checkbox_Fillings_PicoDeGallo);
        checkbox_Fillings_Guacamole = findViewById(R.id.checkbox_Fillings_Guacamole);
        checkbox_Fillings_LBT = findViewById(R.id.checkbox_Fillings_LBT);
        checkbox_Beverage_Soda = findViewById(R.id.checkbox_Beverage_Soda);
        checkbox_Beverage_Cervera = findViewById(R.id.checkbox_Beverage_Cervera);
        checkbox_Beverage_Margarita = findViewById(R.id.checkbox_Beverage_Margarita);
        checkbox_Beverage_Tequila = findViewById(R.id.checkbox_Beverage_Tequila);

        buttonThem = findViewById(R.id.buttonThem);

        icon_vitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_url("https://bitly.com.vn/4hogv1");
            }
        });

//        large = findViewById(R.id.Large);
//        if(large.isChecked())
//            buttonThem.setEnabled(true);


        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fillings =""; // Set về giá trị null khi quay lại từ đơn hàng, để khỏi bị cộng dồn
                Beverage ="";

                // radio Size
                radioGroupSize = findViewById(R.id.radioGroupSize);
                int idSize= radioGroupSize.getCheckedRadioButtonId(); // lấy id của radioButton đã được chọn trong radioGroup
                    if(idSize==-1) {
                        Toast.makeText(MainActivity.this, "Bạn chưa chọn Size", Toast.LENGTH_LONG).show();
                        return;
                    }
                radioButtonSize = findViewById(idSize);
                Size = radioButtonSize.getText().toString();

                // radio Tortilla
                radioGroupTortilla = findViewById(R.id.radioGroupTortilla);
                int idTortilla= radioGroupTortilla.getCheckedRadioButtonId();
                    if(idTortilla==-1) {
                        Toast.makeText(MainActivity.this, "Bạn chưa chọn Tortilla", Toast.LENGTH_LONG).show();
                        return;
                    }
                radioButtonTortilla = findViewById(idTortilla);
                Tortilla = radioButtonTortilla.getText().toString();


                // Các checkbox
                if (findViewById(R.id.checkbox_Fillings_Beef).isClickable())
                    Fillings = Fillings + checkbox_Fillings_Beef.getText().toString() +", ";
                if (checkbox_Fillings_Chicken.isChecked())
                    Fillings = Fillings + checkbox_Fillings_Chicken.getText().toString() +", ";
                if (checkbox_Fillings_WhiteFish.isChecked())
                    Fillings = Fillings + checkbox_Fillings_WhiteFish.getText().toString() +", ";
                if (checkbox_Fillings_Chesse.isChecked())
                    Fillings = Fillings + checkbox_Fillings_Chesse.getText().toString() +", ";
                if (checkbox_Fillings_SeaFood.isChecked())
                    Fillings = Fillings + checkbox_Fillings_SeaFood.getText().toString() +", ";
                if (checkbox_Fillings_Rice.isChecked())
                    Fillings = Fillings + checkbox_Fillings_Rice.getText().toString() +", ";
                if (checkbox_Fillings_Bean.isChecked())
                    Fillings = Fillings + checkbox_Fillings_Bean.getText().toString() +", ";
                if (checkbox_Fillings_PicoDeGallo.isChecked())
                    Fillings = Fillings + checkbox_Fillings_PicoDeGallo.getText().toString() +", ";
                if (checkbox_Fillings_Guacamole.isChecked())
                    Fillings = Fillings + checkbox_Fillings_Guacamole.getText().toString() +", ";
                if (checkbox_Fillings_LBT.isChecked())
                    Fillings = Fillings + checkbox_Fillings_LBT.getText().toString() +", ";
                if (checkbox_Beverage_Soda.isChecked())
                    Beverage = Beverage + checkbox_Beverage_Soda.getText().toString() +", ";
                if (checkbox_Beverage_Cervera.isChecked())
                    Beverage = Beverage + checkbox_Beverage_Cervera.getText().toString() +", ";
                if (checkbox_Beverage_Margarita.isChecked())
                    Beverage = Beverage + checkbox_Beverage_Margarita.getText().toString() +", ";
                if (checkbox_Beverage_Tequila.isChecked())
                    Beverage = Beverage + checkbox_Beverage_Tequila.getText().toString() +", ";

                Fillings = catKiTu(Fillings); // Gọi hàm cắt kí tự cuối
                Beverage = catKiTu(Beverage);

                Intent i = new Intent();
                i.setClass(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }
}
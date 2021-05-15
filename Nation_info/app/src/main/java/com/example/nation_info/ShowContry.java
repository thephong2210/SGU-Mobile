package com.example.nation_info;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ShowContry extends AppCompatActivity {
    TextView textViewarea, textViewpopulation, textViewcountry_name, textViewnational_flag;
    ImageView imageViewQuocKi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.coutries);
        textViewarea = findViewById(R.id.textViewarea);
        textViewcountry_name = findViewById(R.id.textViewcountry_name);
//        textViewnational_flag = findViewById(R.id.textViewnational_flag);
        textViewpopulation = findViewById(R.id.textViewpopulation);
        textViewcountry_name.append(MainActivity.namequocgia);
        textViewarea.append(MainActivity.areaInSqKm+"  km²");
        textViewpopulation.append(MainActivity.population);
        imageViewQuocKi = findViewById(R.id.imageViewFlag);
        



        Log.d("AAA", MainActivity.flag);
        new LoadImageInternet().execute(MainActivity.flag);


    }
    public  class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmaphinh = null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmaphinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmaphinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageViewQuocKi.setImageBitmap(bitmap);
        }
    }
}

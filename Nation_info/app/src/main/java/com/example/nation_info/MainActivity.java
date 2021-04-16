package com.example.nation_info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String namequocgia,flag,population,areaInSqKm;
    Button buttonLoadHinh;
    ArrayList<Country> countries;
    ArrayList<String> name;
    ListView lvcountries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvcountries = findViewById(R.id.ListViewCoutries);
        countries = new ArrayList<Country>();
        name = new ArrayList();

        new ReadJSON().execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=sangdeptrai&style=full");

//        buttonLoadHinh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new LoadImageInternet().execute("https://img.geonames.org/flags/m/vn.png");
//
//            }
//        });
        lvcountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                namequocgia = countries.get(position).countryName;
                areaInSqKm = countries.get(position).areaInSqKm;
                population = countries.get(position).population;
                flag = countries.get(position).flag;



                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShowContry.class);
                startActivity(intent);


            }
        });
    }



    private class ReadJSON extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            StringBuilder contenBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    contenBuilder.append(line);

                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return contenBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonObject = new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("geonames");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                    String countryName = jsonobject.getString("countryName");
                    String population = jsonobject.getString("population");
                    String areaInSqKm = jsonobject.getString("areaInSqKm");
                    String flag = "https://img.geonames.org/flags/m/" + jsonobject.getString("countryCode").toLowerCase() + ".png";


                    Country countryObj = new Country(countryName, flag, population, areaInSqKm);
                    name.add(countryName);
                    countries.add(countryObj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, name);
            lvcountries.setAdapter(arrayAdapter);

        }
    }
}
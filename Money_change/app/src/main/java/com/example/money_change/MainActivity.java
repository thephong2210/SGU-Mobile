package com.example.money_change;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    Spinner dropdown1, dropdown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatDrop(0, 1);
//        new ReadRSS().execute("https://usd.fxexchangerate.com/eur.xml");
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vt1= dropdown1.getSelectedItemPosition();
                int vt2= dropdown2.getSelectedItemPosition();
                creatDrop(vt2, vt1);


            }
        });
        findViewById(R.id.button_concert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText money= findViewById(R.id.money);
                Toast.makeText(MainActivity.this, money.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    void creatDrop(int vt1, int vt2) {
        dropdown1 = findViewById(R.id.spinner1);
        dropdown2 = findViewById(R.id.spinner2);

        String[] items = new String[]{"EUR - Euro", "GBP - British Pound", "JPY - Japanese Yen"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown1.setAdapter(adapter);
        dropdown1.setSelection(vt1);

        dropdown2.setAdapter(adapter);
        dropdown2.setSelection(vt2);

    }


    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String noidung = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                noidung += parser.getValue(element, "description");

            }
            TextView textView = findViewById(R.id.text);
            textView.setText(noidung);
        }
    }
}
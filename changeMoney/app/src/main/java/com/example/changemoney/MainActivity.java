package com.example.changemoney;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;

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
        new ReadRSS().execute("usd.fxexchangerate.com/eur.xml");
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
                Toast.makeText(com.example.changemoney.MainActivity.this, money.getText(), Toast.LENGTH_SHORT).show();
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
            com.example.changemoney.XMLDOMParser parser = new com.example.changemoney.XMLDOMParser();
            Document document = parser.getDocument(s);
            String s1= String.valueOf(document);
//            NodeList nodeList = document.getElementsByTagName("description");
//            Element element = document.get
            String noidung = "";

//                Element element = (Element) nodeList.item(0);
//                noidung += parser.getValue(element, "title");


            Toast.makeText(com.example.changemoney.MainActivity.this, String.valueOf(document), Toast.LENGTH_SHORT).show();
//            Log.d("AAA", String.valueOf(nodeList.getLength()));

        }
    }
}
package com.example.Music;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.documentfile.provider.DocumentFile;

import java.util.ArrayList;

public class ListSong extends AppCompatActivity {

    private static final int MY_PERMISSIONS_READ_EXTERNAL = 1;
    private static final int PICK_REQUEST_CODE = 1;
    private ListView listView;
    private TextView path;
    private ImageButton chooseFolder;
    private String[] items;
    private Uri folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        listView = (ListView) findViewById(R.id.listView);
        path = (TextView) findViewById(R.id.txtPath);
        chooseFolder = (ImageButton) findViewById(R.id.btnPathSelect);

        chooseFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                /*Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                        */
                startActivityForResult(intent, PICK_REQUEST_CODE);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songName = (String) listView.getItemAtPosition(position);
                Uri path = getSong(folder,songName).getUri();

                Bundle bundle = new Bundle();
                bundle.putParcelable("currentSong",path);
                bundle.putParcelable("playlist",folder);
                bundle.putInt("position",position);
                Intent playSong = new Intent(getApplicationContext(), PlayMusic.class);
                playSong.putExtra("song",bundle);
                startActivity(playSong);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_REQUEST_CODE) {
            folder = data.getData();
            Uri docUri = DocumentsContract.buildDocumentUriUsingTree(folder,
                    DocumentsContract.getTreeDocumentId(folder));
            path.setText(folder.getPath());
            displaySong(folder);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Vui lòng cấp quyền", Toast.LENGTH_SHORT).show();
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_READ_EXTERNAL);
        } else {
            // Permission already granted. Enable the message button.
            //enableSmsButton();
        }
    }

    private ArrayList<DocumentFile> getSongs(DocumentFile documentFile) {
        ArrayList<DocumentFile> arrayList = new ArrayList<>();
        for (DocumentFile file : documentFile.listFiles()) {
            if(file.getName().endsWith(".mp3") || file.getName().endsWith(".m4a") || file.getName().endsWith(".flac") ) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    private DocumentFile getSong(Uri folder,String songName) {
        DocumentFile documentFile = DocumentFile.fromTreeUri(this, folder);
        for (DocumentFile file : documentFile.listFiles()) {
            if(file.getName().equalsIgnoreCase(songName)){
                return file;
            }
        }
        return null;
    }


    public void displaySong(Uri path) {
        ArrayList<DocumentFile> songs = getSongs(DocumentFile.fromTreeUri(this, path));
        items = new String[songs.size()];
        for(int i=0;i< songs.size();i++) {
            items[i] = songs.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.row_item,R.id.textViewMusicCode,items);
        listView.setAdapter(adapter);
    }

}
package com.example.Music;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
// nó yêu cầu nâng lên , phiên bản api23 thấp hơn , nó yêu cầu 25
// đổi api được k,thành cuả mình
// m tạo thêm cái máy ảo api cao hơn nó yêu cầu đc r , oke
// k đử lưu lượng 4g
// v buil trên điện thoại đi
// đt chạy đc
//v là đc r tại nó thấp hơn nên ko đc chứ đâu có j đâu , để có wifi t tải oki


public class PlayMusic extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private TextView txtSongName,txtTotalDuration;
    private ImageButton btnPlay,btnNext,btnBack,btnRepeat,btnShuffle;
    private SeekBar songProgress;
    private Thread updateSongProgress;
    private ArrayList<DocumentFile> songs;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_music);

        btnPlay = (ImageButton) findViewById(R.id.imageButton1);
        btnNext = (ImageButton) findViewById(R.id.imageButton2);
        btnBack = (ImageButton) findViewById(R.id.imageButton3);
//        btnShuffle = (ImageButton) findViewById(R.id.imageButton4);
//        btnRepeat = (ImageButton) findViewById(R.id.imageButton5);
        txtSongName = (TextView) findViewById(R.id.textSongName);
        txtTotalDuration = (TextView) findViewById(R.id.textTotalDuration);
        songProgress = (SeekBar) findViewById(R.id.songProgress);

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Uri song = getIntent().getBundleExtra("song").getParcelable("currentSong");
        songs = getSongs(getIntent().getBundleExtra("song").getParcelable("playlist"));
        position = getIntent().getBundleExtra("song").getInt("position");
        mediaPlayer = MediaPlayer.create(this,song);
        mediaPlayer.start();
        txtSongName.setSelected(true);
        txtSongName.setText(StringUtils.substringBeforeLast(songs.get(position).getName(), "."));

        updateSongProgress = new Thread() {
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPosition =0;

                while(currentPosition < totalDuration) {
                    try{
                        sleep(500);

                        currentPosition = mediaPlayer.getCurrentPosition();
                        songProgress.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        songProgress.setMax(mediaPlayer.getDuration());
        txtTotalDuration.setText(getTimeString(mediaPlayer.getDuration()));
        updateSongProgress.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.ic_pause);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousSong();
            }
        });



    }

    private void nextSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        position++;
        Uri song = songs.get(position).getUri();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),song);
        txtSongName.setText(StringUtils.substringBeforeLast(songs.get(position).getName(), "."));
        btnPlay.setImageResource(R.drawable.ic_pause);
        songProgress.setMax(mediaPlayer.getDuration());
        txtTotalDuration.setText(getTimeString(mediaPlayer.getDuration()));
        mediaPlayer.start();
    }

    private void previousSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        position--;
        Uri song = songs.get(position).getUri();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),song);
        txtSongName.setText(StringUtils.substringBeforeLast(songs.get(position).getName(), "."));
        btnPlay.setImageResource(R.drawable.ic_pause);
        songProgress.setMax(mediaPlayer.getDuration());
        txtTotalDuration.setText(getTimeString(mediaPlayer.getDuration()));
        mediaPlayer.start();
    }

    public static String getTimeString(long duration) {
        int minutes = (int) Math.floor(duration / 1000 / 60);
        int seconds = (int) ((duration / 1000) - (minutes * 60));
        return minutes + ":" + String.format("%02d", seconds);
    }

    private ArrayList<DocumentFile> getSongs(Uri uri) {
        DocumentFile documentFile = DocumentFile.fromTreeUri(this, uri);
        ArrayList<DocumentFile> arrayList = new ArrayList<>();
        for (DocumentFile file : documentFile.listFiles()) {
            if(file.getName().endsWith(".mp3") || file.getName().endsWith(".m4a") || file.getName().endsWith(".flac") ) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
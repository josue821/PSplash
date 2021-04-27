package com.psplash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    ProgressBar progress;
    Handler handler = new Handler();
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progressBar);
        threadMethod();

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(tarea, 1000);
    }

    private void threadMethod() {
        Thread hilo = new Thread((Runnable)() ->{
        while(i <= 100){
            handler.post(()-> {progress.setProgress(i);});
            i++;
            try {
                Thread.sleep( 50);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        });
    }
}
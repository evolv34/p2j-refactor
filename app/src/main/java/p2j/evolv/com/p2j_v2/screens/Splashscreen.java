package p2j.evolv.com.p2j_v2.screens;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import p2j.evolv.com.p2j_v2.R;

public class Splashscreen extends AppCompatActivity {
    private final Handler activityHandler = new Handler();
    private final Runnable redirectRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), P2J.class);
            intent.putExtra("path", Environment.getExternalStorageDirectory().getPath());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
        }

        hideActionBar();
        close(1000);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void close(int delayMillis) {
        activityHandler.removeCallbacks(redirectRunnable);
        activityHandler.postDelayed(redirectRunnable, delayMillis);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

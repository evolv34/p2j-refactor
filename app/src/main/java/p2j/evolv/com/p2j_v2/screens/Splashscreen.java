package p2j.evolv.com.p2j_v2.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.services.FileConversionService;

public class Splashscreen extends AppCompatActivity {
    private final Handler activityHandler = new Handler();
    private final Runnable redirectRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), P2J.class);
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
        for(int i=0; i<10; i++) {
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
}

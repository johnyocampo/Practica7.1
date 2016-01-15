package com.ocampojohny.practica71;


        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Window;

        import java.util.Timer;
        import java.util.TimerTask;

/**
 * Created by johny on 12/01/2016.
 */
public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splash_activity);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent=new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        };
        Timer timer=new Timer();
        timer.schedule(task,SPLASH_DELAY);
    }
}
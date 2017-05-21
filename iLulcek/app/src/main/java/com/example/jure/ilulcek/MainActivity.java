package com.example.jure.ilulcek;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView timerView;
    CountDownTimer timer;
    String timerText;
    int countTime = 200000; // miliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init variables
        timerView = (TextView) findViewById(R.id.timerView);

        // Use Time Unit API
        // Countdown timer stuff (TimeToCount, Step [ms])
        timer = new CountDownTimer(countTime, 1000) {

            public void onTick(long millisUntilFinished) {


                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long mins = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(hours);
                long secs = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(mins);
                // bi bilo tukaj boljše če bi dal npr. MILISECONS.toMinutes - (hours*60)?

                timerText = String.format("%02dh : %02dm : %02ds", hours, mins, secs);
                timerView.setText(timerText);
            }

            public void onFinish() {
                timerView.setText("LULAJ!");
            }

        }.start();

    }


    public void pee(View view) {

        // Dej števec na nulo
        timer.cancel();
        timerText = String.format("%02dh : %02dm : %02ds", 0, 0, 0);
        timerView.setText(timerText);

        // Sprazni mehur

        // Nastavi nov peeTimeCountdown
        countTime += 1000;

        // Zaženi nov counter
        //TODO: Kako ponastavit čas novega counterja??
        timer.start();

    }
}

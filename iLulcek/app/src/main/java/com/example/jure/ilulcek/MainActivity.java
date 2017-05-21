package com.example.jure.ilulcek;

import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    TextView timerView;
    TextView etaView;
    CountDownTimer timer;
    Resources res;
    int countTime = 20000; // miliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init variables
        timerView = (TextView) findViewById(R.id.timerView);
        etaView = (TextView) findViewById(R.id.text2);
        res = getResources();

        updatePeeTime(countTime);

        timer = initTimer(countTime);
        timer.start();

    }

    /**
     *  Initializes a CountDownTimer with desired countdown time
     *
     * @param cdTime time to count down
     * @return returns CountDownTimer with initialised time cdTime
     *
     * FIND MORE ELEGANT SOLUTION FOR THIS!
     */
    public CountDownTimer initTimer(int cdTime) {

        return new CountDownTimer(cdTime, 1000) {

            public void onTick(long millisUntilFinished) {

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long mins = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(hours);
                long secs = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(mins);
                // bi bilo tukaj boljše če bi dal npr. MILISECONS.toMinutes - (hours*60)?

                // TODO: Fix this string format crap
                String timerText = String.format("%02dh : %02dm : %02ds", hours, mins, secs);
                timerView.setText(timerText);
            }

            public void onFinish() {
                // bi moral tukaj zbrisat/uničit objekt?
                timerView.setText("LULAJ!");
            }

        };
    }


    /**
     * Reset the counter, empty bladder, start a new countoff
     */
    public void pee(View view) {

        // Dej števec na nulo
        timer.cancel();

        // Nastavi nov peeTimeCountdown. Npr:
        countTime += 1000;
        updatePeeTime(countTime);

        // Zaženi nov counter
        timer = initTimer(countTime);
        timer.start();
    }

    
    /**
     * Calculates estimated time of next peeing
     *
     * @param countTime time until peeing in miliseconds
     */
    public void updatePeeTime(int countTime) {

        // calculate new time
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long peeTime = currentTime + countTime;

        // update textView
        c.setTimeInMillis(peeTime);
        // formatiranje časa: %tT -> HH:mm:ss  %tR -> HH:mm
        String text = String.format("Naslednje lulanje ob %tT", c);
        etaView.setText(text);
    }
}

package com.student.jure.ilulcek.Fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.student.jure.ilulcek.Adapters.SettingsAdapter;
import com.student.jure.ilulcek.AddActivity;
import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;


public class TodayFragment extends Fragment {

    CountDownTimer countDownTimer;

    final int casPretvorbePijaceVLulanje = 10000;
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
    String danes = df.format(today);
    final String dan1 = danes.substring(4,6);
    final String mes1 = danes.substring(2,4);
    final String let1 = danes.substring(0,2);
    final String ura1 = danes.substring(6,8);
    final String min1 = danes.substring(8,10);

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // PEE NOW BUTTON STUFF
        Button peeBtn = (Button) getView().findViewById(R.id.peeBtn);
        peeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    cancel();
                    start();

            }
        });


        // ADD DRINK BUTTON STUFF
        FloatingActionButton addBtn = (FloatingActionButton) getView().findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);

            }
        });

        start();


    }
    public void start(){

        double urin = 0.01;
        int prostorninaMehurja;
        Context cont = getActivity();
        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor resultSet = db.rawQuery("Select * from NASTAVITVE",null);
        resultSet.moveToFirst();
        final String spol = resultSet.getString(1);
        final String starostString = resultSet.getString(2);
        final String teza = resultSet.getString(3);
        resultSet.close();

        if (spol.equals("Moški"))
            prostorninaMehurja = 300;
        else if (spol.equals("Ženska"))
            prostorninaMehurja = 250;
        else
            prostorninaMehurja = 275;

        int starost = Integer.parseInt(starostString);
        if (starost > 40)
            prostorninaMehurja-= (starost-40)*2;


        Cursor resultSet2 = db.rawQuery("Select * from ZGODOVINA",null);
        if (resultSet2 !=null){
            resultSet2.moveToFirst();
            while (resultSet2.moveToNext()) {

                int casOdPopitjaPijace = 0;
                String kolicinaString = resultSet2.getString(2);
                String danString = resultSet2.getString(3);
                String uraString = resultSet2.getString(4);
                String dan2 = danString.substring(0, 2);
                String mes2 = danString.substring(3, 5);
                String let2 = danString.substring(8);
                String ura2 = uraString.substring(0, 2);
                String min2 = uraString.substring(3);

                if (let2.equals(let1)) {
                    if (mes2.equals(mes1)) {
                        if (dan2.equals(dan1)) {
                            if (ura2.equals(ura1)) {
                                if (min2.equals(min1)) {

                                } else {
                                    casOdPopitjaPijace = (Integer.parseInt(min2) - Integer.parseInt(min1)) * 60;
                                }
                            } else {
                                casOdPopitjaPijace = (Integer.parseInt(ura2) - Integer.parseInt(ura1)) * 3600 + (Integer.parseInt(min2) - Integer.parseInt(min1)) * 60;
                            }
                        } else if ((Integer.parseInt(dan2) - Integer.parseInt(dan1)) == 1) {
                            casOdPopitjaPijace = Integer.parseInt(ura1) * 3600 + Integer.parseInt(min1) * 60 + (23 - Integer.parseInt(ura2)) * 3600 + (60 - Integer.parseInt(min2));
                        }
                    }
                }

                if (casOdPopitjaPijace > casPretvorbePijaceVLulanje)
                    continue;
                else {
                    double kolicina = Double.parseDouble(kolicinaString);
                    kolicina *= 1000; //pretvorimo l v ml


                    double urinP = kolicina / casPretvorbePijaceVLulanje;
                    urin += urinP;
                }

            }resultSet2.close();
        }

        final String joze = Double.toString(urin);
        double sekundeDoLulanja = (double) prostorninaMehurja/urin;
        int sekundeDoLulanja2 = (int) sekundeDoLulanja;
        int milisekundeDoLulanja = sekundeDoLulanja2*1000;

        //tocna ura lulanja
        final TextView urca = (TextView)  getView().findViewById(R.id.text2);
        int minute = sekundeDoLulanja2/60;
        int ure = minute/60;
        minute = minute - ure*60;
        minute += Integer.parseInt(min1);
        int ureIzSekund = minute/60;
        minute = minute - ureIzSekund*60;
        ure += Integer.parseInt(ura1) + ureIzSekund;
        if (ure > 23){
            ure-=24;
        }
        String urce = String.format("%02d:%02d", ure, minute);
        urca.setText("NASLEDNJE LULANJE OB "+urce);
        //TIMER
        final TextView timer = (TextView)  getView().findViewById(R.id.timerView);
        countDownTimer = new CountDownTimer(milisekundeDoLulanja, 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String sekunde = String.format("%02d:%02d:%02d", seconds / 3600,
                        (seconds % 3600) / 60, (seconds % 60));
                timer.setText(sekunde);
            }

            public void onFinish() {
                timer.setText("lulaj!");
            }
        }.start();

    }

    public void cancel(){
        if (countDownTimer != null){
            countDownTimer.cancel();

            countDownTimer = null;
        }

    }
}

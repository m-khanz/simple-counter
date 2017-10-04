package com.coderr9.counter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    int increment = 0;
    Button reset;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView counterDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterDisplay = (TextView) findViewById(R.id.number_display);
        reset = (Button) findViewById(R.id.reset);
        pref = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor = pref.edit();

        display();
        fontCheck();

        counterDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                increment++;

                if(increment == 100 || increment == 1000 || increment == 10000 || increment == 100000)
                fontCheck();

                editor.putInt("number", increment);
                editor.apply();

                display();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this, "Counter has been reset", Toast.LENGTH_LONG).show();
                        increment = 0;
                        editor.putInt("number", increment);
                        editor.apply();
                        counterDisplay.setText("00");
                        float f = 150;
                        counterDisplay.setTextSize(f);
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public void display() {

        increment = pref.getInt("number", increment);
        String strCounter = String.valueOf(increment);
        if (increment < 10) {
            counterDisplay.setText("0" + strCounter);
        } else counterDisplay.setText(strCounter);
    }

    public void fontCheck(){
        if(increment > 99){
            float f = 120;
            counterDisplay.setTextSize(f);
        }
        if(increment >999){
            float f = 100;
            counterDisplay.setTextSize(f);
        }
        if(increment > 9999){
            float f = 85;
            counterDisplay.setTextSize(f);
        }
        if(increment > 99999){
            float f = 70;
            counterDisplay.setTextSize(f);
        }
    }
}
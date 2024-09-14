package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public float Code = 0;
    public float Time = 5; // Default to 5 seconds if not set
    private TextView timerTextView;
    private boolean TimeStopStartTRUE_FALSE = false;
    private CountDownTimer countDownTimer;
    private int startTimerIntColorSeyd = 0;
    private  ScrollView blinkDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Code") && intent.hasExtra("Time")) {
            Code = intent.getFloatExtra("Code", 0);
            Time = intent.getFloatExtra("Time", 5); // Default to 5 seconds if not passed
            System.out.println("Received Code: " + Code + ", Time: " + Time);
        }

        Button launchTimeRegulatorButton = findViewById(R.id.SetTimeBtn);
        launchTimeRegulatorButton.setOnClickListener(v -> NewSetTimeActivity());

        // Number buttons
        Button N0 = findViewById(R.id.N0);
        Button N1 = findViewById(R.id.N1);
        Button N2 = findViewById(R.id.N2);
        Button N3 = findViewById(R.id.N3);
        Button N4 = findViewById(R.id.N4);
        Button N5 = findViewById(R.id.N5);
        Button N6 = findViewById(R.id.N6);
        Button N7 = findViewById(R.id.N7);
        Button N8 = findViewById(R.id.N8);
        Button N9 = findViewById(R.id.N9);

        Button N_Enter = findViewById(R.id.N_Enter);

        N0.setOnClickListener(v -> FunNumSwich(0));
        N1.setOnClickListener(v -> FunNumSwich(1));
        N2.setOnClickListener(v -> FunNumSwich(2));
        N3.setOnClickListener(v -> FunNumSwich(3));
        N4.setOnClickListener(v -> FunNumSwich(4));
        N5.setOnClickListener(v -> FunNumSwich(5));
        N6.setOnClickListener(v -> FunNumSwich(6));
        N7.setOnClickListener(v -> FunNumSwich(7));
        N8.setOnClickListener(v -> FunNumSwich(8));
        N9.setOnClickListener(v -> FunNumSwich(9));

        N_Enter.setOnClickListener(v -> FunCheckRigthCode());


        // Timer button
        Button TimeStopStart = findViewById(R.id.TimeStopStart);
        timerTextView = findViewById(R.id.timerTextView);

        TimeStopStart.setOnClickListener(v -> {
            TimeStopStartTRUE_FALSE = !TimeStopStartTRUE_FALSE;
            if (TimeStopStartTRUE_FALSE) {
                startTimer();
            } else {
                stopTimer();
            }
        });
    }

    private void startTimer() {
        if (Time > 0) {
            countDownTimer = new CountDownTimer((long) (Time * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(millisUntilFinished / 1000 + " s");
                    startTimerIntColorSeyd+=1;


                    blinkDiv = findViewById(R.id.blinkDiv);

                    if(startTimerIntColorSeyd%2 == 0){
                        blinkDiv.setBackgroundColor(Color.parseColor("#595858"));
                    }
                    else{
                        blinkDiv.setBackgroundColor(Color.parseColor("#00BD00"));

                    }

                }

                @Override
                public void onFinish() {
                    timerTextView.setText("Time's up!");
                }
            }.start();
        } else {
            timerTextView.setText("Invalid time value.");
        }
    }

    // Stop the countdown timer
    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // Method to start TimeRegilator activity
    public void NewSetTimeActivity() {
        Intent intent = new Intent(this, TimeRegilator.class);
        intent.putExtra("Code", Code);
        intent.putExtra("Time", Time);
        startActivity(intent);
    }


    // Method to handle button clicks and update TextView
    public void FunNumSwich(int n) {
        TextView text = findViewById(R.id.CodeNumId);
        String currentText = text.getText().toString();
        String newText = currentText + n;
        text.setText(newText);
    }
    private void FunCheckRigthCode(){
        TextView text = findViewById(R.id.CodeNumId);

        if (text.getText().toString().equals(Code)) {
            text.setText("rendered harmless");
        }

    }


}

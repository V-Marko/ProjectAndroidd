package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class TimeRegilator extends AppCompatActivity {
    private TextInputEditText editCode;
    private TextInputEditText editTime;
    public float Code = 0;
    public float Time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_regilator);

        // Retrieve the values passed from MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Code") && intent.hasExtra("Time")) {
            Code = intent.getFloatExtra("Code", 0);
            Time = intent.getFloatExtra("Time", 0);
            System.out.println("Received Code in TimeRegilator: " + Code + ", Time: " + Time);
        }

        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> BackActivity());

        editCode = findViewById(R.id.InputCode);
        editTime = findViewById(R.id.InputTime);

        editCode.setOnClickListener(v -> {
            System.out.println("Code value on click: " + Code);
        });
    }

    // Method to go back to MainActivity with updated values
    public void BackActivity() {
        // Get the text from the TextInputEditText (convert it to string)
        String codeText = editCode.getText().toString();
        String timeText = editTime.getText().toString();

        // Print the entered code
        System.out.println("Entered Code: " + codeText);
        System.out.println("Entered Time: " + timeText);

        // Convert the input to a float if necessary
        try {
            Code = Float.parseFloat(codeText);
            Time = Float.parseFloat(timeText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Code", Code);
        intent.putExtra("Time", Time); // Correctly pass the updated Time value
        startActivity(intent);
    }
}

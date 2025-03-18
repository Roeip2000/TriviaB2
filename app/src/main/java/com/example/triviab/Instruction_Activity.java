package com.example.triviab;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Instruction_Activity extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private Button btnSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        // אתחול Text-to-Speech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US); // ניתן לשנות לשפה הרצויה

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported or missing data");
                        btnSpeak.setEnabled(false);
                    } else {
                        btnSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        // כפתור קריאה
        btnSpeak = findViewById(R.id.btnSpeak); // שימוש במשתנה המחלקה
        btnSpeak.setEnabled(false); // הכפתור מושבת עד שה-TTS יאותחל בהצלחה
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakInstructions();
            }
        });

        // כפתור חזור עם אנימציה
        Button btnBack = findViewById(R.id.btnBack);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1000);
        btnBack.startAnimation(fadeIn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textToSpeech != null) {
                    textToSpeech.stop(); // עצירת הדיבור בעת חזרה
                    textToSpeech.shutdown();
                }
                finish(); // חזרה למסך הראשי
            }
        });
    }

    private void speakInstructions() {
        if (textToSpeech != null) {
            String instructions = "Welcome to the game! Here are the instructions: Answer the trivia questions correctly to earn points. Good luck!";
            textToSpeech.speak(instructions, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
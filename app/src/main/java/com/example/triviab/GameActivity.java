package com.example.triviab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvQuestion, tvPoints, tvQuestionNumber, tvGameOver;
    private Button btna1, btna2, btna3, btna4, btnHome;
    private Collection Collection;
    private question currentQuestion;
    private int Points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        Collection = new Collection();


        tvQuestion = findViewById(R.id.tvQuestion);
        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);

        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);
        btnHome = findViewById(R.id.btnHome); // כפתור החזרה לבית

        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);
        btnHome.setOnClickListener(this); // מאזין לכפתור הבית
        Collection.intQuestion();
        nextQuestion();

    }


    private void nextQuestion() {

        if(Collection.isNotLastquestion()) {

            currentQuestion = Collection.getNextquestion(); // reference
            tvQuestion.setText(currentQuestion.getQuestion());

            btna1.setText(currentQuestion.getA1());
            btna2.setText(currentQuestion.getA2());
            btna3.setText(currentQuestion.getA3());
            btna4.setText(currentQuestion.getA4());
        }

    }


    @Override
    public void onClick(View v)
    {

        if (v == btna1) {
            if (currentQuestion.getCorrect() == 1) {
                Points++;
            }
        }
        if (v == btna2) {
            if (currentQuestion.getCorrect() == 2) {
                Points++;
            }
        }
        if (v == btna3) {
            if (currentQuestion.getCorrect() == 3) {
                Points++;
            }
        }
        if (v == btna4) {
            if (currentQuestion.getCorrect() == 4) {
                Points++;
            }
        }

        tvPoints.setText("Points: " + Points);

        if (Collection.isNotLastquestion())
        {
            tvQuestionNumber.setText("Question number: " + (Collection.getIndex() + 1));
            nextQuestion();
        } else
        {

            tvGameOver.setText("Game Over! Final Score: " + Points);


            Custom_dialog customDialog=new Custom_dialog( this);
            customDialog.show();


            /* הצגת כפתור החזרה לבית עם אפקט fade-in
            btnHome.setVisibility(View.VISIBLE);
            btnHome.setAlpha(0f);
            btnHome.animate().alpha(1f).setDuration(500);*/



        }



        // אם המשתמש לחץ על כפתור הבית
        if (v == btnHome) {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // סוגר את המשחק
        }
        }

    public void reset()
    {
        this.Points = 0;
        Collection.intQuestion();
        tvPoints.setText("Points: " + 0);
        tvQuestionNumber.setText("Question number: " + 1);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();

    }
}

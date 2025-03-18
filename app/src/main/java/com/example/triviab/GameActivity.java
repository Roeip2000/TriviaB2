// קובץ: GameActivity.java
package com.example.triviab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    // רכיבי ממשק
    private TextView tvQuestion, tvPoints, tvQuestionNumber, tvGameOver, tvHighScore;
    private Button btna1, btna2, btna3, btna4, btnHome;
    private Collection Collection;  // במקום Collection

    private question currentQuestion;
    private int Points = 0;

    // Firebase
    private DatabaseReference databaseReference;
    private int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        Collection = new Collection();

        // קישור Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("HighScore");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    highScore = snapshot.getValue(Integer.class);
                    tvHighScore.setText("High Score: " + highScore);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {}
        });

        // קישור רכיבי הממשק מה-XML
        tvQuestion = findViewById(R.id.tvQuestion);
        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);
        tvHighScore = findViewById(R.id.tvHighScore);

        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);
        btnHome = findViewById(R.id.btnHome);

        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);
        btnHome.setOnClickListener(this);

        Collection.intQuestion();
        nextQuestion();
    }

    private void endGame() {
        tvGameOver.setText("Game Over! Final Score: " + Points);
        tvGameOver.setVisibility(View.VISIBLE);

        if (Points > highScore) {
            databaseReference.setValue(Points);
            tvHighScore.setText("High Score: " + Points);
        }
    }

    private void nextQuestion() {
        if (Collection.isNotLastquestion()) {
            currentQuestion = Collection.getNextquestion();
            tvQuestion.setText(currentQuestion.getQuestion());
            btna1.setText(currentQuestion.getA1());
            btna2.setText(currentQuestion.getA2());
            btna3.setText(currentQuestion.getA3());
            btna4.setText(currentQuestion.getA4());
        } else {
            endGame();
        }
    }

    public void reset() {
        Points = 0;
        tvPoints.setText("Points: " + Points);
        tvGameOver.setVisibility(View.INVISIBLE);
        Collection.intQuestion();
        nextQuestion();
    }

    @Override
    public void onClick(View v) {
        if (v == btna1 && currentQuestion.getCorrect() == 1) Points++;
        if (v == btna2 && currentQuestion.getCorrect() == 2) Points++;
        if (v == btna3 && currentQuestion.getCorrect() == 3) Points++;
        if (v == btna4 && currentQuestion.getCorrect() == 4) Points++;

        tvPoints.setText("Points: " + Points);

        if (Collection.isNotLastquestion()) {
            tvQuestionNumber.setText("Question number: " + (Collection.getIndex() + 1));
            nextQuestion();
        } else {
            endGame();
            Custom_dialog customDialog = new Custom_dialog(this);
            customDialog.show();
        }

        if (v == btnHome) {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
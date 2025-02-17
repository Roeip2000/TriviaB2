// קובץ: GameActivity.java
package com.example.triviab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvQuestion, tvPoints, tvQuestionNumber, tvGameOver;
    private Button btna1, btna2, btna3, btna4, btnHome;
    private Collection Collection;
    private question currentQuestion;
    private int Points = 0;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        Collection = new Collection();

        // אתחול ה-Layout של המסך
        ll = findViewById(R.id.activity_game);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);

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

        // קבלת הצבע מה-Intent ושינוי הרקע
        String color = getIntent().getStringExtra("background_color");
        if (color != null) {
            SetBeackgroundColor(color);
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
        }
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
            tvGameOver.setText("Game Over! Final Score: " + Points);
            Custom_dialog customDialog = new Custom_dialog(this);
            customDialog.show();
        }

        if (v == btnHome) {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void reset() {
        this.Points = 0;
        Collection.intQuestion();
        tvPoints.setText("Points: " + 0);
        tvQuestionNumber.setText("Question number: " + 1);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();
    }

    // פונקציה לשינוי צבע הרקע
    private void SetBeackgroundColor(String color) {
        switch (color) {
            case "Red":
                ll.setBackgroundColor(Color.RED);
                break;
            case "Blue":
                ll.setBackgroundColor(Color.BLUE);
                break;
            case "Pink":
                ll.setBackgroundColor(Color.parseColor("#FFC0CB"));
                break;
            case "Yellow":
                ll.setBackgroundColor(Color.YELLOW);
                break;
            case "White":
                ll.setBackgroundColor(Color.WHITE);
                break;
            default:
                ll.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}

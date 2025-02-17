package com.example.triviab;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class Custom_dialog extends Dialog implements View.OnClickListener {
    Button btnYes, btnNo;
    Context context;

    public Custom_dialog(@NonNull Context context) {
        super(context);

        setContentView(R.layout. custom_dialog);
        this.context = context;

        this.btnYes = findViewById(R.id.btnYes);
        this.btnNo = findViewById(R.id.btnNo);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        // מונע מהמשתמש לסגור את הדיאלוג בלחיצה מחוץ לחלון
        setCancelable(false);

    }

    @Override
    public void onClick(View view)
        {

        if (view == btnYes) {

            dismiss(); // סגור את הדיאלוג
                ((GameActivity) context).reset();

        }

        if (view == btnNo) {

            ((GameActivity) context).finish();

        }




    }
}









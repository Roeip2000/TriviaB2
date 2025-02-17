// קובץ: Custom_dialog.java
package com.example.triviab;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class Custom_dialog extends Dialog implements View.OnClickListener {

    private Button btnYes, btnNo; // כפתורים לאישור או ביטול
    private Context context; // הקשר של הדיאלוג

    public Custom_dialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.custom_dialog); // קישור לפריסת הדיאלוג ב-XML
        this.context = context;

        // איתור הכפתורים במסך
        this.btnYes = findViewById(R.id.btnYes);
        this.btnNo = findViewById(R.id.btnNo);

        // הגדרת מאזינים לכפתורים
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        // מונע מהמשתמש לסגור את הדיאלוג בלחיצה מחוץ לחלון
        setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        if (view == btnYes) {
            dismiss(); // סגור את הדיאלוג
            ((GameActivity) context).reset(); // הפעלת פונקציית האיפוס של המשחק
        }

        if (view == btnNo) {
            ((GameActivity) context).finish(); // סגירת משחק ללא איפוס
        }
    }
}

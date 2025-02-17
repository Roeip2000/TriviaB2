// קובץ: SettingActivity.java
package com.example.triviab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner; // תפריט נפתח לבחירת צבע
    private String[] arrColor = { "Red", "Blue", "Pink", "Yellow", "White" }; // מערך עם אפשרויות הצבעים
    private Button btnChooseColor; // כפתור אישור הבחירה
    private String chooseColor; // משתנה לשמירת הצבע הנבחר

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting); // טעינת קובץ ה-XML של הפעילות

        // איתור ה-Spinner במסך והגדרת מאזין לבחירת צבע
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // איתור כפתור האישור והגדרת מאזין ללחיצה עליו
        btnChooseColor = findViewById(R.id.btnChooseColor);
        btnChooseColor.setOnClickListener(this);

        // יצירת מתאם (Adapter) לתפריט הנפתח והגדרת תצוגת הרשימה
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrColor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa); // קישור המתאם לרכיב ה-Spinner
    }

    // פונקציה שמתבצעת כאשר משתמש בוחר צבע מהתפריט הנפתח
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chooseColor = arrColor[position]; // שמירת הצבע שנבחר
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // אין צורך לבצע פעולה כאשר לא נבחר צבע
    }

    // כאשר לוחצים על הכפתור, הפעולה שולחת את הצבע שנבחר חזרה ל- MainActivity
    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.putExtra("color", chooseColor); // מעביר את הצבע שנבחר
        setResult(RESULT_OK, i); // קובע את תוצאת הפעילות כ-OK ושולח חזרה
        finish(); // סגירת המסך הנוכחי וחזרה ל-MainActivity
    }
}

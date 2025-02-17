package com.example.triviab;

// ייבוא ספריות של Android
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    // משמש להשקת אקטיביטיז עם קבלת תוצאה
    private ActivityResultLauncher<Intent> launcher;
    private FbModule fbModule; // מודול לניהול פייסבוק (לשמירת צבעי רקע)
    private String backgroundColor = ""; // צבע רקע נוכחי
    private ConstraintLayout ll; // פריסת המסך הראשי

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // קישור לקובץ ה-XML של הפעילות הראשית

        fbModule = new FbModule(this); // אתחול המודול של Firebase
        ll = findViewById(R.id.main); // קישור לפריסת המסך הראשי ב-XML

        // אתחול ה-Launcher כדי לקבל תוצאה מפעילויות אחרות (לדוגמה: דף ההגדרות)
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK) {
                            Intent data = o.getData();
                            String str = data.getStringExtra("color"); // קבלת הצבע שנבחר בהגדרות
                            fbModule.writeBackgroundColorToFb(str); // שמירת הצבע ב-Firebase
                        }
                    }
                }
        );
    }

    // פונקציה שמופעלת בעת לחיצה על כפתור "התחלת המשחק"
    public void OnStartGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("background_color", backgroundColor); // העברת הצבע לדף המשחק
        startActivity(intent); // מעבר לדף המשחק
    }

    // פונקציה שמופעלת בעת לחיצה על כפתור ההוראות (כרגע ריקה)
    public void OnInstruction(View view)
    {
        Intent intent = new Intent(this, Instruction_Activity.class);
        startActivity(intent);
    }

    // פונקציה שמופעלת בעת לחיצה על כפתור ההגדרות
    public void OnSetting(View view) {
        Intent i = new Intent(this, SettingActivity.class);
        launcher.launch(i); // הפעלת דף ההגדרות עם אפשרות להחזיר תוצאה
    }

    // פונקציה שמקבלת צבע חדש מה-Firebase ומעדכנת את המשתנה
    public void setNewColorFromFb(String str) {
        this.backgroundColor = str;
        SetBeackgroundColor(str); // עדכון צבע הרקע
    }

    // פונקציה שמעדכנת את צבע הרקע של המסך לפי המחרוזת שהתקבלה
    private void SetBeackgroundColor(String color) {
        switch (color) {
            case "Red":
                ll.setBackgroundColor(Color.RED);
                break;
            case "Blue":
                ll.setBackgroundColor(Color.BLUE);
                break;
            case "Pink":
                ll.setBackgroundColor(Color.parseColor("#FFC0CB")); // קוד Hex עבור ורוד
                break;
            case "Yellow":
                ll.setBackgroundColor(Color.YELLOW);
                break;
            case "White":
                ll.setBackgroundColor(Color.WHITE);
                break;
            default:
                ll.setBackgroundColor(Color.WHITE); // ברירת מחדל - לבן
                break;
        }
    }
}

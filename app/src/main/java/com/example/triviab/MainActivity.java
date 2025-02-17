package com.example.triviab;


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

    private ActivityResultLauncher<Intent> launcher;
    private FbModule fbModule;
    private String backgroundColor = "";
    private ConstraintLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbModule = new FbModule(this);
         ll=findViewById(R.id.main);// ניתוב לXML

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {

                        if (o.getResultCode() == RESULT_OK) {
                            Intent data = o.getData();
                            String str = data.getStringExtra("color");
                            fbModule.writeBackgroundColorToFb(str);
                            //Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );

    }



    public void OnStartGame(View view) //פעולה להתחלת המשחק
    {
        Intent intent=new Intent(this, GameActivity.class);
        startActivity(intent);//העברת דף
    }

    public void OnInstruction(View view)
    {

    }



    public void OnSetting(View view)
    {
        Intent i = new Intent(this, SettingActivity.class);
        launcher.launch(i);

    }

    public void setNewColorFromFb(String str)
    {

        this.backgroundColor=str;
           SetBeackgroundColor(str);
    }

    private void SetBeackgroundColor(String color)
    {


        switch (color) {
            case "Red":
                ll.setBackgroundColor(Color.RED);
                break;
            case "Blue":
                ll.setBackgroundColor(Color.BLUE);
                break;
            case "Pink":
                ll.setBackgroundColor(Color.parseColor("#FFC0CB")); // Hex code for pink
                break;
            case "Yellow":
                ll.setBackgroundColor(Color.YELLOW);
                break;
            case "White":
                ll.setBackgroundColor(Color.WHITE);
                break;

            default:
                // Handle unknown colors
                ll.setBackgroundColor(Color.WHITE); // Default color
                break;
        }


    }





}

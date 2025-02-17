// קובץ: FbModule.java
package com.example.triviab;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FbModule {
    private Context context;
    private static final String TAG = "FbModule";

    public FbModule(Context context) {
        this.context = context;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("color");

        // מאזין לשינויים בצבע ב-Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = snapshot.getValue(String.class);
                Log.d(TAG, "Firebase Value: " + str); // בדיקה אם הערך מתקבל
                if (str != null) {
                    ((MainActivity) context).setNewColorFromFb(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Firebase read failed: " + error.getMessage());
            }
        });
    }

    // כתיבת צבע ל-Firebase
    public void writeBackgroundColorToFb(String color) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("color");
        reference.setValue(color)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Color saved successfully: " + color))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to save color: " + e.getMessage()));
    }
}

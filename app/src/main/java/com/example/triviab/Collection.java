// קובץ: Collection.java
package com.example.triviab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Collection {

    private ArrayList<question> arr; // רשימה של אובייקטי שאלות
    private int index; // אינדקס המעקב אחרי השאלה הנוכחית
    private Random random;

    public Collection() {
        arr = new ArrayList<>(); // אתחול רשימת השאלות
        random = new Random();
        loadQuestions(); // טוען שאלות חדשות
        shuffleQuestions(); // ערבול רשימת השאלות
        index = 0; // אתחול האינדקס
    }

    // טעינת שאלות חדשות עם ערכים משתנים
    private void loadQuestions() {
        arr.clear(); // ניקוי הרשימה לפני טעינת שאלות חדשות

        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        arr.add(new question(a + " + " + b, String.valueOf(a + b), String.valueOf(a + b + 1), String.valueOf(a + b - 1), String.valueOf(a + b + 2), 1));

        int c = random.nextInt(10) + 1;
        int d = random.nextInt(10) + 1;
        arr.add(new question(c + " * " + d, String.valueOf(c * d), String.valueOf(c * d + 1), String.valueOf(c * d - 1), String.valueOf(c * d + 2), 1));

        int e = (random.nextInt(10) + 1) * 2;
        arr.add(new question(e + " / 2", String.valueOf(e / 2), String.valueOf(e / 2 + 1), String.valueOf(e / 2 - 1), String.valueOf(e / 2 + 2), 1));

        int f = random.nextInt(20) + 10;
        int g = random.nextInt(10) + 1;
        arr.add(new question(f + " - " + g, String.valueOf(f - g), String.valueOf(f - g + 1), String.valueOf(f - g - 1), String.valueOf(f - g + 2), 1));
    }

    // ערבול רשימת השאלות
    private void shuffleQuestions() {
        Collections.shuffle(arr);
    }

    // מחזירה את השאלה הבאה ומקדמת את האינדקס
    public question getNextquestion() {
        question q = arr.get(index);
        index++;
        return q;
    }

    // בודקת האם נותרו עוד שאלות
    public boolean isNotLastquestion() {
        return (index < arr.size());
    }

    // מחזירה את האינדקס הנוכחי של השאלה
    public int getIndex() {
        return index;
    }

    // מאפסת את האינדקס, טוענת שאלות חדשות ומערבבת אותן
    public void intQuestion() {
        index = 0;
        loadQuestions(); // טוען שאלות חדשות עם ערכים משתנים
        shuffleQuestions(); // ערבול מחדש לכל משחק
    }
}
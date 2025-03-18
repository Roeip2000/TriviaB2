package com.example.triviab;

import java.util.ArrayList;

public class Collection_Old
{
    private ArrayList<question> arr; // רשימה של אובייקטי שאלות
    private int index; // אינדקס המעקב אחרי השאלה הנוכחית
    public Collection_Old()
    {
        arr = new ArrayList<>(); // אתחול רשימת השאלות

        // יצירת שאלות חדשות עם תשובות אפשריות ומספר התשובה הנכונה
        question q1 = new question("1+7", "1", "8", "33", "3", 2);
        question q2 = new question("5 * 6", "11", "30", "20", "15", 2);
        question q3 = new question("12 / 4", "3", "4", "6", "2", 1);
        question q4 = new question("9 - 3", "6", "3", "9", "12", 1);

        // הוספת השאלות לרשימה
        arr.add(q1);
        arr.add(q2);
        arr.add(q3);
        arr.add(q4);
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

    // מאפסת את האינדקס כדי להתחיל מחדש את השאלות
    public void intQuestion() {
        index = 0;
    }
}





// קובץ: question.java
package com.example.triviab;

public class question {

    // משתנים לאחסון פרטי השאלה
    private String question, a1, a2, a3, a4; // נוסח השאלה ו-4 תשובות אפשריות
    private int correct; // מספר התשובה הנכונה (1-4)

    // בנאי (Constructor) ליצירת אובייקט של שאלה
    public question(String question, String a1, String a2, String a3, String a4, int correct) {
        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correct = correct;
    }

    // פונקציות להחזרת השאלה
    public String getQuestion() {
        return question;
    }

    // פונקציה לעדכון השאלה
    public void setQuestion(String question) {
        this.question = question;
    }

    // פונקציות להחזרת תשובות אפשריות
    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }


    // פונקציות להחזרת מספר התשובה הנכונה
    public int getCorrect() {
        return correct;
    }

    // פונקציה לעדכון מספר התשובה הנכונה
    public void setCorrect(int correct) {
        this.correct = correct;
    }
}

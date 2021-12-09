package com.example.aiproject;

import android.graphics.Point;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int SIZE = 9;
    private AppInterface appInterface;
    private game gm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        gameBoard = new GameBoard(SIZE);
        //creates the games state
        gm = new game();
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int width = screenSize.x / SIZE;


        appInterface = new AppInterface(this, SIZE, width, gm);

        setContentView(appInterface);


    }
}
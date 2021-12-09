package com.example.passwordmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.LinkedList;

public class delete extends AppCompatActivity {
    private DatabaseManager dbManager;
    int height;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager=new DatabaseManager(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        updateView();
    }

    private void updateView() {
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        RadioGroup group=new RadioGroup(this);
        int radioId=10000;
        group.setId(radioId);
        RadioButton cb;
        RadioGroup.LayoutParams myParams=new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT);
        LinkedList<Account> list=dbManager.all();
        for(int i = 0; i < list.size(); i++) {
            cb = new RadioButton(this);
            Account account=list.get(i);
            cb.setText(account.getLocation()+" "+account.getPassword());
            cb.setBackgroundColor(Color.parseColor("#838383"));
            cb.setWidth(width);
            cb.setLayoutParams(myParams);
            cb.setId(i);
            cb.setHighlightColor(Color.parseColor("#838383"));
            cb.setTextSize(18);
            group.addView(cb);
        }
        ll.addView(group);
        Button back = new Button(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity=(1);
        back.setLayoutParams(params);
        back.setText("Back");
        params.setMargins(0,15,0,0);
        back.setBackgroundColor(Color.parseColor("#000000"));
        back.setTextColor(Color.parseColor("#FFFFFF"));
        back.setWidth(100);
        back.setHeight(50);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    try {
                        RadioGroup radio = (RadioGroup) findViewById(radioId);
                        int id = radio.getCheckedRadioButtonId();
                        RadioButton rb = (RadioButton) findViewById(id);
                        String s = rb.getText().toString();
                        String[] strang = s.split(" ");
                        dbManager.delete(strang[0]);
                    } catch (Exception e) {
                        back(v);
                    }

                back(v);
            }
        });
        ll.addView(back);
        this.setContentView(sv);
    }
    public void back(View view){

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }
}
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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.LinkedList;

public class update extends AppCompatActivity {
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
//        ll.setGravity(1);
        sv.addView(ll);
        LinearLayout rel = null;

        //change 3 to database size
        LinkedList<Account> list=dbManager.all();
        for(int i = 0; i < list.size(); i++) {

            Account account=list.get(i);

            rel=new LinearLayout(this);
            rel.setOrientation(LinearLayout.HORIZONTAL);
            rel.setGravity(1);
            TextView txtView=new TextView(this);
            //change to the location
            txtView.setText(account.getLocation());
            txtView.setMaxWidth(width/3);
            txtView.setMinHeight(140);
            txtView.setMinWidth(width/3);
            txtView.setBackgroundColor(Color.parseColor("#ff0033"));
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            param.weight=1;
            EditText cb = new EditText(this);
            cb.setId(i);
            cb.setBackgroundColor(Color.parseColor("#00ff33"));
            //change to whats the password is
            cb.setHint(account.getPassword());
            cb.setMaxWidth(width/3);
            cb.setMinWidth(width/3);
            Button bt=new Button(this);

            bt.setText("Submit");
            bt.setMaxWidth(50);
            bt.setMinWidth(width/3);
            bt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String loc=txtView.getText().toString();
                    String pass=cb.getText().toString();
                    Account account2=new Account();
                    account2.setPassword(pass);
                    account2.setLocation(loc);
                    dbManager.update(account2);
                }
            });
//            txtView.setLayoutParams(param);
//            cb.setLayoutParams(param);
//            bt.setLayoutParams(param);

//            rel.setLayoutParams(param);
            rel.addView(txtView);
            rel.addView(cb);
            rel.addView(bt);
            ll.addView(rel);
        }

        Button back = new Button(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity=(1);
        back.setLayoutParams(params);
        back.setText("Back");
        back.setBackgroundColor(Color.parseColor("#000000"));
        back.setTextColor(Color.parseColor("#FFFFFF"));
        back.setWidth(100);
        back.setHeight(50);
//        params.setMargins(0,15,0,0);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back(v);
            }
        });
        ll.addView(back);
        this.setContentView(sv);

    }



    public void back(View view){
        MainActivity main =new MainActivity();
        main.updateView();
        this.finish();
    }
}

package com.example.passwordmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Add extends AppCompatActivity {
    //Create global Database manager
    private DatabaseManager dbManager;
    MainActivity main =new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager=new DatabaseManager(this);
        updateView();
    }
    //This method updates the view of the screen on the phone
    private void updateView() {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout setText=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setText.setOrientation(LinearLayout.VERTICAL);
        //Text views and edit texts for user to interact with on the screen
        TextView userView = new TextView(this);
        userView.setText("Username");
        userView.setTextSize(20);
        userView.setTextColor(Color.parseColor("#000000"));
        userView.setBackgroundColor(Color.parseColor("#838383"));
        setText.addView(userView);
        userView.setWidth(180);

        EditText userEdit = new EditText(this);
        userEdit.setHint(" ");
        int id=1;
        userEdit.setId(id);
        userEdit.setWidth(300);
        userEdit.setBackgroundColor(Color.parseColor("#838383"));
        setText.addView(userEdit);

        TextView passwordView = new TextView(this);
        passwordView.setText("Password");
        passwordView.setTextSize(20);
        passwordView.setBackgroundColor(Color.parseColor("#838383"));
        passwordView.setTextColor(Color.parseColor("#000000"));
        setText.addView(passwordView);

        passwordView.setWidth(180);

        EditText passwordEdit = new EditText(this);
        passwordEdit.setHint(" ");
        passwordEdit.setBackgroundColor(Color.parseColor("#838383"));
        passwordEdit.setWidth(300);

        id++;
        passwordEdit.setId(id);
        setText.addView(passwordEdit);
        ll.addView(setText);

        Button add = new Button(this);
        LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.gravity=(1);
        add.setLayoutParams(params2);
        add.setText("ADD");
        add.setBackgroundColor(Color.parseColor("#000000"));
        add.setTextColor(Color.parseColor("#FFFFFF"));
        add.setWidth(100);
        add.setHeight(50);
        params2.setMargins(0,15,0,0);
        //Sends information to the database
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int idd=1;
                EditText location=(EditText)findViewById(idd);
                idd++;
                EditText pass = (EditText) findViewById(idd);
                if(!(location.getText().toString().equals("")) && !(pass.getText().toString().equals(""))) {
                    Account account = new Account();
                    account.setLocation(location.getText().toString().toLowerCase());
                    account.setPassword(pass.getText().toString());
                    dbManager.insert(account);
                    userEdit.setText("");
                    passwordEdit.setText("");
                }
            }
        });
        ll.addView(add);
        //Creates the button to go back
        Button back = new Button(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity=(1);
        back.setLayoutParams(params);
        back.setText("Back");
        back.setBackgroundColor(Color.parseColor("#000000"));
        back.setTextColor(Color.parseColor("#FFFFFF"));
        back.setWidth(100);
        back.setHeight(50);
        params.setMargins(0,15,0,0);
        //Sends you back to the main page
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back(v);
            }
        });
        ll.addView(back);
        this.setContentView(ll);

    }
    //Code for the back button, to go back to the main screen
    public void back(View view) {
        main.updateView();
        this.finish();
    }
}

package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    int height;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        dbManager=new DatabaseManager(this);
        updateView();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ADD:
                Intent add = new Intent(this, Add.class);
                //passes new tuition object
                startActivity(add);
//                updateView();
                return true;
            case R.id.DELETE:
                Intent delete = new Intent(this, delete.class);
                //passes new tuition object
                startActivity(delete);
//                updateView();
                return true;
            case R.id.UPDATE:
                Intent update = new Intent(this, update.class);
                //passes new tuition object
                startActivity(update);
//                updateView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void updateView() {
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;

        LinearLayout main = new LinearLayout(this);
        main.setBackgroundColor(Color.parseColor("#000000"));
        main.setOrientation(LinearLayout.VERTICAL);
        Toolbar myToolbar = new Toolbar(this);
        myToolbar.setBackgroundColor(Color.parseColor("#000000"));
        setSupportActionBar(myToolbar);
        main.addView(myToolbar);
        ScrollView sv = new ScrollView(this);
        ScrollView.LayoutParams params3= new ScrollView.LayoutParams(ScrollView.LayoutParams.FILL_PARENT,ScrollView.LayoutParams.FILL_PARENT);
        sv.setLayoutParams(params3);
        main.addView(sv);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        TableLayout table=new TableLayout(this);
        TableLayout.LayoutParams params= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
        table.setLayoutParams(params);
        TableLayout.LayoutParams params2= new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
//        params2.weight= (float) .5;
        params2.width=width;
        params2.height=width/2;

//        params2.topMargin=width/10;
//        params2.rightMargin=5;
        params2.bottomMargin=5;
        table.setLayoutParams(params);
        TableRow row;
        LinkedList<Account> list=dbManager.all();
        int counter=0;
        for(int i = 0; i < list.size(); i=i+2) {

            row=new TableRow(this);

            row.setLayoutParams(params2);

            TextView txt=new TextView(this);
            Account account=list.get(i);
            txt.setText(account.getLocation().toUpperCase()+"\n"+account.getPassword());
            txt.setGravity(1);
            txt.setPadding(0,width/4,0,0);

            txt.setMinWidth((int) (width/2));
            if(counter%2==0) {
                txt.setBackgroundColor(Color.parseColor("#FF6E00"));
                txt.setTextColor(Color.parseColor("#000000"));
            }else{
                txt.setBackgroundColor(Color.parseColor("#000000"));
                txt.setTextColor(Color.parseColor("#ffffff"));
            }
            txt.setMinHeight(width/2);
            row.addView(txt);

            TextView spacer=new TextView(this);
            spacer.setMinWidth(5);
            spacer.setMinHeight(width/2);
            row.addView(spacer);
//            if(list.size()%2==0) {
            try {


                TextView txt2 = new TextView(this);
                Account account2 = list.get(i + 1);
                txt2.setText(account2.getLocation().toUpperCase() + "\n" + account2.getPassword());
                txt2.setGravity(1);
                txt2.setMinWidth((int) (width / (2)));
                if (counter % 2 == 0) {
                    txt2.setBackgroundColor(Color.parseColor("#000000"));
                    txt2.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    txt2.setBackgroundColor(Color.parseColor("#FF6E00"));
                    txt2.setTextColor(Color.parseColor("#000000"));
                }
                txt2.setMinHeight(width / 2);
                txt2.setPadding(0, width / 4, 0, 0);

                row.addView(txt2);
            }catch (Exception e){

            }
//            }
            counter++;
            table.addView(row);
        }


        ll.addView(table);
        this.setContentView(main);

    }
}
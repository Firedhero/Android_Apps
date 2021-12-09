package com.example.aiproject;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class AppInterface extends GridLayout
{
    private int size;
    private EditText[][] squares;
    private TextView status;
    private int[][]board;
    private final String FILE_NAME = "DatabaseFile";
    private boolean[][]isEditable;
    private boolean[][]isEditable2;
    int[][]answerBoard;
    public AppInterface(Context context, int size, int width,game gm) {
        super(context);
        board = gm.getBoard();
        this.size = size;
        setRowCount(size + 1);
        setColumnCount(size);
        squares = new EditText[size][size];
        int id=0;
        //creates all of the Edit Texts
        RelativeLayout b=new RelativeLayout(context);
        RelativeLayout d=new RelativeLayout(context);
//        LinearLayout b=new LinearLayout(context);

        GridLayout grid=new GridLayout(context);
        grid.setColumnCount(size);
        grid.setRowCount(size);
        grid.setId('1');
        isEditable=new boolean[size][size];
        isEditable2=new boolean[size][size];
        for (int i = 0; i < size+1; i++) {
            for (int j = 0; j < size; j++) {
                if(i<size && j<size) {
                    id++;
                    squares[i][j] = new EditText(context);
                    squares[i][j].setId(id);
                    squares[i][j].setBackgroundColor(Color.parseColor("#009900"));
                    squares[i][j].setTextColor(Color.BLACK);
                    squares[i][j].setTextSize((int) (width * 0.2));
                    squares[i][j].setGravity(Gravity.CENTER);
                    squares[i][j].setMaxLines(1);
                    //sets the maximum amount of
                    int maxLength = 1;
                    InputFilter[] fArray = new InputFilter[1];
                    fArray[0] = new InputFilter.LengthFilter(maxLength);
                    squares[i][j].setFilters(fArray);
                    LayoutParams params = new LayoutParams();
                    params.rowSpec = GridLayout.spec(i, 1);
                    params.columnSpec = GridLayout.spec(j, 1);
                    params.width = width;
                    params.height = width;
                    params.topMargin = params.bottomMargin = 1;
                    params.leftMargin = params.rightMargin = 1;
                    squares[i][j].setLayoutParams(params);
                    grid.addView(squares[i][j]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(board[i][j]);
                    setButtonText(sb.toString(), i, j);
                    int finalI = i;
                    int finalJ = j;
                    //watches the text and checks and changes values if valid
                    squares[i][j].addTextChangedListener(new TextWatcher() {
                        public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

                        public void afterTextChanged(Editable e) {
//                            EditText ee = (EditText) findViewById(squares[finalI][finalJ].getId());
                            StringBuilder sg = new StringBuilder();
                            sg.append(e);
                            int val = -1;
                            try {
                                //changes the val from string to int
                                val = Integer.parseInt(sg.toString());
                                if (gm.check(val, finalI, finalJ)) {
                                    gm.set(val, finalI, finalJ);
                                    squares[finalI][finalJ].setText(val);
                                    board[finalI][finalJ] = val;
                                } else {
                                    squares[finalI][finalJ].setText("");
                                }
                            } catch (Exception E) {
                            }
                            //shows u won
                            int duration = Toast.LENGTH_LONG;
                        }
                    });
                }
                else{
                }
            }
        }
        b.addView(grid);
//        addView(b);
        Button b1,b2,b3,b4,b5;
//        for(int i=0;i<3;i++) {
        //left button
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params2.addRule(RelativeLayout.ALIGN_BOTTOM, grid.getId());
        params2.width = width * 2;
        params2.height = (int) (width * (1.5));
//        params2.topMargin = params2.bottomMargin = 1;
        params2.leftMargin=180;
//        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params2.topMargin=1100;
        b1 = new Button(context);
        b1.setId('1');
        b1.setLayoutParams(params2);
        b1.setText("NEW");
        d.addView(b1);
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                int id=0;
                gm.newGame();
                board=gm.getBoard();
                for (int i = 0; i < size+1; i++) {
                    for (int j = 0; j < size; j++) {
                        answerBoard=new int[size][size];
                        if(i<size && j<size) {
                            id++;
                            squares[i][j] = new EditText(context);
                            squares[i][j].setId(id);
                            squares[i][j].setBackgroundColor(Color.parseColor("#009900"));
                            squares[i][j].setTextColor(Color.BLACK);
                            squares[i][j].setTextSize((int) (width * 0.2));
                            squares[i][j].setGravity(Gravity.CENTER);
                            squares[i][j].setMaxLines(1);
                            //sets the maximum amount of
                            int maxLength = 1;
                            InputFilter[] fArray = new InputFilter[1];
                            fArray[0] = new InputFilter.LengthFilter(maxLength);
                            squares[i][j].setFilters(fArray);
                            LayoutParams params = new LayoutParams();
                            params.rowSpec = GridLayout.spec(i, 1);
                            params.columnSpec = GridLayout.spec(j, 1);
                            params.width = width;
                            params.height = width;
                            params.topMargin = params.bottomMargin = 1;
                            params.leftMargin = params.rightMargin = 1;
                            squares[i][j].setLayoutParams(params);
                            grid.addView(squares[i][j]);
                            StringBuilder sb = new StringBuilder();
                            sb.append(board[i][j]);
                            setButtonText(sb.toString(), i, j);
                            int finalI = i;
                            int finalJ = j;
                            //watches the text and checks and changes values if valid
                            squares[i][j].addTextChangedListener(new TextWatcher() {
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                }

                                public void beforeTextChanged(CharSequence s, int start,
                                                              int count, int after) {
                                }

                                public void afterTextChanged(Editable e) {

//                                    EditText ee = (EditText) findViewById(squares[finalI][finalJ].getId());
                                    StringBuilder sg = new StringBuilder();
                                    sg.append(e);
                                    int val = -1;
                                    try {
                                        //changes the val from string to int
                                        val = Integer.parseInt(sg.toString());
                                        if (gm.check(val, finalI, finalJ)) {
                                            gm.set(val, finalI, finalJ);
                                            squares[finalI][finalJ].setText(val);
                                            board[finalI][finalJ] = val;
                                        } else {
                                            squares[finalI][finalJ].setText("");
                                        }
                                    } catch (Exception E) {

                                    }
                                    //shows u won
                                    int duration = Toast.LENGTH_LONG;

                                }
                            });
                        }
                        else{


                        }


                    }
                }

            }
        });
        //-------------middle button
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params3.addRule(RelativeLayout.BELOW, grid.getId());
        params3.width = width * 2;
        params3.height = (int) (width * (1.5));
        params3.topMargin = params3.bottomMargin = 1;
        params3.leftMargin = params3.rightMargin = 1;
        b2 = new Button(context);
        params3.addRule(RelativeLayout.CENTER_HORIZONTAL);
        b2.setId('z');
        params3.addRule(RelativeLayout.RIGHT_OF,b1.getId());
        params3.addRule(RelativeLayout.ALIGN_TOP,b1.getId());
//        params3.topMargin=100;
        b2.setLayoutParams(params3);
        b2.setText("SAVE");
        d.addView(b2);
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                FileOutputStream fout=null;
                FileOutputStream boo=null;
//                OutputStreamWriter oW=null;
                try {
                    StringBuilder sb;
                    context.deleteFile(FILE_NAME);
                    context.deleteFile("bool");
                    fout = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE | Context.MODE_APPEND);
                    boo = context.openFileOutput("bool", Context.MODE_PRIVATE | Context.MODE_APPEND);
//                    oW= new OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE| Context.MODE_APPEND));
                    for(int i=0;i<size;i++){
                        String total="";
                        for(int j=0;j<size;j++){
                            isEditable2[i][j]=isEditable[i][j];
                            if(isEditable[i][j]){
                                boo.write(1);
                            }else{
                                boo.write(0);
                            }

                            sb=new StringBuilder();
                            String txt;
                            try {
                                txt = squares[i][j].getText().toString();

                            }catch (Exception e){
                                txt="0";
                            }
                            if(txt.equals(""))
                                txt="0";
                            sb.append(txt+"");
                            total=total+" "+txt+" ";
                            fout.write(Integer.parseInt(sb.toString()));
                        }
                        System.out.println(total);
                    }

                    fout.close();
//                    oW.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        //----------------right button
        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params4.addRule(RelativeLayout.BELOW, grid.getId());
        params4.width = width * 2;
        params4.height = (int) (width * (1.5));
        params4.topMargin = params4.bottomMargin = 1;
        params4.leftMargin = params4.rightMargin = 1;
//        params4.topMargin=950;
        b3 = new Button(context);
        params4.addRule(RelativeLayout.RIGHT_OF,b2.getId());
        params4.addRule(RelativeLayout.ALIGN_TOP,b2.getId());
        b3.setLayoutParams(params4);
        b3.setText("RESUME");
        d.addView(b3);
        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FileInputStream fin = null;
                FileInputStream boo = null;
                String ret = "";
                int[][]newBoard = null;
                try {
                    //-------------

                    fin = context.openFileInput(FILE_NAME);
                    boo= context.openFileInput("bool");
                    InputStreamReader inputStreamReader = new InputStreamReader(fin);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    InputStreamReader isr = new InputStreamReader(boo);
                    BufferedReader br = new BufferedReader(isr);
//                    String array=bufferedReader.readLine();
//                    String[]numberS=array.split(" ");
                    int line;
                    String total = "";
                    newBoard = board;
                    //Reads from the boolean file to see which ones are able to edit
                    for(int i=0;i<size;i++){
                        for(int j=0;j<size;j++) {
                            if(br.read()==1) {
                                isEditable[i][j] =true;
                            }else {
                                isEditable[i][j]=false;
                            }
                        }
                    }
                    //reads from the stored sudoku board and stores them in a new board
                    for(int i=0;i<size;i++){
                        total="";
                        for(int j=0;j<size;j++) {

                            StringBuilder sb = new StringBuilder();
                            sb.append(bufferedReader.read());
                            total = total + sb.toString()+" ";
                            newBoard[i][j] = Integer.valueOf(sb.toString());
                        }
//                        total = total + "\n";
                        System.out.println(total);
                        System.out.println("--------------------------------");
                    }
                    fin.close();

                    //------------------------

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Rebuild Board HERE -------------------
                gm.newGame();
                board = newBoard;
                int id = 0;
                for (int i = 0; i < size+1; i++) {
                    for (int j = 0; j < size; j++) {
                        if(i<size && j<size) {
                            id++;
                            squares[i][j] = new EditText(context);
                            squares[i][j].setId(id);
                            squares[i][j].setBackgroundColor(Color.parseColor("#009900"));
                            squares[i][j].setTextColor(Color.BLACK);
                            squares[i][j].setTextSize((int) (width * 0.2));
                            squares[i][j].setGravity(Gravity.CENTER);
                            squares[i][j].setMaxLines(1);
                            //sets the maximum amount of
                            int maxLength = 1;
                            InputFilter[] fArray = new InputFilter[1];
                            fArray[0] = new InputFilter.LengthFilter(maxLength);
                            squares[i][j].setFilters(fArray);
                            LayoutParams params = new LayoutParams();
                            params.rowSpec = GridLayout.spec(i, 1);
                            params.columnSpec = GridLayout.spec(j, 1);
                            params.width = width;
                            params.height = width;
                            params.topMargin = params.bottomMargin = 1;
                            params.leftMargin = params.rightMargin = 1;
                            squares[i][j].setLayoutParams(params);
                            grid.addView(squares[i][j]);
                            StringBuilder sb = new StringBuilder();
                            sb.append(board[i][j]);
                            setButtonTextNew(sb.toString(), i, j);
                            int finalI = i;
                            int finalJ = j;
                            //watches the text and checks and changes values if valid
                            squares[i][j].addTextChangedListener(new TextWatcher() {
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                }

                                public void beforeTextChanged(CharSequence s, int start,
                                                              int count, int after) {
                                }

                                public void afterTextChanged(Editable e) {

                                    EditText ee = (EditText) findViewById(squares[finalI][finalJ].getId());
                                    StringBuilder sg = new StringBuilder();
                                    sg.append(e);
                                    int val = -1;
                                    try {
                                        //changes the val from string to int
                                        val = Integer.parseInt(sg.toString());
                                        if (gm.check(val, finalI, finalJ)) {
                                            gm.set(val, finalI, finalJ);
                                            squares[finalI][finalJ].setText(val);
                                            board[finalI][finalJ] = val;
                                        } else {
                                            squares[finalI][finalJ].setText("");
                                        }
                                    } catch (Exception E) {

                                    }
                                    //shows u won
                                    int duration = Toast.LENGTH_LONG;

                                }
                            });
                        }
                        else{
                        }
                    }
                }
            }
        });
//        }
        //----------------Ai button
        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params4.addRule(RelativeLayout.BELOW, grid.getId());
        params5.width = width * 2;
        params5.height = (int) (width * (1.5));
        params5.topMargin = params5.bottomMargin = 1;
        params5.leftMargin = params5.rightMargin = 1;
//        params4.topMargin=950;
        b4 = new Button(context);
        params5.addRule(RelativeLayout.BELOW,b2.getId());
        params5.addRule(RelativeLayout.ALIGN_RIGHT,b2.getId());
        b4.setLayoutParams(params5);
        b4.setId('4');
        b4.setText("AI");
        d.addView(b4);
//        int[][]temp=board;
        b4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                solverAi ai=new solverAi();
                if(!ai.solver(board,isEditable)){
                        Toast toast=new Toast(context);
                        toast.setText("No Solution");
                        toast.show();
                }else {

                    changes=ai.getColorChange();


                    try {

                        int line;
                        String total = "";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Rebuild Board HERE -------------------
                    gm.newGame();
                    board = ai.newBoard();
                    int id = 0;
                    for (int i = 0; i < size + 1; i++) {
                        for (int j = 0; j < size; j++) {
                            if (i < size && j < size) {
                                id++;
                                squares[i][j] = new EditText(context);
                                squares[i][j].setId(id);
                                squares[i][j].setBackgroundColor(Color.parseColor("#009900"));
                                squares[i][j].setTextColor(Color.BLACK);
                                squares[i][j].setTextSize((int) (width * 0.2));
                                squares[i][j].setGravity(Gravity.CENTER);
                                squares[i][j].setMaxLines(1);
                                //sets the maximum amount of
                                int maxLength = 1;
                                InputFilter[] fArray = new InputFilter[1];
                                fArray[0] = new InputFilter.LengthFilter(maxLength);
                                squares[i][j].setFilters(fArray);
                                LayoutParams params = new LayoutParams();
                                params.rowSpec = GridLayout.spec(i, 1);
                                params.columnSpec = GridLayout.spec(j, 1);
                                params.width = width;
                                params.height = width;
                                params.topMargin = params.bottomMargin = 1;
                                params.leftMargin = params.rightMargin = 1;
                                squares[i][j].setLayoutParams(params);
                                grid.addView(squares[i][j]);
                                StringBuilder sb = new StringBuilder();
                                sb.append(board[i][j]);
                                setButtonTextAi(sb.toString(), i, j, changes);
                                int finalI = i;
                                int finalJ = j;
                                //watches the text and checks and changes values if valid
                                squares[i][j].addTextChangedListener(new TextWatcher() {
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    }

                                    public void beforeTextChanged(CharSequence s, int start,
                                                                  int count, int after) {
                                    }

                                    public void afterTextChanged(Editable e) {

                                        EditText ee = (EditText) findViewById(squares[finalI][finalJ].getId());
                                        StringBuilder sg = new StringBuilder();
                                        sg.append(e);
                                        int val = -1;
                                        try {
                                            //changes the val from string to int
                                            val = Integer.parseInt(sg.toString());
                                            if (gm.check(val, finalI, finalJ)) {
                                                gm.set(val, finalI, finalJ);
                                                squares[finalI][finalJ].setText(val);
                                                board[finalI][finalJ] = val;
                                            } else {
                                                squares[finalI][finalJ].setText("");
                                            }
                                        } catch (Exception E) {

                                        }
                                        //shows u won
                                        int duration = Toast.LENGTH_LONG;
                                    }
                                });
                            } else {
                            }
                        }
                    }
                }
            }
        });
        /*
        was attempting to provide hints to the user
        but it's unusable along with the ai solver
         */
        /*
        RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params4.addRule(RelativeLayout.BELOW, grid.getId());
        params6.width = width * 2;
        params6.height = (int) (width * (1.5));
        params6.topMargin = params6.bottomMargin = 1;
        params6.leftMargin = params6.rightMargin = 1;
//        params4.topMargin=950;
        b5 = new Button(context);
        params6.addRule(RelativeLayout.BELOW,b2.getId());
        params6.addRule(RelativeLayout.RIGHT_OF,b4.getId());
        b5.setLayoutParams(params6);
        b5.setText("HINT");
        d.addView(b5);
        solverAi hint=new solverAi();
        answerBoard=new int[size][size];

        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!hint.solver(board,isEditable)) {
                    Toast toast = new Toast(context);
                    toast.setText("No Solution");
                    toast.show();
                }
                    //Gives random hint
                    if(answerBoard[0][0]==0) {
                        answerBoard = hint.newBoard();
                    }
                    Random rand=new Random();
                    int x= rand.nextInt(8);
                    int y= rand.nextInt(8);
                    while(!isEditable[x][y]){
                        x= rand.nextInt(8);
                        y= rand.nextInt(8);
                    }
                    System.out.println("x: "+x+" y: "+y+" value: "+answerBoard[x][y]);
//                    StringBuilder sb=new StringBuilder();
//                    sb.append(answerBoard[x][y]);
                int num=answerBoard[x][y];
                    board[x][y]=num;
                    int id=0;
//                    StringBuilder s=new StringBuilder();
//                    s.append(num);
//                    setButtonTextNew(s.toString(),x,y);
                for (int i = 0; i < size+1; i++) {
                    for (int j = 0; j < size; j++) {
                        if(i<size && j<size) {
                            id++;
                            squares[i][j] = new EditText(context);
                            squares[i][j].setId(id);
                            squares[i][j].setBackgroundColor(Color.parseColor("#009900"));
                            squares[i][j].setTextColor(Color.BLACK);
                            squares[i][j].setTextSize((int) (width * 0.2));
                            squares[i][j].setGravity(Gravity.CENTER);
                            squares[i][j].setMaxLines(1);
                            //sets the maximum amount of
                            int maxLength = 1;
                            InputFilter[] fArray = new InputFilter[1];
                            fArray[0] = new InputFilter.LengthFilter(maxLength);
                            squares[i][j].setFilters(fArray);
                            GridLayout.LayoutParams params = new LayoutParams();
                            params.rowSpec = GridLayout.spec(i, 1);
                            params.columnSpec = GridLayout.spec(j, 1);
                            params.width = width;
                            params.height = width;
                            params.topMargin = params.bottomMargin = 1;
                            params.leftMargin = params.rightMargin = 1;
                            squares[i][j].setLayoutParams(params);
                            grid.addView(squares[i][j]);
                            StringBuilder sb2 = new StringBuilder();
                            if(i==x &&j==y){
                                sb2.append(num);
                            }else
                                sb2.append(board[i][j]);
                            setButtonTextNew(sb2.toString(), i, j);
                            int finalI = i;
                            int finalJ = j;
                            //watches the text and checks and changes values if valid
                            squares[i][j].addTextChangedListener(new TextWatcher() {
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                }

                                public void beforeTextChanged(CharSequence s, int start,
                                                              int count, int after) {
                                }

                                public void afterTextChanged(Editable e) {

                                    EditText ee = (EditText) findViewById(squares[finalI][finalJ].getId());
                                    StringBuilder sg = new StringBuilder();
                                    sg.append(e);
                                    int val = -1;
                                    try {
                                        //changes the val from string to int
                                        val = Integer.parseInt(sg.toString());
                                        if (gm.check(val, finalI, finalJ)) {
                                            gm.set(val, finalI, finalJ);
                                            squares[finalI][finalJ].setText(val);
                                            board[finalI][finalJ] = val;
                                        } else {
                                            squares[finalI][finalJ].setText("");
                                        }
                                    } catch (Exception E) {

                                    }
                                    //shows u won
                                    int duration = Toast.LENGTH_LONG;

                                }
                            });
                        }
                        else{
                        }
                    }
                }
//
//
                }
//
        });
*/
        TextView txt=new TextView(context);
        txt.setBackgroundColor(Color.parseColor("#A4A6A6"));
        RelativeLayout.LayoutParams params8 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params8.width = width;
//        params8.height = (int) (width * (1.5));
        params8.topMargin = params8.bottomMargin = 1;
        txt.setPadding(20,0,0,0);



        SeekBar difficultyBar=new SeekBar(context);
        RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        difficultyBar.setMax(10);
        difficultyBar.setProgress(4);
        difficultyBar.setMinimumWidth((int) (width/1.5));
//        params7.height = (int) (width * (1.5));
        params7.width=width*5;
        params7.topMargin = params7.bottomMargin = 30;
        params7.addRule(RelativeLayout.BELOW,b4.getId());
        params7.addRule(RelativeLayout.CENTER_HORIZONTAL);
        difficultyBar.setLayoutParams(params7);
        difficultyBar.setId('8');

        params8.addRule(RelativeLayout.ALIGN_BOTTOM,difficultyBar.getId());
        params8.addRule(RelativeLayout.RIGHT_OF,difficultyBar.getId());
        txt.setTextColor(Color.parseColor("#000000"));
        txt.setText("0.4");
        txt.setLayoutParams(params8);
        d.addView(difficultyBar);
        difficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gm.setDifficulty(Double.parseDouble(String.valueOf(progress*.1)));
                txt.setText(String.valueOf(progress*.1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        d.addView(txt);
        //adds the view to the overall view
        b.addView(d);
        //adds the view storing views to view to be displayed
        addView(b);
    }
    public void setButtonText(String text, int row, int column)
    {   //makes it so the filled and unfilled are different colors
        if(text.equals("0")){
            squares[row][column].setBackgroundColor(Color.parseColor("#FF018786"));
            isEditable[row][column]=true;
        }else {
            squares[row][column].setText(text);
            isEditable[row][column]=false;
            squares[row][column].setEnabled(false);
        }
    }
    public void setButtonTextNew(String text, int row, int column)
    {   //makes it so the filled and unfilled are different colors
        // also checks to see if it was editable before and makes it so
        if(isEditable[row][column]){
            if (text.equals("0"))
                text="";
            squares[row][column].setEnabled(true);
            isEditable[row][column]=true;
            squares[row][column].setText(text);

                squares[row][column].setBackgroundColor(Color.parseColor("#FF018786"));
        }else {
            squares[row][column].setText(text);
            squares[row][column].setEnabled(false);
        }
    }
    public void setButtonTextAi(String text, int row, int column,int[][]changes)
    {   //makes it so the filled and unfilled are different colors
        // also checks to see if it was editable before and makes it so the color is different if your guess was wrong
        if(isEditable[row][column]){
            if (text.equals("0"))
                text="";
            squares[row][column].setEnabled(true);
            isEditable[row][column]=true;
            squares[row][column].setText(text);
            if(changes[row][column]==1){
                squares[row][column].setBackgroundColor(Color.parseColor("#FF0000"));
            }else
                squares[row][column].setBackgroundColor(Color.parseColor("#FF018786"));
        }else {
            squares[row][column].setText(text);
            squares[row][column].setEnabled(false);
        }
    }

    double diff=0.4;
    int[][]changes;

}

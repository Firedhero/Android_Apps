package com.example.aiproject;

public class game {

    Sudoku sudo;
    int[][] board;
    solverAi ai =new solverAi();
    double diff=0.4;
    public game(){

        sudo=new Sudoku();
        board=sudo.generate(diff);
//        if(!ai.boardCheck(board)){
//            newGame();
//        }
    }
    public void newGame(){
        sudo=new Sudoku();
        board=sudo.generate(diff);
//        if(!ai.boardCheck(board)){
//            newGame();
//        }
    }
    public boolean checkValue(){
        boolean good=true;
        return good;
    }
    public void setDifficulty(double difficulty){
        diff=difficulty;
    }
    public void set(int value,int x,int y){
            board[x][y] = value;
    }
    public boolean check(int value, int x, int y){
        boolean done = true;
        //Check column
        for (int i =0;i<9;i++){
            if (board[i][y] == value){
                done = false;
            }
        }
        //Check row
        for (int j=0;j<9; j++){
            if (board[x][j] == value){
                done = false;
            }
        }
        //3x3 check
        int row = 0;
        int col = 0;
        //top squares
        if(x <=2 && y <=2){
            row = 0;
            col = 0;
        }
        else if(x >=3 && x <=5 && y <=2){
            row = 0;
            col = 3;
        }
        else if(x >=6 && x <=8 && y <=2){
            row = 0;
            col = 6;
        }
        //middle squares
        else if(x <=2 && y >=2 && y <=5){
            row = 3;
            col = 0;
        }
        else if(x >=3 && x <=5 && y >=3 && y <=5){
            row = 3;
            col = 3;
        }
        else if(x >=6 && x <=8 && y >=3 && y <=5){
            row = 3;
            col = 6;
        }
        //bottom squares
        else if(x <=2 && y >=6 && y <=8){
            row = 6;
            col = 0;
        }
        else if(x >=3 && x <=5 && y >=6 && y <=8){
            row = 6;
            col = 3;
        }
        else if(x >=6 && x <=8 && y >=6 && y <=8){
            row = 6;
            col = 6;
        }
        //cycles through the selected area and returns false if fails
        for (int rw = row; rw < row+3; rw++){
            for (int cl = col; cl < col+3; cl++){
                if(board[cl][rw] == value){
                    done = false;
                }
            }
        }
        return done;
    }
    public boolean isDone(){
        boolean done=true;
        //for testing
//        for(int i=0;i<9;i++){
//            for(int j=0;j<9;j++){
//                System.out.print(board[i][j]+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    return false;
                }
            }
        }

        return done;

    }
    // gets the board
    public int[][] getBoard(){
        return board;
    }







}

package com.example.aiproject;


public class solverAi {

    boolean backTracking(int board[][], int row, int col) {

        // ends backtracking
        if (row == SIZE - 1 && col == SIZE)
            return true;
        //iterate row when at end of the column
        if (col == SIZE) {
            row++;
            col = 0;
        }
        //if a number already exists here and if it is not editable then number set in stone so continue
        if (board[row][col] != 0 && !isEditable[row][col])
            return backTracking(board, row, col + 1);

                //i = 1...9
        for (int i = 1; i < 10; i++) {
            //if i not valid numbers continue
            if (isValid(board, row, col, i)) {
                board[row][col] = i;
                //move to next placement
                if (backTracking(board, row, col + 1))
                    return true;
            }
            //if not true resets value
            board[row][col] = 0;
        }
        //only reaches here if the board has no solution
        return false;
    }
    //to print out board to see if it worked
    static void print(int[][] board){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++){
                System.out.print(board[i][j] + " ");
                }
            System.out.println();
        }
    }
    static boolean isValid(int[][] board, int x, int y,int value){

        //Check column
        for (int i =0;i<9;i++){
            if (board[i][y] == value){
                return false;
            }
        }
        //Check row
        for (int j=0;j<9; j++){
            if (board[x][j] == value){
                return false;
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
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solver(int[][] board, boolean[][] isEditable) {
        this.board=board;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                tempGrid[i][j]=board[i][j];
            }
        }

        boolean solution=false;
        this.isEditable=isEditable;
        if (backTracking(board, 0, 0)) {
            solution=true;
        }
        else {
            System.out.println("No Solution exists");
        }
        //check and see if user had wrong asnwer
        //to check and see if there is a difference between soloution board and original board
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){

                if(this.board[i][j]!=tempGrid[i][j] && isEditable[i][j] && tempGrid[i][j]!=0){
                    colorChange[i][j]=1;
                }else {
                    colorChange[i][j]=0;
                }
            }
        }
        return solution;
    }
    public boolean boardCheck(int[][] board) {
        this.board=board;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                tempGrid[i][j]=board[i][j];
            }
        }

        boolean solution=false;

        if (backTracking(board, 0, 0)) {
            solution=true;
        }
        else {
            System.out.println("No Solution exists");
        }
        return solution;
    }

    public int[][] newBoard(){
        return board;
    }
    public int[][] getColorChange(){
        return colorChange;
    }
    int[][] colorChange=new int[9][9];
    int[][] board;
    int[][] tempGrid=new int[9][9];
    static boolean[][]isEditable;
    static int SIZE = 9;
}

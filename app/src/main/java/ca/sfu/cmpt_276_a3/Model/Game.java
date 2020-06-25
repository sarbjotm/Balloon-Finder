package ca.sfu.cmpt_276_a3.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    //TODO: verification of game end with every click, testing
    //default settings for the length, width, and # of mines
    private int row = 4;
    private int column = 6;
    private int mineGoal = 6;

    /**
     * Enum Values:
     * isEmpty = 0
     * isMine = 1
     */

    //count variables
    private int mineCount = 0;
    private int scanCount = 0;

    //var for mine spawns
    private Random random = new Random();
    private final int isEmpty = 0;
    private final int isMine = 1;

    //boolean var to check game state
    private boolean gameOver = false;

    //actual game arena
    private int[][] field = new int[row][column];
    private boolean[][] clicked = new boolean[row][column];//to register click
    private List<Location> mineLocations = new ArrayList<>();

    //constructor: this method must only be called once
    public Game(int row, int column, int mineGoal){
        //update boundaries
        this.row = row;
        this.column = column;
        this.mineGoal = mineGoal;

        //update field
        this.field = new int[row][column];
        this.clicked = new boolean[row][column];

        //spawn mine & clicker values
        spawnMines();
        initializeClicks();
    }

    //set all fields to false initially
    public void initializeClicks(){
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
                this.clicked[i][j] = false;
        }
    }

    /**
     * these printer codes are only for command line testing
     * to check if logic holds up
     */
    public void printGame(){
        // Loop through all rows
        for (int[] row : this.field)
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        System.out.println("Mines found = " + getMineCount()
                + " out of " + getMineGoal());
    }

    public void printClicked(){
        // Loop through all rows
        for (boolean[] row : this.clicked)
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
    /**
     * end of printer check codes
     */

    public void spawnMines(){
        int x, y;
        Location mineTemp;
        for(int i = 0; i < this.mineGoal; i++){

            do {
                x = random.nextInt(row - 1) + 1;
                y = random.nextInt(column - 1) + 1;
            } while (this.field[x][y] != isEmpty);
            this.field[x][y] = isMine;

            //store mine locations for use later on
            mineTemp = new Location(x, y);
            this.mineLocations.add(mineTemp);
        }

        System.out.println(row + ", " + column);//command line test code
    }

    public void registerClick(int x, int y){

        //check end of game case
        if(isGameOver())
            return;

        //condition to ignore mine cell if clicked already
        if(isMine(x, y) && (!isClicked(x, y)))
        {
            System.out.println("Mine here!");//command line check
            this.mineCount++;
            this.clicked[x][y] = true;
            return;
        }

        Location click = new Location(x, y);
        //set value for display later
        int minesFound = scan(x, y);
        this.scanCount++;
        click.setScanValue(minesFound);

        //command line check code
        System.out.println("Scanned mines within proximity = " + minesFound);//command line check
        System.out.println("Scan check = " + getScanCount());

        if(this.mineCount == this.mineGoal)
            this.gameOver = true;
    }

    public int scan(int x, int y){//helper method to scan for mines

        if(!isClicked(x, y)){
            this.clicked[x][y] = true;
        }
        int scanValue = 0;

        //check all column cells
        for(int i = 0; i < row; i++){
            if(isMine(i, y) && !isClicked(x, y))
                scanValue++;
        }

        //check all row cells
        for(int j = 0; j < column; j++){
            if(isMine(x, j) && !isClicked(x, j))
                scanValue++;
        }

        return scanValue;//return mines within proximity
    }

    public boolean isMine(int x, int y){//check if cell has mine
        if(this.field[x][y] == isMine)
            return true;
        else
            return false;
    }

    public boolean isClicked(int x, int y){//check if cell has been clicked
        return this.clicked[x][y];
    }

    public int getMineGoal(){ return this.mineGoal; }
    public int getMineCount(){ return this.mineCount; }
    public int getScanCount(){ return this.scanCount; }
    public boolean isGameOver(){ return this.gameOver; }
}

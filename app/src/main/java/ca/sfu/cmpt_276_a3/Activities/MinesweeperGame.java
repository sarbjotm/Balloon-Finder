package ca.sfu.cmpt_276_a3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


import java.util.ArrayList;
import java.util.List;

import ca.sfu.cmpt_276_a3.Model.Game;
import ca.sfu.cmpt_276_a3.Model.Location;
import ca.sfu.cmpt_276_a3.R;

/*

Helpful tips and tricks from Dr.Brian Fraser Videos


 */
@SuppressWarnings("IfStatementWithIdenticalBranches")
public class MinesweeperGame extends AppCompatActivity {
    private static int NUM_ROWS = 4;
    private static int NUM_COLS = 6;
    private static int NUM_MINES = 6;

    //Logic binding with array of buttons
    private Game game;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    List<Location> clickedLocations = new ArrayList<>();
    private int test = 0;//nani the fuck is this for?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_game);
        populateTable();
    }

    private void setUpMatrix(){
        int numRows = Integer.parseInt(String.valueOf
                (Settings.getMatrixSize(this).charAt(0)));
        NUM_ROWS = numRows;

        if (NUM_ROWS == 4){
            NUM_COLS = 6;
        }
        else if(NUM_ROWS == 5){
            NUM_COLS =10;
        }
        else{
            NUM_COLS = 15;
        }

    }

    @SuppressLint("SetTextI18n")
    private void populateTable(){

        int numMines = Settings.getMinesAmount(this);
        NUM_MINES = numMines;
        game = new Game(NUM_ROWS, NUM_COLS, NUM_MINES);

        TableLayout table = (TableLayout) findViewById(R.id.tableGame);
        for(int row = 0; row < NUM_ROWS; row++){

            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tablerow);

            for(int col = 0; col < NUM_COLS; col++){
                final int finalCol = col;
                final int finalRow = row;
                Button btn = new Button(this);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                btn.setText("" + row + "," + col);
                //Make text show on all types of buttons
                btn.setPadding(0,0,0,0);
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(finalRow,finalCol);
                    }
                });
                tablerow.addView(btn);
                buttons[row][col] = btn;
            }
        }//end of for loop
    }

    private void gridButtonClicked(int row, int col){

        Button button = buttons[row][col];
        Button tempButton;//to change scan values of previously clicked buttons

        //Prevents button being changed after image
        for(int rowAdjust = 0; rowAdjust < NUM_ROWS; rowAdjust++){
            for(int colAdjust = 0; colAdjust < NUM_COLS; colAdjust++){
                int width = button.getWidth();
                int height = button.getHeight();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }

        int newWidth = button.getWidth();
        int newHeight = button.getHeight();

        if(game.isMine(row, col)) {
            //adjust photo to change into balloon
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.balloon);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,
                    newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            //if mine location clicked again for scan
            if (game.isClicked(row, col)) {
                button.setText(game.scan(row, col) + "");

                //register mine location as clicked location to be scanned.
                Location clickedLocation = new Location(row, col);
                clickedLocation.setScanValue(game.scan(row, col));
                clickedLocations.add(clickedLocation);
            }

            //modify scan values of all other clicked locations in close proximity to it
            if (clickedLocations.size() > 0) {

                for (Location location : clickedLocations) {

                    //check clicked location in closest proximity to mine cell
                    if (location.getX() == row || location.getY() == col) {
                        tempButton = buttons[location.getX()][location.getY()];
                        int scanVal = game.scan(location.getX(), location.getY()) -
                                game.getMineCount();

                        //check scanVal cases
                        if (scanVal > 0)
                            tempButton.setText(scanVal + "");
                        else
                            tempButton.setText(0 + "");
                    }

                    else
                        continue;
                }//end of for loop

            }//end of adjusting scanValues

            //register the click in game
            game.registerClick(row, col);

            //game over condition
            if(game.isGameOver()){
                //change all button values
                for (Location location : clickedLocations) {
                    tempButton = buttons[location.getX()][location.getY()];
                    tempButton.setText(0 + "");
                }
                //TODO: put pop-up here, Amander
            }
        }
        else{//if not mine
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource));
            button.setText(game.scan(row, col) + "");

            Location clickedLocation = new Location(row, col);
            clickedLocation.setScanValue(game.scan(row, col));
            clickedLocations.add(clickedLocation);
            game.registerClick(row, col);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
            setUpMatrix();

    }

    public static Intent makeIntent(Context context){
        return new Intent(context,MinesweeperGame.class);
    }
}
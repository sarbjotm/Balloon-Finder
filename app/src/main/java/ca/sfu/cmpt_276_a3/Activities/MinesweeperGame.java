package ca.sfu.cmpt_276_a3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ca.sfu.cmpt_276_a3.MainActivity;
import ca.sfu.cmpt_276_a3.Model.Game;
import ca.sfu.cmpt_276_a3.Model.PopUp;
import ca.sfu.cmpt_276_a3.R;

/*

Helpful tips and tricks from Dr.Brian Fraser Videos


 */
@SuppressWarnings("IfStatementWithIdenticalBranches")
public class MinesweeperGame extends AppCompatActivity {
    private static int NUM_ROWS = 4;
    private static int NUM_COLS = 6;
    private static int NUM_MINES = 6;
    private static int scansUsed = 0;
    private int minesUsed = 0;
    private int goBack = 0;
    public static int times_played = 0;

    //Logic binding with array of buttons
    private Game game;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    List<Button> clickedButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_game);
        times_played = times_played + 1;
        TextView counter = (TextView) findViewById(R.id.timesPlayed);
        counter.setText(times_played+"");
        populateTable();
    }
    //Comment to fix push error
    private void setUpMatrix(){
        int numRows = Settings.getMatrixSize(this);
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
        Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    }

    @SuppressLint("SetTextI18n")
    private void populateTable(){

        int numMines = Settings.getMinesAmount(this);
//        setUpMatrix();
//        Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
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
                btn.setText("");
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
        TextView scansUsedText = (TextView) findViewById(R.id.textView6);
        TextView minesView = (TextView) findViewById(R.id.textViewMinesFound);
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

//            Toast.makeText(this,button.getBackground().toString(),Toast.LENGTH_LONG).show();
            if(button.getText().toString() == ""){
                scansUsed = scansUsed + 1;
                minesUsed = minesUsed + 1;
//                Toast.makeText(this,minesUsed +"", Toast.LENGTH_LONG).show();
                scansUsedText.setText(scansUsed + "");
                minesView.setText(minesUsed + "");
                button.setText(" ");
            }


            if(minesUsed == NUM_MINES){
                createDialog();

            }


        }
        else{//if not mine
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource));
            if (button.getText().toString() == ""){
                scansUsed = scansUsed + 1;
                scansUsedText.setText(scansUsed + "");
            }
            button.setText(game.scan(row, col) + "");

        }
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WINNER!")
                .setCancelable(false)
                .setMessage("Congrats on winning!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = WelcomeScreen.makeIntent(MinesweeperGame.this);
                        finish();

                    }
                });
        builder.create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
            scansUsed = 0;
            minesUsed = 0;
            setUpMatrix();




    }

    public static Intent makeIntent(Context context){
        return new Intent(context,MinesweeperGame.class);
    }
}
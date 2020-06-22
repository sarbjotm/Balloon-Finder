package ca.sfu.cmpt_276_a3;

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


import static android.widget.TableRow.*;
/*

Helpful tips and tricks from Dr.Brian Fraser Videos


 */
public class MinesweeperGame extends AppCompatActivity {
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 10;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_game);
        populateTable();
    }

    @SuppressLint("SetTextI18n")
    private void populateTable(){
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
        }
    }

    private void gridButtonClicked(int row, int col){
        Button button = buttons[row][col];

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
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
    public static Intent makeIntent(Context context){
        return new Intent(context,MinesweeperGame.class);
    }
}
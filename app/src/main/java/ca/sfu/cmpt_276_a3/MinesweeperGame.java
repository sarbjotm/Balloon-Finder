package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import static android.widget.TableRow.*;

public class MinesweeperGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minesweeper_game);
        populateTable();
    }

    @SuppressLint("SetTextI18n")
    private void populateTable(){
        TableLayout table = (TableLayout) findViewById(R.id.tableGame);
        for(int row = 0; row < 4; row++){
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);

            for(int col = 0; col < 3; col++){
                Button btn = new Button(this);
                tablerow.addView(btn);
                btn.setText("" + row + "," + col);
            }
        }
    }
    public static Intent makeIntent(Context context){
        return new Intent(context,MinesweeperGame.class);
    }
}
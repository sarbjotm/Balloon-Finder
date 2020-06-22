package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

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
        for(int row = 0; row < 10; row++){
            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tablerow);

            for(int col = 0; col < 7; col++){
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
                        gridButtonClicked();
                    }
                });
                tablerow.addView(btn);

            }
        }
    }

    private void gridButtonClicked(){
    }
    public static Intent makeIntent(Context context){
        return new Intent(context,MinesweeperGame.class);
    }
}
package ca.sfu.cmpt_276_a3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.sfu.cmpt_276_a3.R;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createRadiobuttonForMines();
        radiobuttonForMatrix();
        int savedMatrixValue = getMatrixSize(this);
        Toast.makeText(this,"Saved: " + savedMatrixValue, Toast.LENGTH_LONG).show();
        int savedMinesAmount = getMinesAmount(this);
    }


    private void createRadiobuttonForMines(){
        RadioGroup group2 = (RadioGroup)findViewById(R.id.radioGroupMines);
        int[] num_mines = getResources().getIntArray(R.array.num_of_mines);

        for(int j = 0; j < num_mines.length; j++){
            final int num_mine = num_mines[j];
            RadioButton button = button = new RadioButton(this);
            button.setText(num_mine +"");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveNumberMines(num_mine);
                }
            });
            group2.addView(button);
            if (num_mine == getMinesAmount(this)){
                button.setChecked(true);
            }
        }

    }

    private void radiobuttonForMatrix(){
        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroupBoard);
        int[] num_size_array = getResources().getIntArray(R.array.size_of_matrix);
        for(int j = 0; j < num_size_array.length; j++){
            final int num_size = num_size_array[j];
            RadioButton button = button = new RadioButton(this);
            if(num_size == 4){
                button.setText(num_size+"x6");
            }
            else if (num_size == 5){
                button.setText(num_size+"x10");
            }
            else{
                button.setText(num_size+"x15");
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveMatrixSize(num_size);
                }
            });
            group.addView(button);
            if (num_size == getMatrixSize(this)){
                button.setChecked(true);
            }
        }

    }


    private void saveNumberMines(int num_mine) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("MineAmount", num_mine);
        editor.apply();
    }

    static public int getMinesAmount(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.default_mines);
        return prefs.getInt("MineAmount", defaultValue);

    }

    private void saveMatrixSize(int num_size) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("MatrixSize", num_size);
        editor.apply();
    }




    static public int getMatrixSize(Context context){
        SharedPreferences prefs2 = context.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.default_size);
        return prefs2.getInt("MatrixSize",defaultValue);
    }



    public static Intent makeIntent(Context context){
        return new Intent(context,Settings.class);
    }
}
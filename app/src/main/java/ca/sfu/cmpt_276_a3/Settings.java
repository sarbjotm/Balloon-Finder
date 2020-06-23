package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createRadioButtonsForMatrix();
        createRadiobuttonForMines();
        String savedMatrixValue = getMatrixSize(this);
        Toast.makeText(this,"Saved: " + savedMatrixValue, Toast.LENGTH_LONG).show();
        int savedMinesAmount = getMinesAmount(this);
    }

    private void createRadioButtonsForMatrix(){
        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroupBoard);
        String[] matrixSize = getResources().getStringArray(R.array.rowCol);
        for(int i = 0; i < matrixSize.length; i++){
            final String matrix_size = matrixSize[i];
            RadioButton button = new RadioButton(this);
            button.setText(matrix_size);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveMatrixSize(matrix_size);
                }
            });
            group.addView(button);

            //Select default button
            if (matrix_size == getMatrixSize(this)){
                button.setChecked(true);
            }


        }
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

    private void saveMatrixSize(String matrix_size) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MatrixSize", matrix_size);
        editor.apply();
    }


    private void saveNumberMines(int num_mine) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("MineAmount", num_mine);
        editor.apply();
    }

    //static so any activity can access
    static public String getMatrixSize(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        String defaultValue2 = context.getResources().getString(R.string.default_size);
        return prefs.getString("MatrixSize",defaultValue2);

    }

    static public int getMinesAmount(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.default_mines);
        return prefs.getInt("MineAmount", defaultValue);

    }




    public static Intent makeIntent(Context context){
        return new Intent(context,Settings.class);
    }
}
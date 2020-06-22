package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createRadioButtonsForMatrix();
        createRadiobuttonForMines();

    }

    private void createRadioButtonsForMatrix(){
        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroupBoard);
        String[] matrixSize = getResources().getStringArray(R.array.rowCol);
        for(int i = 0; i < matrixSize.length; i++){
            String matrix_size = matrixSize[i];
            RadioButton button = new RadioButton(this);
            button.setText(matrix_size);
            group.addView(button);

        }
    }


    private void createRadiobuttonForMines(){
        RadioGroup group2 = (RadioGroup)findViewById(R.id.radioGroupMines);
        int[] num_mines = getResources().getIntArray(R.array.num_of_mines);
        for(int j = 0; j < num_mines.length; j++){
            int num_mine = num_mines[j];
            RadioButton button = button = new RadioButton(this);
            button.setText(num_mine +"");
            group2.addView(button);

        }
    }



    public static Intent makeIntent(Context context){
        return new Intent(context,Settings.class);
    }
}
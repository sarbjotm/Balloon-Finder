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


    //TO DO: FIX THIS IMPLEMENTATION FOR NUMBER OF MINES
//    private void createRadiobuttonForMines(){
//        RadioGroup group2 = (RadioGroup)findViewById(R.id.radioGroupMines);
//        int[] minesSize = getResources().getIntArray(R.array.minesRadio);
//        for(int j = 0; j < minesSize.length; j++){
//            int MineSize = minesSize[j];
//            RadioButton button2 = new RadioButton(this);
//            button2.setText(MineSize);
//            group2.addView(button2);
//
//        }
//    }

    public static Intent makeIntent(Context context){
        return new Intent(context,Settings.class);
    }
}
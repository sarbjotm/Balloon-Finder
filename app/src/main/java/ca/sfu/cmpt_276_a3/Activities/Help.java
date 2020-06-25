package ca.sfu.cmpt_276_a3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ca.sfu.cmpt_276_a3.R;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    //Make Intent
    public static Intent makeIntent(Context context){
        return new Intent(context,Help.class);
    }
}
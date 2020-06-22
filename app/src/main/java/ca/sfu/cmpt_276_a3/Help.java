package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        launchBackButton();

    }


    private void launchBackButton(){
        Button btn = (Button) findViewById(R.id.btnBackFromHelp);
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = WelcomeScreen.makeIntent(Help.this);
                finish();
                startActivity(intent);
            }
        });



    }
    public static Intent makeIntent(Context context){
        return new Intent(context,Help.class);
    }
}
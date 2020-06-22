package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        launchPlayButton();
        launchSettingsButton();
    }

    private void launchPlayButton() {
        Button btn = (Button) findViewById(R.id.btnPlay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MinesweeperGame.makeIntent(WelcomeScreen.this);
                startActivity(intent);
            }
        });


    }
    private void launchSettingsButton(){
        Button btn = (Button) findViewById(R.id.btnOptions);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Settings.makeIntent(WelcomeScreen.this);
                startActivity(intent);
            }
        });



    }

    public static Intent makeIntent(Context context){
        return new Intent(context,WelcomeScreen.class);
    }
}
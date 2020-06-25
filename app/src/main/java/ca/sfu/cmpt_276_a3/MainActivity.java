package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import ca.sfu.cmpt_276_a3.Activities.WelcomeScreen;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeText;
    private TextView authorsText;
    private static int TIME_OUT = 13000;
    private int changeActivity = 0;
//    private MediaPlayer songPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchButton();
        animation();
    }


    private void animation(){
        welcomeText = findViewById(R.id.welcome);
        authorsText = findViewById(R.id.Authors);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(changeActivity != 1){
                    Intent intent = WelcomeScreen.makeIntent(MainActivity.this);
                    startActivity(intent);
                    finish();
                }

            }
        }, TIME_OUT);

            YoYo.with(Techniques.Wave)
                    .duration(5000)
                    .repeat(0)
                    .playOn(welcomeText);

            YoYo.with(Techniques.FadeIn)
                    .duration(5000)
                    .repeat(0)
                    .playOn(authorsText);

    }

    private void launchButton(){
        Button btn = (Button) findViewById(R.id.btnSkip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity = 1;
                Intent intent = WelcomeScreen.makeIntent(MainActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,MainActivity.class);
    }
}
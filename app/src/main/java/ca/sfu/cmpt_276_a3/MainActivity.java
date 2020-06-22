package ca.sfu.cmpt_276_a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchButton();
        animation();

    }

    private void animation(){
        welcomeText = findViewById(R.id.welcome);
        YoYo.with(Techniques.Wave)
                .duration(5000)
                .repeat(0)
                .playOn(welcomeText);
    }

    private void launchButton(){
        Button btn = (Button) findViewById(R.id.btnSkip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = WelcomeScreen.makeIntent(MainActivity.this);
            finish();
            startActivity(intent);
            }
        });



    }
}
package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TweenedAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened_animation);
        Toast.makeText(getApplicationContext(), "Tweened Animation", Toast.LENGTH_SHORT).show();
    }
}

package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class FrameByFrameAnimationActivity extends AppCompatActivity {
    private AnimationDrawable frameAnimation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_by_frame_animation);

        // Start button
        final Button startButton = findViewById(R.id.btnStartAnim);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Stop button
        final Button stopButton = findViewById(R.id.btnStopAnim);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private void startAnimation() {
        ImageView img = findViewById(R.id.animationImageView);

        // Gets each image
        BitmapDrawable frame01 = (BitmapDrawable) getDrawable(R.drawable.jump_attack01);
        BitmapDrawable frame02 = (BitmapDrawable) getDrawable(R.drawable.jump_attack02);
        BitmapDrawable frame03 = (BitmapDrawable) getDrawable(R.drawable.jump_attack03);
        BitmapDrawable frame04 = (BitmapDrawable) getDrawable(R.drawable.jump_attack04);
        BitmapDrawable frame05 = (BitmapDrawable) getDrawable(R.drawable.jump_attack05);
        BitmapDrawable frame06 = (BitmapDrawable) getDrawable(R.drawable.jump_attack06);
        BitmapDrawable frame07 = (BitmapDrawable) getDrawable(R.drawable.jump_attack07);
        BitmapDrawable frame08 = (BitmapDrawable) getDrawable(R.drawable.jump_attack08);
        BitmapDrawable frame09 = (BitmapDrawable) getDrawable(R.drawable.jump_attack09);
        BitmapDrawable frame10 = (BitmapDrawable) getDrawable(R.drawable.jump_attack10);

        // Get the background, which has been compiled to an AnimationDrawable object.
        int reasonableDuration = 100;
        frameAnimation = new AnimationDrawable();
        frameAnimation.setOneShot(false);    // loop continuously
        frameAnimation.addFrame(Objects.requireNonNull(frame01), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame02), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame03), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame04), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame05), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame06), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame07), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame08), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame09), reasonableDuration);
        frameAnimation.addFrame(Objects.requireNonNull(frame10), reasonableDuration);

        // Sets the background
        img.setBackground(frameAnimation);

        // Starts the animation.
        frameAnimation.setVisible(true, true);
        frameAnimation.start();
    }

    private void stopAnimation() {
        frameAnimation.stop();
        frameAnimation.setVisible(false, false);
    }
}

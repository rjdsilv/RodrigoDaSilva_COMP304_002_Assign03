package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import ca.centennialcollege.rodrigodasilva_comp304_002_assign03.utils.AnimationStatus;
import ca.centennialcollege.rodrigodasilva_comp304_002_assign03.utils.PlayingAnimation;

public class TweenedAnimationActivity extends AppCompatActivity {
    private ImageView earthFrontImageView = null;
    private ImageView earthBackImageView = null;

    private Animation frontAnimation = null;
    private Animation backAnimation = null;

    private PlayingAnimation playingAnimation = null;
    private AnimationStatus animationStatus = null;

    private Button startButton = null;
    private Button stopButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened_animation);
        performAnimation();
    }

    private void performAnimation() {
        setUpImages();
        setUpAnimations();
        setUpButtons();
    }

    private void setUpImages() {
        // Sets up the earth image going.
        earthFrontImageView = findViewById(R.id.earthFrontImageView);
        earthFrontImageView.setImageResource(R.drawable.earth);
        earthFrontImageView.setVisibility(View.VISIBLE);

        // Sets up the earth image coming back.
        earthBackImageView = findViewById(R.id.earthBackImageView);
        earthBackImageView.setImageResource(R.drawable.earth);
        earthBackImageView.setVisibility(View.INVISIBLE);

        // Sets up the sun image
        final ImageView sunImageView = findViewById(R.id.sunImageView);
        sunImageView.setImageResource(R.drawable.sun);
        sunImageView.setVisibility(View.VISIBLE);
    }

    private void setUpAnimations() {
        // Sets up the animation for earth going.
        frontAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_front_sun);
        frontAnimation.setAnimationListener(new FrontAnimationListener());

        // Sets up the animation for earth coming back.
        backAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_back_sun);
        backAnimation.setAnimationListener(new BackAnimationListener());

        // Starts the animation
        earthFrontImageView.startAnimation(frontAnimation);
        playingAnimation = PlayingAnimation.FRONT;
        animationStatus = AnimationStatus.RUNNING;
    }

    private void setUpButtons() {
        // Stop button
        stopButton = findViewById(R.id.btnStopAnim);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });

        // Start button
        startButton = findViewById(R.id.btnStartAnim);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Toggles the buttons
        toggleButtons();
    }

    private void toggleButtons() {
        stopButton.setClickable(AnimationStatus.RUNNING.equals(animationStatus));
        startButton.setClickable(AnimationStatus.STOPPED.equals(animationStatus));
    }

    private void stopAnimation() {
        if (AnimationStatus.RUNNING.equals(animationStatus)) {
            animationStatus = AnimationStatus.STOPPED;

            switch (playingAnimation) {
                case FRONT:
                    earthFrontImageView.clearAnimation();
                    break;

                case BACK:
                    earthBackImageView.clearAnimation();
                    break;
            }

            toggleButtons();
        }
    }

    private void startAnimation() {
        if (AnimationStatus.STOPPED.equals(animationStatus)) {
            animationStatus = AnimationStatus.RUNNING;

            switch (playingAnimation) {
                case FRONT:
                    earthFrontImageView.startAnimation(frontAnimation);
                    break;

                case BACK:
                    earthBackImageView.startAnimation(backAnimation);
                    break;
            }

            toggleButtons();
        }
    }

    private class FrontAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (AnimationStatus.RUNNING.equals(animationStatus)) {
                earthFrontImageView.setVisibility(View.INVISIBLE);
                earthBackImageView.setVisibility(View.VISIBLE);
                earthBackImageView.startAnimation(backAnimation);
                playingAnimation = PlayingAnimation.BACK;
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    private class BackAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (AnimationStatus.RUNNING.equals(animationStatus)) {
                earthFrontImageView.setVisibility(View.VISIBLE);
                earthBackImageView.setVisibility(View.INVISIBLE);
                earthFrontImageView.startAnimation(frontAnimation);
                playingAnimation = PlayingAnimation.FRONT;
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }
}

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

/**
 * Class responsible for setting up the Tweened Animation Activity. In this activity there will be
 * a simple simulation of the earth moving around the sun.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class TweenedAnimationActivity extends AppCompatActivity {
    private ImageView earthFrontImageView = null;
    private ImageView earthBackImageView = null;

    private Animation frontAnimation = null;
    private Animation backAnimation = null;

    private PlayingAnimation playingAnimation = null;
    private AnimationStatus animationStatus = null;

    private Button startButton = null;
    private Button stopButton = null;

    /**
     * Event handler for the activity creation.
     *
     * @param savedInstanceState The instance to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened_animation);
        setUpActivity();
    }

    /**
     * Sets up the activity by creating the necessary images, setting up the necessary animations,
     * and creating and positioning the buttons.
     */
    private void setUpActivity() {
        setUpImages();
        setUpAnimations();
        setUpButtons();
    }

    /**
     * Sets up the activity necessary images: Background, Sun, and Earth.
     */
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

    /**
     * Sets up the activity animations. There are 2 animations set: The earth moving to the left
     * (in front of the sun) and the earth moving right (back to the sun).
     */
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

    /**
     * Sets up the application buttons for starting and stopping the animation.
     */
    private void setUpButtons() {
        // Stop button
        stopButton = findViewById(R.id.stopTweenedButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });

        // Start button
        startButton = findViewById(R.id.startTweenedButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Toggles the buttons
        toggleButtons();
    }

    /**
     * Toggle the buttons clickable property.
     */
    private void toggleButtons() {
        stopButton.setClickable(AnimationStatus.RUNNING.equals(animationStatus));
        startButton.setClickable(AnimationStatus.STOPPED.equals(animationStatus));
    }

    /**
     * Stops the current animation and toggles the buttons properly.
     */
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

    /**
     * Stops the last played animation and toggles the buttons properly.
     */
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

    /**
     * Listener class to take care of the animation where the earth goes in front of the sun.
     */
    private class FrontAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            // Changes the animation to the back one when the front one ends
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

    /**
     * Listener class to take care of the animation where the earth in the back of the sun.
     */
    private class BackAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            // Changes the animation to the front one when the back one ends
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

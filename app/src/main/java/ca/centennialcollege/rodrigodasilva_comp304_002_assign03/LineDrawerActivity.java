package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Class that will create the activity responsible for the drawing lines.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class LineDrawerActivity extends AppCompatActivity {
    private static final int DRAW_MARGIN = 25;

    private Point size;
    private Paint paint;
    private Canvas canvas;
    private ImageView canvasImageView;
    private TextView xValueTextView;
    private TextView yValueTextView;
    private Spinner lineThicknessSpinner;

    private int startX = DRAW_MARGIN;
    private int startY = DRAW_MARGIN;
    private int endX = DRAW_MARGIN;
    private int endY = DRAW_MARGIN;

    /**
     * Event handles for the keys pressed on the keyboard.
     *
     * @param keyCode The key pressed.
     * @param event The event.
     * @return true if the event is treated. false, otherwise.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                drawDown();
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                drawUp();
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                drawLeft();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                drawRight();
                return true;
        }
        return false;
    }

    /**
     * Creates and initializes the activity.
     *
     * @param savedInstanceState The bundle to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_drawer);

        initialize();
    }

    /**
     * Event handler for the screen arrow keys.
     *
     * @param button The arrow key pressed.
     */
    public void onDrawClick(View button) {
        switch (button.getId()) {
            case R.id.arrowDownImageButton:
                drawDown();
                break;

            case R.id.arrowUpImageButton:
                drawUp();
                break;

            case R.id.arrowLeftImageButton:
                drawLeft();
                break;

            case R.id.arrowRightImageButton:
                drawRight();
                break;
        }
    }

    /**
     * Event handler for the clear button. This event will clear and reset the canvas.
     *
     * @param button The clear button.
     */
    public void onClearCanvas(View button) {
        // Resets the background.
        canvas.drawColor(Color.BLACK);

        // Resets the coordinates.
        startX = DRAW_MARGIN;
        startY = DRAW_MARGIN;
        endX = DRAW_MARGIN;
        endY = DRAW_MARGIN;

        // Resets the coordinates display
        setUpCoordinatesValue();
    }

    /**
     * Draws a line downwards.
     */
    private void drawDown() {
        if (endY < size.y - DRAW_MARGIN) {
            endY += 5;
            drawLine();
        }
    }

    /**
     * Draws a line upwards.
     */
    private void drawUp() {
        if (endY > DRAW_MARGIN) {
            endY -= 5;
            drawLine();
        }
    }

    /**
     * Draws a line to the left.
     */
    private void drawLeft() {
        if (endX > DRAW_MARGIN) {
            endX -= 5;
            drawLine();
        }
    }

    /**
     * Draws a line to the right.
     */
    private void drawRight() {
        if (endX < size.x - DRAW_MARGIN) {
            endX += 5;
            drawLine();
        }
    }

    /**
     * Draws the line and set up the coordinates on the screen.
     */
    private void drawLine() {
        setUpCoordinatesValue();

        // Draws the line.
        canvas.drawLine(startX, startY, endX, endY, paint);

        // Resets the start.
        startX = endX;
        startY = endY;
    }

    /**
     * Gets the proper color based on the radio button checked.
     *
     * @param checkedId The checked radio button id.
     * @return The proper color for the selected radio button.
     */
    private int getColorFromRadioGroup(int checkedId) {
        switch (checkedId) {
            case R.id.cyanRadioButton:
                return Color.CYAN;

            case R.id.magentaRadioButton:
                return Color.MAGENTA;

            case R.id.yellowRadioButton:
                return Color.YELLOW;
        }

        return Color.CYAN;
    }

    /**
     * Sets up the event that will handle changes in the radio group.
     */
    private void setUpRadioGroupOnCheckedChangeListener() {
        final RadioGroup radioGroup = findViewById(R.id.lineColorRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                paint.setColor(getColorFromRadioGroup(checkedId));
            }
        });
    }

    /**
     * Sets up the event that will handle item selection changes on the line thickness spinner.
     */
    private void setUpSpinnerItemSelectedListener() {
        lineThicknessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paint.setStrokeWidth(Integer.parseInt(lineThicknessSpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Initializes the activity.
     */
    private void initialize() {
        // Initializes the private components.
        size = new Point();
        lineThicknessSpinner = findViewById(R.id.lineThicknessSpinner);
        canvasImageView = findViewById(R.id.canvasImageView);
        xValueTextView = findViewById(R.id.xValueTextView);
        yValueTextView = findViewById(R.id.yValueTextView);

        // Sets everything up.
        setUpRadioGroupOnCheckedChangeListener();
        setUpSpinnerItemSelectedListener();
        setUpPaint();
        setUpCanvas();
        setUpCoordinatesValue();
    }

    /**
     * Sets up the paint object.
     */
    private void setUpPaint() {
        // Sets the paint up.
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(Integer.parseInt(lineThicknessSpinner.getSelectedItem().toString()));
    }

    /**
     * Prepares the canvas to be drawn.
     */
    private void setUpCanvas() {
        // Sets the size of the bitmap to be drawn.
        final Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        // Creates the bitmap to be used.
        final Bitmap bitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);

        // Sets the bitmap on the image view.
        canvasImageView.setImageBitmap(bitmap);
    }

    /**
     * Sets up the coordinates value on the screen.
     */
    private void setUpCoordinatesValue() {
        xValueTextView.setText(String.valueOf(startX));
        yValueTextView.setText(String.valueOf(startY));
    }
}

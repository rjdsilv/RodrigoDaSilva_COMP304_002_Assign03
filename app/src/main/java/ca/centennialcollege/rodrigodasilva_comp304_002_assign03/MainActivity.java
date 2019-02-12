package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Creates the application's main activity. This activity will contain a Recycler View in order
 * to enable the user to choose what activity he wants to run next.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {
    private final int TASK1_POS = 0;
    private final int TASK2_POS = 1;
    private final int TASK3_POS = 2;

    private String[] tasks = {
            "Task 1 - Line Drawer",
            "Task 2 - Tweened Animation",
            "Task 3 - Frame by Frame Animation"
    };

    /**
     * Creates the current activity.
     *
     * @param savedInstanceState The instance to be used.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the adapter.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                tasks);

        // Creates the List View.
        final ListView tasksListView = findViewById(R.id.tasksListView);
        tasksListView.setAdapter(adapter);
        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case TASK1_POS:
                        launchActivity(LineDrawerActivity.class);
                        break;

                    case TASK2_POS:
                        launchActivity(TweenedAnimationActivity.class);
                        break;

                    case TASK3_POS:
                        launchActivity(FrameByFrameAnimationActivity.class);
                        break;
                }
            }
        });
    }

    /**
     * Launches the activity represented by the given Class.
     *
     * @param clazz The class representing the activity to be launched.
     */
    private void launchActivity(Class<? extends Activity> clazz) {
        final Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}

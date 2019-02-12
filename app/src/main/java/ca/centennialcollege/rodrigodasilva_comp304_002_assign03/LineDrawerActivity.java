package ca.centennialcollege.rodrigodasilva_comp304_002_assign03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LineDrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_drawer);
        Toast.makeText(getApplicationContext(), "Line Drawer Activity", Toast.LENGTH_SHORT).show();
    }
}

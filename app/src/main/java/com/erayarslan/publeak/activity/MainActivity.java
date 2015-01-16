package com.erayarslan.publeak.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.erayarslan.publeak.R;
import com.erayarslan.publeak.adapter.TimeLineAdapter;
import com.erayarslan.publeak.util.Image;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private ListView timeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeLine = (ListView) findViewById(R.id.timeLine);
        List<Image> images = new ArrayList<Image>();
        images.add(new Image("a_man-t1.jpg"));
        images.add(new Image("new_year_2015_wishes-t1.jpg"));
        images.add(new Image("maserati_black-t1.jpg"));
        images.add(new Image("a_man-t1.jpg"));
        images.add(new Image("new_year_2015_wishes-t1.jpg"));
        images.add(new Image("maserati_black-t1.jpg"));
        images.add(new Image("a_man-t1.jpg"));
        images.add(new Image("new_year_2015_wishes-t1.jpg"));
        images.add(new Image("maserati_black-t1.jpg"));
        TimeLineAdapter timeLineAdapter = new TimeLineAdapter(MainActivity.this, images);
        timeLine.setAdapter(timeLineAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } return super.onOptionsItemSelected(item);
    }
}

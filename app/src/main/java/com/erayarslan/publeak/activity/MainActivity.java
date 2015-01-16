package com.erayarslan.publeak.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.erayarslan.publeak.R;
import com.erayarslan.publeak.adapter.TimeLineAdapter;
import com.erayarslan.publeak.util.CustomButtonMenuVM;
import com.erayarslan.publeak.util.Image;
import com.erayarslan.publeak.util.Utils;
import com.tuenti.buttonmenu.ButtonMenu;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private ListView timeLine;
    private ButtonMenu button_menu;
    private ImageButton photo;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_menu = (ButtonMenu) findViewById(R.id.button_menu);
        button_menu.setButtonMenuVM(new CustomButtonMenuVM());
        button_menu.initialize();
        timeLine = (ListView) findViewById(R.id.timeLine);
        photo = (ImageButton) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = null;
                try {
                    photo = Utils.createTemporaryFile(new Date().toString(), ".jpg");
                    photo.delete();
                } catch (IOException e) {}

                mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, 0);
            }
        });

        List<Image> images = new ArrayList<Image>();
        images.add(new Image("a_man-t1.jpg"));
        images.add(new Image("new_year_2015_wishes-t1.jpg"));
        images.add(new Image("maserati_black-t1.jpg"));
        images.add(new Image("a_man-t1.jpg"));
        images.add(new Image("new_year_2015_wishes-t1.jpg"));
        images.add(new Image("maserati_black-t1.jpg"));
        images.add(new Image("fuckingProtected"));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            this.getContentResolver().notifyChange(mImageUri, null);
            ContentResolver contentResolver = this.getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(contentResolver, mImageUri);
            } catch (Exception e) {}
        }
    }
}

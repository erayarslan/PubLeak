package com.erayarslan.publeak.util;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.erayarslan.publeak.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import java.io.IOException;
import java.util.Date;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class DownloadAsyncTask extends AsyncTask<ViewHolder, Void, ViewHolder> {
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();

    private RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://hd.wallpaperswide.com/")
            .setConverter(new GsonConverter(gson))
            .build();

    private TestImage testImage = restAdapter.create(TestImage.class);

    @Override
    protected ViewHolder doInBackground(ViewHolder... params) {
        ViewHolder viewHolder = params[0];
        try {
            viewHolder.bitmap = BitmapFactory.decodeStream(testImage.getImage(viewHolder.imageURL).getBody().in());
        } catch (IOException e) {
            viewHolder.bitmap = null;
        }

        return viewHolder;
    }

    @Override
    protected void onPostExecute(ViewHolder result) {
        if (result.bitmap == null) {
            result.imageView.setImageResource(R.drawable.loading);
        } else {
            result.imageView.setImageBitmap(result.bitmap);
        }
    }

}

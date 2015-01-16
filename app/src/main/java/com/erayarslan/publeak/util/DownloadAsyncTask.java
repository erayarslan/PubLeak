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
import retrofit.RetrofitError;
import retrofit.client.Response;
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
            Response response = testImage.getImage(viewHolder.imageURL);
            viewHolder.bitmap = BitmapFactory.decodeStream(response.getBody().in());
        } catch (IOException ioException) {
            viewHolder.bitmap = null;
        } catch (RetrofitError retrofitError) {
            viewHolder.bitmap = null;
        } return viewHolder;
    }

    @Override
    protected void onPostExecute(ViewHolder result) {
        if (result.bitmap == null) {
            result.imageView.setImageResource(R.drawable.image_not_found);
        } else {
            result.imageView.setImageBitmap(result.bitmap);
        }
    }

}

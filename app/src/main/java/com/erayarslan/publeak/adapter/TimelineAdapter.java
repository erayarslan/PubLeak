package com.erayarslan.publeak.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.erayarslan.publeak.R;
import com.erayarslan.publeak.util.DownloadAsyncTask;
import com.erayarslan.publeak.util.Image;
import com.erayarslan.publeak.util.ViewHolder;
import java.util.List;

@SuppressLint({"ViewHolder", "InflateParams"})
public class TimeLineAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Image> images;
    private Context context;

    public TimeLineAdapter(Activity activity, List<Image> images) {
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.context = activity.getBaseContext();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Image getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_timeline, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.imageURL = images.get(i).getUrl();
        new DownloadAsyncTask().execute(viewHolder);
        //
        return view;
    }
}

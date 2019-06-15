package kr.ac.anheew1kookmin.exhibition.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridImageAdapter extends BaseAdapter {
    private Context mContext;

    public GridImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() { //todo
        return 0;
    }

    @Override
    public Object getItem(int position) {//todo
        return null;
    }

    @Override
    public long getItemId(int position) {//todo
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null ){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridLayoutManager.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else {
            imageView = (ImageView) convertView;
        }
        //imageView.setImageResource(0);
        return imageView;
    }

    private String getImagesrc(){//todo
        return null;
    }
}


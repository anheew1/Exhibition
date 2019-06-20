package kr.ac.anheew1kookmin.exhibition.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bitmap> bitmapArrayList;

    public GridImageAdapter(Context c) {
        mContext = c;
        bitmapArrayList = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return bitmapArrayList.size();
    }

    @Override
    public Object getItem(int position) {//todo
        return bitmapArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null ){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new AbsListView.LayoutParams(350,350));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(bitmapArrayList.get(position));
        return imageView;
    }
    public void setImageBitmapList(ArrayList<Bitmap> bitmapArrayList){
        this.bitmapArrayList = bitmapArrayList;
    }
    public void addImageBitmap(Bitmap bitmap){
        bitmapArrayList.add(bitmap);
    }
    public void insertImageBitmap(int position, Bitmap bitmap){
        bitmapArrayList.add(position,bitmap);
    }
    public void initailizeSize( int size){
        for(int i=0;i<size;i++)
        bitmapArrayList.add(null);
    }
    private String getImagesrc(){//todo
        return null;
    }
}


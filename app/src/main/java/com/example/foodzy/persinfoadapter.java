package com.example.foodzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class persinfoadapter extends BaseAdapter {
    Context context;
    String [] topics;
    String [] data;
    int [] pics;
    LayoutInflater layoutInflater;

    public persinfoadapter (Context ctx, String [] topics, String [] data, int [] pics){
        this.context = ctx;
        this.topics = topics;
        this.data = data;
        this.pics = pics;
        layoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return topics.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.persinfoitem,null);
        TextView textView1 = (TextView) convertView.findViewById(R.id.topicname);
        TextView textView2 = (TextView) convertView.findViewById(R.id.dataname);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView10);
        textView1.setText(topics[position]);
        textView2.setText(data[position]);
        imageView.setImageResource(pics[position]);
        return convertView;
    }
}

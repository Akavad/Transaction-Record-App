package com.example.dell.assignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 04-02-2018.
 */

public class ListViewCustomAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Transaction> list;

    public ListViewCustomAdapter(Context context, int layout, List<Transaction> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,parent,false);

        TextView tvdate= (TextView) convertView.findViewById(R.id.tvdate);
        TextView tvtype= (TextView) convertView.findViewById(R.id.tvtype);
        TextView tvamount= (TextView) convertView.findViewById(R.id.tvamount);
        TextView tvparticular= (TextView) convertView.findViewById(R.id.tvparticular);

        tvdate.setText(list.get(position).getDate());
        tvtype.setText(list.get(position).getType());
        tvamount.setText(list.get(position).getAmount()+" rs.");
        tvparticular.setText(list.get(position).getParticular());
        return convertView;
    }
}

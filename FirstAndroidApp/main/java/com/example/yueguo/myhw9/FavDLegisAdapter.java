package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YueGuo on 16/12/1.
 */

public class FavDLegisAdapter extends BaseAdapter {
    private List<LegisBean> mfList;
    private Context context;
    private LayoutInflater mfInflater;

    public FavDLegisAdapter(Context context, List<LegisBean> data){
        mfList = data;
        mfInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mfList.get(position);
    }

    @Override
    public int getCount() {
        return mfList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mfInflater.inflate(R.layout.favlegis_layout1,null);
            viewHolder.fTitle = (TextView)convertView.findViewById(R.id.fltv_title1);
            viewHolder.fContent = (TextView)convertView.findViewById(R.id.fltv_content1);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置给list中的元素

        viewHolder.fTitle.setText("");
        viewHolder.fContent.setText("");

        return convertView;
    }

    class ViewHolder{
        public TextView fTitle,fContent;
    }
}

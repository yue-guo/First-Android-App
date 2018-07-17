package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YueGuo on 16/11/29.
 */

public class CommHouseAdapter extends BaseAdapter {
    private List<CommBean> mchList;
    private Context context;
    private LayoutInflater mchInflater;

    public CommHouseAdapter(Context context, List<CommBean> data){
        mchList = data;
        mchInflater = LayoutInflater.from(context);
        this.context = context;

    }

    public Object getItem(int position) {
        return mchList.get(position);
    }

    @Override
    public int getCount() {
        return mchList.size();
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
            convertView = mchInflater.inflate(R.layout.commhouse_layout,null);
            viewHolder.chtvTitle = (TextView)convertView.findViewById(R.id.chtv_title);
            viewHolder.chtvContent = (TextView)convertView.findViewById(R.id.chtv_content);
            viewHolder.chtvDate = (TextView)convertView.findViewById(R.id.chtv_date);
            viewHolder.chimButton = (ImageButton)convertView.findViewById(R.id.chimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.chtvTitle.setText(mchList.get(position).commID);
        viewHolder.chtvContent.setText(mchList.get(position).commName);
        viewHolder.chtvDate.setText(mchList.get(position).commChamber);
        viewHolder.chimButton.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
             CommBean comm = mchList.get(position);
                 View view1 = mchInflater.inflate(R.layout.commhouse_layout,null);
                 view1.setBackgroundColor(Color.BLUE);
              Intent intent = new Intent();
              intent.putExtra("commid",comm.commID);
              intent.putExtra("commname",comm.commName);
              intent.putExtra("commchamber",comm.commChamber);
              intent.putExtra("commparent",comm.commParentComm);
              intent.putExtra("commcontact",comm.commContact);
              intent.putExtra("commoffice",comm.commOffice);
              intent.setClass(context,DisplayCommDetail.class);
              context.startActivity(intent);
             }
        }

        );
        return convertView;
    }

    class ViewHolder{
        public TextView chtvTitle,chtvContent,chtvDate;
        public ImageButton chimButton;
    }
}

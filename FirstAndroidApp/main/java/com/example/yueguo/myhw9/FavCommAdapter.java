package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YueGuo on 16/11/30.
 */

public class FavCommAdapter extends BaseAdapter {
    private List<CommBean> mfcList;
    private Context context;
    private LayoutInflater mfcInflater;

    public FavCommAdapter(Context context, List<CommBean> data){
        mfcList = data;
        mfcInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mfcList.get(position);
    }

    @Override
    public int getCount() {
        return mfcList.size();
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
            convertView = mfcInflater.inflate(R.layout.favcomm_layout,null);
            viewHolder.fcTitle = (TextView)convertView.findViewById(R.id.fctv_title);
            viewHolder.fcContent = (TextView)convertView.findViewById(R.id.fctv_content);
            viewHolder.fcDate = (TextView)convertView.findViewById(R.id.fctv_date);
            viewHolder.fcButton = (ImageButton)convertView.findViewById(R.id.fcimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置给list中的元素

        viewHolder.fcTitle.setText(mfcList.get(position).commID);
        viewHolder.fcContent.setText(mfcList.get(position).commName);
        viewHolder.fcDate.setText(mfcList.get(position).commChamber);
        viewHolder.fcButton.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
              CommBean comm = mfcList.get(position);
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
        public TextView fcTitle,fcContent,fcDate;
        public ImageButton fcButton;
    }
}

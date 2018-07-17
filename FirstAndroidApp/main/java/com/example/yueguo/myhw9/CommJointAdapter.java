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

public class CommJointAdapter extends BaseAdapter{
    private List<CommBean> mcjList;
    private Context context;
    private LayoutInflater mcjInflater;

    public CommJointAdapter(Context context, List<CommBean> data){
        mcjList = data;
        mcjInflater = LayoutInflater.from(context);
        this.context = context;

    }

    public Object getItem(int position) {
        return mcjList.get(position);
    }

    @Override
    public int getCount() {
        return mcjList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mcjInflater.inflate(R.layout.commjoint_layout,null);
            viewHolder.cjtvTitle = (TextView)convertView.findViewById(R.id.cjtv_title);
            viewHolder.cjtvContent = (TextView)convertView.findViewById(R.id.cjtv_content);
            viewHolder.cjtvDate = (TextView)convertView.findViewById(R.id.cjtv_date);
            viewHolder.cjimButton = (ImageButton)convertView.findViewById(R.id.cjimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cjtvTitle.setText(mcjList.get(position).commID);
        viewHolder.cjtvContent.setText(mcjList.get(position).commName);
        viewHolder.cjtvDate.setText(mcjList.get(position).commChamber);
        viewHolder.cjimButton.setOnClickListener(new View.OnClickListener(){
                                                     public void onClick(View v){
            CommBean comm = mcjList.get(position);
            View view1 = mcjInflater.inflate(R.layout.commjoint_layout,null);
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
        public TextView cjtvTitle,cjtvContent,cjtvDate;
        public ImageButton cjimButton;
    }
}

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

public class CommSenateAdapter extends BaseAdapter{
    private List<CommBean> mcsList;
    private Context context;
    private LayoutInflater mcsInflater;

    public CommSenateAdapter(Context context, List<CommBean> data){
        mcsList = data;
        mcsInflater = LayoutInflater.from(context);
        this.context = context;

    }

    public Object getItem(int position) {
        return mcsList.get(position);
    }

    @Override
    public int getCount() {
        return mcsList.size();
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
            convertView = mcsInflater.inflate(R.layout.commsenate_layout,null);
            viewHolder.cstvTitle = (TextView)convertView.findViewById(R.id.cstv_title);
            viewHolder.cstvContent = (TextView)convertView.findViewById(R.id.cstv_content);
            viewHolder.cstvDate = (TextView)convertView.findViewById(R.id.cstv_date);
            viewHolder.csimButton = (ImageButton)convertView.findViewById(R.id.csimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cstvTitle.setText(mcsList.get(position).commID);
        viewHolder.cstvContent.setText(mcsList.get(position).commName);
        viewHolder.cstvDate.setText(mcsList.get(position).commChamber);
        viewHolder.csimButton.setOnClickListener(new View.OnClickListener(){
                                                     public void onClick(View v){
             CommBean comm = mcsList.get(position);
              View view1 = mcsInflater.inflate(R.layout.commsenate_layout,null);
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
        public TextView cstvTitle,cstvContent,cstvDate;
        public ImageButton csimButton;
    }
}

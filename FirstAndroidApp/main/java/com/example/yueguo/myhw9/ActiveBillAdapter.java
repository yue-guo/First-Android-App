package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YueGuo on 16/11/28.
 */

public class ActiveBillAdapter  extends BaseAdapter{
    private List<BillBean> mabList;
    private Context context;
    private LayoutInflater mabInflater;

    public ActiveBillAdapter(Context context, List<BillBean> data){
        mabList = data;
        mabInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public Object getItem(int position) {
        return mabList.get(position);
    }

    @Override
    public int getCount() {
        return mabList.size();
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
            convertView = mabInflater.inflate(R.layout.activebill_layout,null);
            viewHolder.abtvTitle = (TextView)convertView.findViewById(R.id.abtv_title);
            viewHolder.abtvContent = (TextView)convertView.findViewById(R.id.abtv_content);
            viewHolder.abtvDate = (TextView)convertView.findViewById(R.id.abtv_date);
            viewHolder.abimButton = (ImageButton)convertView.findViewById(R.id.abimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.abtvTitle.setText(mabList.get(position).billID);
        viewHolder.abtvContent.setText(mabList.get(position).billShortTitle);
        viewHolder.abtvDate.setText(mabList.get(position).billIntroducedOn);
        viewHolder.abimButton.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v){
               BillBean bill = mabList.get(position);
                   View view1 = mabInflater.inflate(R.layout.activebill_layout,null);
                   view1.setBackgroundColor(Color.BLUE);
               Intent intent = new Intent();
                intent.putExtra("billid",bill.billID);
                intent.putExtra("stitle",bill.billShortTitle);
                //intent.putExtra("ltitle",bill.billLongTitle);
                intent.putExtra("billtype",bill.billType);
                intent.putExtra("sponsor",bill.billSponsor);
                intent.putExtra("chamber",bill.billChamber);
                intent.putExtra("status",bill.billStatus);
                intent.putExtra("introducedon",bill.billIntroducedOn);
                intent.putExtra("congressurl",bill.billCongressURL);
                intent.putExtra("version",bill.billVersion);

                intent.putExtra("billurl",bill.billURL);
                intent.setClass(context,DisplayBillDetail.class);
                context.startActivity(intent);

                }
        }

        );
        return convertView;
    }

    class ViewHolder{
        public TextView abtvTitle,abtvContent,abtvDate;
        public ImageButton abimButton;
    }
}

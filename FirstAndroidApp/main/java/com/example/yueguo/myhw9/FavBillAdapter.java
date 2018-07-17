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
 * Created by YueGuo on 16/11/30.
 */

public class FavBillAdapter extends BaseAdapter {
    private List<BillBean> mfbList;
    private Context context;
    private LayoutInflater mfbInflater;

    public FavBillAdapter(Context context, List<BillBean> data){
        mfbList = data;
        mfbInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mfbList.get(position);
    }

    @Override
    public int getCount() {
        return mfbList.size();
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
            convertView = mfbInflater.inflate(R.layout.favbill_layout,null);
            //viewHolder.fbIcon = (ImageView) convertView.findViewById(R.id.fliv_icon);
            viewHolder.fbTitle = (TextView)convertView.findViewById(R.id.fbtv_title);
            viewHolder.fbContent = (TextView)convertView.findViewById(R.id.fbtv_content);
            viewHolder.fbDate = (TextView)convertView.findViewById(R.id.fbtv_date);
            viewHolder.fbButton = (ImageButton)convertView.findViewById(R.id.fbimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置给list中的元素

        viewHolder.fbTitle.setText(mfbList.get(position).billID);
        viewHolder.fbContent.setText(mfbList.get(position).billShortTitle);
        viewHolder.fbDate.setText(mfbList.get(position).billIntroducedOn);
        viewHolder.fbButton.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
             BillBean bill = mfbList.get(position);
             Intent intent = new Intent();
             intent.putExtra("billid",bill.billID);
             intent.putExtra("stitle",bill.billShortTitle);
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
        public TextView fbTitle,fbContent,fbDate;
        public ImageButton fbButton;
    }
}

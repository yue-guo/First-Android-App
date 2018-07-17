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

public class NewBillAdapter extends BaseAdapter {
    private List<BillBean> mnbList;
    private Context context;
    private LayoutInflater mnbInflater;

    public NewBillAdapter(Context context, List<BillBean> data){
        mnbList = data;
        mnbInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mnbList.get(position);
    }

    @Override
    public int getCount() {
        return mnbList.size();
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
            convertView = mnbInflater.inflate(R.layout.newbill_layout,null);
            viewHolder.nbtvTitle = (TextView)convertView.findViewById(R.id.nbtv_title);
            viewHolder.nbtvContent = (TextView)convertView.findViewById(R.id.nbtv_content);
            viewHolder.nbtvDate = (TextView)convertView.findViewById(R.id.nbtv_date);
            viewHolder.nbimButton = (ImageButton)convertView.findViewById(R.id.nbimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nbtvTitle.setText(mnbList.get(position).billID);
        viewHolder.nbtvContent.setText(mnbList.get(position).billShortTitle);
        viewHolder.nbtvDate.setText(mnbList.get(position).billIntroducedOn);
        viewHolder.nbimButton.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
                 BillBean bill = mnbList.get(position);
                 View view1 = mnbInflater.inflate(R.layout.newbill_layout,null);
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
        public TextView nbtvTitle,nbtvContent,nbtvDate;
        public ImageButton nbimButton;
    }
}


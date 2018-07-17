package com.example.yueguo.myhw9;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;
/**
 * Created by YueGuo on 16/11/25.
 */

public class HouseAdapter extends BaseAdapter{
    private List<LegisBean> mHList;
    private Context context;
    private LayoutInflater mHInflater;

    public HouseAdapter(Context context, List<LegisBean> data){
        mHList = data;
        mHInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mHList.get(position);
    }

    @Override
    public int getCount() {
        return mHList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HouseAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new HouseAdapter.ViewHolder();
            convertView = mHInflater.inflate(R.layout.house_layout,null);
            viewHolder.hivIcon = (ImageView) convertView.findViewById(R.id.hiv_icon);
            viewHolder.htvTitle = (TextView)convertView.findViewById(R.id.htv_title);
            viewHolder.htvContent = (TextView)convertView.findViewById(R.id.htv_content);
            viewHolder.imButton = (ImageButton)convertView.findViewById(R.id.image_button2);
            convertView.setTag(viewHolder);
            viewHolder.imButton.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v){
                   //viewHolder.houselayout = convertView.findViewById(R.id.houselayout);
                   View view1 = mHInflater.inflate(R.layout.house_layout,null);
                   view1.setBackgroundColor(Color.BLUE);
                   LegisBean legis = mHList.get(position);
                   Intent intent = new Intent();
                   intent.putExtra("firstname",legis.firstName);
                   intent.putExtra("lastname",legis.lastName);
                   intent.putExtra("title",legis.legisTitle);
                   intent.putExtra("party",legis.legisParty);
                   intent.putExtra("imgurl",legis.legisIconUrl);
                   intent.putExtra("email",legis.legisContact);
                   intent.putExtra("chamber",legis.legisChamber);
                   intent.putExtra("contact",legis.legisContact);
                   intent.putExtra("startterm",legis.termStart);
                                                           //Log.d("guoyue",legis.termStart);
                   intent.putExtra("endterm",legis.termEnd);
                   intent.putExtra("office",legis.legisOffice);
                   intent.putExtra("state",legis.legisState1);
                   intent.putExtra("fax",legis.legisFax);
                   intent.putExtra("birth",legis.legisBirth);
                   intent.setClass(context,DisplayDetail.class);
                   context.startActivity(intent);

               }
            }

            );

        }else{
            viewHolder = (HouseAdapter.ViewHolder) convertView.getTag();
        }
        //设置给list中的元素
        viewHolder.hivIcon.setImageResource(R.mipmap.ic_launcher);
        //new ImageLoader().showImageByThread(viewHolder.ivIcon,mList.get(position).newsIconUrl);//图片传递过去
        viewHolder.hivIcon.setTag(mHList.get(position).legisIconUrl);
        new ImageLoader().showImageByAsyncTask(viewHolder.hivIcon,mHList.get(position).legisIconUrl);
        viewHolder.htvTitle.setText(mHList.get(position).legisName);
        viewHolder.htvContent.setText(mHList.get(position).legisParty);
        return convertView;
    }

    class ViewHolder{
        public TextView htvTitle,htvContent;
        public ImageView hivIcon;
        public ImageButton imButton;
        public LinearLayout houselayout;
    }


}

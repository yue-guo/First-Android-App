package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by YueGuo on 16/11/26.
 */

public class SenateAdapter extends BaseAdapter {
    private List<LegisBean> mSList;
    private LayoutInflater mSInflater;
    private Context context;

    public SenateAdapter(Context context, List<LegisBean> data){
        mSList = data;
        mSInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mSList.get(position);
    }

    @Override
    public int getCount() {
        return mSList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SenateAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new SenateAdapter.ViewHolder();
            convertView = mSInflater.inflate(R.layout.senate_layout,null);
            viewHolder.sivIcon = (ImageView) convertView.findViewById(R.id.siv_icon);
            viewHolder.stvTitle = (TextView)convertView.findViewById(R.id.stv_title);
            viewHolder.stvContent = (TextView)convertView.findViewById(R.id.stv_content);
            viewHolder.imButton = (ImageButton)convertView.findViewById(R.id.image_button3);
            convertView.setTag(viewHolder);
            viewHolder.imButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     View view1 = mSInflater.inflate(R.layout.senate_layout,null);
                     view1.setBackgroundColor(Color.BLUE);
                      LegisBean legis = mSList.get(position);
                      Intent intent = new Intent();
                      intent.putExtra("firstname", legis.firstName);
                      intent.putExtra("lastname", legis.lastName);
                      intent.putExtra("title", legis.legisTitle);
                      intent.putExtra("party", legis.legisParty);
                      intent.putExtra("imgurl", legis.legisIconUrl);
                      intent.putExtra("email", legis.legisContact);
                      intent.putExtra("chamber", legis.legisChamber);
                      intent.putExtra("contact", legis.legisContact);
                      intent.putExtra("startterm", legis.termStart);
                                                           //Log.d("guoyue",legis.termStart);
                       intent.putExtra("endterm", legis.termEnd);
                      intent.putExtra("office", legis.legisOffice);
                      intent.putExtra("state", legis.legisState1);
                      intent.putExtra("fax", legis.legisFax);
                      intent.putExtra("birth", legis.legisBirth);
                      intent.setClass(context, DisplayDetail.class);
                       context.startActivity(intent);
                }
            }

            );

        }else{
            viewHolder = (SenateAdapter.ViewHolder) convertView.getTag();
        }
        //设置给list中的元素
        viewHolder.sivIcon.setImageResource(R.mipmap.ic_launcher);
        //new ImageLoader().showImageByThread(viewHolder.ivIcon,mList.get(position).newsIconUrl);//图片传递过去
        viewHolder.sivIcon.setTag(mSList.get(position).legisIconUrl);
        new ImageLoader().showImageByAsyncTask(viewHolder.sivIcon,mSList.get(position).legisIconUrl);
        viewHolder.stvTitle.setText(mSList.get(position).legisName);
        viewHolder.stvContent.setText(mSList.get(position).legisParty);
        return convertView;
    }

    class ViewHolder{
        public TextView stvTitle,stvContent;
        public ImageView sivIcon;
        public ImageButton imButton;
    }
}

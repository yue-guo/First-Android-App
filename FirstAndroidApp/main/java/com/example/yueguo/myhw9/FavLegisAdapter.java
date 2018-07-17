package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YueGuo on 16/11/29.
 */

public class FavLegisAdapter extends BaseAdapter {
    private List<LegisBean> mflList;
    private Context context;
    private LayoutInflater mflInflater;

    public FavLegisAdapter(Context context, List<LegisBean> data){
        mflList = data;
        mflInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return mflList.get(position);
    }

    @Override
    public int getCount() {
        return mflList.size();
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
            convertView = mflInflater.inflate(R.layout.favlegis_layout,null);
            viewHolder.flIcon = (ImageView) convertView.findViewById(R.id.fliv_icon);
            viewHolder.flTitle = (TextView)convertView.findViewById(R.id.fltv_title);
            viewHolder.flContent = (TextView)convertView.findViewById(R.id.fltv_content);
            viewHolder.flButton = (ImageButton)convertView.findViewById(R.id.flimage_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置给list中的元素
        viewHolder.flIcon.setImageResource(R.mipmap.ic_launcher);
        //new ImageLoader().showImageByThread(viewHolder.ivIcon,mList.get(position).newsIconUrl);//图片传递过去
        //viewHolder.flIcon.setTag(mflList.get(position).legisIconUrl);
        //new ImageLoader().showImageByAsyncTask(viewHolder.flIcon,mflList.get(position).legisIconUrl);
        new ImageLoader1().showdetailImageByAsyncTask(viewHolder.flIcon,mflList.get(position).legisIconUrl);
        viewHolder.flTitle.setText(mflList.get(position).legisName);
        viewHolder.flContent.setText(mflList.get(position).legisParty);
        viewHolder.flButton.setOnClickListener(new View.OnClickListener(){
              public void onClick(View v){
              LegisBean legis = mflList.get(position);
              Intent intent = new Intent();
              intent.putExtra("legismark",legis.legismark);
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
              intent.putExtra("facebook",legis.legisFacebook);
              intent.putExtra("twitter",legis.legisTWitter);
              intent.putExtra("website",legis.legisWebsite);
              intent.setClass(context,DisplayDetail.class);
              context.startActivity(intent);
              }
        }

        );
        return convertView;
    }

    class ViewHolder{
        public TextView flTitle,flContent;
        public ImageView flIcon;
        public ImageButton flButton;
    }
}

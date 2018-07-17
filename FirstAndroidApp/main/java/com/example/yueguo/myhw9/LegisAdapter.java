package com.example.yueguo.myhw9;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.*;
import java.util.*;
import android.util.*;

/**
 * Created by YueGuo on 16/11/25.
 */

public class LegisAdapter extends BaseAdapter{
    private List<LegisBean> mList;
    private Context context;
    //private Context context;
    //private LayoutInflater mInflater;
    private LayoutInflater mInflater;
    //private FirstFragment ffragment;

    /*public LegisAdapter(FirstFragment context, List<LegisBean> data){
        mList = data;
        mInflater = LayoutInflater.from(context);
    }*/
    public LegisAdapter(Context context, List<LegisBean> data){
        mList = data;
        mInflater = LayoutInflater.from(context);
        this.context = context;

    }
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
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
            convertView = mInflater.inflate(R.layout.first_layout,null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView)convertView.findViewById(R.id.tv_content);
            viewHolder.imButton = (ImageButton)convertView.findViewById(R.id.image_button1);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置给list中的元素
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        //new ImageLoader().showImageByThread(viewHolder.ivIcon,mList.get(position).newsIconUrl);//图片传递过去
        viewHolder.ivIcon.setTag(mList.get(position).legisIconUrl);
        new ImageLoader().showImageByAsyncTask(viewHolder.ivIcon,mList.get(position).legisIconUrl);
        viewHolder.tvTitle.setText(mList.get(position).legisName);
        viewHolder.tvContent.setText(mList.get(position).legisParty);
        viewHolder.imButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                View view1 = mInflater.inflate(R.layout.first_layout,null);
                view1.setBackgroundColor(Color.BLUE);
                LegisBean legis = mList.get(position);
                Intent intent = new Intent();
                intent.putExtra("id",legis.legisID);
                intent.putExtra("legismark",legis.legismark);
                intent.putExtra("firstname",legis.firstName);
                intent.putExtra("lastname",legis.lastName);
                intent.putExtra("title",legis.legisTitle);
                intent.putExtra("party",legis.legisParty);
                intent.putExtra("imgurl",legis.legisIconUrl);
                intent.putExtra("email",legis.legisEmail);
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
        public TextView tvTitle,tvContent;
        public ImageView ivIcon;
        public ImageButton imButton;
    }


}

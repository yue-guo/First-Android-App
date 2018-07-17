package com.example.yueguo.myhw9;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.*;
import java.util.*;
import org.w3c.dom.Text;
import java.lang.*;
import java.net.*;
import java.text.*;
import java.util.logging.*;
import android.widget.*;
import android.webkit.*;
/**
 * Created by YueGuo on 16/11/27.
 */

public class DisplayDetail extends AppCompatActivity {
    private static final int PROGRESS = 0X1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private ImageView legisstar;
    private ImageView facebook;
    private ImageView twitter;
    private ImageView website;
    //private String mark = "1";

    //private Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legis_detail);


        Intent intent = getIntent();
        SharedPreferences favlegisdetail = getSharedPreferences("legisdata", Context.MODE_PRIVATE);

        final String legisID = intent.getStringExtra("id");
        final String legismark = intent.getStringExtra("legismark");


        final String firstname = intent.getStringExtra("firstname");
        final String lastname = intent.getStringExtra("lastname");
        final String title = intent.getStringExtra("title");
        final String name = title+"."+firstname+","+lastname;
        final String flname = firstname+","+lastname;
        TextView text = (TextView)findViewById(R.id.legis_name);
        text.setText(name);

        final String imageurl = intent.getStringExtra("imgurl");
        //Log.d("guoyue",imageurl);

        ImageView image = (ImageView)findViewById(R.id.legis_image);
        new ImageLoader1().showdetailImageByAsyncTask(image,imageurl);

        final String email = intent.getStringExtra("email");
        TextView text1 = (TextView)findViewById(R.id.legis_email);
        text1.setText(email);

        final String chamber = intent.getStringExtra("chamber");
        TextView text2 = (TextView)findViewById(R.id.legis_chamber);
        text2.setText(chamber);

        final String startterm = intent.getStringExtra("startterm");
        final String startterm1 = getEDate(startterm);
        TextView text3 = (TextView)findViewById(R.id.legis_startterm);
        text3.setText(startterm1);

        final String endterm = intent.getStringExtra("endterm");
        final String endterm1 = getEDate(endterm);
        TextView text4 = (TextView)findViewById(R.id.legis_endterm);
        text4.setText(endterm1);

        final String office = intent.getStringExtra("office");
        TextView text5 = (TextView)findViewById(R.id.legis_office);
        text5.setText(office);

        final String state = intent.getStringExtra("state");
        TextView text6 = (TextView)findViewById(R.id.legis_state);
        text6.setText(state);

        final String fax = intent.getStringExtra("fax");
        TextView text7 = (TextView)findViewById(R.id.legis_fax);
        text7.setText(fax);

        final String birth = intent.getStringExtra("birth");
        final String birth1 = getBDate(birth);
        TextView text8 = (TextView)findViewById(R.id.legis_birth);
        text8.setText(birth1);

        final String contact = intent.getStringExtra("contact");
        TextView text9 = (TextView)findViewById(R.id.legis_contact);
        text9.setText(contact);

        final String legisfacebook = intent.getStringExtra("facebook");
        final String legistwitter = intent.getStringExtra("twitter");
        final String legiswebsite = intent.getStringExtra("website");

        final String party = intent.getStringExtra("party");
        ImageView image1 = (ImageView)findViewById(R.id.legis_party);
        TextView text10 = (TextView)findViewById(R.id.legis_partyname);
        if(party == "R") {
            image1.setImageResource(R.drawable.r);
            text10.setText("Republican");
        }
        else{
            image1.setImageResource(R.drawable.d);
            text10.setText("Democrat");
        }

        //progressbar
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = format.format(date);
        try {
            Date starttime = format.parse(startterm);
            Date endtime = format.parse(endterm);
            Date nowtime1 = format.parse(nowtime);
            //Log.d("guoyue",nowtime1+"");
            //Log.d("guoyue",starttime+"");
            long diff = (nowtime1.getTime()-starttime.getTime())/(endtime.getTime()-starttime.getTime());
            int dif = (int)diff;
            //Log.d("guoyue",dif+" ");
            mProgress = (ProgressBar)findViewById(R.id.legis_termprogress);
            mProgress.incrementProgressBy(dif);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //facebook
        facebook = (ImageView)findViewById(R.id.legis_facebook);
        Log.d("guoyue",legisfacebook);
        facebook.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Log.d("guoyue",legisfacebook);
                if(legisfacebook == "N.A"){
                    Toast.makeText(getApplicationContext(), "The facebook does not exist",
                            Toast.LENGTH_SHORT).show();
                }else {
                    view = View.inflate(getApplicationContext(),R.layout.web,null);
                    WebView web = (WebView)view.findViewById(R.id.webview);
                    Log.d("yue",legisfacebook);
                    web.loadUrl(legisfacebook);

                }
            }
        });

        //twitter
        twitter = (ImageView)findViewById(R.id.legis_twitter);
        twitter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(legistwitter == "N.A") {
                    Toast.makeText(getApplicationContext(), "The twitter does not exist",
                            Toast.LENGTH_SHORT).show();

                }else{
                    view = View.inflate(getApplicationContext(),R.layout.web1,null);
                    WebView web1 = (WebView)view.findViewById(R.id.webview1);
                    //web1 = (WebView) findViewById(R.id.webview);
                    web1.loadUrl(legistwitter);
                }
            }
        });

        //website
        website = (ImageView)findViewById(R.id.legis_website);
        website.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(legiswebsite == "N.A") {
                    Toast.makeText(getApplicationContext(), "The website does not exist",
                            Toast.LENGTH_SHORT).show();
                }else{
                    view = View.inflate(getApplicationContext(),R.layout.web2,null);
                    WebView web2 = (WebView)view.findViewById(R.id.webview2);
                    //web2.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 );
                    //web2 = (WebView) findViewById(R.id.webview);
                    //Log.d("yue",legiswebsite);
                    web2.loadUrl(legiswebsite);
                    //Uri uri =Uri.parse(legiswebsite);
                    //Intent it = new Intent(Intent.ACTION_VIEW,uri);

//                    startActivity(it);
                }
            }
        });

        legisstar = (ImageView)findViewById(R.id.legis_star);
        String dataID = favlegisdetail.getString("legisid","");
        if(dataID.equals(legisID)){
            //Log.d("guoyue","equals");
            legisstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
            legisstar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    //Log.d("test","cancelled!");
                    legisstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
                    SharedPreferences.Editor pref = getSharedPreferences("legisdata",MODE_PRIVATE).edit();
                    pref.clear();
                    pref.commit();

                }
            });
        }else{
            legisstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
            legisstar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("test","selected!");
                    legisstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
                    String mark = "2";
                    SharedPreferences.Editor editor = getSharedPreferences("legisdata",MODE_PRIVATE).edit();
                    editor.putString("legisid",legisID);
                    editor.putString("legistitle",title);
                    editor.putString("legisfirst",firstname);
                    editor.putString("legislast",lastname);
                    editor.putString("legisimage",imageurl);
                    editor.putString("legisflname",flname);
                    editor.putString("legisname",name);
                    editor.putString("legisemail",email);
                    editor.putString("legischamber",chamber);
                    editor.putString("legisstart",startterm);
                    editor.putString("legisend",endterm);
                    editor.putString("legisoffice",office);
                    editor.putString("legisstate",state);
                    editor.putString("legisfax",fax);
                    editor.putString("legisbirth",birth);
                    editor.putString("legiscontact",contact);
                    editor.putString("legisparty",party);
                    editor.putString("legisfacebook",legisfacebook);
                    editor.putString("legistwitter",legistwitter);
                    editor.putString("legiswebsite",legiswebsite);
                    editor.putString("legismark",mark);
                    //editor.clear();
                    editor.commit();

                }
            });
        }

    }

    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[1].substring(0,1).toUpperCase() + k[1].substring(1) + " " + k[2] + "," + "20"+k[5].substring(2, 4);
        //return k[1].toUpperCase() + " " + k[2] + "," + "20"+k[5].substring(2, 4);
    }

    public static String getBDate(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[1].substring(0,1).toUpperCase() + k[1].substring(1) + " " + k[2] + "," + "19"+k[5].substring(2, 4);

    }


}



package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by YueGuo on 16/11/29.
 */

public class DisplayBillDetail extends AppCompatActivity {
    private String bmark = "1";
    private ImageView billstar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_detail);
        Intent intent = getIntent();
        SharedPreferences favbilldetail = getSharedPreferences("billdata", Context.MODE_PRIVATE);
        final String billid = intent.getStringExtra("billid");
        TextView text = (TextView)findViewById(R.id.bill_id);
        text.setText(billid);

        final String title = intent.getStringExtra("stitle");
        TextView text1 = (TextView)findViewById(R.id.bill_title);
        text1.setText(title);

        final String billtype = intent.getStringExtra("billtype");
        TextView text2 = (TextView)findViewById(R.id.bill_type);
        text2.setText(billtype);

        final String sponsor = intent.getStringExtra("sponsor");
        TextView text3 = (TextView)findViewById(R.id.bill_sponsor);
        text3.setText(sponsor);

        String chamber1 = intent.getStringExtra("chamber");
        final String chamber =chamber1.substring(0,1).toUpperCase()+chamber1.substring(1);
        TextView text4 = (TextView)findViewById(R.id.bill_chamber);
        text4.setText(chamber);

        String status1 = intent.getStringExtra("status");
        final String status = status1.substring(0,1).toUpperCase()+status1.substring(1);
        TextView text5 = (TextView)findViewById(R.id.bill_status);
        text5.setText(status);

        final String introducedon = intent.getStringExtra("introducedon");
        TextView text6 = (TextView)findViewById(R.id.bill_introducedon);
        text6.setText(introducedon);

        final String congressurl = intent.getStringExtra("congressurl");
        TextView text7 = (TextView)findViewById(R.id.bill_congressurl);
        text7.setText(congressurl);

        final String version = intent.getStringExtra("version");
        TextView text8 = (TextView)findViewById(R.id.bill_version);
        text8.setText(version);

        final String billurl = intent.getStringExtra("billurl");
        TextView text9 = (TextView)findViewById(R.id.bill_billurl);
        text9.setText(billurl);

        billstar = (ImageView)findViewById(R.id.bill_star);
        String billID = favbilldetail.getString("billid","");
        if(billid.equals(billID)){
            billstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
            billstar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    billstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
                    SharedPreferences.Editor prefb = getSharedPreferences("billdata",MODE_PRIVATE).edit();
                    prefb.clear();
                    prefb.commit();
                }
            });
        }else{
            billstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
            billstar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    billstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
                    bmark = "2";
                    SharedPreferences.Editor beditor = getSharedPreferences("billdata", MODE_PRIVATE).edit();
                    beditor.putString("billid", billid);
                    beditor.putString("billtitle", title);
                    beditor.putString("billintroducedon", introducedon);
                    beditor.putString("billtype", billtype);
                    beditor.putString("billsponsor", sponsor);
                    beditor.putString("billchamber", chamber);
                    beditor.putString("billstatus", status);
                    beditor.putString("billcongressurl", congressurl);
                    beditor.putString("billversion", version);
                    beditor.putString("billurl", billurl);
                    beditor.commit();

                }
            });


        }

    }
}

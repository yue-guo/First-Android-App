package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.*;
import android.app.*;

/**
 * Created by YueGuo on 16/11/29.
 */

public class DisplayCommDetail extends AppCompatActivity {
    private String cmark = "1";
    private ImageView commstar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_detail);
        Intent intent = getIntent();
        SharedPreferences favcommdetail = getSharedPreferences("commdata", Context.MODE_PRIVATE);
        final String commid = intent.getStringExtra("commid");
        TextView text = (TextView)findViewById(R.id.comm_id);
        text.setText(commid);

        final String commname = intent.getStringExtra("commname");
        TextView text1 = (TextView)findViewById(R.id.comm_name);
        text1.setText(commname);

        final String commchamber = intent.getStringExtra("commchamber");
        TextView text2 = (TextView)findViewById(R.id.comm_chamber);
        text2.setText(commchamber);
        //Log.d("guoyue",commchamber);
        ImageView image = (ImageView)findViewById(R.id.comm_chamberimage);
        if(commchamber == "House"){

            image.setImageDrawable(getResources().getDrawable(R.drawable.h));
        }else{

            image.setImageDrawable(getResources().getDrawable(R.drawable.sv));
        }

        final String commparent = intent.getStringExtra("commparent");
        TextView text3 = (TextView)findViewById(R.id.comm_parent);
        text3.setText(commparent);

        final String commcontact = intent.getStringExtra("commcontact");
        TextView text4 = (TextView)findViewById(R.id.comm_contact);
        text4.setText(commcontact);

        final String commoffice = intent.getStringExtra("commoffice");
        TextView text5 = (TextView)findViewById(R.id.comm_office);
        text5.setText(commoffice);

        commstar = (ImageView)findViewById(R.id.comm_star);

        String commID = favcommdetail.getString("commid","");
        if(commID.equals(commid)){
            commstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
            commstar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
                    SharedPreferences.Editor prefc = getSharedPreferences("commdata",MODE_PRIVATE).edit();
                    prefc.clear();
                    prefc.commit();
                }
            });
        }else{
            commstar.setImageDrawable(getResources().getDrawable(R.drawable.fav));
            commstar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cmark = "2";
                    commstar.setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
                    SharedPreferences.Editor ceditor = getSharedPreferences("commdata", MODE_PRIVATE).edit();
                    ceditor.putString("commid",commid);
                    ceditor.putString("commname",commname);
                    ceditor.putString("commchamber",commchamber);
                    ceditor.putString("commparent",commparent);
                    ceditor.putString("commcontact",commcontact);
                    ceditor.putString("commoffice",commoffice);
                    ceditor.commit();

                }
            });


        }

    }
}

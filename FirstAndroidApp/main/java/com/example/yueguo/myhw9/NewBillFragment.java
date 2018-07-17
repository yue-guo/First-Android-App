package com.example.yueguo.myhw9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by YueGuo on 16/11/26.
 */

public class NewBillFragment extends Fragment{
    private ListView mListView;
    private static String URL="http://yueguoapplication.azmymxatde.us-west-2.elasticbeanstalk.com?bill=nbill";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newbill_fragment,null);
        mListView = (ListView)view.findViewById(R.id.nblv_main);
        new NBillsAsyncTask().execute(URL);
        return view;
    }

    private List<BillBean> getJsonData(String url){
        List<BillBean> billBeanList = new ArrayList<>();
        String dis;
        //获取json格式字符串
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            BillBean billBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    billBean = new BillBean();
                    String billid = jsonObject.getString("bill_id");
                    billBean.billID = billid.toUpperCase();
                    //title
                    String stitle = jsonObject.getString("short_title");
                    //Log.d("guoyue",billBean.billShortTitle);
                    String ltitle = jsonObject.getString("official_title");
                    //Log.d("yueguo",billBean.billLongTitle);
                    if(stitle != "null"){
                        billBean.billShortTitle = stitle;
                        //Log.d("yue","2222");

                    }else{
                        billBean.billShortTitle = ltitle;
                        //Log.d("yue","111111");
                    }
                    //Log.d("guoyue",billBean.billShortTitle);
                    String introducedon = jsonObject.getString("introduced_on");
                    billBean.introducedon = getDDate(introducedon);
                    billBean.billIntroducedOn = getEDate(introducedon);
                    //billBean.billIntroducedOn = jsonObject.getString("introduced_on");
                    String billtype = jsonObject.getString("bill_type");
                    billBean.billType = billtype.toUpperCase();
                    //sponsor
                    String first_name = jsonObject.getJSONObject("sponsor").getString("first_name");
                    String last_name = jsonObject.getJSONObject("sponsor").getString("last_name");
                    String title = jsonObject.getJSONObject("sponsor").getString("title");
                    billBean.billSponsor = title+"."+first_name+","+last_name;
                    //bill chamber
                    billBean.billChamber = jsonObject.getString("chamber");
                    //status
                    String status = jsonObject.getJSONObject("history").getString("active");
                    if(status == "true"){
                        billBean.billStatus = "active";
                    }else{
                        billBean.billStatus = "new";
                    }
                    billBean.billCongressURL = jsonObject.getJSONObject("urls").getString("congress");
                    //version
                    if(jsonObject.has("last_version")){
                        billBean.billVersion = jsonObject.getJSONObject("last_version").getString("version_name");
                    }else{
                        billBean.billVersion = "N.A";
                    }
                    //pdf
                    if(jsonObject.has("last_version")){
                        billBean.billURL = jsonObject.getJSONObject("last_version").getJSONObject("urls").getString("pdf");
                    }else{
                        billBean.billVersion = "N.A";
                    }
                    billBeanList.add(billBean);
                    Collections.sort(billBeanList,Collections.reverseOrder());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("xys",jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billBeanList;
    }

    private String readStream(InputStream is){
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            while((line=br.readLine()) != null){
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    class NBillsAsyncTask extends AsyncTask<String,Void,List<BillBean>> {
        @Override
        protected List<BillBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(final List<BillBean> billBean) {
            super.onPostExecute(billBean);
            //LegisAdapter adapter = new LegisAdapter(FirstFragment.this,legisBean);
            NewBillAdapter adapter = new NewBillAdapter(getActivity(),billBean);
            mListView.setAdapter(adapter);
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

    public static Date getDDate(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.yueguo.myhw9;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.io.*;
import java.net.*;



/**
 * Created by YueGuo on 16/11/24.
 */

public class FirstFragment extends Fragment{
    Map<String, Integer> mapIndex;
    ListView LegisList;
    private ListView mListView;
    //private static String URL="http://www.imooc.com/api/teacher?type=4&num=30";
    private static String URL="http://yueguoapplication.azmymxatde.us-west-2.elasticbeanstalk.com?legislator=all";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mListView = (ListView) findViewById(R.id.lv_main);
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        mListView = (ListView)view.findViewById(R.id.lv_main);
        new NewsAsyncTask().execute(URL);
        return view;
        //return inflater.inflate(R.layout.first_fragment,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*
    将url对应的json格式转化成legisbean
     */
    private List<LegisBean> getJsonData(String url){
        List<LegisBean> legisBeanList = new ArrayList<>();
        String dis;
        //获取json格式字符串
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            LegisBean legisBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    legisBean = new LegisBean();
                    String iconurl = jsonObject.getString("bioguide_id");
                    legisBean.legisID = iconurl;
                    //legisBean.legisIconUrl = jsonObject.getString("picSmall");
                    legisBean.legisIconUrl = "https://theunitedstates.io/images/congress/original/"+iconurl+".jpg";
                    String first_name = jsonObject.getString("first_name");
                    String last_name = jsonObject.getString("last_name");
                    //legisBean.legisName = jsonObject.getString("first_name");
                    legisBean.legisName = first_name+","+last_name;
                    String party = jsonObject.getString("party");
                    String state = jsonObject.getString("state_name");
                    String district = jsonObject.getString("district");
                    legisBean.firstName = jsonObject.getString("first_name");
                    legisBean.lastName = jsonObject.getString("last_name");
                    //legisBean.legisEmail = jsonObject.getString("contact_form");
                    //email
                    if(jsonObject.has("email")){
                        legisBean.legisEmail = jsonObject.getString("email");
                    }else{
                        legisBean.legisEmail = "N.A";
                    }
                    legisBean.legisTitle = jsonObject.getString("title");
                    legisBean.legisChamber = jsonObject.getString("chamber").substring(0,1).toUpperCase()+jsonObject.getString("chamber").substring(1);
                    legisBean.legisContact = jsonObject.getString("phone");
                    legisBean.termStart = jsonObject.getString("term_start");
                    legisBean.termEnd = jsonObject.getString("term_end");
                    legisBean.legisOffice = jsonObject.getString("office");
                    legisBean.legisState = jsonObject.getString("state_name");
                    legisBean.state = jsonObject.getString("state_name");
                    legisBean.legisState1 = jsonObject.getString("state");
                    legisBean.legisFax = jsonObject.getString("fax");
                    legisBean.legisBirth = jsonObject.getString("birthday");
                    legisBean.legismark = "1";
                    //facebook
                    if(jsonObject.has("facebook_id")){
                        legisBean.legisFacebook = "http://facebook.com/"+jsonObject.getString("facebook_id");
                    }
                    else{
                        legisBean.legisFacebook = "N.A";
                    }
                    //twitter
                    if(jsonObject.has("twitter_id")){
                        legisBean.legisTWitter = "http://twitter.com/"+jsonObject.getString("twitter_id");
                    }
                    else{
                        legisBean.legisTWitter = "N.A";
                    }
                    //website
                    if(jsonObject.has("website")){
                        legisBean.legisWebsite = jsonObject.getString("website");
                    }
                    else{
                        legisBean.legisWebsite = "N.A";
                    }
                    if(district != "null"){
                        dis = "District"+" "+district;
                    }else{
                        dis = "N.A";
                    }
                    legisBean.legisParty = "("+party+")"+state+"-"+dis;
                    //将legisbean放入list
                    legisBeanList.add(legisBean);
                    Collections.sort(legisBeanList);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("xys",jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return legisBeanList;
    }

    /*
    通过inputStram解析网页返回的数据
     */
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



    /*
    实现网络的异步访问
     */
    //创建内部类实现Asynctask
    class NewsAsyncTask extends AsyncTask<String,Void,List<LegisBean>>{
        @Override
        protected List<LegisBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(final List<LegisBean> legisBean) {
            super.onPostExecute(legisBean);
            //LegisAdapter adapter = new LegisAdapter(FirstFragment.this,legisBean);
            LegisAdapter adapter = new LegisAdapter(getActivity(),legisBean);
            mListView.setAdapter(adapter);
        }
    }
}

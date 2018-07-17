package com.example.yueguo.myhw9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SenateFragment extends Fragment {
    private ListView mListView;
    String dis;
    private static String URL="http://yueguoapplication.azmymxatde.us-west-2.elasticbeanstalk.com?legislator=senate";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.senate_fragment,container,false);
        mListView = (ListView)view.findViewById(R.id.slv_main);
        new SenateFragment.SenateAsyncTask().execute(URL);
        return view;
        //return inflater.inflate(R.layout.senate_fragment,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private List<LegisBean> getJsonData(String url){
        List<LegisBean> legisBeanList = new ArrayList<>();
        //
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
                    legisBean.legisState1 = jsonObject.getString("state");
                    legisBean.state = jsonObject.getString("state_name");
                    legisBean.legisFax = jsonObject.getString("fax");
                    legisBean.legisBirth = jsonObject.getString("birthday");
                    if(district == "null"){
                        dis = "N.A";
                    }else{
                        dis = "District"+" "+district;
                    }
                    legisBean.legisParty = "("+party+")"+state+"-"+dis;
                    //legisBean.legisParty = jsonObject.getString("party");
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

    class SenateAsyncTask extends AsyncTask<String,Void,List<LegisBean>>{
        @Override
        protected List<LegisBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<LegisBean> legisBean) {
            super.onPostExecute(legisBean);
            //LegisAdapter adapter = new LegisAdapter(FirstFragment.this,legisBean);
            SenateAdapter adapter = new SenateAdapter(getActivity(),legisBean);
            mListView.setAdapter(adapter);
        }
    }
}

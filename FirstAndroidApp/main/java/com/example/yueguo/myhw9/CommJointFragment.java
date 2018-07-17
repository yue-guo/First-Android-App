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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YueGuo on 16/11/28.
 */

public class CommJointFragment extends Fragment{
    private ListView mListView;
    private static String URL="http://yueguoapplication.azmymxatde.us-west-2.elasticbeanstalk.com?committees=cjoint";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commjoint_fragment,container,false);
        mListView = (ListView) view.findViewById(R.id.cjlv_main);
        new CommJAsyncTask().execute(URL);
        return view;

    }

    private List<CommBean> getJsonData(String url){
        List<CommBean> commBeanList = new ArrayList<>();
        //获取json格式字符串
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            CommBean commBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    commBean = new CommBean();
                    String commid = jsonObject.getString("committee_id");
                    commBean.commID = commid.toUpperCase();

                    commBean.commName = jsonObject.getString("name");
                    String commChamber1 = jsonObject.getString("chamber");
                    commBean.commChamber = commChamber1.substring(0,1).toUpperCase()+commChamber1.substring(1);
                    //parent
                    if(jsonObject.has("parent_committee_id")) {
                        commBean.commParentComm = jsonObject.getString("parent_committee_id");
                    }
                    else{
                        commBean.commParentComm = "N.A";
                    }
                    //phone
                    if(jsonObject.has("phone")) {
                        commBean.commContact = jsonObject.getString("phone");
                    }else{
                        commBean.commContact = "N.A";
                    }
                    //office
                    if(jsonObject.has("office")){
                        commBean.commOffice = jsonObject.getString("office");
                    }else{
                        commBean.commOffice = "N.A";
                    }

                    commBeanList.add(commBean);
                    Collections.sort(commBeanList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("xys",jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commBeanList;
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

    class CommJAsyncTask extends AsyncTask<String,Void,List<CommBean>> {
        @Override
        protected List<CommBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(final List<CommBean> commBean) {
            super.onPostExecute(commBean);
            //LegisAdapter adapter = new LegisAdapter(FirstFragment.this,legisBean);
            CommJointAdapter adapter = new CommJointAdapter(getActivity(),commBean);
            mListView.setAdapter(adapter);
        }
    }
}

package com.example.yueguo.myhw9;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YueGuo on 16/11/29.
 */

public class FavLegisFragment extends Fragment {
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favlegis_fragment,null);
        mListView = (ListView)view.findViewById(R.id.fllv_main);
        final List<LegisBean> favlegisBeanList = new ArrayList<>();
        LegisBean favlegisBean = new LegisBean();
        SharedPreferences favlegis = getActivity().getSharedPreferences("legisdata", Context.MODE_PRIVATE);
        //Log.d("yue",favlegis.getString("legisimage",""));
        if(favlegis.contains("legisimage")) {
            Log.d("test","!!!!!!");
            favlegisBean.legisIconUrl = favlegis.getString("legisimage","");
            favlegisBean.firstName = favlegis.getString("legisfirst","");
            favlegisBean.lastName = favlegis.getString("legislast","");
            favlegisBean.legisTitle = favlegis.getString("legistitle","");
            favlegisBean.legisName = favlegis.getString("legisflname","");
            favlegisBean.fullName = favlegis.getString("legisname", "");
            favlegisBean.legisEmail = favlegis.getString("legisemail", "");
            favlegisBean.legisChamber = favlegis.getString("legischamber", "");
            favlegisBean.termStart = favlegis.getString("legisstart", "");
            favlegisBean.termEnd = favlegis.getString("legisend", "");
            favlegisBean.legisOffice = favlegis.getString("legisoffice", "");
            favlegisBean.legisState = favlegis.getString("legisstate", "");
            favlegisBean.legisFax = favlegis.getString("legisfax", "");
            favlegisBean.legisBirth = favlegis.getString("legisbirth", "");
            favlegisBean.legisContact = favlegis.getString("legiscontact", "");
            favlegisBean.legisParty = favlegis.getString("legisparty", "");
            favlegisBean.legismark = favlegis.getString("legismark","");
            favlegisBean.legisFacebook = favlegis.getString("legisfacebook","");
            favlegisBean.legisTWitter = favlegis.getString("legistwitter","");
            favlegisBean.legisWebsite = favlegis.getString("legiswebsite","");
            favlegisBeanList.add(favlegisBean);
            Log.d("guoyue1", favlegisBeanList.get(0).legisParty);

            setdetailAdapter(favlegisBeanList);

            return view;
        }else{

            return view;
        }
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setdetailAdapter(List<LegisBean> favlegisBeanList){
        FavLegisAdapter adapter = new FavLegisAdapter(getActivity(),favlegisBeanList);
        mListView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    public void setDAdapter(List<LegisBean> favlegisBeanList){
        FavDLegisAdapter adapter = new FavDLegisAdapter(getActivity(),favlegisBeanList);
        mListView.setAdapter(adapter);
    }

}

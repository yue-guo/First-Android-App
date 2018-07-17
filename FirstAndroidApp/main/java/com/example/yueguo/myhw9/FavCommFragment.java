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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YueGuo on 16/11/29.
 */

public class FavCommFragment extends Fragment {
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favcomm_fragment,null);
        mListView = (ListView)view.findViewById(R.id.fclv_main);
        final List<CommBean> favCommBeanList = new ArrayList<>();
        CommBean favcommBean = new CommBean();
        SharedPreferences favcomm = getActivity().getSharedPreferences("commdata", Context.MODE_PRIVATE);
        if(favcomm.contains("commid")){
            favcommBean.commID = favcomm.getString("commid","");
            favcommBean.commName = favcomm.getString("commname","");
            favcommBean.commChamber = favcomm.getString("commchamber","");
            favcommBean.commParentComm = favcomm.getString("commparent","");
            favcommBean.commContact = favcomm.getString("commcontact","");
            favcommBean.commOffice = favcomm.getString("commoffice","");
            favCommBeanList.add(favcommBean);
            setcommAdapter(favCommBeanList);
            return view;
        }else {
            return view;
        }
    }

    public void setcommAdapter(List<CommBean> favcommBeanList){
        FavCommAdapter adapter = new FavCommAdapter(getActivity(),favcommBeanList);
        mListView.setAdapter(adapter);
    }


}

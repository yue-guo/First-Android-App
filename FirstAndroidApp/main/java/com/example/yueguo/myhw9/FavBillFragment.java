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
import java.util.*;
import android.util.*;

/**
 * Created by YueGuo on 16/11/29.
 */

public class FavBillFragment extends Fragment {
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favbill_fragment,null);
        mListView = (ListView)view.findViewById(R.id.fblv_main);
        final List<BillBean> favBillBeanList = new ArrayList<>();
        BillBean favbillBean = new BillBean();
        SharedPreferences favbill = getActivity().getSharedPreferences("billdata", Context.MODE_PRIVATE);
        if(favbill.contains("billid")){
            favbillBean.billID = favbill.getString("billid","");
            favbillBean.billShortTitle = favbill.getString("billtitle","");
            favbillBean.billIntroducedOn = favbill.getString("billintroducedon","");
            //Log.d("yuefav",favbillBean.billIntroducedOn);
            favbillBean.billType = favbill.getString("billtype","");
            favbillBean.billSponsor = favbill.getString("billsponsor","");
            favbillBean.billChamber = favbill.getString("billchamber","");
            favbillBean.billStatus = favbill.getString("billstatus","");
            favbillBean.billCongressURL = favbill.getString("billcongressurl","");
            favbillBean.billVersion = favbill.getString("billversion","");
            favbillBean.billURL = favbill.getString("billurl","");
            favBillBeanList.add(favbillBean);
            setbillAdapter(favBillBeanList);
            return view;
        }else {
            return view;
        }
    }

    public void setbillAdapter(List<BillBean> favbillBeanList){
        FavBillAdapter adapter = new FavBillAdapter(getActivity(),favbillBeanList);
        mListView.setAdapter(adapter);
    }
}

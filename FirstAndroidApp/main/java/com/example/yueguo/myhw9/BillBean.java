package com.example.yueguo.myhw9;

import java.util.*;
/**
 * Created by YueGuo on 16/11/28.
 */

public class BillBean implements Comparable<BillBean>{
    public String billID;
    public String billShortTitle;
    public String billIntroducedOn;
    public String billType;
    public String billSponsor;
    public String billChamber;
    public String billStatus;
    public String billCongressURL;
    public String billVersion;
    public String billURL;
    public Date introducedon;
    //public String billLongTitle;

    public Date getBillIntroducedOn(){
        return introducedon;
    }

    public void setBillIntroducedOn(Date introducedOn){
        this.introducedon = introducedOn;
    }

    public int compareTo(BillBean billBean){
        return this.introducedon.compareTo(billBean.getBillIntroducedOn());
    }



}

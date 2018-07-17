package com.example.yueguo.myhw9;

/**
 * Created by YueGuo on 16/11/29.
 */

public class CommBean implements Comparable<CommBean>{
    public String commID;
    public String commName;
    public String commChamber;
    public String commParentComm;
    public String commContact;
    public String commOffice;

    public String getCommName(){
        return commName;
    }

    public void setCommName(String commName){
        this.commName = commName;
    }

     public int compareTo(CommBean commBean){
         return this.commName.compareTo(commBean.getCommName());
     }

}

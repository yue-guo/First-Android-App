package com.example.yueguo.myhw9;

/**
 * Created by YueGuo on 16/11/24.
 */

public class LegisBean implements Comparable<LegisBean>{
    /*public String newsIconUrl;
    public String newsTitle;
    public String newsContent;*/
    public String legisIconUrl;
    public String legisID;
    public String firstName;
    public String lastName;
    public String fullName;
    public String termStart;
    public String termEnd;
    public String legisOffice;
    public String legisState;
    public String state;
    public String legisFax;
    public String legisBirth;
    public String legisName;
    public String legisParty;
    public String legisEmail;
    public String legisFacebook;
    public String legisTWitter;
    public String legisWebsite;
    public String legisChamber;
    public String legisTitle;
    public String legisContact;
    public String legisState1;
    public String legismark;

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public int compareTo(LegisBean legisBean){
        return this.state.compareTo(legisBean.getState());
    }



}

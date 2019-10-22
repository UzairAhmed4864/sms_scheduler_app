package com.example.sms.modelclass;

import com.google.gson.annotations.SerializedName;

public class HistoryModelClass {


    @SerializedName("name")
    public String name;
    @SerializedName("msg")
    public String msg;
    @SerializedName("id")
    public int id;
    @SerializedName("time")
    public long time;


    public HistoryModelClass(String name, String msg , long time) {

        this.name = name;
        this.msg = msg;
        this.time = time;

    }

    public HistoryModelClass() {

    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public String getName() {
        return name;
    }



    public String getMsg() {
        return msg;
    }

    public long getTime() {
        return time;
    }


}

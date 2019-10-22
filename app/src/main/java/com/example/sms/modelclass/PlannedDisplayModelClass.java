package com.example.sms.modelclass;

public class PlannedDisplayModelClass {


    public String name , msg , time , date , freq , check , csv ;
    public int id;




    public PlannedDisplayModelClass( String name, String msg , String csv, String time , String date , String check) {

        //this.id = id;
        this.name = name;
        this.msg = msg;
        this.csv = csv;
        this.time = time;
        this.date = date;
        this.check = check;
        //this.freq = freq;
        //this.check = check;

    }

    public PlannedDisplayModelClass(){}


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public void setCheck(String check) {
        this.check = check;
    }


    public String getName() {
        return name;
    }


    public String getMsg() {
        return msg;
    }

    public String getDate(){return date;}

    public String getTime() {
        return time;
    }
    public String getFreq() {
        return freq;
    }
    public String getCheck() {
        return check;
    }
    public String getCSV() {
        return csv;
    }

    public void setCSV(String csv) { this.csv = csv; }


}

package com.example.sms.modelclass;

import java.util.ArrayList;

public class GroupSelectModelClass extends ArrayList<GroupSelectModelClass> {

    private String title, title1;
    public String name , number;
    public int id;

    public GroupSelectModelClass(){}

    public GroupSelectModelClass(String name , String number) {
        this.name = name;
        this.number = number;
    }


    public String getTitle() {
        return title;
    }

    public String getTitle1() {
        return title1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}

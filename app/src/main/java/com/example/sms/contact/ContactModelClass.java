package com.example.sms.contact;

public class ContactModelClass {

    private String title1;
    private String title;


    public int id;
    public String name;
    private String position;
    private int height;
    private boolean isSelected;
    private long relativeGroupId;

    public ContactModelClass() {
    }

    public ContactModelClass(String name, String position, int height) {
        //this.id = id;
        this.name = name;
        this.position = position;
        this.height = height;
        this.isSelected = isSelected;
        this.relativeGroupId = relativeGroupId;
    }



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getRelativeGroupId() {
        return relativeGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return position;
    }

    public void setNumber(String position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    //public String toString() {
      //  return id + " - " +name + " - " + position + " - " + height + " cm";
    //}

    public String toString() {
        return id + " ";
    }

    public ContactModelClass(String title1, String title ) {
        this.title1 = title1;
        this.title = title;


    }

    public String getPhNumber() {
        return title1;
    }

    public String getPhName() {
        return title;
    }




}

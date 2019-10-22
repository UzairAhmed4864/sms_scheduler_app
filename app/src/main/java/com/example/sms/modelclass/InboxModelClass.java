package com.example.sms.modelclass;

public class InboxModelClass {

    private String message;
    private boolean state;



    public InboxModelClass(String message,  boolean state ) {
        this.message = message;
        this.state = state;

    }

    public String getMessage() {
        return message;
    }

    public boolean getState() {
        return state;
    }


}

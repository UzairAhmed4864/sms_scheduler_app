package com.example.sms.hatkemessenger;

import android.content.Context;
import android.content.Intent;

import com.example.sms.hatkemessenger.activities.MainActivity;

public class Navigator {
    private Navigator(){}

    public static Navigator getInstance() {
        return NavigatorHolder.INSTANCE;
    }

    private static class NavigatorHolder {
        private static final Navigator INSTANCE = new Navigator();
    }

    public void navigateToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
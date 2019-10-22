package com.example.sms;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.HistoryModelClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    SQLiteDatabaseHandler db;
    SQLiteDatabase db1;



    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context,"THIS IS MY ALARM",Toast.LENGTH_LONG).show();\

        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(context);
        db1 = helper.getWritableDatabase();
        db = new SQLiteDatabaseHandler(context);
        long currentTime = Calendar.getInstance().getTimeInMillis();

        //String message = intent.getStringExtra(Constants.KEY_INTENT_MESSAGE);
        String csvid = intent.getStringExtra(Constants.KEY_INTENT_PLANNED_ID);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

//        String idmsg = db.fetchContact(db1 , number );

        String id = PlanSMS.getIdContact(csvid);
        String msg = PlanSMS.getMsgContact(csvid);

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            PlanSMS.sendSMSone(msg , id);

        String check = db.fetchCheck(db1 , csvid );
        String contactname = db.fetchNameContact(db1 , id);
        String groupname = db.fetchNameGroup(db1 , id);

            if(check.equals("contact")){

                String numberToSend = db.fetchContact(db1 , id);

                sendSms(numberToSend , msg);
                HistoryModelClass historyModelClass = new HistoryModelClass(msg, contactname, currentTime);
                db.addHistory(historyModelClass);
            }

            else if(check.equals("group")){

                ArrayList<String> arrayListCSV = new ArrayList<String>(Arrays.asList(id.split(",")));
                ArrayList<String> arrayContactNumbers = new ArrayList<String>();

                String[] csvvalue = new String[arrayListCSV.size()];

                for (int i = 1; i < arrayListCSV.size(); i++) {
                    csvvalue[i] = arrayListCSV.get(i);
                    arrayContactNumbers.add(db.fetchContact(db1 , csvvalue[i]));
                }

                String[] numbervalue = new String[arrayContactNumbers.size()];
                for(int j = 0 ; j < arrayContactNumbers.size() ; j++){

                    numbervalue[j] = arrayContactNumbers.get(j);
                    sendSms(numbervalue[j] , msg);

//            addMessageToSent(numbervalue[j],msg);
//            sendSms(numbervalue[j],msg);

                }
                HistoryModelClass historyModelClass = new HistoryModelClass(msg, groupname, currentTime);
                db.addHistory(historyModelClass);

            }


//        String allItems = ""; //used to display in the toast
//
//        for(String str : arrayContactNumbers){
//            allItems = allItems + "\n" + str; //adds a new line between items
//        }






        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm actived!")
                .setContentText(msg + " " + check)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentInfo("Info")
                .setSound(uri);


        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1,builder.build());
    }

    private void sendSms(String number, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message,
                null, null);
        Log.d("sent to", ":"+ number);


    }

}

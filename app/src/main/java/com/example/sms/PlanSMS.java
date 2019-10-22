package com.example.sms;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.GroupSelectModelClass;
import com.example.sms.modelclass.HistoryModelClass;
import com.example.sms.modelclass.PlannedDisplayModelClass;

import static com.example.sms.Constants.KEY_INTENT_MESSAGE;
import static com.example.sms.Constants.KEY_INTENT_NUMBER;
import static com.example.sms.Constants.KEY_INTENT_PLANNED_ID;

public class PlanSMS extends AppCompatActivity {
    RadioButton radioButton;
    RadioGroup group;
    Button send , select ,later , plan ,btncontact, btngroup ;
    EditText edttxt;
    TextView txtview , date , time , frequency , txtviewnum , txtviewstatus;
    ImageView btn ,speak, msg;

    private final int REQ_CODE = 100;
    private static SQLiteDatabase db1;
    private static SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_plan);

        send = findViewById(R.id.send);
        select = findViewById(R.id.select);
        group = findViewById(R.id.radios);
        msg = findViewById(R.id.msg);
        edttxt = findViewById(R.id.edttxt);
        txtview = findViewById(R.id.txtview);
        later = findViewById(R.id.later);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        frequency = findViewById(R.id.frequency);
        btn = findViewById(R.id.btn);
        txtviewnum = findViewById(R.id.txtviewnum);
        txtviewstatus = findViewById(R.id.txtviewstatus);
        plan = findViewById(R.id.createplan);
        speak = findViewById(R.id.speak);
        btncontact = findViewById(R.id.btncontact);
        btngroup = findViewById(R.id.btngroup);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(this);
        db1 = helper.getWritableDatabase();
        db = new SQLiteDatabaseHandler(this);

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(PlanSMS.this, SendLaterActivity.class);
                startActivityForResult(im, 4);
            }
        });


            speak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                    try {
                        startActivityForResult(intent, REQ_CODE);
                    } catch (ActivityNotFoundException a) {
                        Toast.makeText(getApplicationContext(),
                                "Sorry your device not supported",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });


        //Intent intent = getIntent();
        //String value = (String) intent.getSerializableExtra("key_clicktemplate");

        //Intent intent1 = getIntent();
        //String value1 = (String) intent.getSerializableExtra("key_clicktemplate1");





        //String value = getIntent().getExtras().getString("key_clicktemplate");



        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent im = new Intent(PlanSMS.this, Template.class);
                startActivityForResult(im, 2);
            }
        });



        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanSMS.this, ContactsSelect.class);
                startActivityForResult(i, 1);
            }
        });


        btngroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanSMS.this, SelectGroup.class);
                startActivityForResult(i, 3);
            }
        });

//        select.setOnClickListener(new View.OnClickListener() {
//    @SuppressLint("ResourceType")
//    @Override
//    public void onClick(View view) {
//
//
//        int isSelected = group.getCheckedRadioButtonId();
//        Log.w("uzair" , String.valueOf(isSelected));
//
//        if(isSelected == -1){
//
//            Toast.makeText(PlanSMS.this, "Please Select Any One Box",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        else {
//            radioButton = findViewById(isSelected);
//            String selected = radioButton.getText().toString();
//            // Log.w("uzair" , selected);
//            //int selected1 = group.getCheckedRadioButtonId();
//            //Log.w("uzair1" , selected1);
//
//            if (selected.equals("Groups")) {
//                // Toast.makeText(PlanSMS.this,"Group", Toast.LENGTH_SHORT).show();
//
//                Intent i = new Intent(PlanSMS.this, SelectGroup.class);
//                startActivityForResult(i, 3);
//            } else if (selected.equals("Contacts")) {
//                //Toast.makeText(PlanSMS.this,"Contacts",Toast.LENGTH_SHORT).show();
//
//                Intent i = new Intent(PlanSMS.this, ContactsSelect.class);
//                startActivityForResult(i, 1);
//
//                //Intent intent = new Intent(PlanSMS.this, ContactsSelect.class);
//                //startActivity(intent);
//            }
//                 }
//
//
//
//             }
//
//        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edit = edttxt.getText().toString();
                String empty = "";
                if ((txtview.getText().equals(empty)) || (edit.equals(empty)) || date.getText().equals(empty))  {

                    Toast.makeText(PlanSMS.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else {

                    String contact = "(contact)";
                    String group = "(group)";

                    if(txtviewstatus.getText().equals(contact)) {


                        String dateselect = date.getText().toString();
                        String timeselect = time.getText().toString();
                        String freq = frequency.getText().toString();
                        String number = txtviewnum.getText().toString();
                        String message = edttxt.getText().toString();
                        String name = txtview.getText().toString();
                        String sendnow = message + "\n" + name + "\n" + number + "\n" + dateselect + "\n" +timeselect + "\n" + freq ;
                        //addMessageToSent(number,message);
                        //sendSms(number,message);
                        Toast.makeText(PlanSMS.this, sendnow, Toast.LENGTH_SHORT).show();

//                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        //String csvalue = db.fetchGroup(db1 , name );
                        Long csv = db.fetchID(db1 , number) ;
                        String scvstring = csv.toString();
                        PlannedDisplayModelClass plannedDisplayModelClass = new PlannedDisplayModelClass(name, message, scvstring , timeselect , dateselect , "contact");
                        //db.addLater(plannedDisplayModelClass);
                        Long id = db.addLater(plannedDisplayModelClass);

                        Log.d("uzairid", "onClick: " + number + message + name + id);

//                        NotificationCompat.Builder mBuilder =
//                                new NotificationCompat.Builder(PlanSMS.this)
//                                        .setSmallIcon(R.drawable.app_icon)
//                                        .setContentTitle("SMS Sent To Contact : "+name)
//                                        .setContentText(message);

//                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        int mId = 1;
//                        notificationManager.notify(mId, mBuilder.build());



//                        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
//                        String givenDateString = date.getText().toString() + " " + time.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//                        try {
//                            Date mDate = sdf.parse(givenDateString);
//                            long timeInMilliseconds = mDate.getTime();
//                            System.out.println("Date in milli :: " + timeInMilliseconds);
//                            Log.d("uzairtime", "onClick: " + timeInMilliseconds);
//                            startAlarm(timeInMilliseconds);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            Log.d("uzairtime", "Catch");
//                        }

                        int freqState = 5;

                        if(frequency.getText().toString().equals("Hourly")){
                            freqState = 0;
                        }
                        else if(frequency.getText().toString().equals("Daily")){
                            freqState = 1;
                        }
                        else if(frequency.getText().toString().equals("Weekly")){
                            freqState = 2;
                        }
                        else if(frequency.getText().toString().equals("Monthly")){
                            freqState = 3;
                        }
                        else if(frequency.getText().toString().equals("Annualy")){
                            freqState = 4;
                        }
                        //String dtStart = "11/08/2013 08:48";
                        String dtStart = date.getText().toString() + " " + time.getText().toString();
                        //Log.d("realtime", "onClick: "+dtStart1);
                        //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        try {
                           Date date1 = format.parse(dtStart);
                           long timeInMillis = date1.getTime();
                            Log.d("uzairtime", "onClick: " + date1 +" "+timeInMillis);
                            if(freqState == 5){
                                startAlarm(timeInMillis , message , id);
                            }
                            else{
                                startRepeatingAlarm(timeInMillis , id , freqState);
                            }

                            //startRepeatingAlarm(timeInMillis , id , 0);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(PlanSMS.this, MyTabLayout.class);
                        startActivity(intent);

                    }

                    else if(txtviewstatus.getText().equals(group)){


                        String dateselect = date.getText().toString();
                        String timeselect = time.getText().toString();
                        String freq = frequency.getText().toString();
                        String message = edttxt.getText().toString();
                        String name = txtview.getText().toString();
                        String contactvalue = "";

                        String csvalue = db.fetchGroup(db1 , name );

                        //String dtStart = "11/08/2013 08:48";



//                        ArrayList<String> arrayListCSV = new ArrayList<String>(Arrays.asList(csvalue.split(",")));
//                        ArrayList<String> arrayContactNumbers = new ArrayList<String>();
//
//                        String[] csvvalue = new String[arrayListCSV.size()];
//
//                        for (int i = 1; i < arrayListCSV.size(); i++) {
//                            csvvalue[i] = arrayListCSV.get(i);
//                            arrayContactNumbers.add(db.fetchContact(db1 , csvvalue[i]));
//                        }
//
//                        String[] numbervalue = new String[arrayContactNumbers.size()];
//
//                        for(int j = 0 ; j < arrayContactNumbers.size() ; j++){
//
//                            numbervalue[j] = arrayContactNumbers.get(j);
//                            addMessageToSent(numbervalue[j],message);
//                            sendSms(numbervalue[j],message);
//
//                        }
//
//
//                        String allItems = ""; //used to display in the toast
//
//                        for(String str : arrayContactNumbers){
//                            allItems = allItems + "\n" + str; //adds a new line between items
//                        }


                        String sendnow = edttxt.getText() + "\n" + txtview.getText() + "\n" + txtviewnum.getText() + "\n"  + dateselect + "\n" +timeselect + "\n" + freq;
                        Toast.makeText(PlanSMS.this, sendnow, Toast.LENGTH_SHORT).show();

//                        NotificationCompat.Builder mBuilder =
//                                new NotificationCompat.Builder(PlanSMS.this)
//                                        .setSmallIcon(R.drawable.app_icon)
//                                        .setContentTitle("SMS Sent To Group : "+name)
//                                        .setContentText(message);

//                          NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        int mId = 1;
//                        notificationManager.notify(mId, mBuilder.build());

                        Intent intent = new Intent(PlanSMS.this, MyTabLayout.class);
                        startActivity(intent);

                        PlannedDisplayModelClass plannedDisplayModelClass = new PlannedDisplayModelClass(name, message , csvalue, timeselect , dateselect , "group");
                        //db.addLater(plannedDisplayModelClass);
                        Long id = db.addLater(plannedDisplayModelClass);
//                        long currentTime = Calendar.getInstance().getTimeInMillis();
//                        HistoryModelClass historyModelClass = new HistoryModelClass(message, name, currentTime);
//                        db.addHistory(historyModelClass);
                        String dtStart = date.getText().toString() + " " + time.getText().toString();

                        int freqState = 5;

                        if(frequency.getText().toString().equals("Hourly")){
                            freqState = 0;
                        }
                        else if(frequency.getText().toString().equals("Daily")){
                            freqState = 1;
                        }
                        else if(frequency.getText().toString().equals("Weekly")){
                            freqState = 2;
                        }
                        else if(frequency.getText().toString().equals("Monthly")){
                            freqState = 3;
                        }
                        else if(frequency.getText().toString().equals("Annualy")){
                            freqState = 4;
                        }
                        //Log.d("realtime", "onClick: "+dtStart1);
                        //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        try {
                            Date date1 = format.parse(dtStart);
                            long timeInMillis = date1.getTime();
                            Log.d("uzairtime", "onClick: " + date1 +" "+timeInMillis);
                            if(freqState == 5){
                                startAlarm(timeInMillis , message , id);
                            }
                            else{
                                startRepeatingAlarm(timeInMillis , id , freqState);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edit = edttxt.getText().toString();
                String empty = "";
                if ((txtview.getText().equals(empty)) || (edit.equals(empty)))  {

                    Toast.makeText(PlanSMS.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else {

                    String contact = "(contact)";
                    String group = "(group)";

                    if(txtviewstatus.getText().equals(contact)) {


                        String number = txtviewnum.getText().toString();
                        String message = edttxt.getText().toString();
                        String name = txtview.getText().toString();
                        String sendnow = message + "\n" + name + "\n" + number;
                        addMessageToSent(number,message);
                        sendSms(number,message);
                        Toast.makeText(PlanSMS.this, sendnow, Toast.LENGTH_SHORT).show();

                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        HistoryModelClass historyModelClass = new HistoryModelClass(message, name, currentTime);
                        db.addHistory(historyModelClass);

                        Log.d("uzairid", "onClick: " + number + message + name);

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(PlanSMS.this)
                                        .setSmallIcon(R.drawable.app_icon)
                                        .setContentTitle("SMS Sent To Contact : "+name)
                                        .setContentText(message);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        int mId = 1;
                        notificationManager.notify(mId, mBuilder.build());

                        Intent intent = new Intent(PlanSMS.this, MyTabLayout.class);
                        startActivity(intent);

                    }

                    else if(txtviewstatus.getText().equals(group)){



                        String message = edttxt.getText().toString();
                        String name = txtview.getText().toString();
                        String contactvalue = "";

                        String csvalue = db.fetchGroup(db1 , name );

                        ArrayList<String> arrayListCSV = new ArrayList<String>(Arrays.asList(csvalue.split(",")));
                        ArrayList<String> arrayContactNumbers = new ArrayList<String>();

                        String[] csvvalue = new String[arrayListCSV.size()];

                        for (int i = 1; i < arrayListCSV.size(); i++) {
                            csvvalue[i] = arrayListCSV.get(i);
                            arrayContactNumbers.add(db.fetchContact(db1 , csvvalue[i]));
                        }

                        String[] numbervalue = new String[arrayContactNumbers.size()];

                        for(int j = 0 ; j < arrayContactNumbers.size() ; j++){

                            numbervalue[j] = arrayContactNumbers.get(j);
                            addMessageToSent(numbervalue[j],message);
                            sendSms(numbervalue[j],message);

                        }


                        String allItems = ""; //used to display in the toast

                        for(String str : arrayContactNumbers){
                            allItems = allItems + "\n" + str; //adds a new line between items
                        }


                        String sendnow = edttxt.getText() + "\n" + txtview.getText() + "\n" + txtviewnum.getText() + "\n" + allItems;
                        Toast.makeText(PlanSMS.this, sendnow, Toast.LENGTH_SHORT).show();

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(PlanSMS.this)
                                        .setSmallIcon(R.drawable.app_icon)
                                        .setContentTitle("SMS Sent To Group : "+name)
                                        .setContentText(message);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        int mId = 1;
                        if (notificationManager != null) {
                            notificationManager.notify(mId, mBuilder.build());
                        }

                        Intent intent = new Intent(PlanSMS.this, MyTabLayout.class);
                        startActivity(intent);

                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        HistoryModelClass historyModelClass = new HistoryModelClass(message, name, currentTime);
                        db.addHistory(historyModelClass);

                    }

                }
            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                txtview.setText("" + result);
                String result4 = data.getStringExtra("result4");
                txtviewnum.setText("" + result4);
                txtviewstatus.setText("(contact)");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result1 = data.getStringExtra("result1");
                edttxt.setText(result1);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                String result2 = data.getStringExtra("result2");
                txtview.setText("" + result2);
                String result5 = data.getStringExtra("result5");
                txtviewnum.setText("" + result5);
                txtviewstatus.setText("(group)");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 4) {
            if(resultCode == Activity.RESULT_OK){
                String result3 = data.getStringExtra("result3");
                ArrayList<String> arrayListLaterData = new ArrayList<String>(Arrays.asList(result3.split(",")));
                time.setText(arrayListLaterData.get(0));
                date.setText(arrayListLaterData.get(1));
                frequency.setText(arrayListLaterData.get(2));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }


        switch (requestCode) {
            case REQ_CODE: {
                if(resultCode == RESULT_OK && null != data){
                //if (resultCode == RESULT_OK && null ! = data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //edttxt.setText(result.get(0));
                    if (result != null && !result.isEmpty()) {
                        edttxt.setText(result.get(0));
                    }
                }
                break;
            }
        }

    }//onActivityResult

    public static void sendSms(String number, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message,
                null, null);
        Log.d("sent to", ":"+ number);


    }

    public static void sendSMSone(String phoneNumber, String message)
    {
        //PendingIntent pi = PendingIntent.getActivity(this, 0,
          //      new Intent(this, MyTabLayout.class), 0);
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, null, null);
        Log.d("sent to uzair", ":"+ phoneNumber + message);
    }

    private void addMessageToSent(String telNumber, String messageBody) {
        ContentValues sentSms = new ContentValues();
        sentSms.put("address", telNumber);
        sentSms.put("body", messageBody);

        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(Uri.parse("content://sms/sent"), sentSms);
    }


    private void startAlarm(Long t , String msg , Long num) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(PlanSMS.this, AlarmNotificationReceiver.class);
        int requestcode = (int) Integer.parseInt(String.valueOf(num));
        intent.putExtra(KEY_INTENT_MESSAGE, msg);
        intent.putExtra(KEY_INTENT_PLANNED_ID, num.toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(PlanSMS.this, requestcode, intent, 0);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, t , pendingIntent);
    }

    private void startRAlarm(Long t , String msg , Long num) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(PlanSMS.this, AlarmNotificationReceiver.class);
        int requestcode = (int) Integer.parseInt(String.valueOf(num));
        intent.putExtra(KEY_INTENT_MESSAGE, msg);
        intent.putExtra(KEY_INTENT_PLANNED_ID, num.toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(PlanSMS.this, requestcode, intent, 0);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, t ,AlarmManager.INTERVAL_DAY , pendingIntent);
    }


    /**
     *
     * @param time
     * @param id
     * @param frequency  it should be from 0 to 4,   0 for hourly, 1 for daily, 2 for weekly, 3 for monthly, 4 for yearly
     */
    private void startRepeatingAlarm(Long time, Long id, int frequency) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(PlanSMS.this, AlarmNotificationReceiver.class);
        int requestcode = (int) Integer.parseInt(String.valueOf(id));
        intent.putExtra(KEY_INTENT_PLANNED_ID, id.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PlanSMS.this, requestcode, intent, 0);
        switch (frequency) {
            case 0:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_HOUR , pendingIntent);
                break;
            case 1:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY , pendingIntent);
                break;
            case 2:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, (AlarmManager.INTERVAL_DAY * 7), pendingIntent);
                break;
            case 3:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, (AlarmManager.INTERVAL_DAY * 30), pendingIntent);
                break;
            case 4:
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, (AlarmManager.INTERVAL_DAY * 365), pendingIntent);
                break;
        }
    }

    private void startAlarmGroup(Long t , String msg , String num){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(PlanSMS.this, GroupAlarmNotificationReceiver.class);
        intent.putExtra(KEY_INTENT_MESSAGE, msg);
        intent.putExtra(KEY_INTENT_NUMBER, num);
        int requestcode = Integer.parseInt(num);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(PlanSMS.this, requestcode, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, t , pendingIntent);
    }

    public static String getIdContact (String numbers){
        //String idmsg = db.fetchContact(db1 , numbers );
        String idmsg = db.fetchCSV(db1 , numbers );
        return idmsg;
    }


    public static String getMsgContact (String numbers){
        //String idmsg = db.fetchContact(db1 , numbers );
        String idmsg = db.fetchMsg(db1 , numbers );
        return idmsg;
    }




}

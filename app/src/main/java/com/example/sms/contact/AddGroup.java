package com.example.sms.contact;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.MyTabLayout;
import com.example.sms.R;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.GroupSelectModelClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGroup extends AppCompatActivity {

    private RecyclerView recyclerView;

    ContactAdapter adapter;
    ImageView backbtn;
    SQLiteDatabase db1;
    ProgressDialog progress;
    ArrayList<ContactModelClass> arrContacts = new ArrayList<>();

    private SQLiteDatabaseHandler db;
    //ArrayList<ContactModelClass> playerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);

        StringBuffer sb = null;
        Button button, button1;
        final EditText edttxt;

        backbtn = findViewById(R.id.backbtn);


        progress = new ProgressDialog(AddGroup.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog



        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(this);
        db1 = helper.getWritableDatabase();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        // create our sqlite helper class
        db = new SQLiteDatabaseHandler(this);
        // create some contactModelClasses
        button = findViewById(R.id.button);
        edttxt = findViewById(R.id.edttxt);
        button1 = findViewById(R.id.button1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContactsTask().execute();
            }
        });


        arrContacts = db.getAllContacts();



        if (arrContacts != null) {
            String[] contactNames = new String[arrContacts.size()];
            String[] contactNumbers = new String[arrContacts.size()];


            List<ContactModelClass> contactModelClass = new ArrayList<>();
            for (int i = 0; i < arrContacts.size(); i++) {
                contactNames[i] = arrContacts.get(i).getNumber();
                contactNumbers[i] = arrContacts.get(i).getName();



                contactModelClass.add(new ContactModelClass(contactNumbers[i], contactNames[i]));

            }

            adapter = new ContactAdapter((ArrayList<ContactModelClass>) contactModelClass, this , AddGroup.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String zero = "";
                String selected = "";
                String grpname = edttxt.getText().toString();
                for (int i = 0; i < ContactAdapter.contactModelClasses.size(); i++) {

                    if (ContactAdapter.contactModelClasses.get(i).isSelected()) {


                        selected = selected + " " + ContactAdapter.contactModelClasses.get(i).getPhNumber();

                    }


                }
                if ((grpname.equals(zero)) || (selected.equals(zero))) {

                    Toast.makeText(AddGroup.this, "Name or Contact is Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    String csvid = "0";
                    String noc = "";
                    String groupname = edttxt.getText().toString();
                    String toast = groupname + "\n Group Created!";
                    for (int i = 0; i < ContactAdapter.contactModelClasses.size(); i++) {
                        if (ContactAdapter.contactModelClasses.get(i).isSelected()) {

                            String idvalue = arrContacts.get(i).toString();




                            csvid = csvid + " , " + idvalue;



                            toast = toast + " \n " + ContactAdapter.contactModelClasses.get(i).getPhName();


                        }
                    }

                    GroupSelectModelClass groupSelectModelClass = new GroupSelectModelClass(groupname, csvid);
                    db.addGroup(groupSelectModelClass);

                    Toast.makeText(AddGroup.this, toast, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddGroup.this, MyTabLayout.class);
                    startActivity(intent);


                    //Intent intent = new Intent(MainActivity.this, StoreContact.class);
                    //startActivity(intent);
                }
            }
        });


    }



    class GetContactsTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(AddGroup.this, "",
                    "Loading Contacts Please wait...", true);
            progress.setCancelable(false);
        }

        @Override
        protected String doInBackground(Integer... params) {


            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

            //Cursor phones = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
            //playerArrayList.clear();

            if (phones == null) return "";

            while (phones.moveToNext()) {
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                boolean isContactExists = false;
                for (ContactModelClass contactModelClass : arrContacts) {
                    if (contactModelClass.getNumber().equalsIgnoreCase(phoneNumber)) {
                        isContactExists = true;
                    }
                }

                if (!isContactExists) {
                    ContactModelClass newContactModelClass = new ContactModelClass(name, phoneNumber, 0);
                    db.addContact(newContactModelClass);
                }

            }
            phones.close();



            // To dismiss the dialog
            //progress.dismiss();

            //Intent intent = new Intent(AddGroup.this, AddGroup.class);
            //startActivity(intent);

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            if (progress != null) {
                progress.dismiss();
                Intent intent = new Intent(AddGroup.this, AddGroup.class);
                startActivity(intent);
                finish();


            }

        }
    }


}


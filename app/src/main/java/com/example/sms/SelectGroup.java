package com.example.sms;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.adapter.GroupSelectAdapter;
import com.example.sms.contact.ContactModelClass;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.GroupSelectModelClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectGroup extends AppCompatActivity implements android.widget.AdapterView.OnItemClickListener {


    private RecyclerView recyclerView;
    ArrayList<GroupSelectModelClass> gsmodelClassesListc = new ArrayList<>();
    ArrayList<GroupSelectModelClass> arrayListplayer = new ArrayList<>();
    ImageView backbtn;
    private SQLiteDatabaseHandler db;
    SQLiteDatabase db1;
    private Object AdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_select);

        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(this);
        db1 = helper.getWritableDatabase();

        db = new SQLiteDatabaseHandler(this);

        arrayListplayer = db.getAllGroups();

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setLayoutManager(layoutManager);


        if (arrayListplayer != null) {
            String[] itemsNames = new String[arrayListplayer.size()];
            String[] itemsNames1 = new String[arrayListplayer.size()];
            int[] itemsNames2 = new int[arrayListplayer.size()];

            ArrayList<GroupSelectModelClass> groupSelectModelClass = new ArrayList<>();
            for (int i = 0; i < arrayListplayer.size(); i++) {
                itemsNames[i] = arrayListplayer.get(i).getNumber();
                itemsNames1[i] = arrayListplayer.get(i).getName();
                //itemsNames2[i] = arrContacts.get(i).getId();
                ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(itemsNames[i].split(",")));

                int numberofcontacts = arrayList.size()-1;
                String noc = "No of Contacts : " + numberofcontacts;

                groupSelectModelClass.add(new GroupSelectModelClass(itemsNames1[i], noc));
               // groupSelectModelClasses.add(new GroupSelectModelClass(itemsNames1[i], itemsNames[i]));
                // contactModelClass.add(new ContactModelClass(itemsNames1[i],itemsNames[i]));
            }

             //arrayListplayer.add(new GroupSelectModelClass());



            GroupSelectAdapter adapter = new GroupSelectAdapter(groupSelectModelClass, SelectGroup.this, this, SelectGroup.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }


    }

    @Override
    public void onItemClick(android.widget.AdapterView<?> adapterView, View view, int i, long l) {

  String clicktemplate2 = arrayListplayer.get(i).getName();
        String clicknumber = arrayListplayer.get(i).getNumber();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(clicknumber.split(",")));

        int numberofcontacts = arrayList.size()-1;
        String noc = "No of Contacts : " + numberofcontacts;
         Toast.makeText(SelectGroup.this ,arrayListplayer.get(i).getName(), Toast.LENGTH_SHORT).show();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result2",clicktemplate2);
        returnIntent.putExtra("result5",noc);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

//         Intent intent = new Intent(SelectGroup.this, PlanSMS.class);
//         intent.putExtra("key_clicktemplate1", clicktemplate);
//         startActivity(intent);
    }
}

//@Override
    //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      //  String clicktemplate2 = arrayListplayer.get(i).getName();
       // Toast.makeText(SelectGroup.this ,arrayListplayer.get(i).getName(), Toast.LENGTH_SHORT).show();

        //Intent returnIntent = new Intent();
        //returnIntent.putExtra("result2",clicktemplate2);
        //setResult(Activity.RESULT_OK,returnIntent);
        //finish();

        // Intent intent = new Intent(ContactsSelect.this, PlanSMS.class);
        // intent.putExtra("key_clicktemplate1", clicktemplate);
        // startActivity(intent);


   // }

//}







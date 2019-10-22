package com.example.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.adapter.ContactSelectAdapter;
import com.example.sms.contact.ContactModelClass;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.ContactSelectModelClass;

import java.util.ArrayList;
import java.util.List;

public class ContactsSelect extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private SQLiteDatabaseHandler db;
    private RecyclerView recyclerView;
    ArrayList<ContactSelectModelClass> modelClassesListc = new ArrayList<>();
    ArrayList<ContactModelClass> arrayListcontact = new ArrayList<>();
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_contacts);

        backbtn = findViewById(R.id.backbtn);

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

        db = new SQLiteDatabaseHandler(this);

         ArrayList<ContactModelClass> contactModelClasses = db.getAllContacts();


        if (contactModelClasses != null) {
            String[] Numbers = new String[contactModelClasses.size()];
            String[] Names = new String[contactModelClasses.size()];
            int[] itemsNames2 = new int[contactModelClasses.size()];


            for (int i = 0; i < contactModelClasses.size(); i++) {
                Numbers[i] = contactModelClasses.get(i).getNumber();
                Names[i] = contactModelClasses.get(i).getName();
                //itemsNames2[i] = contactModelClasses.get(i).getId();




                arrayListcontact.add(new ContactModelClass(Names[i], Numbers[i]));
                // player.add(new ContactModelClass(itemsNames1[i],itemsNames[i]));
            }

            ContactSelectAdapter adapter = new ContactSelectAdapter(arrayListcontact, ContactsSelect.this ,this ,ContactsSelect.this );
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String clicktemplate = arrayListcontact.get(i).getPhNumber();
        String clicknumber = arrayListcontact.get(i).getPhName();
        Toast.makeText(ContactsSelect.this ,arrayListcontact.get(i).getPhNumber(), Toast.LENGTH_SHORT).show();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",clicktemplate);
        returnIntent.putExtra("result4",clicknumber);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

       // Intent intent = new Intent(ContactsSelect.this, PlanSMS.class);
       // intent.putExtra("key_clicktemplate1", clicktemplate);
       // startActivity(intent);


    }
}


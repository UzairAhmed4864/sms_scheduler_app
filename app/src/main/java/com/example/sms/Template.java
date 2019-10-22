package com.example.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.adapter.AdapterTemplate;
import com.example.sms.modelclass.ModelClassTemplate;
import com.example.sms.hatkemessenger.activities.MainActivity;

import java.util.ArrayList;

public class Template extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;

    EditText edttxt;
    Button addbtn ;
    ImageView backbtn;


    ArrayList<ModelClassTemplate> modelClassesListt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template);


        edttxt = findViewById(R.id.edttxt);
        addbtn = findViewById(R.id.plusbtn);
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




            modelClassesListt.add(new ModelClassTemplate("Happy Birthday"));
            modelClassesListt.add(new ModelClassTemplate("Good Morning"));
            modelClassesListt.add(new ModelClassTemplate("Call you later"));
            modelClassesListt.add(new ModelClassTemplate("Can`t talk right now!"));
            modelClassesListt.add(new ModelClassTemplate("How are you?"));




        final AdapterTemplate adapter = new AdapterTemplate(modelClassesListt ,this ,Template.this );
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 modelClassesListt.add(0 ,new ModelClassTemplate(edttxt.getText().toString()));
                 adapter.notifyDataSetChanged();
                 adapter.notifyItemInserted(0);
                recyclerView.smoothScrollToPosition(0);
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String clicktemplate1 = modelClassesListt.get(i).getTitle();
        Toast.makeText(Template.this ,modelClassesListt.get(i).getTitle() , Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result1",clicktemplate1);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


}

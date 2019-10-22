package com.example.sms.contact;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sms.R;


public class StoreContact extends AppCompatActivity {

    private TextView tv,tv1,tv2;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);



        //String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
        //String countryList[] = new String[10];
        //        //String[] countryList = new String[0];
        tv = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);


        //lv = (ListView) findViewById(R.id.lv);
        

       // for (int i = 0; i < contactModelClasses.size(); i++){
         //   if(contactModelClasses.get(i).isSelected()) {
           //     tv.setText(tv.getText() + " " + contactModelClasses.get(i).getTitle());
             //   tv1.setText(tv1.getText() + " " + contactModelClasses.get(i).getTitle1());

                //countryList[i] = GroupFragmentAdapter.contactModelClasses.get(i).getTitle();
                
            //}
       // }
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.store_contact, R.id.tv, countryList);
       // lv.setAdapter(arrayAdapter);
    }
}
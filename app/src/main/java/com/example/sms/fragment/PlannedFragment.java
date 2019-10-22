package com.example.sms.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sms.PlanSMS;
import com.example.sms.R;
import com.example.sms.adapter.HistoryFragmentAdapter;
import com.example.sms.adapter.PlannedFragmentAdapter;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.GroupSelectModelClass;
import com.example.sms.modelclass.HistoryModelClass;
import com.example.sms.modelclass.PlannedDisplayModelClass;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlannedFragment extends Fragment {

    private SQLiteDatabaseHandler db;
    SQLiteDatabase db1;
    private Context context;
    public ArrayList<PlannedDisplayModelClass> arrayListPlan = new ArrayList<>();



    public PlannedFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_tab2, container, false);

        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(getActivity());
        db1 = helper.getWritableDatabase();

        db = new SQLiteDatabaseHandler(getActivity());

        arrayListPlan = db.getAllLater();

        Button floatingb = vw.findViewById(R.id.floatingb);
        floatingb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlanSMS.class);
                startActivity(intent);
            }
        });
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_blank);

        //initImageLoader(this);
        //recyclerView =  findViewById(R.id.recycler_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) vw.findViewById(R.id.recycler_view);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // layoutManager.setOrientation(RecyclerView.VERTICAL);

        //recyclerView.setLayoutManager(layoutManager);


//        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
//        startManagingCursor(cursor);
//
//        String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME , ContactsContract.CommonDataKinds.Phone.NUMBER , ContactsContract.CommonDataKinds.Phone._ID };
//
//        int[] to = {android.R.id.text1,android.R.id.text2};
        //  modelClassesList.add(new GroupDisplayModelClass(ContactsContract.CommonDataKinds.Phone. ,  ContactsContract.CommonDataKinds.Phone.NUMBER));


        ArrayList<PlannedDisplayModelClass> modelClassesList2 = new ArrayList<>();

        //for (int i = 0; i < 40; i++) {
        //modelClassesList2.add(new PlannedDisplayModelClass("Testing", "Hello World!", "7:00 am", "11th Sept 2019"));

        if (arrayListPlan != null) {
            String[] itemsNames = new String[arrayListPlan.size()];
            String[] itemsMsg = new String[arrayListPlan.size()];
            String[] itemsTime = new String[arrayListPlan.size()];
            String[] itemsDate = new String[arrayListPlan.size()];
            String[] itemsCSV = new String[arrayListPlan.size()];
            String[] itemsCheck = new String[arrayListPlan.size()];


            ArrayList<GroupSelectModelClass> groupSelectModelClass = new ArrayList<>();
            for (int i = 0; i < arrayListPlan.size(); i++) {
                itemsNames[i] = arrayListPlan.get(i).getName();
                itemsMsg[i] = arrayListPlan.get(i).getMsg();
                itemsTime[i] = arrayListPlan.get(i).getTime();
                itemsDate[i] = arrayListPlan.get(i).getDate();
                itemsCSV[i] = arrayListPlan.get(i).getCSV();
                itemsCheck[i] = arrayListPlan.get(i).getCheck();

                //groupSelectModelClass.add(new GroupSelectModelClass(itemsNames1[i], itemsNames[i]));
                //modelClassesList.add(new GroupDisplayModelClass(itemsNames1[i], itemsNames[i]));
                modelClassesList2.add(new PlannedDisplayModelClass(itemsMsg[i], itemsNames[i] ,itemsCSV[i] , itemsDate[i], itemsTime[i] , itemsCheck[i]));
                // groupSelectModelClasses.add(new GroupSelectModelClass(itemsNames1[i], itemsNames[i]));
                // contactModelClass.add(new ContactModelClass(itemsNames1[i],itemsNames[i]));
                PlannedFragmentAdapter plannedFragmentAdapter = new PlannedFragmentAdapter(modelClassesList2);
                recyclerView.setAdapter(plannedFragmentAdapter);
                plannedFragmentAdapter.notifyDataSetChanged();
            }
            //modelClassesList1.add(new HistoryModelClass("abc" , "abc" , "abc"));

            //}


        }


        return vw;
    }


    public interface OnFragmentInteractionListener {
    }
}


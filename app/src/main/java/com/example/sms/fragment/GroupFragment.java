package com.example.sms.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sms.R;
import com.example.sms.adapter.GroupFragmentAdapter;
import com.example.sms.contact.AddGroup;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.GroupSelectModelClass;
import com.example.sms.modelclass.GroupDisplayModelClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {


    public ArrayList<GroupSelectModelClass> arrayListGroup = new ArrayList<>();
    ImageView backbtn;
    private SQLiteDatabaseHandler db;
    SQLiteDatabase db1;
    private Context context;




    private RecyclerView recyclerView;

    public GroupFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_blank, container, false);


        Button floatingb = vw.findViewById(R.id.floatingb);
        floatingb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddGroup.class);
                startActivity(intent);
            }
        });
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_blank);
        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(getActivity());
        db1 = helper.getWritableDatabase();

        db = new SQLiteDatabaseHandler(getActivity());

        arrayListGroup = db.getAllGroups();
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
        //  groupDisplayModelClassesList.add(new GroupDisplayModelClass(ContactsContract.CommonDataKinds.Phone. ,  ContactsContract.CommonDataKinds.Phone.NUMBER));


        List<GroupDisplayModelClass> groupDisplayModelClassesList = new ArrayList<>();


        if (arrayListGroup != null) {
            String[] itemsNames = new String[arrayListGroup.size()];
            String[] itemsNames1 = new String[arrayListGroup.size()];
            int[] itemsNames2 = new int[arrayListGroup.size()];

            List<GroupSelectModelClass> groupSelectModelClass = new ArrayList<>();
            for (int i = 0; i < arrayListGroup.size(); i++) {
                itemsNames[i] = arrayListGroup.get(i).getNumber();
                itemsNames1[i] = arrayListGroup.get(i).getName();
                //itemsNames2[i] = arrContacts.get(i).getId();

                ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(itemsNames[i].split(",")));

                int numberofcontacts = arrayList.size()-1;
                String noc = "No of Contacts : " + numberofcontacts;
                //groupSelectModelClass.add(new GroupSelectModelClass(itemsNames1[i], itemsNames[i]));
                groupDisplayModelClassesList.add(new GroupDisplayModelClass(itemsNames1[i], noc));
                // groupSelectModelClasses.add(new GroupSelectModelClass(itemsNames1[i], itemsNames[i]));
                // contactModelClass.add(new ContactModelClass(itemsNames1[i],itemsNames[i]));
            }
            // groupDisplayModelClassesList.add(new GroupDisplayModelClass("Office Group", "No of Contact : 3"));


            GroupFragmentAdapter groupFragmentAdapter = new GroupFragmentAdapter(groupDisplayModelClassesList);

            //GroupSelectAdapter groupFragmentAdapter = new GroupSelectAdapter(arrayListGroup, GroupFragment.this, this, GroupFragment.this);
            recyclerView.setAdapter(groupFragmentAdapter);
            groupFragmentAdapter.notifyDataSetChanged();


        }


        //public interface OnFragmentInteractionListener {

        //}

        return vw;
    }

    public interface OnFragmentInteractionListener {
    }
}




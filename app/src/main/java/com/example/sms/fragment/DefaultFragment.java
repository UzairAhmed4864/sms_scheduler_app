package com.example.sms.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms.Inbox;
import com.example.sms.R;
import com.example.sms.Template;
import com.example.sms.adapter.DefaultFragmentAdapter;
import com.example.sms.adapter.GroupFragmentAdapter;
import com.example.sms.hatkemessenger.activities.MainActivity;
import com.example.sms.modelclass.DefaultModelClass;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultFragment extends Fragment {

    Button btnDefaultApp;


    //    //public ArrayList<DefaultModelClass> arrayListDefault = new ArrayList<>();
//    private Context context;
//    private RecyclerView recyclerView;
//
//
//
//    public DefaultFragment() {
//        // Required empty public constructor
//    }
//
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_default, container, false);
        super.onCreate(savedInstanceState);

        btnDefaultApp = vw.findViewById(R.id.btnDefaultApp);

        btnDefaultApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
//        RecyclerView recyclerView = (RecyclerView) vw.findViewById(R.id.default_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        List<DefaultModelClass> arrayListDefault = new ArrayList<>();
//
//        if (arrayListDefault != null) {
//
//            arrayListDefault.add(new DefaultModelClass("uzair", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("hamza", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("waleed", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("faiz", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("anus", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("zohaib", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("arsalan", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("obaid", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("waheed", "Hey how r u?"));
//            arrayListDefault.add(new DefaultModelClass("aziz", "Hey how r u?"));
//
//            DefaultFragmentAdapter defaultFragmentAdapter = new DefaultFragmentAdapter(arrayListDefault , this);
//            recyclerView.setAdapter(defaultFragmentAdapter);
//            defaultFragmentAdapter.notifyDataSetChanged();
//
//        }
//        return vw;
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////        Intent myIntent = new Intent(getContext()  , Inbox.class);
////                startActivity(myIntent);
//        Intent intent = new Intent(getActivity(), Inbox.class);
//        startActivity(intent);
//        Toast.makeText(getActivity(), "You have clicked number : " + i,
//                Toast.LENGTH_SHORT).show();
//    }
//
//    public interface OnFragmentInteractionListener {
//    }
        return vw;
    }

    public interface OnFragmentInteractionListener {
    }
}




package com.example.sms;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.adapter.AdapterTemplate;
import com.example.sms.adapter.InboxAdapter;
import com.example.sms.modelclass.DefaultModelClass;
import com.example.sms.modelclass.InboxModelClass;
import com.example.sms.modelclass.ModelClassTemplate;

import java.util.ArrayList;
import java.util.List;

public class Inbox extends AppCompatActivity {

    private RecyclerView recyclerViewInbox;
    ArrayList<InboxModelClass> inboxModelClassArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox);




        recyclerViewInbox = findViewById(R.id.recycler_view_inbox);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewInbox.setLayoutManager(layoutManager);


       // List<InboxModelClass> inboxModelClassArrayList = new ArrayList<>();

        //inboxModelClassArrayList.add(new InboxModelClass("Happy Birthday"));
        inboxModelClassArrayList.add(new InboxModelClass("hey",true));
        inboxModelClassArrayList.add(new InboxModelClass("How r u bro?",true));
        inboxModelClassArrayList.add(new InboxModelClass("I am fine thank you , and how are you?",false));
        inboxModelClassArrayList.add(new InboxModelClass("I am also fine",false));
        inboxModelClassArrayList.add(new InboxModelClass("Great",true));
        inboxModelClassArrayList.add(new InboxModelClass("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",false));
        inboxModelClassArrayList.add(new InboxModelClass("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.",true));



        final InboxAdapter adapter = new InboxAdapter(inboxModelClassArrayList,Inbox.this);
        recyclerViewInbox.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}

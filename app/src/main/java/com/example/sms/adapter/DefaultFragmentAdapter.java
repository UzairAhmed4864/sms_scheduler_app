package com.example.sms.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.Inbox;
import com.example.sms.R;
import com.example.sms.fragment.DefaultFragment;
import com.example.sms.modelclass.DefaultModelClass;
import com.example.sms.modelclass.GroupDisplayModelClass;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragmentAdapter extends RecyclerView.Adapter<DefaultFragmentAdapter.Viewholder> {

    private final AdapterView.OnItemClickListener onItemClickListener;
    private ArrayList<DefaultModelClass> defaultModelClassList;
    private Context context;


    public DefaultFragmentAdapter(List<DefaultModelClass> defaultModelClassList , AdapterView.OnItemClickListener onItemClickListener) {
        this.defaultModelClassList = (ArrayList<DefaultModelClass>) defaultModelClassList;
        this.onItemClickListener = onItemClickListener;
        //this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_default,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder,final int position) {

        String name = defaultModelClassList.get(position).getName();
        String msg = defaultModelClassList.get(position).getMsg();
        viewholder.setData(name,msg);

        viewholder.llt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent myIntent = new Intent(context, Inbox.class);
//                context.startActivity(myIntent);
                //context.startActivity(new Intent(context, Inbox.class));
                //onItemClickListener.onItemClick(null, view, null, view.getId());
                onItemClickListener.onItemClick(null, view, position, view.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return defaultModelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView txtname , txtmsg;
        private LinearLayout llt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtmsg = itemView.findViewById(R.id.txtmsg);
            llt = itemView.findViewById(R.id.llt);
        }

        private void setData(String nameText, String msgText){

            txtname.setText(nameText);
            txtmsg.setText(msgText);

        }

    }
}

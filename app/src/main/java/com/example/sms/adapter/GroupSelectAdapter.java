package com.example.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sms.SelectGroup;
import com.example.sms.modelclass.GroupSelectModelClass;
import com.example.sms.R;

import java.util.ArrayList;
import java.util.List;

public class GroupSelectAdapter extends RecyclerView.Adapter<GroupSelectAdapter.Viewholder> {


    private final AdapterView.OnItemClickListener onItemClickListener;
    private List<GroupSelectModelClass> gsmodelClassList;
    private Context context;


    public GroupSelectAdapter(ArrayList<GroupSelectModelClass> gsmodelClassList , SelectGroup selectGroup , AdapterView.OnItemClickListener onItemClickListener, Context context) {
        this.gsmodelClassList = gsmodelClassList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int position) {


        String title = gsmodelClassList.get(position).getName();
        String title2 = gsmodelClassList.get(position).getNumber();
        viewholder.setData(title,title2);

        viewholder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, view, position, view.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return gsmodelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private TextView textView , textView1;
        LinearLayout ll;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
            ll = itemView.findViewById(R.id.ll);

        }


        private void setData(String titleText, String titleText1){


            textView.setText(titleText);
            textView1.setText(titleText1);

        }

    }
}

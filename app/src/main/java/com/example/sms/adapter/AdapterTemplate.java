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

import com.example.sms.R;
import com.example.sms.modelclass.ModelClassTemplate;


import java.util.ArrayList;
import java.util.List;

public class AdapterTemplate extends RecyclerView.Adapter<AdapterTemplate.Viewholder> {


    private List<ModelClassTemplate> modelClassListt;
    private Context context;
    private RecyclerView recyclerView;
    private AdapterView.OnItemClickListener onItemClickListener;


    public AdapterTemplate(ArrayList<ModelClassTemplate> modelClassListt, AdapterView.OnItemClickListener onItemClickListener, Context context) {
        this.modelClassListt = modelClassListt;

        this.context = context;
        this.onItemClickListener = onItemClickListener;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_template,viewGroup,false);

        return new Viewholder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int position) {


        String title = modelClassListt.get(position).getTitle();
        viewholder.setData(title);
        viewholder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, view, position, view.getId());
            }
        });

    }



    @Override
    public int getItemCount() {
        return modelClassListt.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{



        private TextView textView;
        LinearLayout ll;



        public Viewholder(@NonNull View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.textView);
            ll = itemView.findViewById(R.id.ll);


        }


        private void setData(String titleText){





            textView.setText(titleText);

        }

    }
}

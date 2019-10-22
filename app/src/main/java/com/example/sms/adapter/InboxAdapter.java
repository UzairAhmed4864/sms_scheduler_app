package com.example.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;
import com.example.sms.modelclass.InboxModelClass;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<InboxModelClass> inboxModelClassList;
    private int ret;


    public InboxAdapter(ArrayList<InboxModelClass> inboxModelClassList, Context context) {
        this.inboxModelClassList = inboxModelClassList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        boolean State = inboxModelClassList.get(position).getState();



        if (State == true) {
            ret = 0;
        } else if (State == false) {
            ret = 1;
        }
        return ret;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        View view;
        switch (viewType){
            case 0:
                view= LayoutInflater.from(context).inflate(R.layout.item_layout_inbox,parent,false);
                holder=new ViewHolder(view);
                break;

            case 1:
                view= LayoutInflater.from(context).inflate(R.layout.item_layout_inbox1,parent,false);
                holder=new ViewHolder1(view);

                break;
            default:
                view= LayoutInflater.from(context).inflate(R.layout.item_layout_inbox,parent,false);
                holder=new ViewHolder(view);
                break;

        }

        return holder;

    }






    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String message = inboxModelClassList.get(position).getMessage();
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder viewHolder0 = (ViewHolder)holder;
                viewHolder0.setData(message);
                break;

            case 1:
                ViewHolder1 viewHolder2 = (ViewHolder1)holder;
                viewHolder2.setData(message);
                break;
        }

    }




    @Override
    public int getItemCount() {
        return inboxModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

        }

        public void setData(String message) {
            textView.setText(message);
        }
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

        }

        public void setData(String message) {
            textView.setText(message);
        }
    }
}

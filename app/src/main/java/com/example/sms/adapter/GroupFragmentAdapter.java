package com.example.sms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sms.modelclass.GroupDisplayModelClass;
import com.example.sms.R;

import java.util.ArrayList;
import java.util.List;

public class GroupFragmentAdapter extends RecyclerView.Adapter<GroupFragmentAdapter.Viewholder> {


    private ArrayList<GroupDisplayModelClass> groupDisplayModelClassList;
    private Context context;

    public GroupFragmentAdapter(List<GroupDisplayModelClass> groupDisplayModelClassList) {
        this.groupDisplayModelClassList = (ArrayList<GroupDisplayModelClass>) groupDisplayModelClassList;

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
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

    //int resource = groupDisplayModelClassList.get(position).getImgResource();
    //int resource = groupDisplayModelClassList.get(position).getImgResource();
    String title = groupDisplayModelClassList.get(position).getTitle();
    String title2 = groupDisplayModelClassList.get(position).getTitle1();
    viewholder.setData(title,title2);

    }

    @Override
    public int getItemCount() {
        return groupDisplayModelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView textView , textView1;
        private EditText et;
        //private Button bt;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
           // et = itemView.findViewById(R.id.et);
           // bt = itemView.findViewById(R.id.bt);

        }

        //ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


        private void setData(String titleText, String titleText1){




            //imageLoader.displayImage(resource, imageView);

            //imageView.setImageResource(resource);


            textView.setText(titleText);
            textView1.setText(titleText1);

        }

    }
}

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

import com.example.sms.R;
import com.example.sms.modelclass.PlannedDisplayModelClass;

import java.util.List;

public class PlannedFragmentAdapter extends RecyclerView.Adapter<PlannedFragmentAdapter.Viewholder> {


    private List<PlannedDisplayModelClass> modelClassList2;
    private Context context;


    public PlannedFragmentAdapter(List<PlannedDisplayModelClass> modelClassList2) {
        this.modelClassList2 = modelClassList2;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout2,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

    //int resource = modelClassList.get(position).getImgResource();
    //int resource = modelClassList.get(position).getImgResource();
    String name = modelClassList2.get(position).getName();
    String msg = modelClassList2.get(position).getMsg();
        String date = modelClassList2.get(position).getDate();
        String time = modelClassList2.get(position).getTime();
    viewholder.setData(name,msg,date,time);

    }

    @Override
    public int getItemCount() {
        return modelClassList2.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView textView , textView1, textView2, textView3;
        private EditText et;
        //private Button bt;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
           // et = itemView.findViewById(R.id.et);
           // bt = itemView.findViewById(R.id.bt);

        }

        //ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


        private void setData(String titleText, String titleText1, String titleText2, String titleText3){




            //imageLoader.displayImage(resource, imageView);

            //imageView.setImageResource(resource);


            textView.setText(titleText);
            textView1.setText(titleText1);
            textView2.setText(titleText2);
            textView3.setText(titleText3);


        }

    }
}

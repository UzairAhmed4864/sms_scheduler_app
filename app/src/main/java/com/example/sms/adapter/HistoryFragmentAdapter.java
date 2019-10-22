package com.example.sms.adapter;
import java.text.SimpleDateFormat;
import java.util.Date;


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
import com.example.sms.modelclass.HistoryModelClass;

import java.util.List;

public class HistoryFragmentAdapter extends RecyclerView.Adapter<HistoryFragmentAdapter.Viewholder> {


    private List<HistoryModelClass> modelClassList1;
    private Context context;


    public HistoryFragmentAdapter(List<HistoryModelClass> modelClassList1) {
        this.modelClassList1 = modelClassList1;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout1,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

    //int resource = modelClassList.get(position).getImgResource();
    //int resource = modelClassList.get(position).getImgResource();
    String title = modelClassList1.get(position).getName();
    String title1 = modelClassList1.get(position).getMsg();
        long title2 = modelClassList1.get(position).getTime();
    viewholder.setData(title,title1,title2);

    }

    @Override
    public int getItemCount() {
        return modelClassList1.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView textView , textView1 , textView2;
        private EditText et;
        //private Button bt;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            //imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
           // et = itemView.findViewById(R.id.et);
           // bt = itemView.findViewById(R.id.bt);

        }

        //ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


        private void setData(String titleText, String titleText1 , long titleText2){




            //imageLoader.displayImage(resource, imageView);

            //imageView.setImageResource(resource);


            textView.setText(titleText);
            textView1.setText(titleText1);


            String dateString = new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date(titleText2));

            textView2.setText(dateString);

        }

    }
}

package com.example.sms.contact;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Viewholder> {


    public static ArrayList<ContactModelClass> contactModelClasses;
    private Context context;
    private long currentGroupId;

    public ContactAdapter(ArrayList<ContactModelClass> contactModelClasses, AddGroup addGroup, Context context) {
        this.contactModelClasses = contactModelClasses;
        this.currentGroupId = currentGroupId;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layoutcontact, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int position) {

        String title1 = contactModelClasses.get(position).getPhNumber();
        String title = contactModelClasses.get(position).getPhName();

        viewholder.setData(title1, title );
        //viewholder.cbSelected.setText("Checkbox"+position);
        viewholder.cbSelected.setChecked(contactModelClasses.get(position).isSelected());
        //viewholder.textView.setText(contactModelClasses.get(position).getName());


        viewholder.cbSelected.setTag(position);
        viewholder.cbSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) viewholder.cbSelected.getTag();
                //Toast.makeText(context, contactModelClasses.get(pos).getTitle1() + " clicked!", Toast.LENGTH_SHORT).show();

                if (contactModelClasses.get(pos).isSelected()) {
                    contactModelClasses.get(pos).setSelected(false);
                } else {
                    contactModelClasses.get(pos).setSelected(true);
                }
            }
        });






    }

    @Override
    public int getItemCount() {
        return contactModelClasses.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {


        View mView;
        public CheckBox cbSelected;
        private ImageView imageView;
        private TextView textView, textView1 ;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;


            textView1 = itemView.findViewById(R.id.textView1);
            textView = itemView.findViewById(R.id.textView);

            //et = itemView.findViewById(R.id.et);
            //bt = itemView.findViewById(R.id.bt);
            cbSelected = itemView.findViewById(R.id.cb);

        }

        // ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


        private void setData(String titleText1, String titleText ) {


            textView.setText(titleText);
            textView1.setText(titleText1);


        }

    }
}

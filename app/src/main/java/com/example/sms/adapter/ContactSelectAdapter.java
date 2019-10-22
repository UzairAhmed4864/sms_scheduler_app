package com.example.sms.adapter;

import android.content.Context;
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

import com.example.sms.ContactsSelect;
import com.example.sms.R;
import com.example.sms.contact.ContactModelClass;


import java.util.ArrayList;

public class ContactSelectAdapter extends RecyclerView.Adapter<ContactSelectAdapter.Viewholder> {


    private ArrayList<ContactModelClass> contactModelClasses;
    private Context context;
    private RecyclerView recyclerView;
    private AdapterView.OnItemClickListener onItemClickListener;





    public ContactSelectAdapter(ArrayList<ContactModelClass> contactModelClasses, ContactsSelect contactsSelect , AdapterView.OnItemClickListener onItemClickListener, Context context) {
        this.contactModelClasses = contactModelClasses;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_contact_select,viewGroup,false);

        return new Viewholder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int position) {

        String PhName = contactModelClasses.get(position).getPhName();
        String PhNumber = contactModelClasses.get(position).getPhNumber();


        viewholder.setData(PhName,PhNumber);

        viewholder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, view, position, view.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactModelClasses.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView contactselectname , contactselectnumber ;
        private EditText et;
        //private Button bt;
        LinearLayout ll;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            //imageView = itemView.findViewById(R.id.imageView);
            contactselectname = itemView.findViewById(R.id.contactselectname);
            contactselectnumber = itemView.findViewById(R.id.contactselectnumber);
            ll = itemView.findViewById(R.id.ll);

            // et = itemView.findViewById(R.id.et);
            // bt = itemView.findViewById(R.id.bt);

        }

        //ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


        private void setData(String PhName, String PhNumber ){




            //imageLoader.displayImage(resource, imageView);

            //imageView.setImageResource(resource);


            contactselectname.setText(PhName);
            contactselectnumber.setText(PhNumber);


        }

    }
}

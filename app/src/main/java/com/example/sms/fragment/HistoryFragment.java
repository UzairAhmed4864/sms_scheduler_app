package com.example.sms.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.FileManager;
import com.example.sms.R;
import com.example.sms.adapter.HistoryFragmentAdapter;
import com.example.sms.db.SQLiteDatabaseHandler;
import com.example.sms.modelclass.HistoryModelClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.sms.FileManager.SMS_PATH;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private SQLiteDatabaseHandler db;
    Button backupbtn;
    Button btnRetreive;
    TextView backuptxt;
    SQLiteDatabase db1;
    Context context;
    public ArrayList<HistoryModelClass> arrayHistory = new ArrayList<>();
    private RecyclerView recyclerView;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_tab1, container, false);

        backupbtn = vw.findViewById(R.id.btnBackup);
        btnRetreive = vw.findViewById(R.id.btnRetrieve);
        //backuptxt = vw.findViewById(R.id.backuptext);

        SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(getActivity());
        db1 = helper.getWritableDatabase();
        db = new SQLiteDatabaseHandler(getActivity());
        arrayHistory = db.getAllHistory();
        recyclerView = (RecyclerView) vw.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return vw;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HistoryFragmentAdapter historyFragmentAdapter = new HistoryFragmentAdapter(arrayHistory);
        recyclerView.setAdapter(historyFragmentAdapter);
        historyFragmentAdapter.notifyDataSetChanged();

        backupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (arrayHistory.isEmpty()) {
                    Toast.makeText(getContext(), "You have no data to backup", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                final String data = gson.toJson(arrayHistory);

//                writeToFile(data , getActivity());

//                writeStringAsFile(getContext(), data, "Backup.txt");
                FileManager.writeResponseBodyToDisk(getContext(), data, "backup.txt", SMS_PATH, false, false);
                //Toast.makeText(HistoryFragment.this, "Saved to : "+ getFilesDir(), Toast.LENGTH_SHORT).show();
            }
        });


        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String getData = readFromFile(getActivity());
try {
    Gson gson = new Gson();
    String data = readFileAsString(getContext(), SMS_PATH);

    Type type = new TypeToken<ArrayList<HistoryModelClass>>() {
    }.getType();
    final ArrayList<HistoryModelClass> arrayList = gson
            .fromJson(data
                    , type);

    if (arrayHistory.isEmpty()) {

        arrayHistory.addAll(arrayList);

        for (HistoryModelClass historyModelClass : arrayHistory) {
            db.addHistory(historyModelClass);
        }

        historyFragmentAdapter.notifyDataSetChanged();

    } else {
        showAlertDialog("If you retrieve history, it will delete your current history, Are you sure you want to delete it?", "Alert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrayHistory.clear();
                arrayHistory.addAll(arrayList);
                db.deleteAllHistory();

                for (HistoryModelClass historyModelClass : arrayHistory) {
                    db.addHistory(historyModelClass);
                }

                historyFragmentAdapter.notifyDataSetChanged();
            }
        }, getContext());
    }
}

catch (IllegalStateException e){
    Toast toast = Toast.makeText(getActivity(),
            "No Data To Retrive!", Toast.LENGTH_SHORT);
    toast.show();
}



            }
        });
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    private String readFileAsString(Context context, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(filePath, "backup.txt")));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (FileNotFoundException e) {
            Log.e("READ FILE", "FileNotFoundException: " + e.getLocalizedMessage());
            Toast.makeText(context, "No file found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("READ FILE", "IOException: " + e.getLocalizedMessage());
            Toast.makeText(context, "No file found", Toast.LENGTH_SHORT).show();
        }

        return stringBuilder.toString();
    }


    public void showAlertDialog(String message, String title,
                                DialogInterface.OnClickListener onClickListener, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Yes", onClickListener);

        if (!title.isEmpty())
            builder.setTitle(title);

        AlertDialog alert = builder.create();
        alert.show();
    }


}


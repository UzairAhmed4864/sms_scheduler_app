package com.example.sms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.sms.contact.AddGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SendLaterActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener  {

    ImageView backbtn;
    Button   done;
    TextView txt , txtd , timetxt;
    CheckBox rfcb;
    LinearLayout radios;
    RadioButton radioButton;
    RadioGroup group1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_later);


        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        txt = findViewById(R.id.settimetxt);
        rfcb = findViewById(R.id.rfcb);
        txtd = findViewById(R.id.setdatetxt);
        done = findViewById(R.id.done);
        timetxt = findViewById(R.id.timetxt);
        radios = findViewById(R.id.radios);
        group1 = findViewById(R.id.group1);

        radios.setVisibility(View.INVISIBLE);

        //final int is = group1.getCheckedRadioButtonId();
        //radioButton = findViewById(is);
        //String selected = radioButton.getText().toString();

        rfcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                   if(rfcb.isChecked()){

                                                       radios.setVisibility(View.VISIBLE);
                                                   }
                                                   else{
                                                       radios.setVisibility(View.INVISIBLE);
                                                   }
                                               }
                                           }
        );



        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timepicker = new TimePicker();
                timepicker.show(getSupportFragmentManager(),"time picker");

            }
        });

        txtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new com.example.sms.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");

//                final Calendar c = Calendar.getInstance();
//                final int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH);
//                int day = c.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        c.set(Calendar.YEAR, year);
//                        c.set(Calendar.MONTH, monthOfYear);
//                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
//                        if (txtd != null) {
//                            txtd.setText(sdf.format(c.getTime()));
//                        }
//                    }
//                }, year, month, day);
//                datePickerDialog.show();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selected = "";
                if( txt.getText().equals(selected)  ||  txtd.getText().equals(selected) ){

                    Toast.makeText(SendLaterActivity.this, "Field is Empty", Toast.LENGTH_SHORT).show();

                }

                else {
                    final int is = group1.getCheckedRadioButtonId();
                    Log.d("uzaircheck", "onClick: " +is);
                    if(is > 0) {
                        radioButton = findViewById(is);
                        selected = radioButton.getText().toString();
                    }

                    else if (is == -1) {
                        selected = "no frequency";
                    }
                    String value = txt.getText() + "," + txtd.getText() + "," + selected;
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result3", value);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int hourOfDay, int minute) {
        txt.setText(+hourOfDay+":"+ minute);

    }





    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        txtd.setText(+ year +"/"+ month + "/" + day);
    }
}

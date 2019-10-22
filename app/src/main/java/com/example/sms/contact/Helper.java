package com.example.sms.contact;

import java.util.ArrayList;
import java.util.Arrays;

public class Helper {



    public static String convertArrayToCSV(ArrayList<Long> arrIDs)  {
        StringBuilder csv = new StringBuilder();

        for (Long l : arrIDs)
        {
            csv.append(l).append(",");
        }

        return csv.toString();
    }

    public static ArrayList<String> convertCSVToArray(String csv )  {

        //ArrayList<String> arrayList = new ArrayList<String>();


       // String[] split = csv.split(",");


        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(csv.split(",")));




        return arrayList;


    }
}

package com.dev.sqliteexampleproject;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static final String INDEX = "index";
    public static final long NEW_ENTRY = -1;
    public static final int REQ_CODE = 213;
    public static final int RES_CODE = 211;
    public static final int MALE = 1;
    public static final int FEMALE = 2;
    public static final int TRANS_GENDER = 3;

    public static final String NAME = "_name";
    public static final String LAST_NAME = "_last_name";
    public static final String ROLL_NUM = "_roll_num";
    public static final String GENDER = "_gender";
    public static final String MATH = "_math";
    public static final String PHY = "_phy";
    public static final String CHE = "_che";
    public static final String ENG = "_eng";
    public static final String TAMIL = "_tamil";


    public static void display(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

}

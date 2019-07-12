package com.dev.sqliteexampleproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDB {
    private SQLiteDatabase db;
    private StudentDBOpenHelper studentDBOpenHelper;
    public static final String TABLE_NAME = "students";
    public static final String ID = "_id";
    public static final String FIRST_NAME = "_first_name";
    public static final String LAST_NAME = "_last_name";
    public static final String ROLL_NUM = "_roll_num";
    public static final String GENDER = "_gender";
    public static final String IS_MATH = "_is_math";
    public static final String IS_PHYSICS = "_is_physics";
    public static final String IS_CHEMISTRY = "_is_chemistry";
    public static final String IS_ENGLISH = "_is_english";
    public static final String IS_TAMIL = "_is_tamil";

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            FIRST_NAME+" TEXT NOT NULL,"+
            LAST_NAME+" TEXT NOT NULL,"+
            ROLL_NUM+" TEXT NOT NULL,"+
            GENDER+" INTEGER,"+
            IS_MATH+" INTEGER,"+
            IS_CHEMISTRY+" INTEGER,"+
            IS_PHYSICS+" INTEGER,"+
            IS_ENGLISH+" INTEGER,"+
            IS_TAMIL+" INTEGER)";

    public static final String[] COLUMNS = new String[]{ID,FIRST_NAME,LAST_NAME,ROLL_NUM,GENDER,IS_TAMIL,IS_ENGLISH,IS_PHYSICS,IS_CHEMISTRY,IS_MATH};

    public static final String DB_NAME = "student.db";
    public static final int DB_VER = 1;

    public StudentDB(Context context){
        studentDBOpenHelper = new StudentDBOpenHelper(context);
    }

    public void openDb(){
        db = studentDBOpenHelper.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
        db = null;
    }

    public Cursor getAllStudents(){
        Cursor cursor = db.query(TABLE_NAME,COLUMNS,null,
                null,null,null,null);
        return cursor;
    }

    public Cursor getStudentByID(long id){
        Cursor cursor = db.query(TABLE_NAME,COLUMNS,
                ID+" = ?",new String[]{Long.toString(id)},
                null,null,null);

        return cursor;
    }

    public void addStudent(String name, String lastName, String rollNumber,
                           int gender, boolean ismath, boolean ischemistry, boolean isphysics,
                           boolean isenglish, boolean istamil){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,name);
        contentValues.put(LAST_NAME,lastName);
        contentValues.put(ROLL_NUM,rollNumber);
        contentValues.put(GENDER,gender);
        contentValues.put(IS_MATH,ismath);
        contentValues.put(IS_CHEMISTRY,ischemistry);
        contentValues.put(IS_ENGLISH,isenglish);
        contentValues.put(IS_PHYSICS,isphysics);
        contentValues.put(IS_TAMIL,istamil);
        db.insert(TABLE_NAME,null,contentValues);

    }

    public void updateStudent(long id,String name, String lastName, String rollNumber,
                              int gender, boolean ismath, boolean ischemistry, boolean isphysics,
                              boolean isenglish, boolean istamil){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,name);
        contentValues.put(LAST_NAME,lastName);
        contentValues.put(ROLL_NUM,rollNumber);
        contentValues.put(GENDER,gender);
        contentValues.put(IS_MATH,ismath);
        contentValues.put(IS_CHEMISTRY,ischemistry);
        contentValues.put(IS_ENGLISH,isenglish);
        contentValues.put(IS_PHYSICS,isphysics);
        contentValues.put(IS_TAMIL,istamil);
        db.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{Long.toString(id)});

    }

    public void deleteStudent(long id){
        db.delete(TABLE_NAME,ID+" = ?",new String[]{Long.toString(id)});
    }

    private class StudentDBOpenHelper extends SQLiteOpenHelper{

        public StudentDBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
            sqLiteDatabase.execSQL("DROP TABLE "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

}

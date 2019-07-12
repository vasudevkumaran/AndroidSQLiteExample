package com.dev.sqliteexampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity extends AppCompatActivity {

    private EditText nameEd;
    private EditText lastNameEd;
    private EditText rollNumEd;
    private RadioButton maleRd;
    private RadioButton femaleRd;
    private RadioButton transRd;
    private CheckBox mathCb;
    private CheckBox phyCb;
    private CheckBox cheCb;
    private CheckBox tamCb;
    private CheckBox engCb;
    private long index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        nameEd = (EditText)findViewById(R.id.nameEdit);
        lastNameEd = (EditText)findViewById(R.id.lastNameEdit);
        rollNumEd = (EditText)findViewById(R.id.rollNumEdit);

        maleRd = (RadioButton)findViewById(R.id.maleRd);
        femaleRd = (RadioButton)findViewById(R.id.femaleRd);
        transRd = (RadioButton)findViewById(R.id.transRd);

        mathCb = (CheckBox)findViewById(R.id.matchCb);
        phyCb = (CheckBox)findViewById(R.id.phyCb);
        cheCb = (CheckBox)findViewById(R.id.cheCb);
        tamCb = (CheckBox)findViewById(R.id.tamCb);
        engCb = (CheckBox)findViewById(R.id.engCb);

        Button saveBtn = (Button)findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDB studentDB = new StudentDB(getBaseContext());
                studentDB.openDb();
                int gender = 1;
                if (maleRd.isChecked()){
                    gender = 1;
                }else if (femaleRd.isChecked()){
                    gender = 2;
                }else {
                    gender = 3;
                }
                //int isMath = 0

                if (index == Util.NEW_ENTRY){
                   //add

                    studentDB.addStudent(nameEd.getText().toString(),
                            lastNameEd.getText().toString(),
                            rollNumEd.getText().toString(),
                            gender,
                            mathCb.isChecked(),
                            cheCb.isChecked(),
                            phyCb.isChecked(),
                            engCb.isChecked(),
                            tamCb.isChecked());
                }else{
                    //edit
                    studentDB.updateStudent(index,nameEd.getText().toString(),
                            lastNameEd.getText().toString(),
                            rollNumEd.getText().toString(),
                            gender,
                            mathCb.isChecked(),
                            cheCb.isChecked(),
                            phyCb.isChecked(),
                            engCb.isChecked(),
                            tamCb.isChecked());
                }
                studentDB.closeDb();
                Intent intent = new Intent();
                intent.putExtra(Util.INDEX,index);
                setResult(Util.RES_CODE,intent);
                finish();

            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            index = bundle.getLong(Util.INDEX);
            if (index == Util.NEW_ENTRY){
                actionBar.setTitle("Add Student Record");

            }else{
                actionBar.setTitle("Edit Student Record");
                StudentDB studentDB = new StudentDB(this);
                studentDB.openDb();
                Cursor cursor = studentDB.getStudentByID(index);
                cursor.moveToFirst();
                nameEd.setText(cursor.getString(cursor.getColumnIndex(StudentDB.FIRST_NAME)));
                lastNameEd.setText(cursor.getString(cursor.getColumnIndex(StudentDB.LAST_NAME)));
                rollNumEd.setText(cursor.getString(cursor.getColumnIndex(StudentDB.ROLL_NUM)));
                if (cursor.getInt(cursor.getColumnIndex(StudentDB.GENDER)) == 1){
                    maleRd.setChecked(true);
                }else if (cursor.getInt(cursor.getColumnIndex(StudentDB.GENDER)) == 2){
                    femaleRd.setChecked(true);
                }else{
                    transRd.setChecked(true);
                }


                mathCb.setChecked(cursor.getInt(cursor.getColumnIndex(StudentDB.IS_MATH))>0);
                phyCb.setChecked(cursor.getInt(cursor.getColumnIndex(StudentDB.IS_PHYSICS))>0);
                cheCb.setChecked(cursor.getInt(cursor.getColumnIndex(StudentDB.IS_CHEMISTRY))>0);
                engCb.setChecked(cursor.getInt(cursor.getColumnIndex(StudentDB.IS_ENGLISH))>0);
                tamCb.setChecked(cursor.getInt(cursor.getColumnIndex(StudentDB.IS_TAMIL))>0);
                cursor.close();
                studentDB.closeDb();



            }
        }


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

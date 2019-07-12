package com.dev.sqliteexampleproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    private StudentDB studentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.students);
        actionBar.setSubtitle(R.string.manage_students);
        ListView listView = (ListView)findViewById(R.id.listView);
        studentDB = new StudentDB(this);
        studentDB.openDb();
        Cursor cursor = studentDB.getAllStudents();
        //cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Log.w(StudentDB.FIRST_NAME,cursor.getString(cursor.getColumnIndex(StudentDB.FIRST_NAME)));
            //cursor.moveToNext();
        }

        adapter = new SimpleCursorAdapter(this,R.layout.student_item,cursor,new String[]{StudentDB.FIRST_NAME,StudentDB.LAST_NAME,StudentDB.ROLL_NUM},new int[]{R.id.firstNameTx,R.id.lastNameTx,R.id.rollNumTx},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {

                Intent intent = new Intent(MainActivity.this,AddEditActivity.class);
                intent.putExtra(Util.INDEX,id);
                startActivityForResult(intent,Util.REQ_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long id) {
                displayDeleteAlert(id);
                return true;
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentDB.closeDb();

    }

    private void displayDeleteAlert(final long index){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wait");
        builder.setMessage("Are you sure to Delete this entry?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                studentDB.deleteStudent(index);
                Cursor cursor = studentDB.getAllStudents();
                adapter.swapCursor(cursor);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel",null);

        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                //Util.display(this,"Add menu pressed");
                Intent intent = new Intent(this,AddEditActivity.class);
                intent.putExtra(Util.INDEX,Util.NEW_ENTRY);
                startActivityForResult(intent,Util.REQ_CODE);
                break;

            case R.id.action_my:

                break;

            case R.id.action_logout:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Util.REQ_CODE){
            if (resultCode == Util.RES_CODE){
                Cursor cursor = studentDB.getAllStudents();
                adapter.swapCursor(cursor);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

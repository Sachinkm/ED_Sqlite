package com.engineersdream.ed_sqlitedatabases;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   DatabaseHelper myDb;
    EditText edname, edsurname, edmarks, edid;
    String stname,stsurname,stmarks, stid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myDb = new DatabaseHelper(this);           //this line will call the constructor and in constructor we are creating the database & Table
        edname = (EditText) findViewById(R.id.editname);
        edsurname = (EditText) findViewById(R.id.editsurname);
        edmarks = (EditText) findViewById(R.id.editmarks);
        edid = (EditText) findViewById(R.id.editid);
    }
public void stringer()
{
    stid = edid.getText().toString();
    stname = edname.getText().toString();
    stsurname = edsurname.getText().toString();
    stmarks = edmarks.getText().toString();
}

        public void addclick (View v)
        {
            stringer();
            boolean isinserted = myDb.insertdata(stname, stsurname, stmarks);
            if (isinserted == true) {
                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        }

    public void allclick(View v)
    {
        stringer();
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) // 0, if no value is there inside the table
        {
            showmessage("Error","Nothing found");
            Toast.makeText(MainActivity.this, "Nothing foun here", Toast.LENGTH_LONG).show();
         return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Id: "+ res.getString(0)+ "\n");
            buffer.append("Name: "+ res.getString(1)+ "\n");
            buffer.append("Surname: "+ res.getString(2)+ "\n");
            buffer.append("Marks: "+ res.getString(3)+"\n\n");
        }

        showmessage("Data",buffer.toString());
    }

    public void modifyclick(View v)
    {
        stringer();
boolean isUpdate = myDb.modifydata(stid, stname, stsurname, stmarks);
    if(isUpdate == true)
    {
        Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
    }
    }

    public void deleteclick(View v)
    {
        stringer();
        Integer deletedRow = myDb.deleteData(stid);

        if(deletedRow > 0 )
        {
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void showmessage(String title,   String Message)

    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

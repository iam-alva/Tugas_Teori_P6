package com.example.tugas_teori_p6;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editFIRST_NAME, editLAST_NAME, editNO_HP, editId;
    Button bAdd;
    Button bViewall;
    Button bUpdate;
    Button bDelete;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editFIRST_NAME = (EditText) findViewById(R.id.editText);
        editLAST_NAME = (EditText) findViewById(R.id.editText2);
        editNO_HP = (EditText) findViewById(R.id.editText3);
        editId = (EditText) findViewById(R.id.editText4);
        bAdd = (Button) findViewById(R.id.bAdd);
        bViewall = (Button) findViewById(R.id.bViewall);
        bUpdate = (Button) findViewById(R.id.bUpdate);
        bDelete = (Button) findViewById(R.id.bDelete);

        AddData();
        ViewAll();
        updateData();
        DeleteData();
    }

    public void DeleteData() {
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.deleteData(editId.getText().toString());

                if (deleteRows > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Failed to Deleted!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateData() {
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateDate(editId.getText().toString(), editFIRST_NAME.getText().toString(), editLAST_NAME.getText().toString(), editNO_HP.getText().toString());

                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Failed to Updaye",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddData() {
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editId.getText().toString(), editFIRST_NAME.getText().toString(),editLAST_NAME.getText().toString(),editNO_HP.getText().toString());

                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Inserted",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewAll() {
        bViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();

                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id           : " + res.getString(0) + "\n");
                    buffer.append("First Name   : " + res.getString(1) + "\n");
                    buffer.append("Last Name    : " + res.getString(2) + "\n");
                    buffer.append("No HP        : " + res.getString(3) + "\n\n");

                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
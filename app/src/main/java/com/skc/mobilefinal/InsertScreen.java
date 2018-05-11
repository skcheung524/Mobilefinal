package com.skc.mobilefinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InsertScreen extends AppCompatActivity {

    MySQLiteHelper dbHelper;
    EditText editName, editMoney, editNotes,editID;
    Button insertData;
    Button updateData;
    Button deleteData;

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String MONEY_KEY = "money";
    public static final String NOTES_KEY = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_screen);
        dbHelper = new MySQLiteHelper(this);


        insertData = (Button) findViewById(R.id.button);
        updateData = (Button) findViewById(R.id.button2);
        deleteData = (Button) findViewById(R.id.button3);
        editName = (EditText) findViewById(R.id.editTextExpenses);
        editMoney = (EditText) findViewById(R.id.editTextCost);
        editNotes = (EditText) findViewById(R.id.editTextNotes);
        editID = (EditText) findViewById(R.id.editTextID);


        // try to get info from Bundle
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            //we have extras, populate fields
            editID.setText(extras.getString(ID_KEY));
            editName.setText(extras.getString(NAME_KEY));
            editMoney.setText(extras.getString(MONEY_KEY));
            editNotes.setText(extras.getString(NOTES_KEY));
        }


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertDataToDatabase();
                emptyFieldAfterSubmit();

            }


        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateDataToDatabase();
                emptyFieldAfterSubmit();

            }


        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteData();
                emptyFieldAfterSubmit();

            }


        });

    }

    //add info into database
    public void insertDataToDatabase() {

        boolean dataInserted = dbHelper.insertData(editName.getText().toString(), editMoney.getText().toString(), editNotes.getText().toString());
            if (dataInserted)
                Toast.makeText(InsertScreen.this, "Data Inserted~~! Yay", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(InsertScreen.this, "Data not inserted! You must filled out the Expenses(Activity Name) and $$ box.", Toast.LENGTH_LONG).show();
    }

    //update the data
    public void updateDataToDatabase() {

        boolean dataUpdate = dbHelper.updateData(editID.getText().toString(),editName.getText().toString(), editMoney.getText().toString(), editNotes.getText().toString());
            if(dataUpdate)
                Toast.makeText(InsertScreen.this,"Data is updated! Yayay",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(InsertScreen.this,"Update fail desu!",Toast.LENGTH_LONG).show();
    }

    // delete data
    public void deleteData() {

       int deletedRows = dbHelper.deleteData(editID.getText().toString());
            if(deletedRows > 0)
                 Toast.makeText(InsertScreen.this,"Data deleted! Yayayay",Toast.LENGTH_LONG).show();
             else
                 Toast.makeText(InsertScreen.this,"Fail deleting data! Try again!!!!",Toast.LENGTH_LONG).show();

    }


    //Empty after pressing insert into planner
    public void emptyFieldAfterSubmit(){

        editName.getText().clear();
        editMoney.getText().clear();
        editNotes.getText().clear();

    }
}
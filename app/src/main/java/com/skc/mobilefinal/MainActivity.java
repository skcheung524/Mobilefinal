package com.skc.mobilefinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper dbHelper;
    ImageButton ImageNew;
    ImageButton ImageViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MySQLiteHelper(getApplicationContext());

        addListenerOnButton();
        viewList();
    }


    public void addListenerOnButton() {

        ImageNew = (ImageButton) findViewById(R.id.imageButtonNew);
        ImageNew.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), InsertScreen.class);
                startActivity(intent);

            }
        });

    }

    public void viewList() {

        ImageViewData= (ImageButton) findViewById(R.id.imageButtonViewList);
        ImageViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = dbHelper.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error!!!","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("$$ :"+ res.getString(2)+"\n");
                            buffer.append("Notes :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                        res.close();
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
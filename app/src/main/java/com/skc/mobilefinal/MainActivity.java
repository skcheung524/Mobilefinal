package com.skc.mobilefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper dbHelper;
    ImageButton ImageNew;
    ImageButton ImageViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        Intent intent = new Intent(getApplicationContext(), BudgetListActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }





}
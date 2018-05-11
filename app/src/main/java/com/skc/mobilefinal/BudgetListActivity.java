package com.skc.mobilefinal;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BudgetListActivity extends ListActivity {

    public static final String TAG = "BDGT LIST";

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String MONEY_KEY = "money";
    public static final String NOTES_KEY = "notes";

    MySQLiteHelper dbHelper;

    List<BudgetItem> budgetItems = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling_list);

        lv = getListView();
        dbHelper = new MySQLiteHelper(getApplicationContext());
        budgetItems = getDbItems();
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, budgetItems);

        lv.setAdapter(adapter);

        /*
         * Setting on click for each list view item. because our array adapter has list of budget items
         * we can get the item at click position and use the fields on pojo to putExtra before we start insertScreen activity
         */
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                BudgetItem budgetItem = (BudgetItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), InsertScreen.class);

                intent.putExtra(ID_KEY, budgetItem.getId());
                intent.putExtra(NAME_KEY, budgetItem.getName());
                intent.putExtra(MONEY_KEY, budgetItem.getMoney());
                intent.putExtra(NOTES_KEY, budgetItem.getNotes());

                startActivity(intent);

            }
        });
    }

    /**
     * Uses dbHelper to get data and Cursor to go over each item and add to list
     * @return List of budgetitems that will be used in array adapter
     */
    private List<BudgetItem> getDbItems() {

        List<BudgetItem> resultList = new ArrayList<>();
        Cursor res = dbHelper.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error!!!","Nothing found");

            //add and return an blank item so no null pointer error
            resultList.add(new BudgetItem("empty","item","",""));
            return resultList;
        }


        //create a new budgetItem and add to list
        while (res.moveToNext()) {

            resultList.add(new BudgetItem(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3)
            ));

        }

        res.close();

        return resultList;
    }

    /**
     * I like this more than toast as it forces an action rather than fade away,
     * This behavior is better for empty list
     * @param title
     * @param Message
     */
    public void showMessage(String title,String Message){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /**
     * When started new activity such as InsertScreen, clear the adapter and budgetItems array
     */
    @Override
    protected void onPause() {
        super.onPause();
        budgetItems.clear();
        adapter.clear();
        Log.d(TAG, "onPause: PAUSED "+ budgetItems.toString());
    }

    /**
     * When returning to List Activity from back button we need a resume to fill items again
     * because back button cause issue
     */
    @Override
    protected void onResume() {
        super.onResume();
        budgetItems = getDbItems();
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, budgetItems);
        lv.setAdapter(adapter);
        Log.d(TAG, "onResume: ON RESUME "+budgetItems.toString());
    }
}
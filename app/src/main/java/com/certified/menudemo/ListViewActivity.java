package com.certified.menudemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> mPlanetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mListView = findViewById(R.id.listView);
        String planets[] = new String[] {"Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
        mPlanetsList = new ArrayList<String>();
        mPlanetsList.addAll(Arrays.asList(planets));
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPlanetsList);
        mListView.setAdapter(mArrayAdapter);

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.listDelete:
                        deleteSelectedItem();
                        mode.finish();
                        return true;

//                    case R.id.listRefresh:

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    private void deleteSelectedItem() {
        SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
        int itemCount = mListView.getCount();

        for(int i = itemCount - 1; i >= 0; i--) {
            if(checkedItemPositions.get(i)) {
                mArrayAdapter.remove(mPlanetsList.get(i));
            }
            checkedItemPositions.clear();
            mArrayAdapter.notifyDataSetChanged();
        }
    }
}

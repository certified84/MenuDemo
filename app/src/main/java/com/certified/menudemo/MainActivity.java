package com.certified.menudemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.action_mode_context_menu, menu);
//            getMenuInflater().inflate(R.menu.action_mode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_mode_context_menu_item_1:
                    TextView textView = findViewById(R.id.textView);
                    textView.setText("");
                    Toast.makeText(MainActivity.this, "Action Mode Context menu 1", Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;

                case R.id.action_mode_context_menu_item_2:
                    Toast.makeText(MainActivity.this, "Action Mode Context menu 2", Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;

                case R.id.action_mode_context_menu_item_3:
                    Toast.makeText(MainActivity.this, "Action Mode Context  menu 3", Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        this.registerForContextMenu(textView);
        this.registerForContextMenu(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.textView);
                textView.setText("This is just a trial");
            }
        });

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView textView = findViewById(R.id.textView);
//                textView.setText("");
//            }
//        });

        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode != null) {
                    return false;
                }

                mActionMode = startSupportActionMode(mActionModeCallBack);
                return true;
            }
        });

        Button button1 = findViewById(R.id.button_list_view);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Check out what I learned");

        menu.addIntentOptions(
                R.id.intent_group,
                0,
                0,
                this.getComponentName(),
                null,
                intent,
                0,
                null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);

        switch (item.getItemId())
        {
            case R.id.menu_item_1:
                Toast.makeText(this, "Options menu 1", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_item_2:
                Toast.makeText(this, "Options menu 2", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_item_3:
                Toast.makeText(this, "Options menu 3", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.floating_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.floating_context_menu_item_1:
                Toast.makeText(this, "Floating Context Menu Item 1", Toast.LENGTH_LONG).show();
                break;

            case R.id.floating_context_menu_item_2:
                Toast.makeText(this, "Floating Context Menu Item 2", Toast.LENGTH_LONG).show();
                break;

            case R.id.floating_context_menu_item_3:
                Toast.makeText(this, "Floating Context Menu Item 3", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    public void clickHandler(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popup_menu_item_1:
                Toast.makeText(this, "Popup Menu Item 1", Toast.LENGTH_LONG).show();
                break;

            case R.id.popup_menu_item_2:
                Toast.makeText(this, "Popup Menu Item 2", Toast.LENGTH_LONG).show();
                break;

            case R.id.popup_menu_item_3:
                Toast.makeText(this, "Popup Menu Item 3", Toast.LENGTH_LONG).show();
                break;

            default:
                return false;
        }
        return true;
    }
}

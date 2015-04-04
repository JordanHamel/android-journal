package com.example.journal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListEntriesActivity extends ActionBarActivity {

    Context context = this;
    MySQLiteOpenHelper db = new MySQLiteOpenHelper(this);
    List<Entry> entries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entries);

        entries = db.getAllEntries();
        final ListView entriesList = (ListView) findViewById(R.id.entries_list);
        final EntryAdapter entryAdapter = new EntryAdapter(this, entries);
        entriesList.setAdapter(entryAdapter);

        entriesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setMessage("Would you like to delete this journal entry?");
                alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEntry(entryAdapter, entries.get(pos));
                        dialog.dismiss();
                    }
                });
                alertDialog.setCancelable(true);

                try {
                    alertDialog.show();
                    return true;
                } catch (Exception e) {
                    Log.d("debug", e.toString());
                    return false;
                }
            }
        });
    }

    private void deleteEntry(EntryAdapter entryAdapter, Entry entry) {
        db.deleteEntry(entry);
        entries.remove(entry);
        entryAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_entries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_entry) {
            Intent intent = new Intent(this, NewEntryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

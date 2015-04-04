package com.example.journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewEntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        final MySQLiteOpenHelper db = new MySQLiteOpenHelper(this);

        final EditText entryBodyField = (EditText) findViewById(R.id.newEntryBody);

        findViewById(R.id.newEntryCreateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entryBody = entryBodyField.getText().toString();
                Long entryTime = System.currentTimeMillis();
                Entry entry = new Entry(entryBody, entryTime);
                db.addEntry(entry);

                Intent intent = new Intent(getApplicationContext(), ListEntriesActivity.class);
                startActivity(intent);
            }
        });
    }

}

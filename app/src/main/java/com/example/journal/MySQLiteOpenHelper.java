package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "entriesManager";
    private static final String TABLE_ENTRIES = "entries";
    private static final String KEY_ID = "id";
    private static final String KEY_BODY = "body";
    private static final String KEY_TIME = "time";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TO_DOS_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_BODY + " TEXT, "
                + KEY_TIME + " INTEGER)";
        db.execSQL(CREATE_TO_DOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        onCreate(db);
    }

    public void addEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BODY, entry.getBody());
        values.put(KEY_TIME, entry.getTime());

        db.insert(TABLE_ENTRIES, null, values);
        db.close();
    }

    public List<Entry> getAllEntries() {
        List<Entry> entryList = new ArrayList<Entry>();
        String selectQuery = "SELECT  * FROM " + TABLE_ENTRIES + " ORDER BY " + KEY_TIME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Entry entry = new Entry();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setBody(cursor.getString(1));
                entry.setTime(Long.getLong(cursor.getString(2)));

                entryList.add(entry);
            } while (cursor.moveToNext());
        }

        return entryList;
    }

    public void deleteEntry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENTRIES, KEY_ID + " = ?",
                new String[] { String.valueOf(entry.getId()) });
        db.close();
    }
}


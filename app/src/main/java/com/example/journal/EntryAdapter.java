package com.example.journal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends BaseAdapter {

    private List<Entry> entries;
    private LayoutInflater layoutInflater;

    public EntryAdapter(Activity activity, List<Entry> entries) {
        layoutInflater = activity.getLayoutInflater();
        this.entries = entries;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int i) {
        return entries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.entry_list_item, viewGroup, false);
        }

        TextView entryBody = (TextView) convertView.findViewById(R.id.entryBody);
        entryBody.setText(entries.get(i).getBody());

        return convertView;
    }
}

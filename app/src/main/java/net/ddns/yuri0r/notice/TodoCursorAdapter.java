package net.ddns.yuri0r.notice;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

class TodoCursorAdapter extends CursorAdapter {
    TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textView = (TextView) view.findViewById(R.id.textView);
        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow(NoticeDbHelper.NoticeEntry.COLUMN_NAME_TITLE));
        String id = cursor.getString(cursor.getColumnIndexOrThrow(NoticeDbHelper.NoticeEntry._ID));
        Log.e(TAG, "bindView:  " + id);
        // Populate fields with extracted properties
        textView.setText(title);
        textView.setTag(id);
    }
}

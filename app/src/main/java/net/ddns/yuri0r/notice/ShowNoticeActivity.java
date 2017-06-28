package net.ddns.yuri0r.notice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNoticeActivity extends Activity {

    NoticeDbHelper dbHelper = new NoticeDbHelper(this);
     /*-----------------------UI Elements--------------------------------------------------------*/
     TextView titleTextView ;
     TextView subTitleTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownotice);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id"); //if it's a string you stored.
        String title = "Fatal";
        String subTitle = "Error";
        titleTextView = (TextView) findViewById(R.id.title);
        subTitleTextView = (TextView) findViewById(R.id.subtitle);

        Cursor cursor = dbHelper.getTask(id);
        if (cursor.moveToFirst()){
            do{
                title = cursor.getString(cursor.getColumnIndexOrThrow(NoticeDbHelper.NoticeEntry.COLUMN_NAME_TITLE));
                subTitle = cursor.getString(cursor.getColumnIndexOrThrow(NoticeDbHelper.NoticeEntry.COLUMN_NAME_SUBTITLE));
            }while(cursor.moveToNext());
        }
        cursor.close();


        titleTextView.setText(title);
        subTitleTextView.setText(subTitle);

    }
}

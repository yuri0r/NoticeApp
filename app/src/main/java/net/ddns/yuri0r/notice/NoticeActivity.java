package net.ddns.yuri0r.notice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeActivity extends Activity {


     /*-----------------------UI Elements--------------------------------------------------------*/
     TextView titleTextView ;
     TextView subTitleTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Intent intent = getIntent();
        String value = intent.getStringExtra("id"); //if it's a string you stored.

        titleTextView = (TextView) findViewById(R.id.title);
        subTitleTextView = (TextView) findViewById(R.id.subtitle);

        titleTextView.setText(value);



    }
}

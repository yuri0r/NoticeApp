package net.ddns.yuri0r.notice;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {

    NoticeDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new NoticeDbHelper(this);
        dbHelper.addTask("test","it works");
/*-----------------------UI Elements--------------------------------------------------------*/
        ListView listView = (ListView) findViewById(R.id.listView);


/*--------------------Other Variables----------------*/
        Cursor todoCursor = dbHelper.getTasks();
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor);
        listView.setAdapter(todoAdapter);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

}

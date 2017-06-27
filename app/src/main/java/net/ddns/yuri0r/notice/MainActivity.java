package net.ddns.yuri0r.notice;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import static android.R.attr.value;


public class MainActivity extends AppCompatActivity {

    NoticeDbHelper dbHelper;
    ListView listView;
/*------------------------------------live cycle---------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new NoticeDbHelper(this);
        dbHelper.addTask("test","it works");
/*-----------------------UI Elements--------------------------------------------------------*/
        listView = (ListView) findViewById(R.id.listView);


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

/*------------------Listener--------------------------------------------------------------------*/

    public void openNotice(View view) {

        Intent intent = new Intent(this, NoticeActivity.class);
        intent.putExtra("id", (String) view.getTag()); //Optional parameters
        this.startActivity(intent);
    }
}

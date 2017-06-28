package net.ddns.yuri0r.notice;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    NoticeDbHelper dbHelper = new NoticeDbHelper(this);
    ListView listView;
    TodoCursorAdapter todoAdapter;

/*------------------------------------live cycle---------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbHelper.addTask("test","it works");
/*-----------------------UI Elements--------------------------------------------------------*/
        listView = (ListView) findViewById(R.id.listView);

/*--------------------Other Variables----------------*/
        Cursor todoCursor = dbHelper.getTasks();
        todoAdapter = new TodoCursorAdapter(this, todoCursor);
        listView.setAdapter(todoAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        todoAdapter.getCursor().close();
        todoAdapter.swapCursor(dbHelper.getTasks());
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoAdapter.getCursor().close();
        todoAdapter.swapCursor(dbHelper.getTasks());
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

/*------------------Listener--------------------------------------------------------------------*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
            addNotice();
            // Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openNotice(View view) {

        Intent intent = new Intent(getApplicationContext(), ShowNoticeActivity.class);
        Log.println(Log.ERROR, (String)view.getTag(),"openNotice " + view.getTag().toString());
        intent.putExtra("id", (String) view.getTag()); //Optional parameters
        this.startActivity(intent);
    }


    public void doNotice(View view) {

        Log.println(Log.ERROR, (String)view.getTag(),"doNotice" + view.getTag().toString());
        if(dbHelper.deleteTask(view.getTag().toString())) {
            Log.println(Log.ERROR, (String)view.getTag(),"doNotice changing dataSet ");
            todoAdapter.getCursor().close();
            todoAdapter.swapCursor(dbHelper.getTasks());
        }
    }

    public void addNotice() {
        Intent intent = new Intent(getApplicationContext(), addNoticeActivity.class);
        this.startActivity(intent);
    }

    /*--------------------------casual methods------------------------------------------------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }




}

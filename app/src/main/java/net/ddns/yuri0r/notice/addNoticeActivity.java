package net.ddns.yuri0r.notice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addNoticeActivity extends AppCompatActivity {

    EditText promptTitle;
    EditText promptSubTitle;
    NoticeDbHelper dbHelper = new NoticeDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        promptTitle = (EditText) findViewById(R.id.prompt_title);
        promptSubTitle = (EditText) findViewById(R.id.prompt_subtitle);
    }

    void saveNotice (View view){
        String title = promptTitle.getText().toString();
        String subTitle = promptSubTitle.getText().toString();

        if( dbHelper.addTask(title, subTitle)){
            Toast.makeText(this, "Notice Saved", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}

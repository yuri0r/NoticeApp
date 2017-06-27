package net.ddns.yuri0r.notice;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

import static net.ddns.yuri0r.notice.NoticeDbHelper.NoticeEntry.COLUMN_NAME_SUBTITLE;
import static net.ddns.yuri0r.notice.NoticeDbHelper.NoticeEntry.COLUMN_NAME_TITLE;
import static net.ddns.yuri0r.notice.NoticeDbHelper.NoticeEntry.TABLE_NAME;

class NoticeDbHelper extends SQLiteOpenHelper {


/*--------------------------------db Statics---------------------------------------*/


    /* Inner class that defines the table contents */
    static class NoticeEntry implements BaseColumns {
        static final String TABLE_NAME = "notice";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    NoticeEntry._ID + " INTEGER PRIMARY KEY," +
                    NoticeEntry.COLUMN_NAME_TITLE + " TEXT," +
                    NoticeEntry.COLUMN_NAME_SUBTITLE + " TEXT" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


/*-----------------------------db methods--------------------------------------------*/


    Cursor getTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    Cursor getTask(int taskID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE TaskID=" + taskID, null);
    }

    boolean addTask(String title, String subtitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE, title);
        contentValues.put(COLUMN_NAME_SUBTITLE, subtitle);
        long result = db.insert(TABLE_NAME, null ,contentValues);
        return result != -1;
    }

    public boolean deleteTask(int taskID) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "TaskID=?", new String[]{Integer.toString(taskID)});
        return result != -1;
    }


/*-----------------------------db managment-------------------------------------------*/


    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Notice.db";



    NoticeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}

package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;

public class DatabaseClass extends SQLiteOpenHelper {

    // Main System class Reference Object
    SQLiteDatabase SQLd;

    // DATABASE NAME
    private static final String DATABASE_NAME = "lab5Database";

    //DATABASE VERSION
    private static final int DATABAS_VERSION = 3;

    // DATABASE TABLE
    // NOTE: WE CAN HAVE MULTIPLE TABLES,BUT FOR NOW, WE JUST NEED ONE TABLE TO STORE DATA
    private static final String TABLE_NAME = "messages";

    // COLUMN NAMES
    public static final String ROW_ID= "id";
    public static final String MESSAGE="message";
    public static final String SEND_MSG="isSend";


    // THIS IS A CURRENT CLASS CONSTRUCTOR THAT ARE EXPENDING SUPER CLASS PARAMETERS.
    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABAS_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

            // This veriable, CREATE_TABLE, will store the table create query, so that we can use it later to create table.
         String MESSAGE_CREATE_TABLE = (
                 " CREATE TABLE " + TABLE_NAME +
                " ( " + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MESSAGE + " varchar, "
                    + SEND_MSG + " varchar);");

         db.execSQL(MESSAGE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String MESSGAE_TABLE_DROP = (
                " DROP TABLE IF EXISTS " + TABLE_NAME
                );

        db.execSQL(MESSGAE_TABLE_DROP);
        onCreate(db);
    }

    public Long insertMessage(String msg, boolean isSend) {

        // This method allow us permission to store data into database
        SQLd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MESSAGE, msg);

        if (isSend) {
            cv.put(SEND_MSG, 0);

        }
            else {
                cv.put(SEND_MSG,1);

            }

            Long result = SQLd.insert(TABLE_NAME, null, cv);

            return result;

    }



//    public long insertReceiveMessage(String msg) {
//
//        // This method allow us permission to store data into database
//        SQLd = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(SEDN_MSG, "null");
//        cv.put(RECEIVE_MSG,msg);
//        return SQLd.insert(TABLE_NAME,null, cv);
//
//
//    }

    // THIS METHOD IS INCOMPLETE //
//    public String printCursor(){
//
//        SQLd = this.getReadableDatabase();
//
//        String[] columns = new String[]{ROW_ID,SEDN_MSG,RECEIVE_MSG};
////        Cursor c = SQLd.query(TABLE_NAME,columns,null,null,null,null,null);
//       Cursor c = SQLd.rawQuery("select * from " + TABLE_NAME,null);
//
//        int row = c.getColumnIndex(ROW_ID);
//        int sendMsg = c.getColumnIndex(SEDN_MSG);
//        int receiveMsg = c.getColumnIndex(RECEIVE_MSG);
//
//        String result ="";
////        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
//
//            result =  result  +  c.getString(sendMsg) + " " + c.getString(receiveMsg) + "\n";
//
////        }
//
//        Log.v("DatabaseClass",result);
//
//        return result;
//
//    }


    // HELP EXAMPLE //
    public Cursor printCursor(){

        // This object allow us to get read permission from From System Class (SQLiteDatabase)
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to view the whole table data!!!
        String query = "Select * from "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        Log.v("Dataase Version", Integer.toString(db.getVersion()));

        Log.v("Column numbers", Integer.toString(cursor.getColumnCount()));

        Log.v("Column Names", Arrays.toString(cursor.getColumnNames()));

        Log.v("Total Rows",Integer.toString(cursor.getCount()));

        Log.v("Each Instances",DatabaseUtils.dumpCursorToString(cursor));

        return cursor;
    }


}

package com.cosmos.sattallah.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sattallah on 10/27/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phoneBook";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACTS = "contacts";
    private static final String CONTACTS_ID = "_id";
    private static final String CONTACTS_NAME = "name";
    private static final String CONTACTS_JOB_DESCRIPTION = "jobDescription";
    private static final String CONTACTS_PHONE = "phone";


    private static final String QUERY_CREATE_CONTACT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    "("+ CONTACTS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    CONTACTS_NAME +" TEXT NOT NULL,"+
                    CONTACTS_JOB_DESCRIPTION + " TEXT NOT NULL," +
                    CONTACTS_PHONE + " TEXT NOT NULL )";

    private static final String QUERY_DELETE_CONTACT = "DELETE TABLE IF EXISTS " + TABLE_CONTACTS;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DELETE_CONTACT);
    }

    public long insertContact(ContactHolder contactHolder){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTACTS_NAME, contactHolder.name);
        contentValues.put(CONTACTS_JOB_DESCRIPTION, contactHolder.jobDescription);
        contentValues.put(CONTACTS_PHONE, contactHolder.phone);

        long result = database.insert(TABLE_CONTACTS, null, contentValues);
        return result;
    }

    public ArrayList<ContactHolder> getContactList(){
        ArrayList<ContactHolder> contactHolderArrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_CONTACTS, null, null, null, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            int id = cursor.getInt(cursor.getColumnIndex(CONTACTS_ID));
            String name = cursor.getString(cursor.getColumnIndex(CONTACTS_NAME));
            String jobDescription = cursor.getString(cursor.getColumnIndex(CONTACTS_JOB_DESCRIPTION));
            String phone = cursor.getString(cursor.getColumnIndex(CONTACTS_PHONE));

            ContactHolder contactHolder = new ContactHolder(id, name, jobDescription, phone);
            contactHolderArrayList.add(contactHolder);
            cursor.moveToNext();
        }
        return contactHolderArrayList;
    }
}

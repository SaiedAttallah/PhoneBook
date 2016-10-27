package com.cosmos.sattallah.phonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    protected EditText etName, etJobDescription, etPhone;
    public DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        etName = (EditText) findViewById(R.id.name_editText);
        etJobDescription = (EditText) findViewById(R.id.jobDescription_editText);
        etPhone = (EditText) findViewById(R.id.phone_editText);
        dbHelper = new DBHelper(getApplicationContext());
    }

    public void saveContact(View button) {
        String name = etName.getText().toString().trim();
        String jobDescription = etJobDescription.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        ContactHolder contactHolder = new ContactHolder(name, jobDescription, phone);

        if (dbHelper.insertContact(contactHolder) != -1){
            setResult(RESULT_OK);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_contact) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

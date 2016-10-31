package com.cosmos.sattallah.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView contactListView;
    public ArrayList<ContactHolder> contactHolderArrayList;
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactListView = (ListView) findViewById(R.id.listView);
        dbHelper = new DBHelper(getApplicationContext());

        updateContactList();
    }

    public void updateContactList() {
        contactHolderArrayList = dbHelper.getContactList();
        contactListView.setAdapter(new ContactAdapter());
    }

    public class ContactAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return contactHolderArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layoutView = convertView;
            if (layoutView == null){
                layoutView = getLayoutInflater().inflate(R.layout.contact_list_item, parent, false);
            }
            TextView tvName = (TextView) layoutView.findViewById(R.id.name_textView);
            TextView tvJobDescription = (TextView) layoutView.findViewById(R.id.jobDescription_textView);
            TextView tvPhone = (TextView) layoutView.findViewById(R.id.phone_textView);

            ContactHolder contactHolder = contactHolderArrayList.get(position);

            tvName.setText(contactHolder.name);
            tvJobDescription.setText(contactHolder.jobDescription);
            tvPhone.setText(contactHolder.phone);
            return layoutView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK){
            updateContactList();
            Toast.makeText(getApplicationContext(), "Contact Saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_contact) {
            startActivityForResult(new Intent(MainActivity.this, AddContactActivity.class), 100);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

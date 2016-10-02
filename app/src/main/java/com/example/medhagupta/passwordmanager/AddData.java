package com.example.medhagupta.passwordmanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AddData extends ActionBarActivity {

    Button addAccount;
    Button viewAccounts;
    Button addNotes;
    Button viewNotes;
    Button internalStorageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        getSupportActionBar().hide();

        addAccount=(Button)findViewById(R.id.buttonAddAccount);
        viewAccounts=(Button)findViewById(R.id.buttonViewAccounts);
        addNotes=(Button)findViewById(R.id.buttonAddNotes);
        viewNotes=(Button)findViewById(R.id.buttonViewNotes);
        internalStorageButton=(Button)findViewById(R.id.intStorage);


        addAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentAddAccount=new Intent(AddData.this,AddAccount.class);
                startActivity(intentAddAccount);
            }
        });

        viewAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentViewAccount=new Intent(AddData.this,ViewAccount.class);
                startActivity(intentViewAccount);

            }
        });

        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddNotes=new Intent(AddData.this, AddNotes.class);
                startActivity(intentAddNotes);
            }
        });

        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentViewNotes=new Intent(AddData.this, ViewNotes.class);
                startActivity(intentViewNotes);
            }
        });

        internalStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIntStorage=new Intent(AddData.this, InternalStorageActivity.class);
                startActivity(intentIntStorage);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.medhagupta.passwordmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddAccount extends ActionBarActivity {

    EditText editTextAccountName, editTextPassword, editTextUserName;
    Button btnAddAccount;
    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        getSupportActionBar().hide();

        loginDatabaseAdapter=new LoginDatabaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();

        editTextAccountName=(EditText)findViewById(R.id.AccountName);
        editTextUserName=(EditText)findViewById(R.id.addAccountUsername);
        editTextPassword=(EditText)findViewById(R.id.addAccountPasssword);
        btnAddAccount=(Button)findViewById(R.id.submit);

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String AccountName = editTextAccountName.getText().toString();
                String UserName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                boolean accountNotExists = loginDatabaseAdapter.isAccount(AccountName);

                if(accountNotExists == false) {
                    Toast.makeText(getApplicationContext(), "You have already added this account", Toast.LENGTH_LONG).show();

                }
                else {
                    String userID = loginDatabaseAdapter.getUsername("PasswordManager");
                    // check if any of the fields are vacant
                    if (AccountName.equals("") || UserName.equals("") || password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        // Save the Data in Database
                        loginDatabaseAdapter.insertEntry(AccountName, UserName, password);
                        Toast.makeText(getApplicationContext(), "Account Successfully Added ", Toast.LENGTH_LONG).show();
                        //account.addItemsToList();
                    }
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_account, menu);
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

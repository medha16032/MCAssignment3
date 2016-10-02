package com.example.medhagupta.passwordmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ViewAccount extends ActionBarActivity {

    EditText accountName;
    EditText username;
    EditText password;
    Button submit;
    Button updateButton;
    Button deleteButton;

    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        getSupportActionBar().hide();

        loginDatabaseAdapter=new LoginDatabaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();


        accountName=(EditText)findViewById(R.id.viewAccountAccountName);
        username=(EditText)findViewById(R.id.viewAccountUsername);
        password=(EditText)findViewById(R.id.viewAccountPassword);
        submit=(Button)findViewById(R.id.viewAccountSubmit);
        updateButton=(Button)findViewById(R.id.update);
        deleteButton=(Button)findViewById(R.id.delete);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AccountName=accountName.getText().toString();

                boolean accountNotExists=loginDatabaseAdapter.isAccount(AccountName);

                if(accountNotExists==true) {
                    Toast.makeText(getApplicationContext(), "You have not added this account", Toast.LENGTH_LONG).show();

                }

                else
                {
                String userID=loginDatabaseAdapter.getUsername(AccountName);
                String storedPassword=loginDatabaseAdapter.getPassword(AccountName);
                username.setText(userID);
                password.setText(storedPassword);

            }}
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AccountName=accountName.getText().toString();
                String usernameEditText=username.getText().toString();
                String passwordEditText=password.getText().toString();

                if (AccountName.equals("") || usernameEditText.equals("") || passwordEditText.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean accountNotExists=loginDatabaseAdapter.isAccount(AccountName);

                    if(accountNotExists==true) {
                        Toast.makeText(getApplicationContext(), "You have not added this account", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        loginDatabaseAdapter.updateEntry(AccountName, usernameEditText, passwordEditText);
                        Toast.makeText(getApplicationContext(), "Account details updated", Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AccountName=accountName.getText().toString();
                String usernameEditText=username.getText().toString();
                String passwordEditText=password.getText().toString();

                if (AccountName.equals("") || usernameEditText.equals("") || passwordEditText.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                }

                else if(AccountName.equalsIgnoreCase("PasswordManager"))
                {
                    Toast.makeText(getApplicationContext(), "Operation failed", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean accountNotExists=loginDatabaseAdapter.isAccount(AccountName);

                    if(accountNotExists==true) {
                        Toast.makeText(getApplicationContext(), "You have not added this account", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        loginDatabaseAdapter.deleteEntry(AccountName);
                        accountName.setText("");
                        username.setText("");
                        password.setText("");
                        Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_account, menu);
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

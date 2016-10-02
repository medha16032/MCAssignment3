package com.example.medhagupta.passwordmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends ActionBarActivity {

    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;
    String userName;

    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        loginDatabaseAdapter=new LoginDatabaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.signupUsername);
        editTextPassword=(EditText)findViewById(R.id.signupPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.confirmPassword);

        btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

                if(userName.length()==0)
                    editTextUserName.setError("User name is required!");

                if(password.length()==0 || password.length()<8)
                    editTextPassword.setError("Password is required and must be 8 characters long!");

                if(confirmPassword.length()==0 || !password.equals(confirmPassword))
                    editTextConfirmPassword.setError("Passwords must match");


                else
                {
                    // Save the Data in Database
                    loginDatabaseAdapter.insertEntry("PasswordManager",userName, password);
                    String pass=loginDatabaseAdapter.getSinlgeEntry(userName);

                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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

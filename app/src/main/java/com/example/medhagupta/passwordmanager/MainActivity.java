package com.example.medhagupta.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText editTextNameToLogin, editTextPasswordToLogin;
    Button buttonLogin;
    Button buttonSignup;
    SharedPreferences sp;
    String mypref="mypref";
    String Name="namekey";
    String Password="passkey";
    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loginDatabaseAdapter=new LoginDatabaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();

        editTextNameToLogin= (EditText) findViewById(R.id.Username);
        editTextPasswordToLogin= (EditText) findViewById(R.id.Password);
        buttonLogin= (Button) findViewById(R.id.Login);
        buttonSignup= (Button) findViewById(R.id.SignUp);

        sp=getSharedPreferences(mypref, Context.MODE_PRIVATE);
        if(sp.contains(Name)){
            editTextNameToLogin.setText(sp.getString(Name, ""));
        }
        if(sp.contains(Password)){
            editTextPasswordToLogin.setText(sp.getString(Password, ""));
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=editTextNameToLogin.getText().toString();
                String userID=loginDatabaseAdapter.getUsername("PasswordManager");
                String password=editTextPasswordToLogin.getText().toString();
                String storedPassword=loginDatabaseAdapter.getSinlgeEntry(userName);

                if(userName.equals(userID)) {

                    if (password.equals(storedPassword)) {

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(Name, userName);
                        editor.putString(Password, password);
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent intentLogin=new Intent(getApplicationContext(),AddData.class);
                        startActivity(intentLogin);
                    } else {
                        Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name does not exists", Toast.LENGTH_LONG).show();
                }


            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Create Intent for SignUpActivity  abd Start The Activity

                Boolean canSignup=loginDatabaseAdapter.isAccount("PasswordManager");
                if(canSignup==true) {
                    Intent intentSignUP = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(intentSignUP);
                }
                else{
                    Toast.makeText(MainActivity.this, "You already have an account!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

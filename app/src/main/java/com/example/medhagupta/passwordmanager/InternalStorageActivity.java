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

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class InternalStorageActivity extends ActionBarActivity {

    Button b2;
    TextView tv;


    String data;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        getSupportActionBar().hide();


        b2=(Button)findViewById(R.id.loadButton);


        tv=(TextView)findViewById(R.id.textViewRead);

                data = "Since now a days there are lots of accounts, one can easily"+
                        " forget the details of those accounts. So, you can save the details in this app. Also, notes can be saved here.";

                try {
                    FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);
                    fOut.write(data.getBytes());
                    fOut.close();
                    //Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp = "";

                    while ((c = fin.read()) != -1) {
                        temp = temp + Character.toString((char) c);
                    }
                    tv.setText(temp);
                    Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_internal_storage, menu);
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

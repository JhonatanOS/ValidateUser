package com.dev.joliveira.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase connection;

    private EditText login;
    private EditText password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recovery values of EditTexts
        login = (EditText)findViewById(R.id.edt_Login);
        password = (EditText)findViewById(R.id.edt_password);
        submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser(login.getText().toString(),password.getText().toString()); // receive the values of editText and convert to Strings
            }
        });

        try { // try a connection with database

            dataBase = new DataBase(this);
            connection = dataBase.getWritableDatabase();

        }catch (SQLException ex){ //run only don't have connection

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("ERROR");
            dlg.setMessage("Failed to create the database" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    //method for user validation
    public void validateUser(String user, String password){
        String[]selectionArgs = new String[]{user,password};
        try{
            int i = 0;
            Cursor c = null;
            c = connection.rawQuery("SELECT * FROM USERS WHERE USER=? AND PASSWORD =?",selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();

            if( i == 1){
                Intent it = new Intent(MainActivity.this, Hello.class);
                startActivity(it);
                Toast.makeText(getApplication(), "WELCOME",Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(getApplication(), "User or password don't match",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }


}

package com.example.raj.newbmicalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Double b,a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void submitreports(View view){
        EditText sname=(EditText)findViewById(R.id.edit_text_views1);
        String name=sname.getText().toString();
        EditText sname2=(EditText)findViewById(R.id.edit_text_views2);
        String name2=sname2.getText().toString();
        b=Double.parseDouble(name);
        a=Double.parseDouble(name2);
        Double feet=b*12;
        Double inches=((feet+a)*2.54)/100;
        String message="Your Height in metres is: \n"+inches;
        displayMessage(message);



    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.textviews);
        priceTextView.setText(message);
    }

}

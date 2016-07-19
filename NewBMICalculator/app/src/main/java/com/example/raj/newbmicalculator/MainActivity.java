package com.example.raj.newbmicalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Double a,b;
    String pricemessage,name,name2,name3,name4,sirname="",sirname2="";
    Double bmi;

    EditText buckysInput;
    TextView buckysText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_TEXT, pricemessage);
                intent.putExtra(Intent.EXTRA_SUBJECT, "BMI Report");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        final Button kbutton=(Button)findViewById(R.id.kbutton);
        kbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        buckysInput = (EditText) findViewById(R.id.edit_text_view);
        buckysText = (TextView) findViewById(R.id.buckysText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();




    }

    //Add a product to the database
   public void addButtonClicked(View view){
       Product product = new Product(buckysInput.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
   }

    //Delete items
    public void deleteButtonClicked(View view){
        String inputText = buckysInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText("");
    }


    public void submitreport(View view)

    {
        EditText fieldname = (EditText) findViewById(R.id.edit_text_view);
        name = fieldname.getText().toString();


        EditText fieldname2 = (EditText) findViewById(R.id.edit_text_view2);
        name2 = fieldname2.getText().toString();
        EditText fieldname3 = (EditText) findViewById(R.id.edit_text_view3);
        name3 = fieldname3.getText().toString();
        a = Double.parseDouble(name3);
        EditText fieldname4 = (EditText) findViewById(R.id.edit_text_view4);
        name4 = fieldname4.getText().toString();

        b = Double.parseDouble(name4);
        bmi = b / (a * a);

        RadioButton hascheckbox = (RadioButton) findViewById(R.id.check_box);
        boolean addcheckbox = hascheckbox.isChecked();

        RadioButton hascheckbox1 = (RadioButton) findViewById(R.id.check1_box);
        boolean addcheckbox1 = hascheckbox1.isChecked();

        if (addcheckbox) {
            sirname = "Mr.";

        }
        if (addcheckbox1) {
            sirname2 = "Mrs.";
        }


        pricemessage = createreport(sirname, sirname2, name, name2, name3, name4, bmi);

        displayMessage(pricemessage);

    }


    private String createreport(String sirname,String sirname2,String name,String name2,String name3,String name4,Double bmi){
        String message="Name: " +sirname + sirname2 + name;
        message +="\nAge :" + name2;
        message +="\nHeight: " + name3;

        message +="\nWeight: " + name4;
        message +="\nBMI : " + bmi;
        String words ="";
        if (bmi>=18.5 && bmi<22){
            words="your health is normal.";
        }else if (bmi>=22 && bmi<25){
            words="Congrats! your body is in great shape.";
        }
        else if (bmi>=25){
            words="Overweight,Get ready to run.";
        }
        else if(bmi<18.5){
            words="Underweight,your body need sufficient nutrients.";
        }
        message +="\n" + words;

        return message;



    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.textview);
        priceTextView.setText(message);
    }


}

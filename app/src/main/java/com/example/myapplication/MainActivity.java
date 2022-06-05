package com.example.ashotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDb;
    EditText name, type, date;
    Button b1, b2, b3, b4, b5;
    TextView here;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.ed2);
        type = (EditText) findViewById(R.id.ed3);
        date = (EditText) findViewById(R.id.ed4);
        //get the ids of button
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        //get the ids of textviwe
        here=(TextView) findViewById(R.id.here);
        //define user defined methods
        addData();
        updateData();
        deleteData();
        viewData();
        clearData();
    }
    public void addData(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insert= mDb.insertData(name.getText().toString(),type.getText().toString(),date.getText().toString());
                if(insert==true)
                    Toast.makeText(MainActivity.this,"Data insterd",Toast.LENGTH_LONG).show();
                else{
                    int price=20;
                    int daten=Integer.parseInt(date.getText().toString());
                    int calculte=price*daten;
                    here.setText(String.valueOf(calculte));
                }


            }
        });
    }
    public void updateData(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update=mDb.updateData(name.getText().toString(),type.getText().toString(),date.getText().toString());
                if(update==true)
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void deleteData(){
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer del=mDb.deleteData(name.getText().toString());
                if(del>0)
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void viewData(){
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor r=mDb.getAllData();
                if(r.getCount()==0){
                    showMessage("Error","Nothing found");
                    return;

                }
                StringBuffer b=new StringBuffer();
                while (r.moveToNext()){

                    name.setText("name-" + name.getText().toString() + "\n"
                            + "type-" + type.getText().toString() + "\n" +
                            "date-" + date.getText().toString());
                }
                showMessage("Customer details",b.toString());
            }

        });
    }
    public void clearData(){
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                type.setText("");
                date.setText("");
            }
        });
    }
    public void showMessage(String title,String mes)
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.layout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        int id=menuItem.getItemId();
        if(id==R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

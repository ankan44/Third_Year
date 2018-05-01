package com.example.admin.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {
    private TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        v =(TextView) findViewById(R.id.version_number);
        final ArrayList<com.example.admin.myapplication.List> disease = new ArrayList<com.example.admin.myapplication.List>();

        disease.add(new List("BP", R.drawable.blood_pressure));
        disease.add(new List("Sugar Level(before meal)", R.drawable.sugar));
        disease.add(new List("Sugar Level(after meal)", R.drawable.sugar1));
        disease.add(new List("Pulse Rate", R.drawable.heart_rate));
        disease.add(new List("Heart Rate", R.drawable.heart_rate));


        final ListAdapter listAdapter = new ListAdapter(this, disease);

        ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                List word = disease.get(position);
open(view);
                }

    });
}
    public void open(final View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Value");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alertDialogBuilder.setView(input);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        ((TextView)(view.findViewById(R.id.version_number))).setText(input.getText().toString());
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
}}
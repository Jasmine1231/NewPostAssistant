package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.attr.value;

public class EditMessageActivity extends AppCompatActivity {

    private Spinner spinner02;
    private ImageView moban;
    private LinearLayout model;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(  WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_message);
        spinner02 = (Spinner) findViewById(R.id.spinner22);
        model = (LinearLayout)findViewById(R.id.model) ;
        moban = (ImageView) findViewById(R.id.imageView5);
        model.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(EditMessageActivity.this,MessageModelActivity.class);
                startActivity(i);
            }
        });


    }


}

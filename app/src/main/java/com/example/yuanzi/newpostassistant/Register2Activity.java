package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Register2Activity extends AppCompatActivity {
    private Button registerComplete;
    private LinearLayout choose;
    private TextView school_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        choose = (LinearLayout)findViewById(R.id.choosen);
        choose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Register2Activity.this,ChooseSchoolActivity.class);
                startActivity(i);
            }
        });

        registerComplete = (Button)findViewById(R.id.register_complete);
        registerComplete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Register2Activity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        school_name = (TextView)findViewById(R.id.school_name);
        Intent intent = getIntent();
        String school = intent.getStringExtra("school");
        school_name.setText(school);


    }
}

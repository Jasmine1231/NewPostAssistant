package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseSchoolActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView ecnu;
    private  TextView fddx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        ecnu = (TextView) findViewById(R.id.ecnu);
        ecnu.setOnClickListener(this);
        fddx = (TextView) findViewById(R.id.fddx);
        fddx.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ecnu:
                Intent intent1 = new Intent(ChooseSchoolActivity.this,Register2Activity.class);
                intent1.putExtra("school",ecnu.getText());
                startActivity(intent1);
                break;
            case R.id.fddx:
                Intent intent2 = new Intent(ChooseSchoolActivity.this,Register2Activity.class);
                intent2.putExtra("school",fddx.getText());
                startActivity(intent2);
                break;
        }
    }

}

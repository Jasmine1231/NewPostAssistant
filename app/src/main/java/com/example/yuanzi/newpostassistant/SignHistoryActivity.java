package com.example.yuanzi.newpostassistant;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SignHistoryActivity extends AppCompatActivity {

    private List<SignHistory> messagesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_history);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_sign_history);

        initOrder();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SignHistoryAdapter adapter = new SignHistoryAdapter(messagesList);
        recyclerView.setAdapter(adapter);
    }
    private void initOrder(){
        SignHistory a = new SignHistory("圆通");
        messagesList.add(a);

        SignHistory  b= new SignHistory("中通");
        messagesList.add(b);

    }
}
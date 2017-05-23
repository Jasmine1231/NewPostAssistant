package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageModelActivity extends AppCompatActivity {

    private List<MessageModel> messagesList = new ArrayList<>();
    private ImageView addMessagemodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_model);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_message_model);

        initOrder();// 需要添加短信模板至服务器
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageModelAdapter adapter = new MessageModelAdapter(messagesList);
        recyclerView.setAdapter(adapter);

        addMessagemodel = (ImageView) findViewById(R.id.add_messagemodel);
        addMessagemodel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MessageModelActivity.this,addMessageModelActivity.class);
                startActivityForResult(i,1);
            }
        });



    }protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        if(requestCode == 1){
            String messageModel=i.getStringExtra("messageModel");

            messagesList.add(new MessageModel(messageModel));
        }
    }
    private void initOrder(){
        /*MessageModel a = new MessageModel("你的圆通大件已到学子驿站，请快领取。");
        messagesList.add(a);

        MessageModel  b= new MessageModel("你的圆通大件已到学子驿站，请快领取，过时不候。");
        messagesList.add(b);

        MessageModel  c= new MessageModel("速度领取你的快递，快快快。");
        messagesList.add(c);

        MessageModel  d= new MessageModel("不跟你多哔哔，快来拿快递。");
        messagesList.add(d);

        MessageModel  e= new MessageModel("速度点，快递来了。");
        messagesList.add(e);*/

    }
}


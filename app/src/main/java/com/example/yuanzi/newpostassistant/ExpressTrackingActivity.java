package com.example.yuanzi.newpostassistant;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.tabhost;

public class ExpressTrackingActivity extends AppCompatActivity {

    private Spinner spinner02;
    private TextView isget;
    private ImageButton more2_button;
    private List<Message> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_tracking);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        more2_button = (ImageButton) findViewById(R.id.more2);
        spinner02 = (Spinner) findViewById(R.id.spinner3);
        isget = (TextView) findViewById(R.id.isget);
        initOrder();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(messagesList);
        recyclerView.setAdapter(adapter);




    }



    public void popupMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(),"item1",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(),"item2",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item3:
                        Toast.makeText(getApplicationContext(),"item3",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;

                }

            }


        });
        inflater.inflate(R.menu.express_tracking_menu,popupMenu.getMenu());
        popupMenu.show();
    }
    private void initOrder(){

        for(int i = 0; i <18; i++){
            Message a = new Message("2015-03-08 17:35","圆通","17317738817","未签收");
            messagesList.add(a);

        }
    }
}


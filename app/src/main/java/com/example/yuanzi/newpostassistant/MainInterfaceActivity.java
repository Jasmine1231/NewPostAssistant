package com.example.yuanzi.newpostassistant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.view.View.OnClickListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainInterfaceActivity extends AppCompatActivity {

    private ImageButton more_button;
    private ImageButton edit_button;
    private ImageButton tracking_button;
    private LinearLayout addressList;
    //private PopupWindow popupWindow;
    private CircleImageView head_view;
    private LinearLayout history;
    private DrawerLayout drawerLayout;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_interface);
        more_button = (ImageButton) findViewById(R.id.more);
        edit_button = (ImageButton  ) findViewById(R.id.edit_message);
        tracking_button = (ImageButton) findViewById(R.id.imageButton4);
        addressList = (LinearLayout)findViewById(R.id.address_list) ;
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        head_view = (CircleImageView) findViewById(R.id.head);
        //head_view.setOnClickListener(popClick);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        history = (LinearLayout)findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  i = new Intent(MainInterfaceActivity.this,SignHistoryActivity.class);
                startActivity(i);
            }
        });


        edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  i = new Intent(MainInterfaceActivity.this,EditMessageActivity.class);
                startActivity(i);
            }
        });
        tracking_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  i = new Intent(MainInterfaceActivity.this,ExpressTrackingActivity.class);
                startActivity(i);
            }
        });
        addressList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  i = new Intent(MainInterfaceActivity.this,AddressListActivity.class);
                startActivity(i);
            }
        });
        head_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.exit:
//                        pref.edit().putBoolean("auto_login",false).commit();
//                        finish();
//                }
//                return true;
//            }
//        });
    }


    //暂时不用的
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
                    default:
                        return false;

                }

            }


        });
        inflater.inflate(R.menu.menu,popupMenu.getMenu());
        popupMenu.show();
    }


}




package com.example.yuanzi.newpostassistant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
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


public class MainInterfaceActivity extends AppCompatActivity {

    private ImageButton more_button;
    private ImageButton edit_button;
    private ImageButton tracking_button;
    private LinearLayout addressList;
    //private PopupWindow popupWindow;
    private ImageView head_view;
    private LinearLayout history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main_interface);
        more_button = (ImageButton) findViewById(R.id.more);
        edit_button = (ImageButton  ) findViewById(R.id.edit_message);
        tracking_button = (ImageButton) findViewById(R.id.imageButton4);
        addressList = (LinearLayout)findViewById(R.id.address_list) ;
        head_view = (ImageView) findViewById(R.id.head);
        //head_view.setOnClickListener(popClick);
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
                Toast.makeText(getApplicationContext(),"touxiang",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*View.OnClickListener popClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getPopupWindow();
            popupWindow.showAtLocation(v, Gravity.LEFT,0,0);

        }
    };
    protected  void  initPopupWindow(){

        final View popipWindow_view = getLayoutInflater().inflate(R.layout.pop_left,null,false);
        //创建Popupwindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popipWindow_view,700, ViewGroup.LayoutParams.MATCH_PARENT,true);
//设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        popipWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {

                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));

    }
    /**
     * 获取PopipWinsow实例
     */
   /* private  void  getPopupWindow(){
        if (null!=popupWindow){
            popupWindow.dismiss();
            return;
        }else {
            initPopupWindow();
        }}*/


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




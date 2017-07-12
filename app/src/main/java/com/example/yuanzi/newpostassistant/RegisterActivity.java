package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.os.*;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanzi.newpostassistant.Bean.Msg;
import com.example.yuanzi.newpostassistant.WebService.WebService;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.mob.tools.utils.ResHelper.getStringRes;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button nextStep;
    private EditText phone;
    private EditText cord;
    private TextView now;
    private TextView getCord;
    private Button saveCord;
    private EditText password1;
    private EditText password2;
    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;
    private TextView info;
    private String infoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        SMSSDK.initSDK(this, "1d4283aee734d", "8111d088d5d818ba90beb83e52b94a01");
        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                android.os.Message msg = new android.os.Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }
    private void init() {
        phone = (EditText) findViewById(R.id.phone);
        cord = (EditText) findViewById(R.id.cord);
        now = (TextView) findViewById(R.id.now);
        getCord = (TextView) findViewById(R.id.getcord);
        saveCord = (Button) findViewById(R.id.savecord);
        getCord.setOnClickListener(this);
        saveCord.setOnClickListener(this);
        nextStep = (Button)findViewById(R.id.next_step);
        //nextStep.setEnabled(false);
        nextStep.setOnClickListener(this);
        password1 = (EditText)findViewById(R.id.password1);
        password2 = (EditText)findViewById(R.id.password2);
        info = (TextView)findViewById(R.id.register_info);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step:
                if(password1.getText().toString().equals(password2.getText().toString())) {
                    new RegisterTask().execute(phone.getText().toString(),password1.getText().toString().trim());
                }
                else{
                    Toast.makeText(RegisterActivity.this, "请输入相同密码", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.getcord:
                if(!TextUtils.isEmpty(phone.getText().toString().trim())){
                    if(phone.getText().toString().trim().length()==11){
                        iPhone = phone.getText().toString().trim();
                        SMSSDK.getVerificationCode("86",iPhone);
                        cord.requestFocus();
                        getCord.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(RegisterActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                        phone.requestFocus();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                    phone.requestFocus();
                }
                break;

            case R.id.savecord:
                if(!TextUtils.isEmpty(cord.getText().toString().trim())){
                    if(cord.getText().toString().trim().length()==4){
                        iCord = cord.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", iPhone, iCord);
                        flag = false;
                    }else{
                        Toast.makeText(RegisterActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                        cord.requestFocus();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    cord.requestFocus();
                }
                break;

            default:
                break;
        }
    }

    private class RegisterTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String []str=new String[params.length];
            int i=0;
            for(String p:params){
                str[i]=p;
                i++;
            }

            infoString= WebService.executeRegister(str[0],str[1]);
            return infoString;

        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson=new Gson();
            Msg m=gson.fromJson(s,Msg.class);
            if(m.getStatus()==0){
                Intent  i = new Intent(RegisterActivity.this,Register2Activity.class);
                startActivity(i);
            }
        }
    }

    private void reminderText() {
        now.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText =new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==1){
                if(time>0){
                    now.setText("验证码已发送"+time+"秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                }else{
                    now.setText("提示信息");
                    time = 60;
                    now.setVisibility(View.GONE);
                    getCord.setVisibility(View.VISIBLE);
                }
            }else{
                cord.setText("");
                now.setText("提示信息");
                time = 60;
                now.setVisibility(View.GONE);
                getCord.setVisibility(View.VISIBLE);
            }
        };
    };

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
                    handlerText.sendEmptyMessage(2);
                    nextStep.setEnabled(true);//验证码通过，允许进入下一步
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    getCord.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    phone.requestFocus();
                }else{
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    cord.selectAll();
                    if (resId > 0) {
                        Toast.makeText(RegisterActivity.this, resId, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}


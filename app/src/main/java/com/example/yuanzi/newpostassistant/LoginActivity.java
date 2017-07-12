package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanzi.newpostassistant.Bean.Msg;
import com.example.yuanzi.newpostassistant.WebService.WebService;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup_button;
    private TextView register;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private EditText signup_account;
    private EditText signup_passwd;
    private String info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        signup_account = (EditText)findViewById(R.id.signup_account);
        signup_passwd = (EditText)findViewById(R.id.signup_pswd);
        rememberPass =(CheckBox)findViewById(R.id.rememberPass);
        signup_button = (Button)findViewById(R.id.signup);
        register = (TextView)findViewById(R.id.register);
//        signup_account = (EditText)findViewById(R.id.signup_account);
//        signup_passwd = (EditText)findViewById(R.id.signup_pswd);
        signup_button.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:
                Intent  i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.signup:
                new LoginTask().execute(signup_account.getText().toString(),signup_passwd.getText().toString().trim());
                break;
            default:
                break;

        }
    }

    private class LoginTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String []str=new String[params.length];
            int i=0;
            for(String p:params){
                str[i]=p;
                i++;
            }

            info= WebService.executeLogin(str[0],str[1]);
            return info;

        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson=new Gson();
            Msg m=gson.fromJson(info,Msg.class);
            if(m.getStatus()==0){
                Intent  i = new Intent(LoginActivity.this,MainInterfaceActivity.class);
                startActivity(i);
            }
        }
    }
}
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        signup_account = (EditText)findViewById(R.id.signup_account);
        signup_passwd = (EditText)findViewById(R.id.signup_pswd);
        rememberPass =(CheckBox)findViewById(R.id.rememberPass);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            signup_account.setText(account);
            signup_passwd.setText(password);
            rememberPass.setChecked(true);
        }

        signup_button = (Button)findViewById(R.id.signup);
        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account = signup_account.getText().toString();
                String password = signup_passwd.getText().toString();

                if(account.equals("18918077730")&&password.equals("5647477230")){
                    editor = pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Intent  i = new Intent(LoginActivity.this,MainInterfaceActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"手机号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
*/
package com.example.yuanzi.newpostassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button signup_button;
    private TextView register;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private EditText signup_account;
    private EditText signup_passwd;
    @Override
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

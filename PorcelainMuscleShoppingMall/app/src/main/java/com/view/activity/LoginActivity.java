package com.view.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.data.domain.MyUser;
import com.logic.function.Verification;

import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {
     private String name;
     private String pwd;
     private EditText username;
     private EditText password;
     private Button login;
     private Button register;
     private Handler handler;
     private Verification verification = new Verification();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x123){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        final TextInputLayout textinput1 = (TextInputLayout)findViewById(R.id.textinput1);
        textinput1.setHint("用户名:");
         username = textinput1.getEditText();
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>11){
                    textinput1.setErrorEnabled(true);
                    textinput1.setError("用户名不能超过11个字符");
                }else{
                    textinput1.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>11){

                    textinput1.setError("用户名不能超过11个字符");
                    textinput1.setErrorEnabled(true);
                }else{
                    textinput1.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       final TextInputLayout textinput2 = (TextInputLayout)findViewById(R.id.textinput2);
        textinput2.setHint("密码:");
         password = textinput2.getEditText();
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>18){

                    textinput2.setError("密码不能超过18个字符");
                    textinput2.setErrorEnabled(true);
                }else{
                    textinput2.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                pwd = password.getText().toString();
                String info;
                if((info=verification.LoginVerification(name,pwd)).equals("success")){
                    MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(pwd);
                    user.login(getApplicationContext(), new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Message message = new Message();
                            message.what = 0x123;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                               Snackbar snackbar = Snackbar.make(login,"登陆失败"+String.valueOf(i),Snackbar.LENGTH_LONG);
                               snackbar.show();
                            if(i == 101){
                                Snackbar snackbar2 = Snackbar.make(login,"用户名或密码错误",Snackbar.LENGTH_LONG);
                                snackbar2.show();
                            }

                        }
                    });
                }else{
                    InputMethodManager imm = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                    Snackbar snackbar = Snackbar.make(login,info,Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ((TextView) view .findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });
    }


}

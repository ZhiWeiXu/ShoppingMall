package com.view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.data.domain.MyUser;
import com.logic.function.Verification;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;
public class RegisterActivity extends Activity{
    private EditText username = null;
    private EditText password = null;
    private EditText phonenumber = null;
    private EditText verificationcode = null;
    private Button codebutton = null;
    private Button registerbutton = null;
    private String code = "";
    private Verification verification;
    private String number = "";
    private MyUser user;
    private  boolean Checkstate = false;
    private Handler handler;
    private Message message = new Message();
    private String name;
    private String pwd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        phonenumber = (EditText)findViewById(R.id.phonenumber);
        verificationcode = (EditText)findViewById(R.id.verificationcode);
        codebutton = (Button)findViewById(R.id.Codebutton);
        registerbutton = (Button)findViewById(R.id.registerbutton);
        user = new MyUser();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x458){
                    user.setUsername(name);
                    user.setPassword(pwd);
                    user.setMobilePhoneNumber(number);
                    user.signUp(RegisterActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {

                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(getApplicationContext(),"注册失败"+i,Toast.LENGTH_LONG).show();
                            if(i == 202){
                                Snackbar snackbar = Snackbar.make(registerbutton,"用户已存在",Snackbar.LENGTH_INDEFINITE);
                                snackbar.show();
                            }
                            if(i == 209){
                                Snackbar snackbar = Snackbar.make(registerbutton,"该手机号已注册",Snackbar.LENGTH_INDEFINITE);
                                snackbar.show();
                            }
                        }
                    });
                }
            }
        };
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification = new Verification();
                 name = username.getText().toString().trim();
                 pwd = password.getText().toString().trim();
                number = phonenumber.getText().toString().trim();
                code = verificationcode.getText().toString();
                String info;
                if((info=verification.RegisterVerification(name,pwd,number,code)).equals("success")){
                               Checkup(number,code);

                }else{
                    Snackbar snackbar = Snackbar.make(registerbutton,info,Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        codebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                number = phonenumber.getText().toString().trim();
                Toast.makeText(getApplication(),"正在向"+phonenumber.getText().toString()+"发送验证",Toast.LENGTH_SHORT).show();
                if(number==null||number.equals("")){
                    Snackbar snackbar = Snackbar.make(codebutton,"手机号码不能为空!",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    BmobSMS.requestSMSCode(getApplicationContext(), number, "短信模板", new RequestSMSCodeListener() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if(e==null){
                                Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"发送失败"+String.valueOf(integer),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void Checkup(String number,String code){
        BmobSMS.verifySmsCode(getApplicationContext(),number, code, new VerifySMSCodeListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//短信验证码已验证成功
                 //   Log.i("smile", "验证通过");
                    message.what = 0x458;
                    handler.sendMessage(message);
                    Toast.makeText(getApplicationContext(),"验证成功",Toast.LENGTH_LONG).show();
                }else{
                    //Log.i("smile", "验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                    Toast.makeText(getApplicationContext(),"验证失败"+String.valueOf(ex.getErrorCode()),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

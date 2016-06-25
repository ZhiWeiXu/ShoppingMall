package com.logic.function;

/**
 * Created by Administrator on 2016/6/4.
 */
public class Verification {
    public String  LoginVerification(String username,String password){
        if(username.length()>11){
            return "用户名不能超过11个字符";
        }
        if(username.length()<4){
            return "用户名不能少于4个字符";
        }
        if(password.length()>18){
            return  "密码不能超过18个字符";
        }
        if(password.length()<6){
            return  "密码不能少于6个字符";
        }
        return "success";
    }
    public String RegisterVerification(String username,String password,String phonenumber,String code){
        if(username.length()>11){
            return "用户名不能超过11个字符";
        }
        if(username.length()<4){
            return "用户名不能少于4个字符";
        }
        if(password.length()>18){
            return  "密码不能超过18个字符";
        }
        if(password.length()<6){
            return  "密码不能少于6个字符";
        }
        if(phonenumber.equals("")||phonenumber == null){
            return "手机号码不能为空";
        }
        if(code.equals("")||code==null){
            return "验证码不能为空";
        }
        return "success";
    }
}

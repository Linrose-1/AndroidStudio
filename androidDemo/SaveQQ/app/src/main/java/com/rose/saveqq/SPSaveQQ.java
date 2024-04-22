package com.rose.saveqq;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SPSaveQQ {
    public static boolean saveUserInfo (Context context, String account, String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("userName",account);
        edit.putString("pwd",password);
        edit.commit();
        return true;
    }

    public static Map<String,String> getUserInfo (Context context) {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String account =sp.getString("username",null);
        String password = sp.getString("pwd",null);
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("account",account);
        userMap.put("password",password);
        return userMap;
    }
}

package com.rose.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_contact;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        rv_contact=findViewById(R.id.rv_contact);
        rv_contact.setLayoutManager(new LinearLayoutManager(this));
        getPermissions();
    }
    String[] permissionList;
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            permissionList = new String[] {"android.permission.READ_CONTACTS"};
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < permissionList.length; i++) {
                if (ActivityCompat.checkSelfPermission(this,permissionList[i]) != PackageManager.PERMISSION_GRANTED)
                    list.add(permissionList[i]);
            }
            if (list.size() > 0){
                ActivityCompat.requestPermissions(this,list.toArray(new String[list.size()]),1);
            }else {
                setData();
            }
        }else {
            setData();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode , String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals("android.permission.READ_CONTACTS") && grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this , "读取通讯录权限申请成功",Toast.LENGTH_SHORT).show();
                    setData();
                }else {
                    Toast.makeText(this,"读取通讯录权限申请失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setData() {
        List<ContactInfo> contactInfos = getContacts();
        adapter = new ContactAdapter(MainActivity.this,contactInfos);
        rv_contact.setAdapter(adapter);
    }

    private List<ContactInfo> getContacts() {
        List<ContactInfo> contactInfos = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI ,null,null,null,null);
        if (contactInfos != null)contactInfos.clear();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int isHas = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
            if (isHas > 0){
                Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,null,null);
                while (c.moveToNext()){
                    ContactInfo info = new ContactInfo();
                    info.setContactName(name);
                    String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).trim();
                    number = number.replace(" ","");
                    number = number.replace("-","");
                    info.setPhoneNumber(number);
                    contactInfos.add(info);
                }
                c.close();
            }
        }
        cursor.close();
        return  contactInfos;
    }
}
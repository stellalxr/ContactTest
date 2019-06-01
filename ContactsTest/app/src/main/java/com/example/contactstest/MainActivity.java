package com.example.contactstest;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
  //  List<String> contactsList=new ArrayList<>();
    private List<String> contactsList;
    private ListView contactListView;
    private  SQLiteDatabase db;
    private  SQLiteHelper dbHelper;

    Button queryButton;
    private TextView text;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new SQLiteHelper(this);
        db=dbHelper.getReadableDatabase();
    //    initList();
        initView();
        readContacts();
    /*    ListView contactView=(ListView) findViewById(R.id.contacts_view);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactView.setAdapter(adapter);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CONTACTS },1);
        }else {
            readContacts();

        }*/
    }
  /*  private void initList(){
        contactsList=new ArrayList<String>();
        contactListView=(ListView)findViewById(R.id.contacts_view);
        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,contactsList);
        contactListView.setAdapter(adapter);
    }*/

    private  void initView(){
        text=(TextView)findViewById(R.id.text);
        queryButton=(Button)findViewById(R.id.query);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri=Uri.parse("content://com.example.databasetest.provider/phone");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                String ss="";
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        String data = cursor.getString(cursor.getColumnIndex("CONTEXT"));
                        ss = ss + name + data;
                    }
                    text.setText(ss);
                    cursor.close();
                }
            }
        });
    }
    private void readContacts(){
        Cursor cursor=null;
        try{
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String displayName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName+"\n"+number);
           /*         ContentValues values=new ContentValues();
                    values.put("NAME",displayName);
                    values.put("CONTEXT",number);
                    db.insert("TLB_CONTACT",null,values);*/
                }
             //   adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }
   /* public void onRequestPermissionResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }*/
}

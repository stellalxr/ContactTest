package com.example.contactstest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {


    private String TableNames[];
    private String FieldNames[][];
    private String FieldTypes[][];

    private static int version = 1;//数据库版本号
    private static String myDBName = "mydb";

    public void onCreate(SQLiteDatabase db){
        TableNames=SQLlnfo.getTableNames();
        FieldNames=SQLlnfo.getFieldNames();
        FieldTypes=SQLlnfo.getFieldTypes();
        for(int i = 0; i < TableNames.length; i++){
            String sql = "CREATE TABLE " + TableNames[i] + " (";
            for (int j = 0; j < FieldNames[i].length; j++)
            {
                sql += FieldNames[i][j] + " " + FieldTypes[i][j] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += ")";
            db.execSQL(sql);
        }
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
    public SQLiteHelper(Context context){
        super(context,myDBName,null,version);
    }
}


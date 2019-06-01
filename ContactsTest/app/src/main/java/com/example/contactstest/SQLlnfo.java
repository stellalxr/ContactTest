package com.example.contactstest;

public class SQLlnfo {
    private static String TableNames[]={
            "TLB_CONTACT"
    };
    private static String FieldNames[][]={
            {"ID","NAME","CONTEXT"}
    };
    private static String FieldTypes[][] = {
            {"INTEGER PRIMARY KEY AUTOINCREMENT","TEXT","TEXT"},
    };

    public static  String[] getTableNames(){
        return  TableNames;
    }
    public static String[][] getFieldNames() {
        return FieldNames;
    }

    public static String[][] getFieldTypes() {
        return FieldTypes;
    }

}

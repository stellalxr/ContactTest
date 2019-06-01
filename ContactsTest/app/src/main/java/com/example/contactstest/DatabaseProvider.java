package com.example.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int PHONE_DIR=0;
    public static final  int PHONE_ITEM=1;
    private static UriMatcher uriMatcher;
    private SQLiteDatabase db;
    private SQLiteHelper dbHepler;
    public static final String AUTHORITY="com.example.databasetest.provider";
    static {
        uriMatcher =new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"phone",PHONE_DIR);
        uriMatcher.addURI(AUTHORITY,"phone/#",PHONE_ITEM);
    }
    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case PHONE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.phone";
            case PHONE_ITEM:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";
        }
        return  null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHepler=new SQLiteHelper(getContext());
        db=dbHepler.getReadableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Cursor cursor=null;
        switch ((uriMatcher.match(uri))){
            case PHONE_DIR:
                cursor=db.query("TLB_CONTACT",projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case PHONE_ITEM:
                String phoneId=uri.getPathSegments().get(1);
                cursor=db.query("TLB_CONTACT",projection,"id=?",new String[]{phoneId},null,
                        null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

package com.imooc.imooc_expandablelistview.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.imooc.imooc_expandablelistview.bean.Chapter;
import com.imooc.imooc_expandablelistview.bean.ChapterItem;

public class ChapterDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_chapter.db1";
    private static final int VERSION = 1;
    private static ChapterDbHelper sInstance;

    public static synchronized ChapterDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ChapterDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private ChapterDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + Chapter.TABLE_NAME
//                + "(" + Chapter.COL_ID + " INTEGER PRIMARY KEY,"
//                + Chapter.COL_NAME + " VARCHAR" + ")");
//
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + ChapterItem.TABLE_NAME
//                + "(" + ChapterItem.COL_ID + " INTEGER PRIMARY KEY,"
//                + ChapterItem.COL_NAME + " VARCHAR,"
//                +ChapterItem.COL_PID+" " + " INTEGER"
//                + ")");
//
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + Chapter.TABLE_NAME + " ("
                        + Chapter.COL_ID + " INTEGER PRIMARY KEY, "
                        + Chapter.COL_NAME + " VARCHAR"
                        + ")"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ChapterItem.TABLE_NAME + " ("
                        + ChapterItem.COL_ID + " INTEGER PRIMARY KEY, "
                        + ChapterItem.COL_NAME + " VARCHAR, "
                        + ChapterItem.COL_PID + " INTEGER"
                        + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

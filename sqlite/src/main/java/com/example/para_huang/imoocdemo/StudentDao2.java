package com.example.para_huang.imoocdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDao2 {
    private SQLiteDatabase db;

    public StudentDao2(Context context) {
        String path = Environment.getExternalStorageDirectory() + "/Download/test3.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, path, null, 2) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                Toast.makeText(context, "数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
                String sql = "create table info_tb (_id integer primary key autoincrement," +
                        "name varhcar(20)," +
                        "age integer,"+"gender varchar(2))";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Toast.makeText(context, "数据库更新", Toast.LENGTH_SHORT).show();
            }
        };
        db = helper.getReadableDatabase();
    }

    public void addStudent(Student stu) {
        String sql = "insert into info_tb (name,age,gender) values(?,?,?)";
        db.execSQL(sql, new String[]{stu.getName(), String.valueOf(stu.getAge()), stu.getGender()});
    }

    public void deleteStudent(String... strs) {
        String sql = "delete from info_tb where " + strs[0] + "='" + strs[1] + "'";
        db.execSQL(sql);

    }

    public Cursor getStudent(String... strs) {
//1. すべてを検索
        String sql = "select * from info_tb";
//2. 条件付き検索（名前/年齢/番号）（パラメータ形式：最初のパラメータが条件を指定し、2番目のパラメータが条件値を指定）
        if (strs.length != 0) {
            sql += " where " + strs[0] + "='" + strs[1] + "'"; // 整数値もシングルクォートで囲むことができます
        }
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public ArrayList<Student> getStudentList(String...strs){
        ArrayList<Student> list = new ArrayList<>();
        Cursor c = getStudent(strs);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            int age = c.getInt(2);
            String gender = c.getString(3);
            Student stu = new Student(id, name, age, gender);
            list.add(stu);
        }
        return list;
    }

    public void updateStudent(Student stu) {
        String sql = "update info_tb set name=? , age=? , gender=?  where _id=?";
        db.execSQL(sql, new String[]{stu.getName(), String.valueOf(stu.getAge()), stu.getGender(), String.valueOf(stu.getId())});
    }

}

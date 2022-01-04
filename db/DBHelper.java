package com.example.employeedb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE employee (eCode TEXT PRIMARY KEY, eName TEXT, eGender TEXT, eDept TEXT, eSalary NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS employee");
    }

    public Boolean insertEmployee(String eCode, String eName, String eGender, String eDept, long eSalary) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eCode", eCode);
        contentValues.put("eName", eName);
        contentValues.put("eGender", eGender);
        contentValues.put("eDept", eDept);
        contentValues.put("eSalary", eSalary);

        long result = DB.insert("employee", null, contentValues);

        return result != -1;
    }

    public Boolean updateEmployee(String eCode, String eName, String eGender, String eDept, long eSalary) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eName", eName);
        contentValues.put("eGender", eGender);
        contentValues.put("eDept", eDept);
        contentValues.put("eSalary", eSalary);

        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("SELECT * FROM employee WHERE eCode=?", new String[]{eCode});
        if(cursor.getCount() > 0) {
            long result = DB.update("employee", contentValues, "eCode=?", new String[] {eCode});
            return result != -1;
        }
        else return false;
    }

    public Boolean deleteEmployee(String eCode) {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("SELECT * FROM employee WHERE eCode=?", new String[]{eCode});
        if(cursor.getCount() > 0) {
            long result = DB.delete("employee", "eCode=?", new String[] {eCode});
            return result != -1;
        }
        else return false;
    }

    public Cursor retrieveEmployee(String eCode) {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM employee WHERE eCode=?", new String[]{eCode});
    }

    public Cursor retrieveDepartment(String eDept) {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM employee WHERE eDept=?", new String[]{eDept});
    }
}
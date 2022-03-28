package com.spiner_checkbox.android.spiner_checkbox.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHanderl extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHanderl.class.getSimpleName();
    public static final String DATABASE_NAME = "qlsv.db";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_SINHVIEN ="sinhvien";
    public static final String KEY_ID = "id";
    public static final String KEY_MA = "ma";
    public static final String KEY_NAME = "name";
    public static final String KEY_SEX = "sex";
    public static final String KEY_JOB = "job";
    public static final String KEY_BRITHDAY = "brithday";
    public SQLiteHanderl(Context context) {
        super(context,DATABASE_NAME ,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sinhvien = "CREATE TABLE " +TABLE_SINHVIEN +"(" +
                KEY_MA +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ID +" TEXT," +
                KEY_NAME +" TEXT," +
                KEY_SEX +" TEXT," +
                KEY_JOB +" TEXT," +
                KEY_BRITHDAY +" TEXT" +
                ")";
        String them = "INSERT INTO "+TABLE_SINHVIEN +" ("+KEY_MA+", "+KEY_ID+", "+KEY_NAME+","
                +KEY_SEX+","+KEY_JOB+","+KEY_BRITHDAY+") VALUES" +
                "(1, 'pd01901', 'Phi','Nam','Sinh viên', '08/12/2018')," +
                "(2, 'pd01996', 'Tý','Nam','Sinh viên', '08/12/2018')," +
                "(3, 'pd01755', 'Lâm','Nam','Sinh viên', '08/12/2018')," +
                "(4, 'pd7785', 'Toại', 'Nam','Sinh viên','08/12/2018')," +
                "(5, 'pd75445', 'Luyn','Nam','Sinh viên', '08/12/2018');";
        sqLiteDatabase.execSQL(sinhvien);
        sqLiteDatabase.execSQL(them);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TABLE_SINHVIEN);
    }
    //lấy dữ liệu bảng danh sách
    public Cursor getAllDataSV(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SINHVIEN, null);
        return cursor;
    }
    public void themsv(String id, String name,String sex,String job,String birthday){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        values.put(KEY_SEX, sex);
        values.put(KEY_JOB, job);
        values.put(KEY_BRITHDAY, birthday);
        long ma = db.insert(TABLE_SINHVIEN, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "Thêm mới thành công " + ma);
    }
    public void delete(String ma){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM " + TABLE_SINHVIEN +" WHERE ma='%s' ",ma);
        db.execSQL(query);
    }
    //cập nhật dữ liệu bảng
    public void updateDataList(String ma,String id,String name,String sex,String job,String birthday){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MA,ma);
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        values.put(KEY_SEX, sex);
        values.put(KEY_JOB, job);
        values.put(KEY_BRITHDAY, birthday);
        long result = db.update(TABLE_SINHVIEN, values, KEY_MA + "=?", new String[]{ma});
        db.close(); // Closing database connection
        Log.d(TAG, "Cập nhật thành công " + result);
 /*       if(result == -1){
            return false;
        }else {
            return true;
        }*/
    }
}

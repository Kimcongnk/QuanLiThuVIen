package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThuThu;

public class ThuThuDao {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public ThuThuDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertThuThu(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("thuThu_hoTen", thuThu.getHoTen());
        values.put("thuThu_matKhau", thuThu.getMatKhau());
        open();
        return database.insert("tbl_thuThu", null, values);
    }

    public int updateThuThu(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("thuThu_hoTen", thuThu.getHoTen());
        values.put("thuThu_matKhau", thuThu.getMatKhau());
        return database.update("tbl_thuThu", values, "thuThu_id = ?", new String[]{thuThu.getMaTT()});
    }

    public int deleteThuThu(ThuThu thuThu) {
        return database.delete("tbl_thuThu", "thuThu_id = ?", new String[]{thuThu.getMaTT()});
    }


    public boolean checkLogin(String hoTen, String matKhau) {
        String[] columns = {"thuThu_id"};
        String selection = "thuThu_hoTen = ? AND thuThu_matKhau = ?";
        String[] selectionArgs = {hoTen, matKhau};
        open();
        Cursor cursor = database.query("tbl_thuThu", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
    public int updatePass(String matKhau) {
        ContentValues values = new ContentValues();
        values.put("thuThu_matKhau", matKhau);
        open();
        return database.update("tbl_thuThu", values, null, null);
    }
    @SuppressLint("Range")
    public String getID(String hoTen, String matKhau) {
        String[] columns = {"thuThu_id"};
        String selection = "thuThu_hoTen = ? AND thuThu_matKhau = ?";
        String[] selectionArgs = {hoTen, matKhau};
        Cursor cursor = database.query("tbl_thuThu", columns, selection, selectionArgs, null, null, null);
        String id = null;
        if (cursor.moveToFirst()) {
            id = cursor.getString(cursor.getColumnIndex("thuThu_id"));
        }
        cursor.close();
        return id;
    }


    public void rememberUser(String userId, String p, boolean status) {
        // Lưu trạng thái người dùng đã đăng nhập vào SharedPreferences hoặc cơ chế lưu trữ khác
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

//    public String getRememberedUser() {
//        // Lấy thông tin người dùng đã đăng nhập từ SharedPreferences hoặc cơ chế lưu trữ khác
//        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
//        return sharedPreferences.getString("userId", null);
//    }
//
//    public void clearRememberedUser() {
//        // Xóa thông tin người dùng đã đăng nhập từ SharedPreferences hoặc cơ chế lưu trữ khác
//        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("userId");
//        editor.apply();
//    }

}

package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.LoaiSach;

public class LoaiSachDao {
    private SQLiteDatabase database;
    private Context context;
    public LoaiSachDao(Context context) {
        DBHelper databaseHelper = new DBHelper(context);
        this.database = databaseHelper.getWritableDatabase();
        this.context = context;
    }

    public long addLoaiSach(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("loaiSach_tenLoai", loaiSach.getTenLoai());
        return database.insert("tbl_loaiSach", null, values);
    }

    public int updateLoaiSach(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("loaiSach_tenLoai", loaiSach.getTenLoai());
        String whereClause = "loaiSach_id = ?";
        String[] whereArgs = {String.valueOf(loaiSach.getMaLoai())};
        return database.update("tbl_loaiSach", values, whereClause, whereArgs);
    }

    public int deleteLoaiSach(String maLoai) {
        String whereClause = "loaiSach_id = ?";
        String[] whereArgs = {maLoai};
        return database.delete("tbl_loaiSach", whereClause, whereArgs);
    }
    @SuppressLint("Range")
    public List<LoaiSach> getAllLoaiSach() {
        List<LoaiSach> loaiSachList = new ArrayList<>();
        Cursor cursor = database.query("tbl_loaiSach", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(cursor.getInt(cursor.getColumnIndex("loaiSach_id")));
            loaiSach.setTenLoai(cursor.getString(cursor.getColumnIndex("loaiSach_tenLoai")));
            loaiSachList.add(loaiSach);
        }
        cursor.close();
        return loaiSachList;
    }
    @SuppressLint("Range")
    public LoaiSach getLoaiSachById(String maLoai) {
        String[] columns = {"loaiSach_id", "loaiSach_tenLoai"};
        String selection = "loaiSach_id = ?";
        String[] selectionArgs = {maLoai};
        Cursor cursor = database.query("tbl_loaiSach", columns, selection, selectionArgs, null, null, null);
        LoaiSach loaiSach = null;
        if (cursor.moveToFirst()) {
            loaiSach = new LoaiSach();
            loaiSach.setMaLoai(cursor.getInt(cursor.getColumnIndex("loaiSach_id")));
            loaiSach.setTenLoai(cursor.getString(cursor.getColumnIndex("loaiSach_tenLoai")));
        }
        cursor.close();
        return loaiSach;
    }
    public boolean isLoaiSachExists(String maLoai) {
        String[] columns = {"loaiSach_id"};
        String selection = "loaiSach_id = ?";
        String[] selectionArgs = {maLoai};
        Cursor cursor = database.query("tbl_loaiSach", columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

}

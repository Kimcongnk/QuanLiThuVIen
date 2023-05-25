    package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;

public class SachDao {
    private SQLiteDatabase database;
private Context context;
    public SachDao(Context context) {
        DBHelper databaseHelper = new DBHelper(context);
        this.database = databaseHelper.getWritableDatabase();
        this.context = context;
    }


    public long addSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("Sach_tenSach", sach.getTenSach());
        values.put("Sach_giaThue", sach.getGiaThue());
        values.put("loaiSach_id", sach.getMaLoai());

        return database.insert("tbl_Sach", null, values);
    }

    public int updateSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("Sach_tenSach", sach.getTenSach());
        values.put("Sach_giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        String whereClause = "Sach_id = ?";
        String[] whereArgs = {String.valueOf(sach.getMaSach())};
        return database.update("tbl_Sach", values, whereClause, whereArgs);
    }

    public int deleteSach(int maSach) {
        String whereClause = "Sach_id = ?";
        String[] whereArgs = {String.valueOf(maSach)};
        return database.delete("tbl_Sach", whereClause, whereArgs);
    }
    @SuppressLint("Range")
    public List<Sach> getAllSach() {
        List<Sach> sachList = new ArrayList<>();
        Cursor cursor = database.query("tbl_Sach", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getInt(cursor.getColumnIndex("Sach_id")));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex("Sach_tenSach")));
            sach.setGiaThue(cursor.getInt(cursor.getColumnIndex("Sach_giaThue")));
            sach.setMaLoai(cursor.getInt(cursor.getColumnIndex("loaiSach_id")));
            sachList.add(sach);
        }
        cursor.close();
        return sachList;
    }
    @SuppressLint("Range")
    public Sach getSachById(int maSach) {
        String[] columns = {"Sach_id", "Sach_tenSach", "Sach_giaThue", "loaiSach_id"};
        String selection = "Sach_id = ?";
        String[] selectionArgs = {String.valueOf(maSach)};
        Cursor cursor = database.query("tbl_Sach", columns, selection, selectionArgs, null, null, null);
        Sach sach = null;
        if (cursor.moveToFirst()) {
            sach = new Sach();
            sach.setMaSach(cursor.getInt(cursor.getColumnIndex("Sach_id")));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex("Sach_tenSach")));
            sach.setGiaThue(cursor.getInt(cursor.getColumnIndex("Sach_giaThue")));
            sach.setMaLoai(cursor.getInt(cursor.getColumnIndex("loaiSach_id")));
        }
        cursor.close();
        return sach;
    }
    public boolean isSachExists(int maSach) {
        String[] columns = {"Sach_id"};
        String selection = "Sach_id = ?";
        String[] selectionArgs = {String.valueOf(maSach)};
        Cursor cursor = database.query("tbl_Sach", columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

}
package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThanhVien;

public class ThanhVienDao {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ThanhVienDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertThanhVien(ThanhVien thanhVien) {

        ContentValues values = new ContentValues();
        values.put("thanhVien_hoTen", thanhVien.getHoTen());
        values.put("thanhVien_namSinh", thanhVien.getNamSinh());
        open();
        return database.insert("tbl_thanhVien", null, values);
    }

    public int updateThanhVien(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("thanhVien_hoTen", thanhVien.getHoTen());
        values.put("thanhVien_namSinh", thanhVien.getNamSinh());
        return database.update("tbl_thanhVien", values, "thanhVien_id = ?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }

    public int deleteThanhVien(ThanhVien thanhVien) {
        return database.delete("tbl_thanhVien", "thanhVien_id = ?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }
    @SuppressLint("Range")
    public List<ThanhVien> getAllThanhVien() {
        List<ThanhVien> thanhVienList = new ArrayList<>();
        String[] columns = {"thanhVien_id", "thanhVien_hoTen", "thanhVien_namSinh"};
        Cursor cursor = database.query("tbl_thanhVien", columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMaTV(cursor.getInt(cursor.getColumnIndex("thanhVien_id")));
                thanhVien.setHoTen(cursor.getString(cursor.getColumnIndex("thanhVien_hoTen")));
                thanhVien.setNamSinh(cursor.getString(cursor.getColumnIndex("thanhVien_namSinh")));
                thanhVienList.add(thanhVien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return thanhVienList;
    }
    @SuppressLint("Range")
    public ThanhVien getThanhVienById(int maTV) {
        String[] columns = {"thanhVien_id", "thanhVien_hoTen", "thanhVien_namSinh"};
        String selection = "thanhVien_id = ?";
        String[] selectionArgs = {String.valueOf(maTV)};
        Cursor cursor = database.query("tbl_thanhVien", columns, selection, selectionArgs, null, null, null);
        ThanhVien thanhVien = null;
        if (cursor.moveToFirst()) {
            thanhVien = new ThanhVien();
            thanhVien.setMaTV(cursor.getInt(cursor.getColumnIndex("thanhVien_id")));
            thanhVien.setHoTen(cursor.getString(cursor.getColumnIndex("thanhVien_hoTen")));
            thanhVien.setNamSinh(cursor.getString(cursor.getColumnIndex("thanhVien_namSinh")));
        }
        cursor.close();
        return thanhVien;
    }

    public boolean isThanhVienExists(int maTV) {
        String[] columns = {"thanhVien_id"};
        String selection = "thanhVien_id = ?";
        String[] selectionArgs = {String.valueOf(maTV)};
        Cursor cursor = database.query("tbl_thanhVien", columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
}

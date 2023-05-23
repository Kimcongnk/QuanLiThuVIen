package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThuThu;

public class ThuThuDao {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

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

    public Cursor getAllThuThu() {
        return database.query("tbl_thuThu", null, null, null, null, null, null);
    }
    public boolean checkLogin(String hoTen, String matKhau) {
        String[] columns = {"thuThu_id"};
        String selection = "thuThu_hoTen = ? AND thuThu_matKhau = ?";
        String[] selectionArgs = {hoTen, matKhau};
        Cursor cursor = database.query("tbl_thuThu", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
    public int updatePass(String matKhau) {
        ContentValues values = new ContentValues();
        values.put("thuThu_matKhau", matKhau);
        return database.update("tbl_thuThu", values, null, null);
    }
    public String getID(String hoTen, String matKhau) {
        String[] columns = {"thuThu_id"};
        String selection = "thuThu_hoTen = ? AND thuThu_matKhau = ?";
        String[] selectionArgs = {hoTen, matKhau};
        Cursor cursor = database.query("tbl_thuThu", columns, selection, selectionArgs, null, null, null);
        String id = null;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("thuThu_id");
            if (columnIndex != -1) {
                id = cursor.getString(columnIndex);
            }
        }
        cursor.close();
        return id;
    }

}

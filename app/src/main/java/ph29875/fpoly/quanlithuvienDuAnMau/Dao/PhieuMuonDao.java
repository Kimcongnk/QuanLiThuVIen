package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.PhieuMuon;

public class PhieuMuonDao {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public PhieuMuonDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPhieuMuon(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("thuThu_id", phieuMuon.getMaTT());
        values.put("thanhVien_id", phieuMuon.getMaTV());
        values.put("Sach_id", phieuMuon.getMaSach());
        values.put("phieuMuon_tienThue", phieuMuon.getTienThue());
        values.put("phieuMuon_ngay", phieuMuon.getNgay().toString());
        values.put("phieuMuon_traSach", phieuMuon.getTraSach());
        return database.insert("tbl_phieuMuon", null, values);
    }

    public int updatePhieuMuon(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("thuThu_id", phieuMuon.getMaTT());
        values.put("thanhVien_id", phieuMuon.getMaTV());
        values.put("Sach_id", phieuMuon.getMaSach());
        values.put("phieuMuon_tienThue", phieuMuon.getTienThue());
        values.put("phieuMuon_ngay", phieuMuon.getNgay().toString());
        values.put("phieuMuon_traSach", phieuMuon.getTraSach());
        return database.update("tbl_phieuMuon", values, "phieuMuon_id = ?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public int deletePhieuMuon(PhieuMuon phieuMuon) {
        return database.delete("tbl_phieuMuon", "phieuMuon_id = ?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public Cursor getAllPhieuMuon() {
        return database.query("tbl_phieuMuon", null, null, null, null, null, null);
    }

    @SuppressLint("Range")
    public PhieuMuon getPhieuMuonById(int maPM) {
        String[] columns = {"phieuMuon_id", "thuThu_id", "thanhVien_id", "Sach_id", "phieuMuon_tienThue", "phieuMuon_ngay", "phieuMuon_traSach"};
        String selection = "phieuMuon_id = ?";
        String[] selectionArgs = {String.valueOf(maPM)};
        Cursor cursor = database.query("tbl_phieuMuon", columns, selection, selectionArgs, null, null, null);
        PhieuMuon phieuMuon = null;
        if (cursor.moveToFirst()) {
            phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(cursor.getInt(cursor.getColumnIndex("phieuMuon_id")));
            phieuMuon.setMaTT(cursor.getString(cursor.getColumnIndex("thuThu_id")));
            phieuMuon.setMaTV(cursor.getInt(cursor.getColumnIndex("thanhVien_id")));
            phieuMuon.setMaSach(cursor.getInt(cursor.getColumnIndex("Sach_id")));
            phieuMuon.setTienThue(cursor.getInt(cursor.getColumnIndex("phieuMuon_tienThue")));
            // Parse the date from the cursor
            String dateStr = cursor.getString(cursor.getColumnIndex("phieuMuon_ngay"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = dateFormat.parse(dateStr);
                phieuMuon.setNgay(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            phieuMuon.setTraSach(cursor.getInt(cursor.getColumnIndex("phieuMuon_traSach")));
        }
        cursor.close();
        return phieuMuon;
    }
    public boolean isPhieuMuonExists(int maPM) {
        String[] columns = {"phieuMuon_id"};
        String selection = "phieuMuon_id = ?";
        String[] selectionArgs = {String.valueOf(maPM)};
        Cursor cursor = database.query("tbl_phieuMuon", columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

}
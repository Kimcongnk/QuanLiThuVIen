package ph29875.fpoly.quanlithuvienDuAnMau.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ph29875.fpoly.quanlithuvienDuAnMau.Database.DBHelper;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.Sach;


public class ThongKeDAO {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Sach> getTop() {
        List<Sach> list = new ArrayList<>();

        String sqlTop = "SELECT Sach_tenSach, COUNT(*) AS soluong FROM tbl_Sach GROUP BY Sach_tenSach ORDER BY soluong DESC LIMIT 10";

        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            String tenSach = cursor.getString(cursor.getColumnIndex("Sach_tenSach"));
            int soLuong = cursor.getInt(cursor.getColumnIndex("soluong"));
            Sach sach = new Sach(tenSach, soLuong);
            list.add(sach);
        }
        cursor.close();

        return list;
    }


    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(GiathueS) as doanhThu FROM phieumuon WHERE ngaythue BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }

        }
        return list.get(0);
    }
}

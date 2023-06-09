package ph29875.fpoly.quanlithuvienDuAnMau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "phuongnamlibs.db";
    private static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(TABLE_THU_THU_CREATE);
        //Thanh Vien
        db.execSQL(TABLE_THANH_VIEN_CREATE);
        //Loai Sach
        db.execSQL(TABLE_LOAI_SACH_CREATE);
        //Sach
        db.execSQL(TABLE_SACH_CREATE);
        //Phieu Muon
        db.execSQL(TABLE_PHIEU_MUON_CREATE);
    }
    public static final String TABLE_THU_THU_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_thuThu (" +
            "thuThu_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thuThu_hoTen TEXT NOT NULL," +
            "thuThu_matKhau TEXT NOT NULL" +
            ")";
    public static final String TABLE_THANH_VIEN_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_thanhVien (" +
            "thanhVien_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thanhVien_hoTen TEXT NOT NULL," +
            "thanhVien_namSinh TEXT NOT NULL" +
            ")";
    public static final String TABLE_LOAI_SACH_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_loaiSach (" +
            "loaiSach_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "loaiSach_tenLoai TEXT NOT NULL" +
            ")";
    public static final String TABLE_SACH_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_Sach (" +
            "Sach_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Sach_tenSach TEXT NOT NULL," +
            "Sach_giaThue TEXT NOT NULL," +
            "loaiSach_id INTEGER REFERENCES tbl_loaiSach(loaiSach_id)" +
            ")";
    public static final String TABLE_PHIEU_MUON_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_phieuMuon (" +
            "phieuMuon_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thuThu_id TEXT REFERENCES tbl_thuThu(thuThu_id)," +
            "thanhVien_id TEXT REFERENCES tbl_thanhVien(thanhVien_id)," +
            "Sach_id INTEGER REFERENCES tbl_Sach(Sach_id)," +
            "phieuMuon_tienThue TEXT NOT NULL," +
            "phieuMuon_ngay TEXT NOT NULL," +
            "phieuMuon_traSach INTEGER NOT NULL" +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(TABLE_THU_THU_CREATE);
        db.execSQL(TABLE_THANH_VIEN_CREATE);
        db.execSQL(TABLE_LOAI_SACH_CREATE);
        db.execSQL(TABLE_SACH_CREATE);
        db.execSQL(TABLE_PHIEU_MUON_CREATE);

        // Insert hardcoded data into tbl_thuThu
        insertThuThuData(db);
    }

    private void insertThuThuData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("thuThu_hoTen", "Doe");
        values.put("thuThu_matKhau", "123");
        db.insert("tbl_thuThu", null, values);

//        values.clear();
//        values.put("thuThu_hoTen", "Jane Smith");
//        values.put("thuThu_matKhau", "abcdef");
//        db.insert("tbl_thuThu", null, values);
//
//        values.clear();
//        values.put("thuThu_hoTen", "David Johnson");
//        values.put("thuThu_matKhau", "qwerty");
//        db.insert("tbl_thuThu", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_thuThu");
        db.execSQL("DROP TABLE IF EXISTS tbl_thanhVien");
        db.execSQL("DROP TABLE IF EXISTS tbl_loaiSach");
        db.execSQL("DROP TABLE IF EXISTS tbl_Sach");
        db.execSQL("DROP TABLE IF EXISTS tbl_phieuMuon");
        onCreate(db);
    }
}

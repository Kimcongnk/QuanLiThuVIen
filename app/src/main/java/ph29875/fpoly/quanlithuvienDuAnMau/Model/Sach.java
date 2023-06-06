package ph29875.fpoly.quanlithuvienDuAnMau.Model;

public class Sach {
   private int maSach;
    private String tenSach;
    private int giaThue;
    private String maLoai;
    private int soLuong;

    public Sach() {
    }

    public Sach(String tensach, int soluong) {
        this.tenSach = tensach;
        this.soLuong = soluong;
    }
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

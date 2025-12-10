public class NhanVien {
    private String maNV;
    private String tenNV;
    private int tuoi;
    private double luong;
    private String phongBan;

    public NhanVien(String maNV, String tenNV, int tuoi, double luong, String phongBan) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tuoi = tuoi;
        this.luong = luong;
        this.phongBan = phongBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public int getTuoi() {
        return tuoi;
    }

    public double getLuong() {
        return luong;
    }

    public String getPhongBan() {
        return phongBan;
    }


    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}

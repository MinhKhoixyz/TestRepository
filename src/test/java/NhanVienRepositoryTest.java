import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NhanVienRepositoryTest {
    private NhanVienRepository service;
    @BeforeEach
    public void setUp() {
        service = new NhanVienRepository();
    }
    @Test
    public void add_hopLe8tr() {
        NhanVien nv = new NhanVien("NV01", "Nguyễn Văn A", 25, 8_000_000, "IT");
        assertTrue(service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLeAm() {
        NhanVien nv = new NhanVien("NV02", "Nguyễn Văn B", 30, -5000, "HR");
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLe0() {
        NhanVien nv = new NhanVien("NV03", "Nguyễn Văn C", 28, 0, "kế toán");
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLeQuaLon() {
        NhanVien nv = new NhanVien("NV04", "Nguyễn Văn D", 35, 150_000_000, "Marketing");
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLeTenTrong() {
        NhanVien nv = new NhanVien("NV05", "", 26, 800000, "IT");
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLeMa() {
        NhanVien nv = new NhanVien("", "Abc", 26, 800000, "IT");
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(nv));
    }
    @Test
    public void add_khongHopLeNull() {
        assertThrows(IllegalArgumentException.class, () -> service.themNhanVien(null));
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TinhTongTest {
    private TinhTong service;
    @BeforeEach
    void setUP(){
        service = new TinhTong();
    }
    @Test
    void HL1(){
        int[] arr ={1,2,3,4,5};
        int mongMuon= 15;
        int thucTe = service.sumArray(arr);
        assertEquals(mongMuon,thucTe);
    }
    @Test
    void KHL_Null(){
        assertThrows(IllegalArgumentException.class,() ->service.sumArray(null));
    }
    @Test
    void KHL_Am(){
        assertThrows(IllegalArgumentException.class,() ->service.sumArray(new int[]{-1,2,-3}));
    }
    @Test
    void KHL_QuaLon(){
        assertThrows(IllegalArgumentException.class,() ->service.sumArray(new int[]{1500,2000}));
    }
}
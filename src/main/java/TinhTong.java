public class TinhTong {
    public int sumArray(int [] arr){
        if (arr == null){
            throw new IllegalArgumentException("Mảng ko được null");
        }
        for (int num: arr){
            if(num<0||num>1000){
                throw new IllegalArgumentException("Phải trong khoảng từ 0-1000");
            }
        }
        int sum =0;
        for (int num : arr) {
            sum += num;
        }
        return  sum;
    }
}

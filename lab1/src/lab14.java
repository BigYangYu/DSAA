import java.util.Scanner;
public class lab14 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        long xr = input.nextLong();
        long yr = input.nextLong();
        long xc = input.nextLong();
        long yc = input.nextLong();
        int c = input.nextInt();
        String[] instruction = new String[c];
        String fk = input.next();
        for (int a = 0 ;a<fk.length();a++){
            instruction[a]= String.valueOf(fk.charAt(a));
        }
        long xrr = xr;
        long yrr = yr;
        for (int a = 0 ;a<c;a++){
            switch (instruction[a]) {
                case "U" : yrr++;break;
                case "D" : yrr--;break;
                case "L" : xrr--;break;
                case "R" : xrr++;break;
            }
        }
        long dx = xrr-xr;
        long dy = yrr-yr;
      //二分查找下界(找到追到前的那个周期)
long left = 0;
long right = (long) Math.pow(10,15);
while (left<right){
    long mid = (right+left+1)/2;
    if (Math.abs(xr+mid*dx-xc)+Math.abs(yr+mid*dy-yc)<=mid*c){
        right=mid-1;
    }
    else {
        left=mid;
    }
}
if (left==(long) Math.pow(10,10)){
    System.out.println("-1");
                   return;
}
            //更新坐标
            long xrn = xr+left*dx-xc;
            long yrn = yr+left*dy-yc;
            //可能在周期末尾追上
        //check函数
        if (Math.abs(xrn)+Math.abs(yrn)>left*c) {
            left++;
            xrn = xr+left*dx-xc;
            yrn = yr+left*dy-yc;
            for (int a = instruction.length-1 ;a>=0;a--){
                switch (instruction[a]) {
                    case "U" : yrn--;break;
                    case "D" : yrn++;break;
                    case "L" : xrn++;break;
                    case "R" : xrn--;break;
                }
                long sb=Math.abs(xrn)+Math.abs(yrn);
                long sb1 = left*c-(instruction.length-a);
               if (sb>sb1){
                    System.out.println(sb1+1);
                    return;
                }
            }
        }

        }
    }









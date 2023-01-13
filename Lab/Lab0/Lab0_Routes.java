import java.util.Scanner;
public class Lab0_Routes {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
      long x1 = input.nextLong();
      long y1 = input.nextLong();
      long x2 = input.nextLong();
      long y2 = input.nextLong();
      long a = input.nextLong();
      long xC = x2-x1;
      long yC = y2-y1;
      long add = yC+ xC;
        long p = Math.min(xC,yC);
long output = YangHui(add,p,a);
System.out.println(output%a);
         }

    private static long YangHui(long n, long m,long c){
        long[][] add =new long[(int) (n+1)][(int) (m+1)];
            for (long b = 0 ;b<n+1;b++){
                add[(int) b][0]=1%c;
            }
                for (long p = 0 ;p<m+1;p++){
                    add[(int) p][(int) p]=1%c;
                }


        for (long a = 1 ; a<n+1;a++){
            for (long b = 1 ;b<m+1;b++){
              add[(int) a][(int) b]=add[(int) (a-1)][(int) b]%c+add[(int) (a-1)][(int) (b-1)]%c;
            }
        }
        return add[(int) n][(int) m];
    }




}

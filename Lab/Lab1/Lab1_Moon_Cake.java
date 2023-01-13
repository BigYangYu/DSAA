

import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab1_Moon_Cake {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int people = input.nextInt();
        int many= input.nextInt();
        double [] area = new double[many];
        double add=0;
        for (long a = 0 ;a<many;a++){
            int c =input.nextInt();
            area[(int) a]= (double) (Math.pow(c,2)*Math.PI);
            add += area[(int) a];
        }
//二分查找

        double max = add/people;
        double min = 0;
if (check(area,max,people)){
    DecimalFormat answer=new DecimalFormat("#.#######");
    System.out.println(answer.format(max));
    return;
}
        while (max-min>=0.00001){
            double mid = (max+min)/2;
            if (check(area,mid,people)){
                min=mid;
            }
            else {
                max=mid;
            }
//if (mid==max||mid==min){
//    break;
//}
        }
        DecimalFormat answer=new DecimalFormat("#.#######");
        System.out.println(answer.format(max));

    }
    //函数判断
    public static boolean check(double[] yb, double r, int a ){
for (int b = 0;b<yb.length;b++){
    a-= (int) (yb[b]/r);
}
if (a>0){
    return false;
}
else {
    return true;
}
    }

}

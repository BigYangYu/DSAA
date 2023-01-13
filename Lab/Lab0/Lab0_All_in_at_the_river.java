import java.util.Scanner;
public class Lab0_All_in_at_the_river {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String ZJ= input.next();
        String[][]chart1=new String[1][5];
        for (int a = 0 ; a<5;a++){
            chart1[0][a]=input.next();
        }
        String[][]chart2=new String[2][5];
        for (int a = 0 ; a<5;a++){
            chart2[0][a]= String.valueOf(chart1[0][a].charAt(0));
            chart2[1][a]= String.valueOf(chart1[0][a].charAt(1));
        }
        String L = String.valueOf(ZJ.charAt(0));
        String C= String.valueOf(ZJ.charAt(1));
        for (int a = 0 ; a<5;a++){
            if (L.equals(chart2[0][a])||C.equals(chart2[1][a])){
                System.out.println("All in");
                return;
            }
        }
        System.out.println("Fold");
    }
}

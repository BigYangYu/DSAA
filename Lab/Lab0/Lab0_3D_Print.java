
import java.util.Scanner;
public class Lab0_3D_Print {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int  number1 = input.nextInt();
        int[][] a =new int [number1][3];
        for (int b = 0;b<number1;b++){
            a[b][0]=input.nextInt();
            a[b][1]=input.nextInt();
            a[b][2]=input.nextInt();
        }//统计成表格
StringBuilder output = new StringBuilder();
        for (int cont = 0 ; cont<number1;cont++){
            int long1 = a[cont][0];
            int wide = a[cont][1];
            int high = a[cont][2];
            int dot = wide*2;
            int long2=dot+long1*2+1;
            int high2=dot+high*2+1;
            String[][] chart =new String [dot+high*2+1][dot+long1*2+1];
            int copy = dot;
            for (int cont2 = 0;cont2<dot;cont2++){
                for (int cont3 = 0;cont3<copy;cont3++){
                chart[cont3][cont2]=".";

                }
                copy--;
            }
            copy = dot;
            for (int cont2 = 0;cont2<dot;cont2++){
                for (int cont3 = 0;cont3<copy;cont3++){
                    chart[high2-cont2-1][long2-cont3-1]=".";

                }
                copy--;
            }//点

            for (int cont4 = 0;cont4<long1*2;cont4+=2){
                int copy21 = dot;
                for (int cont5 = 0 ;cont5<wide;cont5++){
                    chart[cont5*2][copy21+cont4]="+";
                    chart[cont5*2][copy21+cont4+1]="-";
                    chart[cont5*2+1][copy21+cont4-1]="/";
                    chart[cont5*2+1][copy21+cont4]=".";
                    copy21-=2;
                }

            }
            int copy3 = 0;
            for (int cont5 = 0 ;cont5<wide;cont5++){
                chart[cont5*2][long2-1-copy3]="+";
                chart[cont5*2+1][long2-2-copy3]="/";
                copy3+=2;
            }//上底面

            for (int cont3 = 0;cont3<high;cont3++){
                for (int cont4 = 0 ;cont4<long1;cont4++){
                    chart[cont3*2+dot][cont4*2+1]="-";
                    chart[cont3*2+dot][cont4*2+1+1]="+";
                    chart[cont3*2+dot+1][cont4*2+1]=".";
                    chart[cont3*2+dot+1][cont4*2+1+1]="|";
                }
            }
            for (int cont2 =0;cont2<high*2;cont2+=2){
                chart[cont2+dot][0]="+";
                chart[cont2+dot+1][0]="|";
            }
            chart[high2-1][0]="+";
            for (int cont2 =0;cont2<long1*2;cont2+=2){
                chart[high2-1][cont2+1]="-";
                chart[high2-1][cont2+2]="+";
            }//正面
            int copy2 = 0;
            for (int cont4 = 0;cont4<wide;cont4++){

                for (int cont5 = 0 ;cont5<high;cont5++){
                    chart[cont5*2+1+copy2][long2-cont4*2-1]="|";
                    chart[cont5*2+2+copy2][long2-cont4*2-1]="+";
                    chart[cont5*2+2+copy2][long2-cont4*2-2]=".";
                    chart[cont5*2+3+copy2][long2-cont4*2-2]="/";
                }
                copy2+=2;
            }//侧面
           for (int aa = 0;aa<high2;aa++){
               for (int bb = 0;bb<long2;bb++){
                   output.append(chart[aa][bb]);
               }
               output.append("\r\n");
           }
        }
        System.out.print(output);

    }
}
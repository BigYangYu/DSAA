import java.io.IOException;
import java.util.Scanner;

public class lab41 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String breath = input.nextLine();
        String [] wei = new String[50010];
        int cont = 0;
        for (int a =0;a<breath.length();a++){
            String temple = String.valueOf(breath.charAt(a));
            if (temple.equals("(")){
                wei[cont]="(";
                cont++;
            }
            else if (temple.equals(")")){
                if (wei[cont-1].equals("(")){
                    cont--;
                    wei[cont]= String.valueOf(1);
                    if ((cont!=0)&&!wei[cont-1].equals("(")){
                        long gang = Long.parseLong(wei[cont-1])+1;
                        wei[cont-1]= String.valueOf(gang);
                        cont--;

                    }
                }
                else {
                    cont--;
                    long gang = Long.parseLong(wei[cont]);
                    gang=(gang*2)%514329;
                    wei[cont-1]= String.valueOf(gang);
                    cont--;
                }
                if ((cont!=0)&&!wei[cont-1].equals("(")){
                    long gang = Long.parseLong(wei[cont-1])+ Long.parseLong(wei[cont]);
                    wei[cont-1]= String.valueOf(gang);
                    cont--;

                }
                cont++;
            }

        }
        long output =Long.parseLong(wei[0]) %514329;
        System.out.println(output);
    }
}

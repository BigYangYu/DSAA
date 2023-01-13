import java.io.IOException;
import java.util.Scanner;

public class bonus_lab_D {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        boolean[] cao = new boolean[number];
        for (int a = 0; a < number; a++) {
            boolean answer = true;
            int bonus = input.nextInt();
            String[] pre = new String[bonus];
            int cont = -1;
            String cur1 = input.next();
            for (int b = 0; b < bonus; b++) {
                String cur= String.valueOf(cur1.charAt(b));
                if (cur.equals("(") || cur.equals("{") || cur.equals("[")) {
                    cont++;
                    pre[cont] = cur;
                } else if (cur.equals(")")) {
                      if (cont==-1){
                          answer=false;
                      }else if (!pre[cont].equals("(")){
                          answer=false;
                      }else if (pre[cont].equals("(")){
                          cont--;
                      }

                } else if (cur.equals("]")) {

                    if (cont==-1){
                        answer=false;
                    }else if (!pre[cont].equals("[")){
                        answer=false;
                    }else if (pre[cont].equals("[")){
                        cont--;
                    }

                } else if (cur.equals("}")) {

                    if (cont==-1){
                        answer=false;
                    }else if (!pre[cont].equals("{")){
                        answer=false;
                    }else if (pre[cont].equals("{")){
                        cont--;
                    }
                }
                else {
                    answer=false;
                }
            }
            if (cont!=-1){
                answer=false;
            }
           cao[a]=answer;
        }
        for (int a =0;a<number;a++){
            if (cao[a]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }
}
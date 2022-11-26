import java.util.Scanner;
public class lab4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        StringBuilder output = new StringBuilder();
        for (int a = 0 ; a<number;a++){

            String SP = input.next();
            boolean a1 = false;
            boolean a2 = false;
            boolean a3 = false;
            boolean a4 = false;
            char b='b';
            char s='s';
            char w='w';
            char z='z';
            int[] B = new int[9];
            int[] S = new int[9];
            int[] W = new int[9];
            int[] Z = new int[7];
            int shunzi = 0;
            int peng = 0;
            int duizi = 0;

            for (int e = 0 ; e<9;e++){
                B[e]=0;
            }
            for (int e = 0 ; e<9;e++){
                S[e]=0;
            }
            for (int e = 0 ; e<9;e++){
                W[e]=0;
            }
            for (int e = 0 ; e<7;e++){
                Z[e]=0;
            }
            for (int cont = 0 ; cont<14;cont++){
                int io= Integer.parseInt(SP.substring(cont*2,cont*2+1));
                if (SP.charAt(cont*2+1)==b){
                    B[io-1]++;
                }
               else if (SP.charAt(cont*2+1)==s){
                    S[io-1]++;
                }
                else if (SP.charAt(cont*2+1)==w){
                    W[io-1]++;
                }
                else if (SP.charAt(cont*2+1)==z){
                    Z[io-1]++;
                }
            }
for (int cont1 = 0 ;cont1<7;cont1++){
    if (B[cont1]%3==1){
        if (cont1<6&&B[cont1+1]%3==0 &&B[cont1+2]%3==0&&B[cont1+1]>0 &&B[cont1+2]>0&&B[cont1+3]%3==1){
            B[cont1]-=2;
            B[cont1+1]-=3;
            B[cont1+2]-=3;
            B[cont1+3]-=1;
            shunzi+=3;
        }
        else if (B[cont1+1]%3==2 &&B[cont1+2]%3==2&&B[cont1]>3){
            B[cont1]-=2;
            B[cont1+1]-=2;
            B[cont1+2]-=2;
            shunzi+=2;
        }
        else {
            peng+=B[cont1]/3;
            B[cont1]-=1;
            B[cont1+1]-=1;
            B[cont1+2]-=1;
            if (B[cont1+1]<0||B[cont1+2]<0){
                output.append("Bad luck\n");
                a1=true;
                break;
            }
            else {
                shunzi++;

            }
        }

    }
    if (B[cont1]%3==2){
        if (B[cont1+1]%3==2 &&B[cont1+2]%3==2){
            B[cont1]-=2;
            B[cont1+1]-=2;
            B[cont1+2]-=2;
            shunzi+=2;
        }
        else if (cont1<6){
            if (B[cont1+1]%3==0 &&B[cont1+2]%3==0&&B[cont1+1]>0 &&B[cont1+2]>0&&B[cont1+3]%3==1){
                B[cont1]-=2;
                B[cont1+1]-=3;
                B[cont1+2]-=3;
                B[cont1+3]-=1;
                shunzi+=3;
            }
        }
        else {
            B[cont1]-=2;
            duizi++;
        }
    }
    if (B[cont1]%3==0&&B[cont1]!=0){
        if (B[cont1+1]%3==1 &&B[cont1+2]%3==1){
            B[cont1]-=3;
            B[cont1+1]-=1;
            B[cont1+2]-=1;
            duizi++;
        }
        else
        if (B[cont1+1]%3==2 &&B[cont1+2]%3==0&&B[cont1+2]>0){
            B[cont1]-=1;
            B[cont1+1]-=1;
            B[cont1+2]-=1;
            shunzi++;
        }
        else if (B[cont1+1]%3==2 &&B[cont1+2]%3==2&&cont1<6&&B[cont1+3]%3==1){
            B[cont1]-=1;
            B[cont1+1]-=2;
            B[cont1+2]-=2;
            B[cont1+3]-=1;
            shunzi+=2;
        }
        else {
            peng+=B[cont1]/3;
            B[cont1]=0;
        }
    }


}

for (int cont1 = 0 ;cont1<9;cont1++){
    if (B[cont1]%3==0){
  peng+=B[cont1]/3;
    }
    if (B[cont1]%3==2){
        peng+=B[cont1]/3;
        duizi++;
    }
    if (B[cont1]%3==1){
        if (!a1){
            output.append("Bad luck\n");
            a1=true;
            break ;
        }

    }
}//B

            for (int cont1 = 0 ;cont1<7;cont1++){
                if (S[cont1]%3==1){
                    if (cont1<6&&S[cont1+1]%3==0 &&S[cont1+2]%3==0&&S[cont1+1]>0 &&S[cont1+2]>0&&S[cont1+3]%3==1){
                        S[cont1]-=2;
                        S[cont1+1]-=3;
                        S[cont1+2]-=3;
                        S[cont1+3]-=1;
                        shunzi+=3;
                    }
                    else if (S[cont1+1]%3==2 &&S[cont1+2]%3==2&&S[cont1]>3){
                        S[cont1]-=2;
                        S[cont1+1]-=2;
                        S[cont1+2]-=2;
                        shunzi+=2;
                    }
                    else {
                        peng+=S[cont1]/3;
                        S[cont1]-=1;
                        S[cont1+1]-=1;
                        S[cont1+2]-=1;
                        if ((S[cont1+1]<0||S[cont1+2]<0)&&!a1){
                            output.append("Bad luck\n");
                            a2=true;
                            break ;
                        }
                        else {
                            shunzi++;

                        }
                    }

                }
                if (S[cont1]%3==2){
                    if (S[cont1+1]%3==2 &&S[cont1+2]%3==2){
                        S[cont1]-=2;
                        S[cont1+1]-=2;
                        S[cont1+2]-=2;
                        shunzi+=2;
                    }
                    else if (cont1<6){
                        if (S[cont1+1]%3==0 &&S[cont1+2]%3==0&&S[cont1+1]>0 &&S[cont1+2]>0&&S[cont1+3]%3==1){
                            S[cont1]-=2;
                            S[cont1+1]-=3;
                            S[cont1+2]-=3;
                            S[cont1+3]-=1;
                            shunzi+=3;
                        }
                    }
                    else {
                        S[cont1]-=2;
                        duizi++;
                    }
                }
                if (S[cont1]%3==0&&S[cont1]!=0){
                    if (S[cont1+1]%3==1 &&S[cont1+2]%3==1){
                        S[cont1]-=3;
                        S[cont1+1]-=1;
                        S[cont1+2]-=1;
                        duizi++;
                    }
                    else
                    if (S[cont1+1]%3==2 &&S[cont1+2]%3==0&&S[cont1+2]>0){
                        S[cont1]-=1;
                        S[cont1+1]-=1;
                        S[cont1+2]-=1;
                        shunzi++;
                    }
                    else if (S[cont1+1]%3==2 &&S[cont1+2]%3==2&&cont1<6&&S[cont1+3]%3==1){
                        S[cont1]-=1;
                        S[cont1+1]-=2;
                        S[cont1+2]-=2;
                        S[cont1+3]-=1;
                        shunzi+=2;
                    }
                    else {
                        peng+=S[cont1]/3;
                        S[cont1]=0;
                    }
                }


            }

            for (int cont1 = 0 ;cont1<9;cont1++){
                if (S[cont1]%3==0){
                    peng+=S[cont1]/3;
                }
                if (S[cont1]%3==2){
                    peng+=S[cont1]/3;
                    duizi++;
                }
                if (S[cont1]%3==1){
                    if (!a2&&!a1){
                        output.append("Bad luck\n");
                        a2=true;
                        break ;
                    }
                }
            }//S


            for (int cont1 = 0 ;cont1<7;cont1++){
                if (W[cont1]%3==1){
                    if (cont1<6&&W[cont1+1]%3==0 &&W[cont1+2]%3==0&&W[cont1+1]>0 &&W[cont1+2]>0&&W[cont1+3]%3==1){
                        W[cont1]-=2;
                        W[cont1+1]-=3;
                        W[cont1+2]-=3;
                        W[cont1+3]-=1;
                        shunzi+=3;
                    }
                    else if (W[cont1+1]%3==2 &&W[cont1+2]%3==2&&W[cont1]>3){
                        W[cont1]-=2;
                        W[cont1+1]-=2;
                        W[cont1+2]-=2;
                        shunzi+=2;
                    }
                    else {
                        W[cont1]-=1;
                        W[cont1+1]-=1;
                        W[cont1+2]-=1;
                        if ((W[cont1+1]<0||W[cont1+2]<0)&&!a2&&!a1){
                            output.append("Bad luck\n");
                            a3=true;
                            break ;
                        }

                        else {
                            shunzi++;
                        }
                    }

                }
                if (W[cont1]%3==2){
                    if (W[cont1+1]%3==2 &&W[cont1+2]%3==2){
                        W[cont1]-=2;
                        W[cont1+1]-=2;
                        W[cont1+2]-=2;
                        shunzi+=2;

                    }
                    else if (cont1<6){
                        if (W[cont1+1]%3==0 &&W[cont1+2]%3==0&&W[cont1+1]>0 &&W[cont1+2]>0&&W[cont1+3]%3==1){
                            W[cont1]-=2;
                            W[cont1+1]-=3;
                            W[cont1+2]-=3;
                            W[cont1+3]-=1;
                            shunzi+=3;
                        }
                    }
                    else {
                        W[cont1]-=2;
                        duizi++;
                    }
                }
                if (W[cont1]%3==0&&W[cont1]!=0){
                    if (W[cont1+1]%3==1 &&W[cont1+2]%3==1){
                        W[cont1]-=3;
                        W[cont1+1]-=1;
                        W[cont1+2]-=1;
                        duizi++;
                    }
                    else if (W[cont1+1]%3==2 &&W[cont1+2]%3==0&&W[cont1+2]>0){
                            W[cont1]-=1;
                            W[cont1+1]-=1;
                            W[cont1+2]-=1;
                            shunzi++;
                        }
                    else if (W[cont1+1]%3==2 &&W[cont1+2]%3==2&&cont1<6&&W[cont1+3]%3==1){
                        W[cont1]-=1;
                        W[cont1+1]-=2;
                        W[cont1+2]-=2;
                        W[cont1+3]-=1;
                        shunzi+=2;
                    }
                    else {
                        peng+=W[cont1]/3;
                        W[cont1]=0;
                    }
                }


            }

            for (int cont1 = 0 ;cont1<9;cont1++){
                if (W[cont1]%3==0){
                    peng+=W[cont1]/3;
                }
                if (W[cont1]%3==2){
                    peng+=W[cont1]/3;
                    duizi++;
                }
                if (W[cont1]%3==1){
                    if (!a2&&!a1&&!a3){
                        output.append("Bad luck\n");
                        a3=true;
                        break ;
                    }
                }
            }//W

            for (int cont1 = 0 ;cont1<7;cont1++){
                if (Z[cont1]%3==0){
                    peng+=Z[cont1]/3;
                }
                if (Z[cont1]%3==2){
                    peng+=Z[cont1]/3;
                    duizi++;
                }
                if (W[cont1]%3==1&&!a2&&!a1&&!a3){
                    output.append("Bad luck\n");
                    a4=true;
                    break ;
                }
            }
if (duizi==1&&shunzi+peng==4&&!a2&&!a1&&!a3&&!a4){
    output.append("Blessing of Heaven\n");
}
else if (!a2&&!a1&&!a3&&!a4){
    output.append("Bad luck\n");
}

        }
        System.out.println(output);
    }

}

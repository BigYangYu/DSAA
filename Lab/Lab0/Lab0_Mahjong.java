import java.util.Scanner;
public class Lab0_Mahjong {
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
            int[] Bc = new int[9];
            int[] Sc = new int[9];
            int[] Wc = new int[9];
            int[] Zc = new int[7];
            int shunzi = 0;
            int peng = 0;
            int duizi = 0;

            for (int e = 0 ; e<9;e++){
                B[e]=0;
                Bc[e]=0;
            }
            for (int e = 0 ; e<9;e++){
                S[e]=0;
                Sc[e]=0;
            }
            for (int e = 0 ; e<9;e++){
                W[e]=0;
                Wc[e]=0;
            }
            for (int e = 0 ; e<7;e++){
                Z[e]=0;
                Zc[e]=0;
            }
            for (int cont = 0 ; cont<14;cont++){
                int io= Integer.parseInt(SP.substring(cont*2,cont*2+1));
                if (SP.charAt(cont*2+1)==b){
                    B[io-1]++;
                    Bc[io-1]++;
                }
                else if (SP.charAt(cont*2+1)==s){
                    S[io-1]++;
                    Sc[io-1]++;
                }
                else if (SP.charAt(cont*2+1)==w){
                    W[io-1]++;
                    Wc[io-1]++;
                }
                else if (SP.charAt(cont*2+1)==z){
                    Z[io-1]++;
                    Zc[io-1]++;
                }
            }


            for (int cont1 = 0 ;cont1<7;cont1++){
                if (Z[cont1]%3==0){
                    peng+=(Z[cont1]/3);
                }
                if (Z[cont1]%3==2){
                    peng+=(Z[cont1]/3);
                    duizi++;
                }
                if (Z[cont1]%3==1&&!a2&&!a1&&!a3){
                    output.append("Bad luck\n");
                    a4=true;
                    break ;
                }
            }//先判断z

            if (duizi>1&&!a2&&!a1&&!a3&&!a4){
                output.append("Bad luck\n");
            }
if (duizi==1&&!a2&&!a1&&!a3&&!a4){
    if ( HU(B,S,W, shunzi, peng, duizi)){
        output.append("Blessing of Heaven\n");
    }
    else {
        output.append("Bad luck\n");
    }
}
int duizicopy = duizi;
int pengcopy =peng;
int shunzicopy = shunzi;
if (duizi==0){
    boolean result =false;
    for (int cont1 = 0 ;cont1<9;cont1++){


        if (B[cont1]/2>=1){
            B[cont1]-=2;
            duizi++;
            if (HU(B,S,W, shunzi, peng, duizi)){
                output.append("Blessing of Heaven\n");
                result=true;

                break;
            }
            else {
                result=false;
                System.arraycopy(Bc, 0, B, 0, 9);
                System.arraycopy(Wc, 0, W, 0, 9);
                System.arraycopy(Sc, 0, S, 0, 9);
                duizi=duizicopy;
                peng=pengcopy;
               shunzi=shunzicopy;
            }

        }
         if (S[cont1]/2>=1){
            S[cont1]-=2;
            duizi++;
            if (HU(B,S,W, shunzi, peng, duizi)){
                output.append("Blessing of Heaven\n");
                result=true;
                break;
            }
            else {
                result=false;
                System.arraycopy(Bc, 0, B, 0, 9);
                System.arraycopy(Wc, 0, W, 0, 9);
                System.arraycopy(Sc, 0, S, 0, 9);
                duizi=duizicopy;
                peng=pengcopy;
                shunzi=shunzicopy;
            }

        }
       if (W[cont1]/2>=1){
            W[cont1]-=2;
            duizi++;
            if (HU(B,S,W, shunzi, peng, duizi)){
                output.append("Blessing of Heaven\n");
                result=true;
                break;
            }
            else {
                result=false;
                System.arraycopy(Bc, 0, B, 0, 9);
                System.arraycopy(Wc, 0, W, 0, 9);
                System.arraycopy(Sc, 0, S, 0, 9);
                duizi=duizicopy;
                peng=pengcopy;
                shunzi=shunzicopy;
            }

        }
    }
    if (!result&&!a2&&!a1&&!a3&&!a4){
        output.append("Bad luck\n");
    }
}

        }
        System.out.println(output);
    }

    public static boolean HU(int[]B,int[]S,int[]W,int shunzi,int peng,int duizi){
        for (int cont1 = 0 ;cont1<7;cont1++){
            if (B[cont1]%3==1){
                    B[cont1]-=1;
                    B[cont1+1]-=1;
                    B[cont1+2]-=1;
                    if (B[cont1+1]<0||B[cont1+2]<0){
                        return false;
                    }
                    else {
                        shunzi++;
                    }
            }
            if (B[cont1]%3==2){
                B[cont1]-=2;
                B[cont1+1]-=2;
                B[cont1+2]-=2;
                if (B[cont1+1]<0||B[cont1+2]<0){
                    return false;
                }
                else {
                    shunzi+=2;

                }
            }
        }
        for (int cont1 = 0 ;cont1<9;cont1++){
            if (B[cont1]%3==0){
                peng+=B[cont1]/3;
            }
            if (B[cont1]%3==2){
               return false;
            }
            if (B[cont1]%3==1){
                return false;
            }
    }//B
        for (int cont1 = 0 ;cont1<7;cont1++){
            if (S[cont1]%3==1){
                S[cont1]-=1;
                S[cont1+1]-=1;
                S[cont1+2]-=1;
                if (S[cont1+1]<0||S[cont1+2]<0){
                    return false;
                }
                else {
                    shunzi++;
                }
            }
            if (S[cont1]%3==2){
                S[cont1]-=2;
                S[cont1+1]-=2;
                S[cont1+2]-=2;
                if (S[cont1+1]<0||S[cont1+2]<0){
                    return false;
                }
                else {
                    shunzi+=2;
                }
            }
        }
        for (int cont1 = 0 ;cont1<9;cont1++){
            if (S[cont1]%3==0){
                peng+=S[cont1]/3;
            }
            if (S[cont1]%3==2){
                return false;
            }
            if (S[cont1]%3==1){
                return false;
            }
        }//S
        for (int cont1 = 0 ;cont1<7;cont1++){
            if (W[cont1]%3==1){
                W[cont1]-=1;
                W[cont1+1]-=1;
                W[cont1+2]-=1;
                if (W[cont1+1]<0||W[cont1+2]<0){
                    return false;
                }
                else {
                    shunzi++;
                }
            }
            if (W[cont1]%3==2){
                W[cont1]-=2;
                W[cont1+1]-=2;
                W[cont1+2]-=2;
                if (W[cont1+1]<0||W[cont1+2]<0){
                    return false;
                }
                else {
                    shunzi+=2;

                }
            }
        }
        for (int cont1 = 0 ;cont1<9;cont1++){
            if (W[cont1]%3==0){
                peng+=W[cont1]/3;
            }
            if (W[cont1]%3==2){
                return false;
            }
            if (W[cont1]%3==1){
                return false;
            }
        }//W
        if (peng+shunzi==4){
            return true;
        }
        else {
            return false;
        }
}
}

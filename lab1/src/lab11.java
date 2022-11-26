import java.util.Scanner;
public class lab11 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int many = input.nextInt();
        int [] question = new int[length+1];
        question[0]=Integer.MIN_VALUE;
        for (int a =1 ;a<question.length;a++){
            question[a]=input.nextInt();
        }
        StringBuilder output =new StringBuilder();

for (int a = 0;a<many;a++){
    int answer =0;
    int x = input.nextInt()+1;
    int y = input.nextInt()-1;
    int shangjie = ShangJie(question,y);
    int xiajie = XiaJie(question,x);
//    if (question[shangjie]==y){
//         answer = shangjie-xiajie-1;
//    }
//   else {
         answer = shangjie-xiajie+1;
//    }
    if (answer>0){
        output.append("YES ");
        output.append(answer);
        output.append("\n");
    }
    else {
        output.append("NO");
        output.append("\n");
    }
}
System.out.println(output);
    }
    private static int XiaJie(int[]question,int x){
        int l =0;
        int  a = question.length-1;
        while (l<=a){
            int mid = (l+a)/2;
            if (question[mid]>=x){
                a=mid-1;
            }
            else {
                l=mid+1;
            }
        }
return l;
    }


    private static int ShangJie(int[]question,int y){
        int l =0;
        int  a = question.length-1;
        while (l<=a){
            int mid = (l+a)/2;
            if (question[mid]<=y){
                l=mid+1;
            }
            else {
                a=mid-1;
            }
        }
        return a;
    }




}

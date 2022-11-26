import java.util.Scanner;
public class lab12 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int many = input.nextInt();
        long add = input.nextLong();
      long [] question =new  long[many];
      for (int a = 0 ;a<many;a++){
          question[a]=input.nextLong();
      }
      long answer = 0;
    for (int first = 0 ; first<many-2;first++){
        int second = first+1;
        int third = many-1;
        while (second<third){
            if (question[second]+question[third]+question[first]==add){
               int Second = 1;
               int Third = 1;
               int left = second+1;
               int right = third-1;
               boolean jieguo;
                if (question[second]!=question[third]){
                    jieguo=true;
                }
                else {
                    jieguo=false;
                }
//               if (question[second]!=question[third]){
//                   for (int b = second+1;b<third;b++){
//                       if (question[b]==question[second]){
//                           Second++;
//                       }
//                       else if (question[b]==question[third]){
//                           Third++;
//                       }
//                   }
//                   answer+= (long) Second *Third;
//               }
//               else {
//                   for (int b = second+1;b<third;b++){
//                       if (question[b]==question[second]){
//                           Second++;
//                       }
//                   }
//                   long xixi = Second+Third;
//                   answer+=xixi*(xixi-1)/2;
//               }
//                break;
                while (question[left]==question[second]&&left<right){
                    Second++;
                    left++;
                }
                while (question[right]==question[third]&&left<right){
                  Third++;
                   right--;
                }
                if (left==right&&question[left]==question[second]){
                   Second++;
                }
                else if (question[right]==question[third]&&left==right){
                    Third++;
                }
                second=left;
                third=right;
                if (jieguo){
                    answer+= (long) Second *Third;
                }
                else {
                    long xixi = Second+Third;
                  answer+=xixi*(xixi-1)/2;
                }
            }
            else if (question[second]+question[third]+question[first]<add){
                second++;
            }
            else{
               third--;
            }
        }
    }
    System.out.println(answer);
    }
}

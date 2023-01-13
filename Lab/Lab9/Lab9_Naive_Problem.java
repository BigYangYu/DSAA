import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Lab9_Naive_Problem {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        int c=in.nextInt();
        node[] tree = new node[n];
        for(int i=0;i<n;i++){
            tree[i]= new node();
            tree[i].path=new long[k];
        }
        for (int a =0;a<n;a++){
            tree[a].color=in.nextInt()-1;
        }
        for(int i=0;i<m;i++){
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            tree[a].val=a;
            tree[b].val=b;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }
        for (int v = 0;v<k;v++){
            for (int o =0;o<n;o++){
                tree[o].isvisited=false;
            }
            node[] q=new node[n];
            int front=0,rear=0;
            for (int a =0;a<n;a++){
                if (tree[a].color==v){
                    tree[a].path[v]=0;
                    tree[a].isvisited=true;
                    q[rear]=tree[a];
                    rear++;
                }
            }
            while (rear!=front){
                node temp=q[front++];
                temp.isvisited=true;
                for(int i=0;i<temp.child.size();i++){
                    if(!temp.child.get(i).isvisited){
                        q[rear++]=temp.child.get(i);
                        temp.child.get(i).isvisited=true;
                        temp.child.get(i).path[v]=temp.path[v]+1;
                    }
                }
            }
        }
       for (int a =0;a<n;a++){
           Arrays.sort(tree[a].path);
           long answer=0;
           for (int b =0;b<c;b++){
               answer+=tree[a].path[b];
           }
           System.out.print(answer);
           System.out.print(" ");
       }

    }
    static class node{
        int color;
        long[] path;
        boolean isvisited;
        int val;
        ArrayList<node> child = new ArrayList<>();

    }
}

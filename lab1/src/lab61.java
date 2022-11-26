import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab61 {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
       int n=in.nextInt();
        int num=in.nextInt();
        node[] tree = new node[n];
        for(int i=0;i<n;i++){
            tree[i]= new node();
        }
        for(int i=0;i<n-1;i++){
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            int w=in.nextInt();
            tree[a].child.add(tree[b]);
            tree[a].w.add(w);
            tree[b].child.add(tree[a]);
            tree[b].w.add(w);
        }
        node[] q=new node[n];
        int front=0,rear=0;
        q[rear++]=tree[0];
        tree[0].val=0;
        int cnt=0;

        while (rear!=front){
            node temp=q[front++];
            if(temp.val==num && temp.child.size()==1) cnt++;

            temp.isvisited=true;
            for(int i=0;i<temp.child.size();i++){
                if(!temp.child.get(i).isvisited){
                    q[rear++]=temp.child.get(i);
                    temp.child.get(i).isvisited=true;
                    temp.child.get(i).val=temp.val+temp.w.get(i);
                }
            }
        }
        System.out.println(cnt);
    }

//    static void dfs(node temp){
//        if (temp.val==num&&temp.child.size()==1)
//        for(int i=0;i<temp.child.size();i++){
//            if(temp.child.get(i).isvisited==false){
//              temp.child.get(i).isvisited=true;
//    temp.child.get(i).val=temp.val+temp.w.get(i);
//           dfs(temp.child.get(i))
//            }
//        }
//    }

    static class node{
        boolean isvisited;
        int val;
        ArrayList<node> child = new ArrayList<>();
        ArrayList<Integer> w=new ArrayList<>();
    }
}

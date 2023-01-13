import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lab9_Simple_Problem {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int target = in.nextInt()-1;
        node[] tree = new node[n];
        bian[] guanxi = new bian[m];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
            tree[i].number = i;
        }//初始化树
        node[] tree_reverse = new node[n];
        for (int i = 0; i < n; i++) {
            tree_reverse[i] = new node();
            tree_reverse[i].number = i;
        }//初始化反向树
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            tree[a].child.add(tree[b]);
            tree_reverse[b].child.add(tree_reverse[a]);
            guanxi[i] = new bian(a, b);
        }//实例化位置的父子关系以及节点
        ArrayList<node> breath=new ArrayList<>();
        node[] stack = new node[n];
        dfs(tree_reverse[0],breath, stack,-1,tree_reverse,0,0);
        node[] tree1=new node[breath.size()];
        for (int a =0;a<breath.size();a++){
            tree1[a]=tree[breath.get(breath.size()-1-a).number];
        }
        ArrayList<node> breath2=new ArrayList<>();
       dfs(tree1[0],breath2, stack,-1,tree1,0,0);
        int number=0;
        for (int c =0;c<tree.length;c++){
            number=Math.max(number,tree[c].scc);
        }
        int[] wei=new int[number+1];
        for (int a =0;a<guanxi.length;a++){
            if (tree[guanxi[a].parent].scc!=tree[guanxi[a].son].scc){
                wei[tree[guanxi[a].son].scc]++;
            }
        }
        int all =0;
       for (int a =0;a<number+1;a++){
           if (wei[a]==0){
               all++;
           }
       }
       if (wei[tree[target].scc]==0){
           out.println(all-1);
       }else {
           out.println(all);
       }
        out.close();
    }

    static void dfs(node temp, ArrayList<node> answer, node[] stack,int pop,node[]tree,int count,int scc) {
        if (count==tree.length){
            return;
        }
        pop++;
        temp.isvisited=true;
        stack[pop]=temp;
        for (int i = 0; i < temp.child.size(); i++) {
            if (!temp.child.get(i).isvisited) {
                temp.child.get(i).isvisited = true;
                dfs(temp.child.get(i), answer, stack,pop,tree,count,scc);
            }
        }
        node cur = stack[pop];
        pop--;
        answer.add(cur);
        cur.scc=scc;
        if (pop==-1){
            while (count<tree.length){
                if (tree[count].isvisited){
                    count++;
                }else {
                    scc++;
                    dfs(tree[count], answer, stack,pop,tree,count,scc);
                    break;
                }
            }
            if (count==tree.length){
                return;
            }
        }
    }

    static class node {
        boolean isvisited;
        int scc;
        int number;
        ArrayList<node> child = new ArrayList<>();

    }

    public static class bian {
        int parent;
        int son;

        bian(int parent, int son) {
            this.parent = parent;
            this.son = son;
        }


    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}

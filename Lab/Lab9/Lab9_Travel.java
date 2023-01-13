import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lab9_Travel {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
            if (i==0){
                tree[i].val=0;
            }else {
                tree[i].val=Long.MAX_VALUE;
            }

        }//初始化树

        for (int i = 0; i <m; i++) {
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            int w=in.nextInt();
            tree[a].child.add(tree[b]);
            tree[a].number=a;
            tree[b].number=b;
            tree[a].w.add(w);
        }//实例化位置的父子关系以及节点
        node cur =new node();
        node1 cur1 = new node1();
        heap answer =new heap(m);
for (int a =1;a<=n;a++){
    if (a==1){
        cur=tree[0];
        cur.isvisited=true;
    }
    else {
        cur1=answer.delete();
        cur=tree[cur1.A];
        while (cur.isvisited){
            cur1=answer.delete();
            cur=tree[cur1.A];
        }
        cur.val=cur1.value;
        cur.isvisited=true;
    }
    for (int b =0;b<cur.child.size();b++){
        node1 cur2 = new node1();
        cur2.A=cur.child.get(b).number;
        cur2.value=cur.val+cur.w.get(b);
        answer.insert(cur2);
    }

}
if (tree[tree.length-1].val==Long.MAX_VALUE){
    out.println("-1");
}else {
    out.println(tree[tree.length-1].val);
}
        out.close();
    }

    static class node {
        boolean exist;
        boolean isvisited;
        long val;
        int number;
        ArrayList<node> child = new ArrayList<>();
        ArrayList<Integer> w = new ArrayList<>();
    }

    public  static class node1{
        int A;
        boolean visited;
        long value;


    }
    public static class heap {
        node1[] heap ;
        int size = 0;

        public heap(int n) {
            heap = new node1[n + 1];
            for (int a =0;a<n+1;a++){
                heap[a]=new node1();
            }
        }

        public void insert(node1 x) {
            size++;
            heap[size].value = x.value;
            heap[size].A = x.A;

            int index = size;
            while (index > 1) {
                if (heap[index].value < heap[index / 2].value) {
                    long temp = heap[index].value;
                    int ta=heap[index].A;
                    heap[index].value = heap[index / 2].value;
                    heap[index].A = heap[index / 2].A;
                    heap[index / 2].value = temp;
                    heap[index / 2].A=ta;
                    index = index / 2;
                } else break;
            }

        }

        public node1 delete() {
            long temp = heap[size].value;
            int ta=heap[size].A;
           node1 fuck = new node1();
            fuck.value=heap[1].value;
            fuck.A=heap[1].A;
            size--;
            heap[1].value = temp;
            heap[1].A=ta;
            int index = 1;
            while (2 * index <= size) {//有儿子
                if (2 * index + 1 <= size) {//有右儿子
                    if (heap[2 * index + 1].value < heap[2 * index].value) {//右儿子小于左儿子
                        if (heap[index].value > heap[2 * index + 1].value) {//儿子<父亲
                            long temp1 = heap[2 * index + 1].value;
                            int ta1=heap[2 * index + 1].A;
                            heap[2 * index + 1].value = heap[index].value;
                            heap[2 * index + 1].A = heap[index].A;
                            heap[index].value = temp1;
                            heap[index].A = ta1;
                            index = 2 * index + 1;
                        } else break;
                    } else {
                        if (heap[index].value > heap[2 * index].value) {//儿子<父亲
                            long temp1 = heap[2 * index ].value;
                            int ta1=heap[2 * index ].A;
                            heap[2 * index ].value = heap[index].value;
                            heap[2 * index ].A = heap[index].A;
                            heap[index].value = temp1;
                            heap[index].A = ta1;
                            index = 2 * index ;
                        } else break;
                    }
                } else {//没右儿子
                    if (heap[index].value > heap[2 * index].value) {//儿子<父亲
                        long temp1 = heap[2 * index ].value;
                        int ta1=heap[2 * index ].A;
                        heap[2 * index ].value = heap[index].value;
                        heap[2 * index ].A = heap[index].A;
                        heap[index].value = temp1;
                        heap[index].A = ta1;
                        index = 2 * index ;
                    } else break;
                }
            }


            return fuck;
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

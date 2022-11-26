import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class lab64 {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int cont = 0;
        long number = 0;
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
        }//初始化树
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }//实例化位置的父子关系以及节点
        for (int i = 0; i < n; i++) {
            tree[i].val = in.nextLong();
            if (tree[i].val > number) {
                number = tree[i].val;
                cont = i;
            }
        }
        long max = tree[cont].val;
        posterorder(tree[cont]);
        node[] q = new node[n];
        int front = 0, rear = 0;
        q[rear] = tree[cont];
        rear++;
        long cur2=0;
        long cur = 0;
        int cur1 = 0;
        if(tree[cont].child.size() == 1){
            while (rear != front) {
                node temp = q[front++];

                temp.isvisited = false;
                for (int i = 0; i < temp.child.size(); i++) {
                    if (temp.child.get(i).isvisited) {
                        cur2++;
                        q[rear] = temp.child.get(i);
                        rear++;
                        if (temp.child.get(i).isvisited && temp.child.get(i).val > cur) {
                            cur = temp.child.get(i).val;
                            cur1 = i;
                        }
                        temp.child.get(i).isvisited = false;
                    }
                }
                if (cur2>0) {
                    temp.child.get(cur1).val = temp.val;
                }
                cur = 0;
                cur1 = 0;
                cur2=0;
            }
        }
        else {
            int first=0;
            int second=0;
            long firstval=0;
            long secondval=0;
            for (int a =0;a<tree[cont].child.size();a++){
                if (tree[cont].child.get(a).val>firstval){
                    secondval=firstval;
                    second=first;
                    firstval=tree[cont].child.get(a).val;
                    first=a;
                }
                else {
                    if (tree[cont].child.get(a).val>secondval){
                        secondval=tree[cont].child.get(a).val;
                        second=a;
                    }
                }
            }
            tree[cont].child.get(first).val=max;
            tree[cont].child.get(second).val=max;
            while (rear != front) {
                node temp = q[front++];
                temp.isvisited = false;
                for (int i = 0; i < temp.child.size(); i++) {
                    if (temp.child.get(i).isvisited) {
                        cur2++;
                        q[rear] = temp.child.get(i);
                        rear++;
                        if (temp.child.get(i).isvisited && temp.child.get(i).val > cur) {
                            cur = temp.child.get(i).val;
                            cur1 = i;
                        }
                        temp.child.get(i).isvisited = false;
                    }
                }
                if (cur2>0) {
                    temp.child.get(cur1).val = temp.val;
                }
                cur = 0;
                cur1 = 0;
                cur2=0;
            }
        }
        long[] answer = new long[200000];
        int count1 = 0;
        int count2 = n - 1;
        int count3 = 0;
        while (count2 > 0) {
            if (q[count2].child.size() == 1) {
                answer[count1] = q[count2].val;
                count1++;
            }
            count2--;
        }
        if (tree[cont].child.size() == 1) {
            long output = 0;
            while (answer[count3] != 0) {
                output += answer[count3];
                count3++;
            }
            out.println(output + max);
            out.close();
        } else {
            long output = 0;
            while (answer[count3] != 0) {
                output += answer[count3];
                count3++;
            }
            out.println(output);
            out.close();
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

    static class node {
        boolean isvisited;
        long val;
        ArrayList<node> child = new ArrayList<>();
    }
    public static void posterorder(node root) {
        if (root == null) {
            return;
        }
        root.isvisited = true;
        for (int i = 0; i < root.child.size(); i++) {
            if (!root.child.get(i).isvisited) {
                root.child.get(i).isvisited = true;
                posterorder(root.child.get(i));
                if (root.child.get(i).val > root.val) {
                    root.val = root.child.get(i).val;
                }
            }
        }
    }

}

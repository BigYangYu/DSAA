import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lab6_City_Selection {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
            tree[i].val = i;
        }//初始化树
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }//实例化位置的父子关系以及节点
        int contain = in.nextInt();
        int[][] lim = new int[contain][3];
        for (int a = 0; a < contain; a++) {
            lim[a][0] = in.nextInt() - 1;
            lim[a][1] = in.nextInt() - 1;
            lim[a][2] = in.nextInt();
        }
        for (int a = 0; a < n; a++) {
            tree[a].max = 1;
        }//先把节点最大值赋值成1
        posterorder(tree[0]);
        //首先更新树的最大值；
        long[] store = new long[n];
        for (int a = 0; a < n; a++) {
            store[a] = tree[a].max;
        }
        int l = 0;
        int r = n-1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(tree, lim, contain, mid, store)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //二分查找
        if (!check(tree, lim, contain, n, store)) {
            out.println (-1);
        } else {
            out.println(l);
        }
        out.close();
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
        int val;
        boolean isvisited;
        long min;
        long max;
        ArrayList<node> child = new ArrayList<>();
        node parent;
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
                root.max += root.child.get(i).max;
                root.child.get(i).parent = root;
            }
        }
    }

    public static boolean check(node[] tree, int[][] lim, int n, int k, long[] store) {
        for (int c = 0; c < tree.length; c++) {
            tree[c].max = store[c];
            tree[c].min = 0;
            tree[c].isvisited = false;

        }//更新一下
        boolean answer = true;
        for (int a = 0; a < n; a++) {
            int A = lim[a][0];
            int B = lim[a][1];
            int na = lim[a][2];
            boolean ck1 = false;
            boolean ck2 = false;
            boolean ck3 = false;
            if (tree[A].parent == null) {
                ck1 = true;
            }
            if (tree[B].parent == null) {
                ck2 = true;
            } else {
                if (tree[B].parent.val == A) {
                    ck3 = true;
                }
            }//判断父子关系
            if (ck1 || ck3) {
                //A是B的父亲
                int cha = k - na;
                if (cha < 0) {
                    return false;
                }
                //先判断右边界是否会小于0
                if (cha < tree[B].max) {
                    tree[B].max = cha;
                }
                    if (tree[B].min > tree[B].max) {
                        return false;
                    }//判断左边界是否小于右边界
            } else {
                //B是A的父亲
                if (na > tree[A].min) {
                    tree[A].min = na;
                }
                if (tree[A].min > tree[A].max) {
                    return false;
                }//判断左边界是否小于右边界
            }
        }
        answer = change(tree[0]);
        if (!(tree[0].min <= k && tree[0].max >= k)) {
            return false;
        }//判断最后根是否满足题意
        return answer;
    }
    public static boolean change(node root) {
        boolean a = true;
        if (root == null) {
            return true;
        }
        long shangjie = 1;
        long xiajie = 0;
        for (int i = 0; i < root.child.size(); i++) {
            if (root.child.get(i).parent==root) {
                a = change(root.child.get(i));
                if (!a) {
                    return false;
                }
                shangjie += root.child.get(i).max;
                xiajie += root.child.get(i).min;
            }
        }
        if (xiajie > root.min) {
            root.min = xiajie;
        }
        if (shangjie < root.max) {
            root.max = shangjie;
        }
        if (root.max <  root.min) {
            a = false;
        }
        return a;
    }

}

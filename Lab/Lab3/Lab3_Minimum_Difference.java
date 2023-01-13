import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab3_Minimum_Difference {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt(), m = in.nextInt(), q = in.nextInt();
        node head = new node(-1919810,-1);
        node cur = head;
        for (int a = 0; a <= n; a++) {
            node temp = new node(-1919810,-1);
            cur.next = temp;
            cur = cur.next;
        }
        cur = head;
        node cur1;
        node cur2;
        for (int a = 1; a <= n; a++) {
            node temp1 = new node(-1919810,-1);
            cur.down = temp1;
            temp1.up = cur;
            cur = cur.down;
            cur1 = cur;
            cur2 = cur.up.next;
            for (int b = 1; b <= m; b++) {
                node temp = new node(in.nextLong(),0);
                cur1.next = temp;
                temp.previse = cur1;
                cur2.down = temp;
                temp.up = cur2;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            node temp2 = new node(-1919810,-1);
            cur1.next = temp2;
            temp2.previse = cur1;
            cur2.down = temp2;
            temp2.up = cur2;
        }

        node tail = new node(-1919810,-1);
        cur.down = tail;
        tail.up = cur;
        cur = cur.down;
        cur1 = cur;
        cur2 = cur.up.next;
        for (int b = 0; b <= m; b++) {
            node temp = new node(-1919810,-1);
            cur1.next = temp;
            temp.previse = cur1;
            cur2.down = temp;
            temp.up = cur2;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        //添加假头与假尾，并且构建完整个链表
        for (int l = 0; l < q; l++) {
            cur1 = head.down.next;
            cur2 = head.down.next;
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int l1 = in.nextInt();
            int l2 = in.nextInt();
           for (int a =0;a<x1-1;a++){
               cur1=cur1.down;
           }
            for (int a =0;a<y1-1;a++){
                cur1=cur1.next;
            }
            for (int a =0;a<x2-1;a++){
                cur2=cur2.down;
            }
            for (int a =0;a<y2-1;a++){
                cur2=cur2.next;
            }
            node son1head1 = cur1;
            node son2head1 = cur2;
//找到两个子矩阵的头
            for (int c = 0; c < l2; c++) {
                cur1.up.down = cur2;
                cur2.up.down = cur1;
                node temple = cur2.up;
                cur2.up = cur1.up;
                cur1.up = temple;
                cur2 = cur2.next;
                cur1 = cur1.next;
            }//第一行
            node son1head2 = cur1.previse;
            node son2head2 = cur2.previse;
            cur1 = son1head1;
            cur2 = son2head1;
            for (int c = 0; c < l1; c++) {
                cur1.previse.next = cur2;
                cur2.previse.next = cur1;
                node temple = cur2.previse;
                cur2.previse = cur1.previse;
                cur1.previse = temple;
                cur2 = cur2.down;
                cur1 = cur1.down;
            }//第一列
            for (int c = 0; c < l2; c++) {
                cur1.up.down = cur2;
                cur2.up.down = cur1;
                node temple = cur2.up;
                cur2.up = cur1.up;
                cur1.up = temple;
                cur2 = cur2.next;
                cur1 = cur1.next;
            }//最后一行
            cur1 = son1head2;
            cur2 = son2head2;
            for (int c = 0; c < l1; c++) {
                cur1.next.previse = cur2;
                cur2.next.previse = cur1;
                node temple = cur2.next;
                cur2.next = cur1.next;
                cur1.next = temple;
                cur2 = cur2.down;
                cur1 = cur1.down;
            }//最后一列

        }
        cur1 = head.down.next;
        for (int a = 1; a <= n; a++) {
            cur = cur1;
            for (int b = 1; b <= m; b++) {
                if (cur.value != -1) {
                    out.print(cur.value + " ");
                    cur = cur.next;
                } else {
                    cur = cur.next;
                }
            }
            out.println();
            cur1 = cur1.down;
        }
        out.close();
    }

    public static boolean check1(int x1, int y1, int l1, int l2) {
        if ((x1 == l1)) {
            if (y1 == l2) {
                return true;
            }
        }
        return false;
    }

    public static boolean check2(int x1, int y1, int l1, int l2) {
        if ((x1 == l1)) {
            if (y1 == l2) {
                return true;
            }
        }
        return false;
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
    public static class node {
        long value;
        int order;
        node next;
        node previse;
        node up;
        node down;

        public node(long value ,int order) {
            this.value = value;
            this.order=order;
        }

    }
}



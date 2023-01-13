import java.io.*;

public class Lab3_Shopping {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        long m = in.nextLong();
        node head = new node(in.nextLong());
        node cur = head;
        long money = head.prize;
        long cont = 0;
        long min = head.prize;
        for (int a = 1; a < n; a++) {
            node temp = new node(in.nextLong());
            money += temp.prize;
            cur.next = temp;
            cur = cur.next;
            long mid = temp.prize;
            if (mid < min) {
                min = mid;
            }
        }
        cur.next = head;
        cur = head;
        cont += (m / money)*n;
        m = m % money;
        while (m >=min) {
            if (m >= cur.prize) {
                m -= cur.prize;
                cont++;
                cur = cur.next;
            } else {
                cur = cur.next;
            }
        }
       System.out.println(cont);
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
    public static  class node {
        long prize;
        node next;

        public node(long prize) {
            this.prize = prize;

        }

    }
}


//        10 30
//        10 5 8 28 0 19 2 31 1 22
//        I 6 9
//        M 1 11
//        I 8 17
//        M 1 31
//        M 6 26
//        Q 2 7 6
//        I 23 30
//        M 31 7
//        I 22 27
//        M 26 18
//        Q 1 10 9
//        I 5 2
//        I 18 13
//        Q 3 3 1
//        I 27 19
//        Q  1 15 13
//        Q 5 13 5
//        I 3 0
//        M 15 27
//        Q 1 8 2
//        Q 3 7 4
//        M 2 8
//        Q 5 12 7
//        I 30 19
//        M 11 19
//        Q 17 8 1
//        M 29 4
//        Q 3 1 1
//        I 7 18
//        M 29 27
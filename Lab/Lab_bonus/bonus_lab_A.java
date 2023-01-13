import java.io.*;
import java.util.*;

public class bonus_lab_A {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder output = new StringBuilder();
        int test = in.nextInt();
       long [] answer =new long[test];
        for (int a =0;a<test;a++){
            int n =in.nextInt();
            long []igong=new long[n];
            for (int b =0;b<n;b++){
                igong[b]=in.nextLong();
            }
            long ikun = Long.MIN_VALUE;
            long c=MergeSort(igong, n,ikun);
            if (c== Long.MIN_VALUE){
                long p= Long.MIN_VALUE;
                for (int r =0;r<n-1;r++){
                    p=Math.max(p,igong[r]-igong[r+1]);
                }
              answer[a]=p;
            }
            else {
                answer[a]=c;
            }
        }

        for (int a =0;a<test;a++){
            out.println(answer[a]);
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

    private static long MergeSort(long[] igang, int n,long ikun) {
        if (n > 1) {
            int middle = n / 2;
            long[] son1 = new long[middle];
            long[] son2 = new long[n - middle];
            for (int a = 0; a <= middle - 1; a++) {
                son1[a] = igang[a];
            }
            for (int a = 0; a <= n - middle - 1; a++) {
                son2[a] = igang[a + middle];
            }
            ikun= Math.max(MergeSort(son1, middle,ikun) ,MergeSort(son2, n - middle,ikun)) ;
            int left = son1.length;
            int right = n - son1.length;
            long[] temp = new long[left + right];
            int cont2 = 0;
            int cont3 = 0;
            for (int cont1 = 0; cont1 < left + right; cont1++) {
                if (cont2 <= left - 1 && (cont3 > right - 1 || son1[cont2] <= son2[cont3])) {
                    temp[cont1] = son1[cont2];
                    cont2++;
                } else {
                    long count = left  - cont2;
                    if (count!=0){
                        ikun =Math.max(ikun,son1[son1.length-1]- son2[cont3]);
                    }
                    temp[cont1] = son2[cont3];
                    cont3++;

                }
            }
            for (int a = 0; a < igang.length; a++) {
                igang[a] = temp[a];
            }
        }
        return ikun;
    }


}
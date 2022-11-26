//快读模板1：更快，但没有next()用于读字符串

import java.io.*;
import java.util.*;

public class lab23 {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        long number = in.nextLong();
        long[] igang = new long[(int) number];
        for (int a = 0; a < number; a++) {
            igang[a] = in.nextLong();
        }
        MergeSort(igang, (int) number);
        long output = igang[(int) (number / 3)];
        long[] ikun = new long[(int) number];
        long[] itai = new long[(int) number - (int) (number / 3) + 1];
        long[] ylm = new long[(int) number / 3 - 1];
        itai[0] = igang[0];
        for (int a = 1; a < itai.length; a++) {
            itai[a] = igang[(int) (number / 3) + a - 1];
        }
        for (int b = 0; b < ylm.length; b++) {
            ylm[b] = igang[b + 1];
        }
        int cont1 = 1;
        int cont2 = 0;
        ikun[0] = igang[0];
        int a = 1;
        while (a < number) {
            if ((a % 3 != 0 && cont1 < itai.length)||cont2== ylm.length) {
                ikun[a] = itai[cont1];
                cont1++;
                a++;
            } else {
                ikun[a] = ylm[cont2];
                cont2++;
                a++;
            }
        }
        out.println(output);
        for (int v = 0; v < ikun.length; v++) {
            out.print(ikun[v]);
            out.print(" ");
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

    private static void MergeSort(long[] igang, int n) {
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
            MergeSort(son1, middle);
            MergeSort(son2, n - middle);
            long[] output = Merge(son1, son1.length, son2, n - son1.length);
            for (int a = 0; a < igang.length; a++) {
                igang[a] = output[a];
            }
        }
    }

    private static long[] Merge(long[] son1, int left, long[] son2, int right) {
        long[] temp = new long[left + right];
        int cont2 = 0;
        int cont3 = 0;
        for (int cont1 = 0; cont1 < left + right; cont1++) {
            if (cont2 <= left - 1 && (cont3 > right - 1 || son1[cont2] <= son2[cont3])) {
                temp[cont1] = son1[cont2];
                cont2++;
            } else {
                temp[cont1] = son2[cont3];
                cont3++;
            }
        }
        return temp;
    }
}

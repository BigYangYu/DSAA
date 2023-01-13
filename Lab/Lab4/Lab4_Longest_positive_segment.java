import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab4_Longest_positive_segment {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        long[][] breath = new long[n][2];
        for (int a = 0; a < n; a++) {
            if (a == 0) {
                breath[a][0] = in.nextLong();
                breath[a][1]=a+1;
            } else {
                breath[a][0] = breath[a - 1][0] + in.nextLong();
                breath[a][1]=a+1;
            }
        }
        MergeSort(breath,n);
        long max=breath[n-1][1];
        long xb = breath[n-1][1];
        if (max<=0){
            out.println(0);
            return;
        }
     for (int a=n-2;a>=0;a--){
       if (breath[a][1]>xb){
           xb=breath[a][1];
           if (breath[a][0]>=0){
               max=xb;
           }
       }

         max=Math.max(max,xb-breath[a][1]);
     }
        out.println(max);
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
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }
    private static void MergeSort(long[][] igang, int n) {
        if (n > 1) {
            int middle = n / 2;
            long[][] son1 = new long[middle][2];
            long[][] son2 = new long[n - middle][2];
            for (int a = 0; a <= middle - 1; a++) {
                son1[a][1] = igang[a][1];
                son1[a][0] = igang[a][0];
            }
            for (int a = 0; a <= n - middle - 1; a++) {
                son2[a][1] = igang[a + middle][1];
                son2[a][0] = igang[a + middle][0];
            }
            MergeSort(son1, middle);
            MergeSort(son2, n - middle);
            long[][] output = Merge(son1, son1.length, son2, n - son1.length);
            for (int a = 0; a < igang.length; a++) {
                igang[a][0] = output[a][0];
                igang[a][1] = output[a][1];
            }
        }
    }
    private static long[][] Merge(long[][] son1, int left, long[][] son2, int right) {
        long[][] temp = new long[left + right][2];
        int cont2 = 0;
        int cont3 = 0;
        for (int cont1 = 0; cont1 < left + right; cont1++) {
            if (cont2 <= left - 1 && (cont3 > right - 1 || son1[cont2][0] <= son2[cont3][0])) {
                temp[cont1][0] = son1[cont2][0];
                temp[cont1][1] = son1[cont2][1];
                cont2++;
            } else {
                temp[cont1][0] = son2[cont3][0];
                temp[cont1][1] = son2[cont3][1];
                cont3++;
            }
        }
        return temp;
    }
}

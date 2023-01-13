
//快读模板1：更快，但没有next()用于读字符串

import java.io.*;

public class Lab2_Swapping {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        long n = in.nextLong();
        long m = in.nextLong();
        long k = in.nextLong();
        long[] igang = new long[(int) n];
        for (int b = 0; b < n; b++) {
            igang[b] = in.nextLong();
        }
        long[] exchange = new long[2000000];//每次交换的改变值都存进去
        int count = 0;//exchange数组的计数器
        long add = 0;//记录总和

        for (int a = 0; a < n; a++) {
            here:
            if (igang[a] > 0) {
                String temp = String.valueOf(igang[a]);//转成string数组
                int[] middle = new int[temp.length()];//把数存进去
                for (int b = 0; b < temp.length(); b++) {
                    String lol = String.valueOf(temp.charAt(b));
                    middle[b] = Integer.parseInt(lol);
                }
                for (int b = 0; b < middle.length - 1; b++) {
                    int ok = b;
                    for (int c = b + 1; c < middle.length; c++) {
                        if (middle[ok] < middle[c]) {
                            ok = c;
                        }
                    }
                    int temple = middle[ok];
                    middle[ok] = middle[b];
                    middle[b] = temple;  //选择排序
                    long change = 0;
                    for (int d = middle.length - 1; d >= 0; d--) {
                        change += middle[d] * Math.pow(10, middle.length - 1 - d);
                    }
                    long dx = change - igang[a];
                    if (dx > k) {
                        exchange[count] = dx;
                        count++;
                        igang[a] = change;
                    } else if (dx > 0) {
                        add += igang[a];
                        break here;
                    } else if (dx == 0) {
                        exchange[count] = dx;
                        count++;
                        igang[a] = change;
                    }
                }
                add+=igang[a];
            } else if (igang[a] < 0) {
                igang[a] = igang[a] * -1;//变成正数
                String temp = String.valueOf(igang[a]);//转成string数组
                int[] middle = new int[temp.length()];//把数存进去
                for (int b = 0; b < temp.length(); b++) {
                    String lol = String.valueOf(temp.charAt(b));
                    middle[b] = Integer.parseInt(lol);
                }
                for (int b = 0; b < middle.length - 1; b++) {
                    int ok = b;
                    for (int c = b + 1; c < middle.length; c++) {
                        if (middle[ok] > middle[c]) {
                            ok = c;
                        }
                    }
                    int temple = middle[ok];
                    middle[ok] = middle[b];
                    middle[b] = temple;
                    long change = 0;//选择排序
                    for (int d = middle.length - 1; d >= 0; d--) {
                        change += middle[d] * Math.pow(10, middle.length - 1 - d);
                    }
                    long dx = igang[a] - change;
                    if (dx > k) {
                        exchange[count] = dx;
                        count++;
                        igang[a] = change;
                    } else if (dx > 0) {
                        add -= igang[a];
                        break here;
                    } else if (dx == 0) {
                        exchange[count] = dx;
                        count++;
                        igang[a] = change;
                    }
                }
                add -= igang[a];
            }
        }
        MergeSort(exchange, exchange.length);
        int cont2 = 1;
        for (int v = exchange.length - 1; v >= 0; v--) {
            if (exchange[v] != 0 && cont2 <= m) {
                add -= k;
                cont2++;
            } else if (exchange[v] != 0 && cont2 > m) {
                add -= exchange[v];
                cont2++;
            } else if (exchange[v] == 0) {
                break;
            }
        }
        out.println(add);
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

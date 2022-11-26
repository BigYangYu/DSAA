import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class lab43 {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        long[] breath = new long[n];
        for (int a = 0; a < n; a++) {
            breath[a] = in.nextLong();
        }
        int[] ming = new int[n];
        long[] gala = new long[n];
        int left = 0;
        int right = 0;
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        ming[0] = 0;
        right++;
        for (int b = 1; b < k; b++) {
            if (breath[b] <= breath[ming[cont2]]) {
                cont2++;
                ming[cont2] = b;
                right++;
            } else {
                while (cont2 >= 0) {
                    if (breath[b] > breath[ming[cont2]]) {
                        if (cont2 == 0) {
                            ming[cont2] = b;
                            break;
                        } else {
                            cont2--;
                        }
                    } else {
                        cont2++;
                        ming[cont2] = b;
                        break;
                    }
                }
                right++;
            }
        }
        gala[0] = breath[ ming[0]];
        cont3++;
        right--;
        for (int b = k; b < n; b++) {
            right++;
            left++;
            if (breath[b] <= breath[ming[cont2]]) {
                cont2++;
                ming[cont2] = b;

                while (true) {
                    if ( ming[cont1] >= left) {
                        gala[cont3] = breath[ming[cont1]];
                        cont3++;
                        break;
                    } else {
                        cont1++;
                    }
                }
            } else {
                while (cont2 >= cont1) {
                    if (breath[b] > breath[ming[cont2]]) {
                        if (cont2 == cont1) {
                            ming[cont2] = b;
                            break;
                        } else {
                            cont2--;
                        }
                    } else {
                        cont2++;
                        ming[cont2] = b;
                        break;
                    }
                }
                while (true) {
                    if (ming[cont1] >= left) {
                        break;
                    } else {
                        cont1++;
                    }
                }
                gala[cont3] = breath[ming[cont1]];
                cont3++;
            }
        }
        for (int b = 0; b < q; b++) {
            out.println(gala[in.nextInt() - 1]);
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
}

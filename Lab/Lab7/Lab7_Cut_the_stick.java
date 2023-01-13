import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab7_Cut_the_stick {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int[] answer = new int[n];
        for (int a = 0; a < n; a++) {
            int number = in.nextInt();
            heap small = new heap(number);
            for (int b = 0; b < number; b++) {
                small.insert(in.nextInt());
            }
            int cur =0;
            while (small.size > 1) {
              int  breath = small.delete()+small.delete();
               cur+=breath;
                small.insert(breath);
            }
            answer[a] = cur;
        }
        for (int a = 0; a < n; a++) {
            out.println(answer[a]);
        }
        out.close();
    }

    public static class heap {
        int[] heap;
        int size = 0;

        public heap(int n) {
            heap = new int[n + 1];
        }

        public void insert(int x) {
            size++;
            heap[size] = x;
            int index = size;
            while (index > 1) {
                if (heap[index] < heap[index / 2]) {
                    int temp = heap[index];
                    heap[index] = heap[index / 2];
                    heap[index / 2] = temp;
                    index = index / 2;
                } else break;
            }

        }

        public int delete() {
            int temp = heap[size];
            int fuck = heap[1];
            size--;
            heap[1] = temp;
            int index = 1;
            while (2 * index <= size) {//有儿子
                if (2 * index + 1 <= size) {//有右儿子
                    if (heap[2 * index + 1] < heap[2 * index]) {//右儿子小于左儿子
                        if (heap[index] > heap[2 * index + 1]) {//儿子<父亲
                            int temp1 = heap[2 * index + 1];
                            heap[2 * index + 1] = heap[index];
                            heap[index] = temp1;
                            index = 2 * index + 1;
                        } else break;
                    } else {
                        if (heap[index] > heap[2 * index]) {//儿子<父亲
                            int temp1 = heap[2 * index];
                            heap[2 * index] = heap[index];
                            heap[index] = temp1;
                            index = 2 * index;
                        } else break;
                    }
                } else {//没右儿子
                    if (heap[index] > heap[2 * index]) {//儿子<父亲
                        int temp1 = heap[2 * index];
                        heap[2 * index] = heap[index];
                        heap[index] = temp1;
                        index = 2 * index;
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

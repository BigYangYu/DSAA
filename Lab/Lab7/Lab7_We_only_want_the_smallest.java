
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
public class Lab7_We_only_want_the_smallest {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        long[] A = new long[N];
        for (int a = 0; a < N; a++) {
            A[a] = in.nextLong();
        }
        long[] B = new long[M];
        for (int a = 0; a < M; a++) {
            B[a] = in.nextLong();
        }
        Arrays.sort(A);
        Arrays.sort(B);
        long[] output =new long[K];
       heap answer=new heap(M);
        for (int a =0;a<M;a++){
            node temple =new node();
            temple.value=A[0]*B[a];
            temple.A=0;
            temple.B=a;
            answer.insert(temple);
        }
        node cur=new node();
        node cur1=new node();
        cur=answer.delete();
        output[0]=cur.value;
        int b = 1;
        while (b<=K-1){
            if (cur.A+1<N){
                node temple =new node();
                temple.value=A[cur.A+1]*B[cur.B];
                temple.A=cur.A+1;
                temple.B=cur.B;
                answer.insert(temple);
                cur1=answer.delete();
                cur.value= cur1.value;
                cur.A=cur1.A;
                cur.B=cur1.B;
                output[b]=cur.value;
                b++;
            }
            else {
                cur1=answer.delete();
                cur.value= cur1.value;
                cur.A=cur1.A;
                cur.B=cur1.B;
                output[b]=cur.value;
                b++;
            }
        }
        for (int a =0;a<K;a++){
            out.println(output[a]);
        }
        out.close();
    }
public  static class node{
    int A;
    int B;
    long value;
    node next;

}
    public static class heap {
        node[] heap ;
        int size = 0;

        public heap(int n) {
            heap = new node[n + 1];
            for (int a =0;a<n+1;a++){
                heap[a]=new node();
            }
        }

        public void insert(node x) {
            size++;
            heap[size].value = x.value;
            heap[size].A = x.A;
            heap[size].B = x.B;
            int index = size;
            while (index > 1) {
                if (heap[index].value < heap[index / 2].value) {
                    long temp = heap[index].value;
                    int ta=heap[index].A;
                    int tb=heap[index].B;
                    heap[index].value = heap[index / 2].value;
                    heap[index].A = heap[index / 2].A;
                    heap[index].B = heap[index / 2].B;
                    heap[index / 2].value = temp;
                    heap[index / 2].A=ta;
                    heap[index / 2].B=tb;
                    index = index / 2;
                } else break;
            }

        }

        public node delete() {
            long temp = heap[size].value;
            int ta=heap[size].A;
            int tb=heap[size].B;
            node fuck = new node();
            fuck.value=heap[1].value;
            fuck.A=heap[1].A;
            fuck.B=heap[1].B;
            size--;
            heap[1].value = temp;
            heap[1].A=ta;
            heap[1].B=tb;
            int index = 1;
            while (2 * index <= size) {//有儿子
                if (2 * index + 1 <= size) {//有右儿子
                    if (heap[2 * index + 1].value < heap[2 * index].value) {//右儿子小于左儿子
                        if (heap[index].value > heap[2 * index + 1].value) {//儿子<父亲
                            long temp1 = heap[2 * index + 1].value;
                            int ta1=heap[2 * index + 1].A;
                            int tb1=heap[2 * index + 1].B;
                            heap[2 * index + 1].value = heap[index].value;
                            heap[2 * index + 1].A = heap[index].A;
                            heap[2 * index + 1].B = heap[index].B;
                            heap[index].value = temp1;
                            heap[index].A = ta1;
                            heap[index].B = tb1;
                            index = 2 * index + 1;
                        } else break;
                    } else {
                        if (heap[index].value > heap[2 * index].value) {//儿子<父亲
                            long temp1 = heap[2 * index ].value;
                            int ta1=heap[2 * index ].A;
                            int tb1=heap[2 * index ].B;
                            heap[2 * index ].value = heap[index].value;
                            heap[2 * index ].A = heap[index].A;
                            heap[2 * index ].B = heap[index].B;
                            heap[index].value = temp1;
                            heap[index].A = ta1;
                            heap[index].B = tb1;
                            index = 2 * index ;
                        } else break;
                    }
                } else {//没右儿子
                    if (heap[index].value > heap[2 * index].value) {//儿子<父亲
                        long temp1 = heap[2 * index ].value;
                        int ta1=heap[2 * index ].A;
                        int tb1=heap[2 * index ].B;
                        heap[2 * index ].value = heap[index].value;
                        heap[2 * index ].A = heap[index].A;
                        heap[2 * index ].B = heap[index].B;
                        heap[index].value = temp1;
                        heap[index].A = ta1;
                        heap[index].B = tb1;
                        index = 2 * index ;
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

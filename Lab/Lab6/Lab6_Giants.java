import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lab6_Giants {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
        }//初始化树

        for (int i = 0; i < n - 1; i++) {
            int w;
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (i == 0) {
                w = 0;
            } else {
                w = 1;
            }
            tree[a].child.add(tree[b]);
            tree[a].w.add(w);
            tree[b].child.add(tree[a]);
            tree[b].w.add(w);
        }//实例化位置的父子关系以及节点
        int num = in.nextInt();
        for (int a = 0; a < num; a++) {
            int b = in.nextInt();
            tree[b - 1].exist = true;
        }
        tree[0].isvisited=true;
        //将城市里存在的人打标
        int second = tree[0].child.size();
        long[][] number = new long[second][n];
        node[][] q = new node[second][n];
        int front = 0, rear = 0;
        for (int a = 0; a < second; a++) {
            q[a][0] = tree[0].child.get(a);
            number[a][0]=0;
            tree[0].val = 0;
            int cnt = 0;
        }
        int cont =0;
        //将的第一批子节点作为后续步骤的根首先存入队列中
        for (int a = 0; a < second; a++) {
            front=0;
            rear=1;
            cont=1;
            while (rear != front) {
                node temp = q[a][front++];
                temp.isvisited = true;
                for (int i = 0; i < temp.child.size(); i++) {
                    if (!temp.child.get(i).isvisited) {
                        q[a][rear] = temp.child.get(i);
                        temp.child.get(i).isvisited = true;
                        temp.child.get(i).val = temp.val + temp.w.get(i);
                        if (temp.child.get(i).exist){
                            if ( temp.child.get(i).val>number[a][cont-1]){
                                number[a][cont]= temp.child.get(i).val;
                            }
                          else {
                                number[a][cont]= number[a][cont-1]+1;
                            }
                            cont++;
                        }
                        rear++;
                    }
                }
            }
        }
long[] answer = new long[second];
        for (int b = 0;b<second;b++){
            for (int a =n-1;a>=0;a--){
            if (number[b][a]!=0){
                answer[b]=number[b][a];
                break;
            }
                answer[b]=number[b][a];
            }
        }
        MergeSort(answer,answer.length);
        out.println(answer[second-1]+1);
        out.close();
    }

    static class node {
        boolean exist;
        boolean isvisited;
        long val;
        ArrayList<node> child = new ArrayList<>();
        ArrayList<Integer> w = new ArrayList<>();
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

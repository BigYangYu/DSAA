import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lab8_Cycles_are_Bad {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        long []answer=new long[2];
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
            tree[i].val=i+1;
        }//初始化树
        for (int i = 0; i <m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }//实例化并反向构建节点
        for (int a = n-1;a>=0;a--){
            if (!tree[a].exist){

              answer=fuzhi(tree[a],a+1);
                answer[1]=answer[1]/2;

                if (answer[1]>=answer[0]&&answer[0]!=1) {
                    out.println("Bad");
                    out.close();
                    return;
                }
            }
        }
        out.println("Good");
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

    public static long[] fuzhi(node a,int n){
        long []output1 = new long[2];
        long output=0;
        long count=0;
        node[] q=new node[n];
        int front=0,rear=0;
        q[rear++]=a;
        while (rear!=front){
            node temp=q[front++];
            if (temp!=null){
                temp.isvisited=true;
                temp.exist=true;
                output +=temp.child.size();
                count++;
                for(int i=0;i<temp.child.size();i++){
                    if(!temp.child.get(i).isvisited){
                        q[rear++]=temp.child.get(i);
                        temp.child.get(i).isvisited=true;
                        temp.child.get(i).exist=true;
                    }
                }
            }
        }
        output1[0]=count;
        output1[1]=output;
        return output1;
    }
    static class node {
        boolean exist;
        boolean isvisited;
        long val;
        ArrayList<node> child = new ArrayList<>();

    }




}

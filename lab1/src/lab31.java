//
//import java.io.*;
//
//public class lab31{
//    public static void main(String[] args) throws IOException {
//       Reader in = new Reader();
//        PrintWriter out = new PrintWriter(System.out);
//        int n=in.nextInt(),m=in.nextInt();
//        node head=new node(-1000000001,-1000000001);
//        node cur=head;
//        for (int i = 0; i < n; i++) {
//            node temp=new node(in.nextInt(),in.nextInt());
//            cur.next=temp;
//            cur=cur.next;
//        }
//        node tail=new node(1000000001,1000000001);
//        cur.next=tail;
//
//
//        cur=head;
//        for (int i = 0; i < m; i++) {
//            node temp=new node(in.nextInt(),in.nextInt());
//            while(true){
//                if(temp.exp<cur.next.exp) break;
//                cur=cur.next;
//            }
//            if(cur.exp==temp.exp) cur.coe+=temp.coe;
//            else{
//                temp.next=cur.next;
//                cur.next=temp;
//            }
//        }
//
//        int cnt=0;
//        cur=head.next;
//        while(cur!=tail){
//            if(cur.coe!=0) cnt++;
//            cur=cur.next;
//        }
//        out.println(cnt);
//
//        cur=head.next;
//        while(cur!=tail){
//            if(cur.coe!=0) out.println(cur.coe+" "+cur.exp);;
//            cur=cur.next;
//        }
//        out.close();
//    }
//    static class Reader {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader() {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.') {
//                while ((c = read()) >= '0' && c <= '9') {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException {
//            if (din == null)
//                return;
//            din.close();
//        }
//    }
//}
//
//class node{
//    int coe;
//    int exp;
//    node next;
//    public node(int coe,int exp){
//        this.coe=coe;
//        this.exp=exp;
//    }
//
//}
//

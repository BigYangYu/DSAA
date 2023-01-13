
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab7_Funny_Fluffy_Tuzi {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        long N = in.nextLong();
        node head = new node();
        head.position = -1;
        head.value = -1;
        node cur = new node();
        cur.value = in.nextInt();
        cur.pre = head;
        cur.second=1;
        head.next = cur;
        for (int a = 1; a < N; a++) {
            node temp = new node();
            temp.value = in.nextInt();
            temp.second=a+1;
            temp.delete=false;
            temp.pre = cur;
            cur.next = temp;
            cur = cur.next;
        }
        node tail = new node();
        tail.value = -1;
        tail.position = -1;
        tail.pre = cur;
        cur.next = tail;
        cur=head.next;
        int counter = (int) N;
        heap answer = new heap((int) N);
        for (int a =0;a<N;a++){
            answer.insert(cur);
            cur=cur.next;
        }
        for (int b =1 ; b<N;b++){
            answer.heap[b].position=b;
        }
        while (counter>=2){
            node tem1 = answer.delete();
            while (tem1.delete){
                tem1=answer.delete();
            }
            node tem2 = tem1.pre;
            node tem3 = tem1.next;
            node tem4 = new node();
            if (tem2.value!=-1){
                if (tem3.value==-1){
                    answer.delete1((int) tem1.pre.position);
                    tem4.value=(tem2.value^tem1.value)+1;
                    tem2.pre.next=tem4;
                    tem4.pre=tem2.pre;
                    tem1.next.pre=tem4;
                    tem4.next=tem1.next;
                    tem4.second=tem1.second;

                }else {
                    if ((tem2.value^tem1.value)>(tem3.value^tem1.value)||((tem2.value^tem1.value)==(tem3.value^tem1.value))&&tem2.second<=tem3.second){
                        answer.delete1((int) tem1.pre.position);
                        tem4.value=(tem2.value^tem1.value)+1;
                        tem2.pre.next=tem4;
                        tem4.pre=tem2.pre;
                        tem1.next.pre=tem4;
                        tem4.next=tem1.next;
                        tem4.second=tem1.second;
                    }
                    else {
                        answer.delete1((int) tem1.next.position);
                        tem4.value=(tem3.value^tem1.value)+1;
                        tem1.pre.next=tem4;
                        tem4.pre=tem1.pre;
                        tem3.next.pre=tem4;
                        tem4.next=tem3.next;
                        tem4.second=tem1.second;
                    }
                }
            }
            else {
                answer.delete1((int) tem1.next.position);
                tem4.value=(tem3.value^tem1.value)+1;
                tem1.pre.next=tem4;
                tem4.pre=tem1.pre;
                tem3.next.pre=tem4;
                tem4.next=tem3.next;
                tem4.second=tem1.second;
            }
            answer.insert(tem4);
            counter--;
        }
        int output = 0;
        for (int a =1;a<N;a++){
            if (!answer.heap[a].delete){
                output=answer.heap[a].value;
                break;
            }
        }
out.println(output);
        out.close();
    }

    public static class node {
        boolean delete;
        int second;
        int position;
        int value;
        node next;
        node pre;

    }

    public static class heap {
        node[] heap;
        int size = 0;

        public heap(int n) {
            heap = new node[n + 1];
            for (int a = 0; a < n + 1; a++) {
                heap[a] = new node();
            }
        }

        public void insert(node x) {
            size++;
            heap[size] = x;
            heap[size].position=size;
            int index = size;
            while (index > 1) {
                if ((heap[index].value < heap[index / 2].value)||(heap[index].value == heap[index / 2].value&&heap[index].second <= heap[index / 2].second)) {
                    node temp = heap[index];
                    int ta=heap[index].position;
//                    int  tb=heap[index].second;
                    heap[index].position=heap[index / 2].position;
//                    heap[index].second=heap[index / 2].second;
                    heap[index / 2].position=ta;
//                    heap[index / 2].second=tb;
                    heap[index] = heap[index / 2];
                    heap[index / 2] = temp;
                    index = index / 2;
                } else break;
            }
        }

        public node delete() {
            node temp = heap[size];
            node fuck = new node();
            fuck = heap[1];
            int tb = heap[size].position;
            heap[size].position=fuck.position;
            fuck.position=tb;
            size--;
            heap[1] = temp;
            int index = 1;
            while (2 * index <= size) {//有儿子
                if (2 * index + 1 <= size) {//有右儿子
                    if (heap[2 * index + 1].value < heap[2 * index].value||(heap[2 * index + 1].value == heap[2 * index].value&&heap[2 * index + 1].second <= heap[2 * index].second)) {//右儿子<=左儿子
                        if (heap[index].value > heap[2 * index + 1].value||(heap[index].value == heap[2 * index + 1].value)&&heap[index].second >= heap[2 * index + 1].second) {//儿子<=父亲
                            node temp1 = heap[2 * index + 1];
                            int ta=heap[index].position;
//                            int tc=heap[index].second;
                            heap[index].position=heap[2 * index + 1].position;
//                            heap[index].second=heap[2 * index + 1].second;
                            heap[2 * index + 1].position=ta;
//                            heap[2 * index + 1].second=tc;
                            heap[2 * index + 1] = heap[index];
                            heap[index] = temp1;
                            index = 2 * index + 1;
                        } else break;
                    } else {
                        if (heap[index].value > heap[2 * index].value||(heap[index].value == heap[2 * index ].value)&&heap[index].second >= heap[2 * index ].second) {//儿子<=父亲
                            node temp1 = heap[2 * index];
                            int ta=heap[index].position;

//                            int tc=heap[index].second;
                            heap[index].position=heap[2 * index ].position;
//                            heap[index].second=heap[2 * index ].second;
                            heap[2 * index ].position=ta;
//                            heap[2 * index ].second=tc;
                            heap[2 * index] = heap[index];
                            heap[index] = temp1;
                            index = 2 * index;
                        } else break;
                    }
                } else {//没右儿子
                    if (heap[index].value > heap[2 * index].value||(heap[index].value == heap[2 * index ].value)&&heap[index].second >= heap[2 * index ].second) {//儿子<父亲
                        node temp1 = heap[2 * index];
                       int ta=heap[index].position;
//                        int tc=heap[index].second;
                        heap[index].position=heap[2 * index ].position;
//                        heap[index].second=heap[2 * index ].second;
                        heap[2 * index ].position=ta;
//                        heap[2 * index ].second=tc;
                        heap[2 * index] = heap[index];
                        heap[index] = temp1;
                        index = 2 * index;
                    } else break;
                }
            }



            return fuck;
        }

        public void delete1(int x) {
            heap[x].delete=true;
//            node temp = heap[size];
//            node fuck = new node();
//            fuck = heap[x];
//            int tb = heap[size].position;
//            heap[size].position=fuck.position;
//            fuck.position=tb;
//            size--;
//
//            heap[x] = temp;
//            int index = x;
//            while (2 * index <= size) {//有儿子
//                if (2 * index + 1 <= size) {//有右儿子
//                    if (heap[2 * index + 1].value < heap[2 * index].value||(heap[2 * index + 1].value == heap[2 * index].value&&heap[2 * index + 1].second <= heap[2 * index].second)) {//右儿子小于左儿子
//                        if (heap[index].value > heap[2 * index + 1].value||(heap[index].value == heap[2 * index + 1].value)&&heap[index].second >= heap[2 * index + 1].second) {//儿子<父亲
//                            node temp1 = heap[2 * index + 1];
//                            int ta=heap[index].position;
////                            int tc=heap[index].second;
//                            heap[index].position=heap[2 * index + 1].position;
////                            heap[index].second=heap[2 * index + 1].second;
//                            heap[2 * index + 1].position=ta;
////                            heap[2 * index + 1].second=tc;
//                            heap[2 * index + 1] = heap[index];
//                            heap[index] = temp1;
//                            index = 2 * index + 1;
//                        } else break;
//                    } else {
//                        if (heap[index].value > heap[2 * index].value||(heap[index].value == heap[2 * index ].value)&&heap[index].second >= heap[2 * index ].second) {//儿子<父亲
//                            node temp1 = heap[2 * index];
//                            int ta=heap[index].position;
//
////                            int tc=heap[index].second;
//                            heap[index].position=heap[2 * index ].position;
////                            heap[index].second=heap[2 * index ].second;
//                            heap[2 * index ].position=ta;
////                            heap[2 * index ].second=tc;
//                            heap[2 * index] = heap[index];
//                            heap[index] = temp1;
//                            index = 2 * index;
//                        } else break;
//                    }
//                } else {//没右儿子
//                    if (heap[index].value > heap[2 * index].value||(heap[index].value == heap[2 * index ].value)&&heap[index].second >= heap[2 * index ].second) {//儿子<父亲
//                        node temp1 = heap[2 * index];
//                        int ta=heap[index].position;
////                        int tc=heap[index].second;
//                        heap[index].position=heap[2 * index ].position;
////                        heap[index].second=heap[2 * index ].second;
//                        heap[2 * index ].position=ta;
////                        heap[2 * index ].second=tc;
//                        heap[2 * index] = heap[index];
//                        heap[index] = temp1;
//                        index = 2 * index;
//                    } else break;
//                }
//            }

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

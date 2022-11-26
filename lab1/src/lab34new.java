import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class lab34new {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] previous = new long[n];
        long[] a1 = {-1};
        long[] a2 = {-1};
        long[] a3 = {-1};
        node head = new node(Integer.MIN_VALUE, a2, a3, -1);
        for (int a = 0; a < n; a++) {
            previous[a] = in.nextLong();
        }//赋值
        int big = (int) Math.ceil(Math.pow(80000, 0.5));
        int length = (int) Math.ceil((double) 80000 / big);
        int cont1 = 0;
        int sb = 0;
        long[] temp22 = new long[big];
        long[] temp33 = new long[big * length];
        node cure = head;
        node cure1 = head;
        int cur = 0;
        for (int a = 0; a < n; a++) {
            cont1++;
            if (a % length == 0) {
                cure.setAlive(temp33);
                cure.setSize(temp22);
                node temple = new node(length, null, null, a / length);
                cure.next = temple;
                temple.previous = cure;
                cure = cure.next;
                cure1 = cure;
            }
            node temple = new node(previous[a], null, null, a % length + 1);
            temple.up = cure1;
            cure1.down = temple;
            cure1 = cure1.down;
            temp22[(int) (previous[a] / length)]++;
            temp33[(int) previous[a]]++;
        }
        cure.setAlive(temp33);
        cure.setSize(temp22);
        node tail = new node(Integer.MIN_VALUE, a2, a3, -1);
        tail.previous = cure;
        cure.next = tail;
        //构建两个前缀和数组
        for (int b = 0; b < m; b++) {
            String order = in.next();
            if (order.equals("Q")) {
                cure = head.next;
                cure1 = head.next;
                int left22 = in.nextInt();
                int right33 = in.nextInt();
                int left = Math.min(left22, right33);
                int right = Math.max(left22, right33);
                int k = in.nextInt();
                long[] mlxg = new long[big];
                long[] uzi = new long[big * length];
                cure = head.next;
                node cure2 = head.next;
                while (left > cure.value) {
                    left -= cure.value;
                    cure = cure.next;
                }
                while (right > cure2.value) {
                    right -= cure2.value;
                    cure2 = cure2.next;
                }
                //左边和右边在一个块里面
                if (cure.order == cure2.order) {
                    cure = head.next;
                    cure1 = cure;
                    long max2 = 0;
                    int p = 0;
                    long[] xiaohu = new long[right - left + 1];
                    for (int a = 0; a <= left; a++) {
                        cure1 = cure1.down;
                    }
                    for (int a = left + 1; a <= right; a++) {
                        if (max2 < cure1.value) {
                            max2 = cure1.value;
                        }
                        xiaohu[p] = cure1.value;
                        cure1 = cure1.down;
                        p++;
                    }
                    int big1 = (int) Math.ceil(Math.pow(max2, 0.5));
                    int length1 = (int) Math.ceil((double) max2 / big1);
                    long[] gala = new long[big1];
                    long[] ming = new long[(int) max2 + 1];
                    for (int c = 0; c <= right - left; c++) {
                        ming[(int) xiaohu[c]]++;
                        gala[(int) (xiaohu[c] / length1)]++;
                    }
                    long answer = search(gala, ming, k, length1);
                    out.println(answer);
                }
                //左边和右边不在一个块里面
                else {
                    cure1 = cure;
                    node cure3 = cure2;
                    for (int a = 0; a < left; a++) {
                        cure1 = cure1.down;
                    }
                    for (int a = left + 1; a <= cure.value; a++) {
                        uzi[(int) cure1.value]++;
                        mlxg[(int) (cure1.value / length)]++;
                        cure1 = cure1.down;
                    }
                    for (int a = 0; a < right; a++) {
                        cure3 = cure3.down;
                        uzi[(int) cure3.value]++;
                        mlxg[(int) (cure3.value / length)]++;
                    }
                    if (cure.next.order != cure2.order) {
                        for (int a = 0; a < mlxg.length; a++) {
                            mlxg[a] += cure2.previous.size[a] - cure.size[a];
                        }
                        for (int a = 0; a < uzi.length; a++) {
                            uzi[a] += cure2.previous.alive[a] - cure.alive[a];
                        }
                    }
                    long answer = search(mlxg, uzi, k, length);
                    out.println(answer);
                }
            } else if (order.equals("M")) {
                int where = in.nextInt();
                int value = in.nextInt();
                cure = head.next;
                while (where > cure.value) {
                    where -= cure.value;
                    cure = cure.next;
                }
                cure1 = cure;
                for (int a = 0; a < where; a++) {
                    cure1 = cure1.down;
                }
                int igang = (int) cure1.value;
                cure1.value = value;
                while (cure.order != -1) {
                    cure.alive[igang]--;
                    cure.alive[value]++;
                    cure.size[igang / length]--;
                    cure.size[value / length]++;
                    cure = cure.next;
                }
            } else if (order.equals("I")) {
                int where = in.nextInt();
                int value = in.nextInt();
                cure = head.next;
                while (where > cure.value) {
                    where -= cure.value;
                    cure = cure.next;
                }
                if (cure.value + 1 < 2L * length) {
                    cure1 = cure;
                    cure.setValue(cure.value + 1);
                    for (int a = 0; a < where; a++) {
                        cure1 = cure1.down;
                    }
                    node temple = new node(value, null, null, cure.value);
                    temple.down = cure1;
                    cure1.up.down = temple;
                    temple.up = cure1.up;
                    cure1.up = temple;
                    while (cure.order != -1) {
                        cure.alive[value]++;
                        cure.size[value / length]++;
                        cure = cure.next;
                    }
                } else {
                    cure1 = cure;
                    cure.setValue(length);
                    int cont8 = 0;
                    int cont9 = 0;
                    node cure2 = cure1;
                    node cure3 = cure1;
                    for (int a = 0; a < where; a++) {
                        cure1 = cure1.down;
                        cont9++;
                        if (a < length) {
                            cure2 = cure2.down;
                            cont8++;
                        }
                    }
                    node temple = new node(value, null, null, cure.value);
                    temple.down = cure1;
                    cure1.up.down = temple;
                    temple.up = cure1.up;
                    cure1.up = temple;
                    node new1 = new node(length, cure.size, cure.alive, -cure.order - 1010010);
                    if (cont9 == cont8) {
                        cont8++;
                    }
                    while (cont8 < length) {
                        cure2 = cure2.down;
                        cont8++;
                    }
                    new1.down = cure2.down;
                    cure2.down.up = new1;
                    while (cure3.order != -1) {
                        cure3.alive[value]++;
                        cure3.size[value / length]++;
                    }
                    cure1 = cure.down;
                    for (int c = 0; c < length; c++) {
                        new1.alive[(int) cure1.value]--;
                        new1.size[(int) cure1.value / length]--;
                        cure1 = cure1.down;
                    }
                    cure.next.previous = new1;
                    new1.next = cure.next;
                    cure.next = new1;
                    new1.previous = cure;
                }//插入
            }
        }
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }

    public static long search(long[] gala, long[] ming, long k, int length1) {
        int cont4 = 0;
        long answer = 0;
        for (int d = 0; d < gala.length; d++) {
            if (gala[d] < k) {
                k = (int) (k - gala[d]);
                cont4++;
            } else {
                break;
            }
        }
        for (int e = 0; e < length1; e++) {
            if (ming[(length1 * cont4) + e] != 0) {
                answer = (long) length1 * cont4 + e;
                k -= ming[(length1 * cont4) + e];
                if (k <= 0) {
                    break;
                }
            }
        }
        return answer;
    }

}

class node {
    long value;
    long[] size;
    long[] alive;
    long order;
    node next;
    node previous;
    node up;
    node down;

    public void setValue(long value) {
        this.value = value;
    }

    public void setSize(long[] size) {
        this.size = size;
    }

    public void setAlive(long[] alive) {
        this.alive = alive;
    }

    public node(long value, long[] size, long[] alive, long order) {
        this.value = value;
        this.size = size;
        this.alive = alive;
        this.order = order;
    }
}

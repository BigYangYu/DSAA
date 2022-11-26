//import java.io.*;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.StringTokenizer;
//
//public class lab34 {
//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = System.in;
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        long[] previous = new long[n];
//        long[] a1 = {-1};
//        long[] a2 = {-1};
//        long[] a3 = {-1};
//        node head = new node(a1, a2, a3, -1);
//        for (int a = 0; a < n; a++) {
//            previous[a] = in.nextLong();
//        }//赋值
//        //找到最大值
//        int big = (int) Math.ceil(Math.pow(80000, 0.5));
//        int length = (int) Math.ceil((double)80000 / big);
//        int cont1 = 0;
//        long[] temp11 = new long[length];
//        long[] temp22 = new long[big];
//        long[] temp33 = new long[80000];
//        node cure = head;
//        int cur = 0;
//        for (int a = 0; a < n; a++) {
//            cont1++;
//            cur = cont1 / length;
//            if (cur != 0) {
//                node temple = new node(temp11, temp22, temp33, cur);
//                cure.next = temple;
//                temple.previous = cure;
//                cure = cure.next;
//            }
//            temp11[a % length] = previous[a];
//            temp22[(int) (previous[a] / length)]++;
//            temp33[(int) previous[a]]++;
//        }
//        node temple = new node(temp11, temp22, temp33, cur + 1);
//        cure.next = temple;
//        temple.previous = cure;
//        cure = cure.next;
//        node tail = new node(a1, a2, a3, -1);
//        tail.previous = cure;
//        cure.next = tail;
//        //构建两个前缀和数组
//        for (int b = 0; b < m; b++) {
//            String order =in.next();
//            if (order.equals("Q")) {
//                int left22 = in.nextInt();
//                int right33 = in.nextInt();
//                int left = Math.min(left22,right33);
//                int right = Math.max(left22,right33);
//                int k = in.nextInt();
//                cure = head.next;
//                node cure1 = head.next;
//                while (left > cure.list.length) {
//                    left -= cure.list.length;
//                    cure = cure.next;
//                }
//                while (right > cure.list.length) {
//                    right -= cure.list.length;
//                    cure1 = cure1.next;
//                }
//                if (cure.order==cure1.order) {
//                    long max2 = 0;
//                    int p = 0;
//                    long[] xiaohu = new long[right - left+1];
//                    for (int c = left - 1; c < right ; c++) {
//                        if (max2 < cure.list[c]) {
//                            max2 = cure.list[c];
//                        }
//                        xiaohu[p] = cure.list[c];
//                        p++;
//                    }
//                    int big1 = (int) Math.ceil(Math.pow(max2,0.5));
//                    int length1 = (int) Math.ceil((double) max2 / big1);
//                    long[] gala = new long[big1];
//                    long[] ming = new long[(int) max2 + 1];
//                    for (int c = 0; c <= right-left; c++) {
//                        ming[(int) xiaohu[c]]++;
//                        gala[(int) (xiaohu[c] / length1)]++;
//                    }
//                    long answer = search(gala, ming, k, length1);
//                    out.println(answer);
//
//                }//左边和右边在一个块里面
//                else {
//                    long[] mlxg = new long[big];
//                    long[] uzi = new long[80000];
//                    cure = head.next;
//                    node cure2 = head.next;
//                    while (left > cure.list.length) {
//                        left -= cure.list.length;
//                        cure = cure.next;
//                    }
//                    while (right > cure2.list.length) {
//                        right -= cure2.list.length;
//                        cure2 = cure2.next;
//                    }
//
//                    for (int a = left - 1; a < length; a++) {
//                        uzi[(int) cure.list[a]]++;
//                        mlxg[(int) (cure.list[a] / length)]++;
//                    }
//                    for (int a = 0; a < right; a++) {
//                        uzi[(int) cure2.list[a]]++;
//                        mlxg[(int) (cure2.list[a] / length)]++;
//                    }
//                    if (cure.next.order != cure2.previous.order) {
//                        for (int a = 0; a < mlxg.length; a++) {
//                            mlxg[a] += cure2.previous.size[a] - cure.next.size[a];
//                        }
//                        for (int a = 0; a < uzi.length; a++) {
//                            uzi[a] += cure2.previous.alive[a] - cure.next.alive[a];
//                        }
//                    }
//                    long answer = search(mlxg, uzi, k, length);
//                    out.println(answer);
//                }//左边和右边不在一个块里面
//            } else if (order.equals("M")) {
//                int where = in.nextInt();
//                int value = in.nextInt();
//                cure = head.next;
//                while (where > cure.list.length) {
//                    where -= cure.list.length;
//                    cure = cure.next;
//                }
//                int igang = (int) cure.list[where - 1];
//                cure.list[where - 1] = value;
//                while (cure.order != -1) {
//                    cure.alive[igang]--;
//                    cure.alive[value]++;
//                    cure.size[igang / length]--;
//                    cure.size[value / length]++;
//                    cure=cure.next;
//                }
//            } else if (order.equals("I")) {
//                int where = in.nextInt();
//                int value = in.nextInt();
//                cure = head.next;
//                while (where > cure.list.length) {
//                    where -= cure.list.length;
//                    cure = cure.next;
//                }
//                if (cure.list.length + 1 < 2 * length) {
//                    long[] temple2 = new long[cure.list.length + 1];
//                    for (int a = 0; a < cure.list.length + 1; a++) {
//                        if (a < where - 1) {
//                            temple2[a] = cure.list[a];
//                        } else if (a == where - 1) {
//                            temple2[a] = value;
//                        } else {
//                            temple2[a] = cure.list[a - 1];
//                        }
//                    }
//                    cure.setList(temple2);
//                    while (cure.order != -1) {
//                        cure.alive[value]++;
//                        cure.size[value / length]++;
//                        cure=cure.next;
//                    }
//                } else {
//                    long[] temple2 = new long[length];
//                    long[] temple3 = new long[length];
//                    for (int a = 0; a < cure.list.length + 1; a++) {
//                        if (a < where - 1 && a <= length - 1) {
//                            temple2[a] = cure.list[a];
//                        } else if (a < where - 1 && a > length - 1) {
//                            temple3[a - length] = cure.list[a];
//                        } else if (a == where - 1 && a <= length - 1) {
//                            temple2[a] = value;
//                        } else if (a == where - 1 && a > length - 1) {
//                            temple3[a - length] = value;
//                        } else if (a > where - 1 && a <= length - 1) {
//                            temple2[a] = cure.list[a - 1];
//                        } else if (a > where - 1 && a > length - 1) {
//                            temple3[a - length] = cure.list[a - 1];
//                        }
//                    }
//                    node mid = cure;
//                    while (cure.order != -1) {
//                        cure.alive[value]++;
//                        cure.size[value / length]++;
//                    }
//                    cure = mid;
//                    node new1 = new node(temple2, cure.size, cure.alive, cure.order);
//                    node new2 = new node(temple3, cure.size, cure.alive, 900000 - cure.order);
//                    for (int c = 0; c < length; c++) {
//                        new1.alive[(int) new2.list[c]]--;
//                        new1.size[(int) new2.list[c] / length]--;
//                    }
//                    new1.next = new2;
//                    new2.previous = new1;
//                    new2.next = cure.next;
//                    cure.next.previous = new2;
//                    cure.previous.next = new1;
//                    new1.previous = cure.previous;
//                }//插入
//            }
//
//        }
//
//        out.close();
//    }
//    static class InputReader {
//        public BufferedReader reader;
//        public StringTokenizer tokenizer;
//
//        public InputReader(InputStream stream) {
//            reader = new BufferedReader(new InputStreamReader(stream), 32768);
//            tokenizer = null;
//        }
//
//        public String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return tokenizer.nextToken();
//        }
//
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public char[] nextCharArray() {
//            return next().toCharArray();
//        }
//
//        //         public boolean hasNext() {
////             try {
////                 return reader.ready();
////             } catch(IOException e) {
////                 throw new RuntimeException(e);
////             }
////         }
//        public boolean hasNext() {
//            try {
//                String string = reader.readLine();
//                if (string == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(string);
//                return tokenizer.hasMoreTokens();
//            } catch (IOException e) {
//                return false;
//            }
//        }
//
//        public BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
//
//        public BigDecimal nextBigDecinal() {
//            return new BigDecimal(next());
//        }
//    }
//
//    public static long search(long[] gala, long[] ming, long k, int length1) {
//        int cont4 = 0;
//        long answer = 0;
//        for (int d = 0; d < gala.length; d++) {
//            if (gala[d] <k) {
//                k = (int) (k - gala[d]);
//                cont4++;
//            }
//            else {
//                break;
//            }
//        }
//        cont4--;
//        cont4--;
//        for (int e = 0; e < length1; e++) {
//            if (ming[(length1 * cont4) + e] != 0) {
//                answer = (long) length1 * cont4 + e;
//                k -= ming[(length1 * cont4) + e];
//                if (k <= 0) {
//                    break;
//                }
//            }
//        }
//        return answer;
//    }
//
//}
//
//class node {
//    long[] list;
//    long[] size;
//    long[] alive;
//    long order;
//    node next;
//    node previous;
//
//    public void setList(long[] list) {
//        this.list = list;
//    }
//
//    public void setSize(long[] size) {
//        this.size = size;
//    }
//
//    public void setAlive(long[] alive) {
//        this.alive = alive;
//    }
//
//    public void setOrder(long order) {
//        this.order = order;
//    }
//
//    public node(long[] list, long[] size, long[] alive, long order) {
//        this.list = list;
//        this.size = size;
//        this.alive = alive;
//        this.order = order;
//    }
//}

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class lab51 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader input = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int number = input.nextInt();
        long[][] fuck = new long[number][300];
        for (int a = 0; a < number; a++) {
            fuck[a][0] = 0;
        }
        for (int a = 0; a < number; a++) {
            int shu = 2;
            int number1 = 0;
            String text = input.next();
            int many = input.nextInt();
            String[] temple = new String[many];
            long[][] order = new long[many][2];
            for (int b = 0; b < many; b++) {
                temple[b] = input.next();
                order[b][1] = temple[b].length();
                order[b][0] = b;
            }
            MergeSort(order, temple.length, 0);
            int cont11 = 0;
            int cont12 = 0;
            int cont2 = 0;
            boolean xixi = false;
            here:
            while (cont2 != text.length()) {
                if (cont11 == 0) {
                    boolean sd = true;
                    here4:
                    for (int c = 0; c < many; c++) {

                        for (int d = 0; d < order[many - 1 - c][1]; d++) {
                            if ( order[many - 1 - c][1] > text.length()) {
                                sd = false;
                                break;
                            }
                            if (text.charAt(d) != temple[(int) order[many - 1 - c][0]].charAt(d)) {
                                sd = false;
                                break;
                            }
                            if (d == order[many - 1 - c][1] - 1) {
                                sd = true;
                            }
                        }
                        if (sd) {
                            number1++;
                            xixi = true;
                            fuck[a][shu] = (int) order[many - 1 - c][0] + 1;
                            shu++;
                            fuck[a][shu] = 1;
                            shu++;
                            cont11++;
                            cont12 += order[many - 1 - c][1];
                            cont2 = cont12;
                            break here4;
                        }
                    }
                    if (!xixi) {
                        fuck[a][0] = 0;
                        break here;
                    }
                } else {
                    int compare1 = 0;
                    int compare2 = 0;
                    int compare3 = 0;
                    boolean haha = false;
                    boolean sd = true;
                    for (int e = cont11; e <=cont12; e++) {
                        for (int c = 0; c < many; c++) {
                            for (int d = e; d < e + order[many - 1 - c][1]; d++) {
                                if (e + order[many - 1 - c][1] > text.length()) {
                                    sd = false;
                                    break;
                                }
                                if (text.charAt(d) != temple[(int) order[many - 1 - c][0]].charAt(d - e)) {
                                    sd = false;
                                    break;
                                }
                                if (d == order[many - 1 - c][1] - 1+e) {
                                    sd = true;
                                }
                            }
                            if (sd) {
                                haha = true;
                                if ((int) order[many - 1 - c][1] + e > compare1 + compare2) {
                                    compare1 = e;
                                    compare2 = (int) order[many - 1 - c][1];
                                    compare3 = (int) order[many - 1 - c][0] + 1;
                                }
                            }
                        }
                    }
                    if (!haha) {
                        fuck[a][0] = 0;
                        break here;
                    } else {
                        number1++;
                        fuck[a][shu] = compare3;
                        shu++;
                        fuck[a][shu] = compare1 + 1;
                        shu++;
                        cont11 = compare1 + 1;
                        cont12 = compare1+compare2;
                        cont2 = cont12;
                    }
                }
                fuck[a][0] = 1;
                fuck[a][1] = number1;
            }

        }

        for (int a = 0; a < number; a++) {
            if (fuck[a][0] == 0) {
                out.println("-1");
            } else {
                out.println(fuck[a][1]);
                for (int b = 0; b < fuck[a][1] * 2; b += 2) {
                    out.print(fuck[a][2 + b] + " ");
                    out.print(fuck[a][3 + b]);
                    out.println();
                }
            }
        }
        out.close();
    }

    private static long MergeSort(long[][] igang, int n, long ikun) {
        if (n > 1) {
            int middle = n / 2;
            long[][] son1 = new long[middle][2];
            long[][] son2 = new long[n - middle][2];
            for (int a = 0; a <= middle - 1; a++) {
                son1[a][0] = igang[a][0];
                son1[a][1] = igang[a][1];
            }
            for (int a = 0; a <= n - middle - 1; a++) {
                son2[a][0] = igang[a + middle][0];
                son2[a][1] = igang[a + middle][1];
            }
            ikun = MergeSort(son1, middle, ikun) + MergeSort(son2, n - middle, ikun);
            int left = son1.length;
            int right = n - son1.length;
            long[][] temp = new long[left + right][2];
            int cont2 = 0;
            int cont3 = 0;
            for (int cont1 = 0; cont1 < left + right; cont1++) {
                if (cont2 <= left - 1 && (cont3 > right - 1 || son1[cont2][1] <= son2[cont3][1])) {
                    temp[cont1][0] = son1[cont2][0];
                    temp[cont1][1] = son1[cont2][1];
                    cont2++;
                } else {
                    temp[cont1][0] = son2[cont3][0];
                    temp[cont1][1] = son2[cont3][1];
                    ikun += left - cont2;
                    cont3++;

                }
            }
            for (int a = 0; a < igang.length; a++) {
                igang[a][0] = temp[a][0];
                igang[a][1] = temp[a][1];
            }
        }
        return ikun;
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
//    public static int map(char a) {
//        if (a == 'a') {
//            return 1;
//        } else if (a == 'b') {
//            return 2;
//        } else if (a == 'c') {
//            return 3;
//        } else if (a == 'd') {
//            return 4;
//        } else if (a == 'e') {
//            return 5;
//        } else if (a == 'f') {
//            return 6;
//        } else if (a == 'g') {
//            return 7;
//        } else if (a == 'h') {
//            return 8;
//        } else if (a == 'i') {
//            return 9;
//        } else if (a == 'j') {
//            return 10;
//        } else if (a == 'k') {
//            return 11;
//        } else if (a == 'l') {
//            return 12;
//        } else if (a == 'm') {
//            return 13;
//        } else if (a == 'n') {
//            return 14;
//        } else if (a == 'o') {
//            return 15;
//        } else if (a == 'p') {
//            return 16;
//        } else if (a == 'q') {
//            return 17;
//        } else if (a == 'r') {
//            return 18;
//        } else if (a == 's') {
//            return 19;
//        } else if (a == 't') {
//            return 20;
//        } else if (a == 'u') {
//            return 21;
//        } else if (a == 'v') {
//            return 22;
//        } else if (a == 'w') {
//            return 23;
//        } else if (a == 'x') {
//            return 24;
//        } else if (a == 'y') {
//            return 25;
//        } else {
//            return 26;
//        }
//    }
}
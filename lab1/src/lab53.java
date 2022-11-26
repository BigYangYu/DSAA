import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class lab53 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        String text = in.next();
        int number = text.length();
        int cont = 0;
        int[][] transition = new int[number][26];
            for (int b=0;b<26;b++){
                transition[0][b]=0;
            }
        transition[0][map(text.charAt(0))]=1;
        for (int a = 1; a < number ; a++) {
        for (int b=0;b<26;b++){
            if (map(text.charAt(a))==b){
                transition[a][b]=a+1;
            }
            else {
                transition[a][b]=transition[cont][b];
            }

        }
            cont=transition[cont][map(text.charAt(a))];
        }
        for (int a =0;a<number;a++){
            for (int b=0;b<26;b++){
               out.print(transition[a][b]+" ");
            }
            out.println();
        }
        out.close();
    }
    public static int map(char a) {
        if (a == 'a') {
            return 0;
        } else if (a == 'b') {
            return 1;
        } else if (a == 'c') {
            return 2;
        } else if (a == 'd') {
            return 3;
        } else if (a == 'e') {
            return 4;
        } else if (a == 'f') {
            return 5;
        } else if (a == 'g') {
            return 6;
        } else if (a == 'h') {
            return 7;
        } else if (a == 'i') {
            return 8;
        } else if (a == 'j') {
            return 9;
        } else if (a == 'k') {
            return 10;
        } else if (a == 'l') {
            return 11;
        } else if (a == 'm') {
            return 12;
        } else if (a == 'n') {
            return 13;
        } else if (a == 'o') {
            return 14;
        } else if (a == 'p') {
            return 15;
        } else if (a == 'q') {
            return 16;
        } else if (a == 'r') {
            return 17;
        } else if (a == 's') {
            return 18;
        } else if (a == 't') {
            return 19;
        } else if (a == 'u') {
            return 20;
        } else if (a == 'v') {
            return 21;
        } else if (a == 'w') {
            return 22;
        } else if (a == 'x') {
            return 23;
        } else if (a == 'y') {
            return 24;
        } else {
            return 25;
        }
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
}

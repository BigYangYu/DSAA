import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Lab5_Long_Long_Road {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        String A = in.next();
        String B = in.next();
        char[] Av = new char[2*n - 2];
        for (int a = n-2; a >= 0; a--) {
            if (A.charAt(n-2-a) == 'S') {
                Av[a] = 'N';
            } else if (A.charAt(n-2-a) == 'N') {
                Av[a] = 'S';
            } else if (A.charAt(n-2-a) == 'W') {
                Av[a] = 'E';
            } else if (A.charAt(n-2-a) == 'E') {
                Av[a] = 'W';
            }
        }
        for (int a = n-1;a<Av.length;a++){
            if (B.charAt(a-(n-1)) == 'S') {
                Av[a] = 'S';
            } else if (B.charAt(a-(n-1)) == 'N') {
                Av[a] = 'N';
            } else if (B.charAt(a-(n-1)) == 'W') {
                Av[a] = 'W';
            } else if (B.charAt(a-(n-1)) == 'E') {
                Av[a] = 'E';
            }
        }
        long[] m =new long[2*n-2];
        m[0]=0;
        long k = 0;
        for (int a =1;a< m.length;a++){
            while (k>0&&Av[(int) k]!=Av[a]){
                k=m[(int) k-1];
            }
            if (Av[(int) k]==Av[a]){
                k=k+1;
            }
            m[a]=k;
        }

           if (m[m.length-1]!=0){
               out.println( "NO");
               out.close();
               return;
           }

       out.println("YES");
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
}

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.Arrays;
public class lab52 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        String A = in.next();
        String B = in.next();
        int length = Math.min(A.length(), B.length());
        int answer1 = -1;
        int answer2 = -1;
        if (length / 2 > 0) {
            int[] even = new int[length / 2];
            int cont2 = 2;
            for (int a = 0; a < even.length; a++) {
                even[a] = cont2;
                cont2 += 2;
            }
            answer1 = ShangJie(even, A, B);
            if (answer1 >= 0) {
                answer1 = even[answer1];
            } else {
                answer1 = -1;
            }
        }
        int[] odd = new int[length - length / 2];
        int cont1 = 1;
        for (int a = 0; a < odd.length; a++) {
            odd[a] = cont1;
            cont1 += 2;
        }
        answer2 = ShangJie(odd, A, B);
        if (answer2 >= 0) {
            answer2 = odd[answer2];
        } else {
            answer2 = -1;
        }
        out.println(Math.max(answer1, answer2));
        out.close();

    }

    public static boolean check(String A, String B, int d, int q) {
        int cont = 0;
        int al = A.length();
        int bl = B.length();
        int ka = al - d + 1;
        int kb = bl - d + 1;
        long h=1;
        for (int a =1;a<=d-1;a++){
            h*=q;
        }
        long[] mapa1 = new long[ka];
        long[] mapa2 = new long[ka];
        long[] huiwen = new long[ka];
        long[] mapb = new long[kb];
        long c1 = 0;
        long c2 = 0;
        for (int a = 0; a <d; a++) {
            c1 = q * c1 + (A.charAt(a));
        }
        for (int  a = 0; a <d; a++) {
            c2 = q * c2 + (A.charAt(al - 1 - a));
        }
        mapa1[0] = c1;
        mapa2[ka - 1] = c2;
        int cont1 = 1;
        int cont2 = ka - 2;
        for (int i = d; i < al; i++) {
            mapa1[cont1] = q * (mapa1[cont1 - 1] - (A.charAt(i - d)) * h) + (A.charAt(i));
            cont1++;
        }
        for (int i = d; i < al; i++) {
            mapa2[cont2] = q * (mapa2[cont2 + 1] - (A.charAt(al - 1 - (i - d))) * h) + (A.charAt(al - 1 - i));
            cont2--;

        }
        for (int f = 0; f < ka; f++) {
            if (mapa1[f] == mapa2[f]) {
                huiwen[cont] = mapa1[f];
                cont++;
            }
        }
        c1 = 0;
        for (int a = 0; a < d; a++) {
            c1 = q * c1 + (B.charAt(a));
        }
        mapb[0] = c1;
        cont1 = 1;
        for (int f = 0; f < cont ; f++) {
            if (huiwen[f] == mapb[0]) {
                return true;
            }
        }
        for (int i = d; i < bl; i++) {
            mapb[cont1] = q * (mapb[cont1 - 1] - (B.charAt(i - d)) * h) + (B.charAt(i));
            cont1++;
        }
        Arrays.sort(mapb);
        for (int c=0;c<cont;c++){
           if (Arrays.binarySearch(mapb,huiwen[c])>=0){
               return true;
           }
        }
        int g = 0;
        return false;
    }



    private static int ShangJie(int[] question, String A, String B) {
        int l = 0;
        int a = question.length - 1;
        while (l <= a) {
            int mid = (l + a) / 2;
            if (check(A, B, question[mid], 31)) {
                l = mid + 1;
            } else {
                a = mid - 1;
            }
        }
        return a;
    }
//    private static boolean search(long[] huiwen, long[] mapb, int cont) {
//        int l = 0;
//        int a = mapb.length-1;
//        for (int c=0;c<cont;c++){
//            while (l <= a) {
//                int mid = (l + a) / 2;
//                if (huiwen[c]>mapb[mid]) {
//                    l = mid + 1;
//                } else if (huiwen[c]<mapb[mid]){
//                    a = mid - 1;
//                }
//                else {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
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

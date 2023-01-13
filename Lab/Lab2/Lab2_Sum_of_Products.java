
import java.util.Scanner;

public class Lab2_Sum_of_Products {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long number = input.nextLong();
        long[] igang = new long[(int) number];
        for (int a = 0; a < number; a++) {
            igang[a] = input.nextLong();
        }
        MergeSort(igang, (int) number);
        long c = 0;
        for (int a = 0, b = (int) (number - 1); a < b; a++, b--) {
            c = c + igang[a] * igang[b];
        }
        System.out.println(c);

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

}

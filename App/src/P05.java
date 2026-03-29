import java.util.*;

public class P05 {

    /* ========== LINEAR SEARCH ========== */
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        if (first == -1) {
            System.out.println("\nLinear: Not found (" + comparisons + " comparisons)");
        } else {
            System.out.println("\nLinear first " + target + ": index " + first +
                    " (" + comparisons + " comparisons)");
            System.out.println("Linear last " + target + ": index " + last);
        }
    }

    /* ========== BINARY SEARCH (FIRST OCCURRENCE) ========== */
    public static int binarySearchFirst(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // go left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    /* ========== BINARY SEARCH (LAST OCCURRENCE) ========== */
    public static int binarySearchLast(String[] arr, String target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // go right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    /* ========== MAIN ========== */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        String target = sc.next();

        // Sort for binary search
        Arrays.sort(arr);

        System.out.print("Sorted logs: ");
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Linear Search
        linearSearch(arr, target);

        // Binary Search
        int[] comp = {0};

        int first = binarySearchFirst(arr, target, comp);
        int last = binarySearchLast(arr, target, comp);

        if (first == -1) {
            System.out.println("\nBinary: Not found (" + comp[0] + " comparisons)");
        } else {
            int count = last - first + 1;
            System.out.println("\nBinary " + target + ": index " + first +
                    " (" + comp[0] + " comparisons), count=" + count);
        }

        sc.close();
    }
}
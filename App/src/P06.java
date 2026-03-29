import java.util.*;

public class P06 {

    /* ========== LINEAR SEARCH ========== */
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Linear: threshold " + target + " found (" + comparisons + " comps)");
        } else {
            System.out.println("Linear: threshold " + target + " -> not found (" + comparisons + " comps)");
        }
    }

    /* ========== FLOOR (largest <= target) ========== */
    public static int findFloor(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    /* ========== CEILING (smallest >= target) ========== */
    public static int findCeiling(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] > target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    /* ========== INSERTION POINT ========== */
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /* ========== MAIN ========== */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        // Sort array (required for binary search)
        Arrays.sort(arr);

        System.out.print("Sorted risks: ");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();

        // Linear Search
        linearSearch(arr, target);

        // Binary Search Floor & Ceiling
        int[] comp = {0};

        int floor = findFloor(arr, target, comp);
        int ceil = findCeiling(arr, target, comp);

        System.out.println("Binary floor(" + target + "): " + floor +
                ", ceiling(" + target + "): " + ceil +
                " (" + comp[0] + " comps)");

        // Insertion point
        int pos = insertionPoint(arr, target);
        System.out.println("Insertion position: " + pos);

        sc.close();
    }
}
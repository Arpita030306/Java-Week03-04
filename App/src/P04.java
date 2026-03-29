import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class P04 {

    /* ================= MERGE SORT (ASC, STABLE) ================= */
    public static void mergeSort(ArrayList<Asset> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    public static void merge(ArrayList<Asset> list, int left, int mid, int right) {
        ArrayList<Asset> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).returnRate <= list.get(j).returnRate) {
                temp.add(list.get(i++)); // stable
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    /* ================= QUICK SORT (DESC + VOL ASC) ================= */

    // Median of 3 pivot selection
    public static int medianOf3(ArrayList<Asset> list, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = list.get(low);
        Asset b = list.get(mid);
        Asset c = list.get(high);

        if ((a.returnRate >= b.returnRate && a.returnRate <= c.returnRate) ||
                (a.returnRate <= b.returnRate && a.returnRate >= c.returnRate))
            return low;

        if ((b.returnRate >= a.returnRate && b.returnRate <= c.returnRate) ||
                (b.returnRate <= a.returnRate && b.returnRate >= c.returnRate))
            return mid;

        return high;
    }

    public static void quickSort(ArrayList<Asset> list, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOf3(list, low, high);
            Collections.swap(list, pivotIndex, high);

            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Asset> list, int low, int high) {
        Asset pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (list.get(j).returnRate > pivot.returnRate ||
                    (list.get(j).returnRate == pivot.returnRate &&
                            list.get(j).volatility < pivot.volatility)) {

                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    /* ================= PRINT ================= */
    public static void printList(ArrayList<Asset> list, String msg) {
        System.out.print(msg + ": ");
        for (Asset a : list) {
            System.out.print(a.name + ":" + a.returnRate + "% ");
        }
        System.out.println();
    }

    /* ================= MAIN ================= */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Asset> list = new ArrayList<>();
        ArrayList<Asset> copy = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            double ret = sc.nextDouble();
            double vol = sc.nextDouble();

            Asset a = new Asset(name, ret, vol);
            list.add(a);
            copy.add(new Asset(name, ret, vol));
        }

        // Merge Sort (ASC)
        mergeSort(list, 0, list.size() - 1);
        printList(list, "Merge");

        // Quick Sort (DESC + volatility ASC)
        quickSort(copy, 0, copy.size() - 1);
        printList(copy, "Quick (desc)");

        sc.close();
    }
}
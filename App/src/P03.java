import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }
}

public class P03 {

    /* ================= MERGE SORT (ASCENDING) ================= */
    public static void mergeSort(ArrayList<Trade> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    public static void merge(ArrayList<Trade> list, int left, int mid, int right) {
        ArrayList<Trade> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).volume <= list.get(j).volume) {
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

    /* ================= QUICK SORT (DESCENDING) ================= */
    public static void quickSort(ArrayList<Trade> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Trade> list, int low, int high) {
        int pivot = list.get(high).volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume > pivot) { // DESC
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    /* ================= MERGE TWO LISTS ================= */
    public static ArrayList<Trade> mergeLists(ArrayList<Trade> a, ArrayList<Trade> b) {
        ArrayList<Trade> merged = new ArrayList<>();
        merged.addAll(a);
        merged.addAll(b);
        return merged;
    }

    /* ================= PRINT ================= */
    public static void printList(ArrayList<Trade> list, String msg) {
        System.out.print(msg + ": ");
        for (Trade t : list) {
            System.out.print(t.id + ":" + t.volume + " ");
        }
        System.out.println();
    }

    /* ================= MAIN ================= */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ArrayList<Trade> list = new ArrayList<>();
        ArrayList<Trade> copy = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String id = sc.next();
            int vol = sc.nextInt();

            Trade t = new Trade(id, vol);
            list.add(t);
            copy.add(new Trade(id, vol));
        }

        // Merge Sort (Ascending)
        mergeSort(list, 0, list.size() - 1);
        printList(list, "MergeSort (asc)");

        // Quick Sort (Descending)
        quickSort(copy, 0, copy.size() - 1);
        printList(copy, "QuickSort (desc)");

        // Merge two lists (example: same list merged for demo)
        ArrayList<Trade> merged = mergeLists(list, copy);

        int total = 0;
        for (Trade t : merged) {
            total += t.volume;
        }

        System.out.println("Merged total volume: " + total);

        sc.close();
    }
}
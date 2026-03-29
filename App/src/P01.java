import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }
}

public class P01 {

    public static void bubbleSort(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nBubble Sort (fees):");
        for (Transaction t : list) {
            System.out.print("[" + t.id + ":" + t.fee + "] ");
        }
        System.out.println("\nPasses: " + passes + ", Swaps: " + swaps);
    }

    public static void insertionSort(ArrayList<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort (fee+timestamp):");
        for (Transaction t : list) {
            System.out.print("[" + t.id + ":" + t.fee + "@" + t.timestamp + "] ");
        }
        System.out.println();
    }

    public static void findOutliers(ArrayList<Transaction> list) {
        boolean found = false;

        System.out.println("\nHigh-fee outliers (>50):");
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.print("[" + t.id + ":" + t.fee + "] ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("none");
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ArrayList<Transaction> list = new ArrayList<>();
        ArrayList<Transaction> copy = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String id = sc.next();
            double fee = sc.nextDouble();
            String ts = sc.next();

            Transaction t = new Transaction(id, fee, ts);
            list.add(t);
            copy.add(new Transaction(id, fee, ts));
        }

        if (n <= 100) {
            bubbleSort(list);
        } else {
            System.out.println("\nBubble Sort skipped (n > 100)");
        }

        if (n <= 1000) {
            insertionSort(copy);
        } else {
            System.out.println("\nInsertion Sort skipped (n > 1000)");
        }

        findOutliers(list);

        sc.close();
    }
}
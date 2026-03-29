import java.util.*;

class Client {
    String name;
    int riskScore;
    int accountBalance;

    Client(String name, int riskScore, int accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }
}

public class P02 {

    // Bubble Sort (Ascending riskScore)
    public static void bubbleSort(ArrayList<Client> list) {
        int n = list.size();
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    Client temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.print("\nBubble (asc): ");
        for (Client c : list) {
            System.out.print(c.name + ":" + c.riskScore + " ");
        }
        System.out.println("// Swaps: " + swaps);
    }

    // Insertion Sort (riskScore DESC + accountBalance ASC)
    public static void insertionSort(ArrayList<Client> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Client key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).riskScore < key.riskScore ||
                            (list.get(j).riskScore == key.riskScore &&
                                    list.get(j).accountBalance > key.accountBalance))) {

                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.print("\nInsertion (desc): ");
        for (Client c : list) {
            System.out.print(c.name + ":" + c.riskScore + " ");
        }
        System.out.println();
    }

    // Top 10 highest risk clients
    public static void topClients(ArrayList<Client> list) {
        System.out.print("\nTop 10 highest risk clients: ");

        int limit = Math.min(10, list.size());
        for (int i = 0; i < limit; i++) {
            Client c = list.get(i);
            System.out.print(c.name + "(" + c.riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Client> list = new ArrayList<>();
        ArrayList<Client> copy = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int risk = sc.nextInt();
            int balance = sc.nextInt();

            Client c = new Client(name, risk, balance);
            list.add(c);
            copy.add(new Client(name, risk, balance));
        }

        bubbleSort(list);        // ascending
        insertionSort(copy);     // descending
        topClients(copy);        // top from descending list

        sc.close();
    }
}
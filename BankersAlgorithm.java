import java.util.Scanner;

public class BankersAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of processes and resources
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        System.out.print("Enter the number of resources: ");
        int r = scanner.nextInt();

        int[][] alloc = new int[n][r];
        int[][] max = new int[n][r];
        int[] avail = new int[r];

        // Input Allocation Matrix
        System.out.println("Enter the Allocation Matrix:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter values for process P" + i + ": ");
            for (int j = 0; j < r; j++) {
                alloc[i][j] = scanner.nextInt();
            }
        }

        // Input MAX Matrix
        System.out.println("Enter the MAX Matrix:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter values for process P" + i + ": ");
            for (int j = 0; j < r; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        // Input Available Resources
        System.out.println("Enter the available resources:");
        for (int i = 0; i < r; i++) {
            avail[i] = scanner.nextInt();
        }

        // Check if the system is in a safe state
        if (isSafeState(alloc, max, avail, n, r)) {
            System.out.println("The system is in a SAFE state.");
        } else {
            System.out.println("The system is in a DEADLOCK state.");
        }

        scanner.close();
    }

    private static boolean isSafeState(int[][] alloc, int[][] max, int[] avail, int n, int r) {
        int[] work = avail.clone();
        boolean[] finish = new boolean[n];

        // Compute the need matrix
        int[][] need = new int[n][r];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < r; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        // Check if a safe sequence exists
        boolean progressMade;
        do {
            progressMade = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < r; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }
                    if (j == r) { // All needs are met
                        // Simulate process completion
                        for (j = 0; j < r; j++) {
                            work[j] += alloc[i][j];
                        }
                        finish[i] = true;
                        progressMade = true;
                    }
                }
            }
        } while (progressMade);

        // Check if all processes are finished
        for (boolean finished : finish) {
            if (!finished) {
                return false; // At least one process is not finished
            }
        }

        return true; // All processes finished
    }
}

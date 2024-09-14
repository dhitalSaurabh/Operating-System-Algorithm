// Program to demonstrate 'Shortest Remmaining Time Next' algorithm.
import java.util.Scanner;

public class SRTNProcessScheduling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int pid[] = new int[n]; // Process id
        int bt[] = new int[n]; // Brust time
        int at[] = new int[n]; // Arrival time
        int rt[] = new int[n]; // Remmaining time
        int wt[] = new int[n]; // Waiting time
        int tat[] = new int[n]; // Turn arround time
        boolean isCompleted[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter the burst time for process " + pid[i] + ": ");
            bt[i] = scanner.nextInt();
            rt[i] = bt[i];
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter the arrival time for process " + pid[i] + ": ");
            at[i] = scanner.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[j] < at[i] ||
                        (at[j] == at[i] && bt[j] < bt[i])) {
                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        int currentTime = 0;
        int completed = 0;
        int totalwt = 0;
        int totaltat = 0;

        while (completed < n) {
            int shortestIndex = -1;
            int shortestrt = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && at[i] <= currentTime &&
                        rt[i] < shortestrt) {
                    shortestrt = rt[i];
                    shortestIndex = i;
                }
            }

            if (shortestIndex == -1) {
                currentTime++;
                continue;
            }

            rt[shortestIndex]--;
            if (rt[shortestIndex] == 0) {
                completed++;
                isCompleted[shortestIndex] = true;
                tat[shortestIndex] = currentTime + 1 - at[shortestIndex];
                wt[shortestIndex] = tat[shortestIndex] - bt[shortestIndex];

                totalwt += wt[shortestIndex];
                totaltat += tat[shortestIndex];
            }

            currentTime++;
        }
        System.out.println("\nProcess\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\n",
                    pid[i], bt[i], at[i], wt[i], tat[i]);
        }
        System.out.printf("\nAverage Waiting Time = %.2f\n", (float) totalwt / n);
        System.out.printf("Average Turnaround Time = %.2f\n", (float) totaltat / n);
        scanner.close();
    }
}

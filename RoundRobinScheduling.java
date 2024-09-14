// Program to demonstrate the 'Round Robin' algorithm.
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobinScheduling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        
        int[] at = new int[n];  // Arrival time
        int[] bt = new int[n];  // Brust time
        int[] rt = new int[n];  // Remmaining time
        int[] wt = new int[n];  // Waiting time
        int[] tat = new int[n]; // Turnaround time
        int[] ct = new int[n]; // Completion time
        boolean[] isCompleted = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the burst time for process " + (i + 1) + ": ");
            bt[i] = scanner.nextInt();
            rt[i] = bt[i];
            
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the arrival time for process " + (i + 1) + ": ");
            at[i] = scanner.nextInt();
        }
        System.out.print("Enter the time quantum: ");
        int timeQuantum = scanner.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0;
        int completedProcesses = 0;
        int totalwt = 0;
        int totaltat = 0;

        for (int i = 0; i < n; i++) {
            if (at[i] <= currentTime) {
                queue.add(i);
            }
        }

        while (completedProcesses < n) {
            Integer processIndex = queue.poll();
            if (processIndex == null) {
                currentTime++;
                for (int i = 0; i < n; i++) {
                    if (at[i] <= currentTime && !queue.contains(i) && !isCompleted[i]) {
                        queue.add(i);
                    }
                }
                continue;
            }

            int timeToRun = Math.min(timeQuantum, rt[processIndex]);
            rt[processIndex] -= timeToRun;
            currentTime += timeToRun;

            if (rt[processIndex] == 0) {
                isCompleted[processIndex] = true;
                completedProcesses++;
                ct[processIndex] = currentTime; // Calculate completion time
                tat[processIndex] = ct[processIndex] - at[processIndex];
                wt[processIndex] = tat[processIndex] - bt[processIndex];

                totalwt += wt[processIndex];
                totaltat += tat[processIndex];
            } else {
                queue.add(processIndex);
            }

            for (int i = 0; i < n; i++) {
                if (at[i] <= currentTime && !queue.contains(i) && !isCompleted[i]) {
                    queue.add(i);
                }
            }
        }

        System.out.println("\nProcess\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time\tCompletion Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",
                              i + 1, bt[i], at[i], wt[i], tat[i], ct[i]);
        }

        System.out.printf("\nAverage Waiting Time = %.2f\n", (float) totalwt / n);
        System.out.printf("Average Turnaround Time = %.2f\n", (float) totaltat / n);

        scanner.close();
    }
}

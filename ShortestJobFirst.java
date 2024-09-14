// Program to demonstrate the 'Shortest Job First' algorithm.
import java.util.Scanner;

public class ShortestJobFirst {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no of processes: ");
        int n = sc.nextInt();
        int at[] = new int[n]; // arrival time
        int bt[] = new int[n]; // brust time
        int ct[] = new int[n]; // completion time
        int StartTime[] = new int[n];
        int wt[] = new int[n]; // wating time
        int tat[] = new int[n]; // turn arround time
        int TotalWaitingTime = 0;
        int Totaltat = 0;
        float AVGWaitingTime;
        float AVGtat;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Brust time for process  " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arival time for process  " + (i + 1) + ": ");
            at[i] = sc.nextInt();
        }

        int time = 0; // "time" for the current time.
        int min = 0;
        int j = 0;
        int count;

        for (int c = 0; c < n; c++) {
            count = 0;
            for (int i = 0; i < n; i++) {
                if (ct[i] == 0) {
                    if (at[i] <= time) {
                        count++;
                        if (count == 1) {
                            min = bt[i];
                            j = i;
                        } else {
                            if (bt[i] < min) {
                                min = bt[i];
                                j = i;
                            }
                        }
                    }
                }
            }
            StartTime[j] = time;
            time += min;
            ct[j] = time;
        }
        for (int y = 0; y < n; y++) {
            wt[y] = StartTime[y] - at[y];
            tat[y] = wt[y] + bt[y];
            TotalWaitingTime += wt[y];
            Totaltat += tat[y];
        }
        AVGWaitingTime = (float) TotalWaitingTime / n;
        AVGtat = (float) Totaltat / n;
        System.out.println("Brust Time\t Arrival Time\t Completion Time\t Waiting Time\t Turnaround Time");

        for (int i = 0; i < n; i++) {
            System.out.println(bt[i] + "\t\t\t" + at[i] + "\t\t" + ct[i] + "\t\t" + wt[i] + "\t\t\t" + tat[i]);
        }
        System.out.println("Average waiting time for processes  " + "  " + AVGWaitingTime);
        System.out.println("Average turn arround time for processes  " + "  " + AVGtat);
        sc.close();
    }
}
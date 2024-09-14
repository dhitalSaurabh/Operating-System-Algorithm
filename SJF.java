import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] bt = new int[20];
        int[] wt = new int[20];
        int[] p = new int[20];
        int[] tat = new int[20];
        int i, j, n = 0, total = 0, pos, temp;
        float avg_wt, avg_tat;
        System.out.println("Enter the number of process: ");
        n = s.nextInt();
        for (i = 0; i < n; i++) {
            System.out.println("P" + i + 1 + ": ");
            bt[i] = s.nextInt();
            p[i] = i + 1;
        }
        for (i = 0; i < n; i++) {
            pos = i;
            for (j = i + 1; j < n; j++) {
                if (bt[j] < bt[pos]) {
                    pos = j;
                }
                temp = bt[i];
                bt[i] = bt[pos];
                bt[pos] = temp;
                temp = p[i];
                p[i] = p[pos];
                p[pos] = temp;
            }
            wt[0] = 0;
            for (i = 1; i < n; i++) {
                wt[i] = 0;
                for (j = 0; j < i; j++)
                    wt[i] += bt[j];
                total += wt[i];
            }
            avg_wt = (float) total / n;
            total = 0;
            System.out.println("\n Process\tBrust Time\t Waiting Time\t Turnaround Time");
            for (i = 0; i < n; i++) {
                tat[i] = bt[i] + wt[i];
                total += tat[i];
                System.out.println("\n" + p[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
            }
            avg_tat = (float) total / n;
            System.out.println("\n\n Average Waiting Time = " + avg_wt);
            System.out.println("Average Turnaround Time = " + avg_tat);
        }
        s.close();
    }
}

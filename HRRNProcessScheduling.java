// Program to demonstrate the 'Highest Response Ratio Next' algorithm.
import java.util.Scanner;

class Process {
    char name;
    int at, bt, ct, wt, tt;
    int completed;
    float ntt;
}

public class HRRNProcessScheduling {

    static void sortByArrival(Process p[], int n) {
        Process temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p[i].at > p[j].at) {
                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        
        Process[] p = new Process[n];

        int[] arriv = new int[n];
        int[] burst = new int[n];
        
        for (int i = 0, c = 'A'; i < n; i++, c++) {
            p[i] = new Process();
            p[i].name = (char) c;
            
            System.out.print("Enter the arrival time for process " + p[i].name + ": ");
            arriv[i] = scanner.nextInt();
            p[i].at = arriv[i];
            
            System.out.print("Enter the burst time for process " + p[i].name + ": ");
            burst[i] = scanner.nextInt();
            p[i].bt = burst[i];
            
            p[i].completed = 0;
        }
        

        sortByArrival(p, n);

        int sum_bt = 0;
        for (int i = 0; i < n; i++) {
            sum_bt += p[i].bt;
        }

        float t = p[0].at;
        float avgwt = 0, avgtt = 0;

        System.out.println("Process Name\tArrival Time\tBrust Time\tWaiting Time\tTurnaround Time");

        for (int completedProcesses = 0; completedProcesses < n; ) {
            float hrr = -1;
            int loc = -1;

            for (int i = 0; i < n; i++) {
                if (p[i].at <= t && p[i].completed != 1) {
                    float temp = (p[i].bt + (t - p[i].at)) / (float) p[i].bt;
                    if (hrr < temp) {
                        hrr = temp;
                        loc = i;
                    }
                }
            }

            if (loc == -1) {
                t++;
                continue;
            }

            t += p[loc].bt;

            p[loc].wt = (int) (t - p[loc].at - p[loc].bt);
            p[loc].tt = (int) (t - p[loc].at);

            avgtt += p[loc].tt;
            p[loc].ntt = ((float) p[loc].tt / p[loc].bt);
            p[loc].completed = 1;
            avgwt += p[loc].wt;

            System.out.println(p[loc].name + "\t\t" + p[loc].at + "\t\t" + p[loc].bt
                    + "\t\t" + p[loc].wt + "\t\t" + p[loc].tt);
            completedProcesses++;
        }
        System.out.println("Average waiting time: " + (avgwt / n));
        System.out.println("Average Turn Around time: " + (avgtt / n));

        scanner.close();
    }
}


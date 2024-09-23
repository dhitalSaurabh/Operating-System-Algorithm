// // Program to demonstrate 'Priority Scheduling' algorithm.
// import java.util.*;
// class Process {
//     int id;
//     int burstTime;
//     int priority;
//     int arrivalTime;
//     int completionTime;
//     int waitingTime;
//     int turnaroundTime;

//     Process(int id, int burstTime, int priority, int arrivalTime) {
//         this.id = id;
//         this.burstTime = burstTime;
//         this.priority = priority;
//         this.arrivalTime = arrivalTime;
//     }
// }

// public class PriorityScheduling {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of processes: ");
//         int n = scanner.nextInt();

//         Process[] processes = new Process[n];
//         int[] burstTimes = new int[n];
//         int[] priorities = new int[n];
//         int[] arrivalTimes = new int[n];

        
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter burst time for process " + (i + 1) + ": ");
//             burstTimes[i] = scanner.nextInt();
//             System.out.print("Enter priority for process " + (i + 1) + ": ");
//             priorities[i] = scanner.nextInt();
//             System.out.print("Enter arrival time for process " + (i + 1) + ": ");
//             arrivalTimes[i] = scanner.nextInt();
//             processes[i] = new Process(i + 1, burstTimes[i], priorities[i], arrivalTimes[i]);
//         }

//         Arrays.sort(processes, (p1, p2) -> {
//             if (p1.priority != p2.priority) {
//                 return Integer.compare(p2.priority, p1.priority); 
//             } else {
//                 return Integer.compare(p1.arrivalTime, p2.arrivalTime); 
//             }
//         });

//         int currentTime = 0;
//         int totalWaitingTime = 0;
//         int totalTurnaroundTime = 0;

//         for (int i = 0; i < n; i++) {
//             Process p = processes[i];

//             if (currentTime < p.arrivalTime) {
//                 currentTime = p.arrivalTime;
//             }

//             p.completionTime = currentTime + p.burstTime;

//             p.turnaroundTime = p.completionTime - p.arrivalTime;

//             p.waitingTime = p.turnaroundTime - p.burstTime;

//             currentTime = p.completionTime;

//             totalWaitingTime += p.waitingTime;
//             totalTurnaroundTime += p.turnaroundTime;
//         }

//         System.out.println(
//                 "\nProcess\tBurst Time\tPriority\tArrival Time\tCompletion Time\tWaiting Time\tTurnaround Time");
//         for (Process p : processes) {
//             System.out.println(p.id + "\t\t" + p.burstTime + "\t\t" + p.priority + "\t\t" + p.arrivalTime + "\t\t"
//                     + p.completionTime + "\t\t" +
//                     p.waitingTime + "\t\t" + p.turnaroundTime);
//         }

//         System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / n);
//         System.out.println("\nAverage Turnaround Time: " + (double) totalTurnaroundTime / n);

//         scanner.close();
//     }
// }

// Program to demonstrate 'Priority Scheduling with non-primption' algorithm.
import java.util.*;

class Scheduling {
    int id;
    int arrivalTime;
    int burstTime;
    int priority;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Scheduling(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Scheduling> processList = new ArrayList<>();
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + ": ");
            int priority = sc.nextInt();
            processList.add(new Scheduling(i + 1, arrivalTime, burstTime, priority));
        }
        processList.sort(Comparator.comparingInt((Scheduling p) -> p.arrivalTime)
                .thenComparingInt(p -> p.priority));

        int currentTime = 0;
        for (Scheduling p : processList) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            currentTime = p.completionTime;
        }

        System.out.println("ID\tArrival\tBurst\tPriority\tCompletion\tTurnaround\tWaiting");
        for (Scheduling p : processList) {
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t\t%d\t\t%d\n", p.id, p.arrivalTime,
                    p.burstTime, p.priority, p.completionTime, p.turnaroundTime, p.waitingTime);
        }

        double totalWaitingTime = processList.stream().mapToInt(p -> p.waitingTime).sum();
        double totalTurnaroundTime = processList.stream().mapToInt(p -> p.turnaroundTime).sum();

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));
        sc.close();
    }
}

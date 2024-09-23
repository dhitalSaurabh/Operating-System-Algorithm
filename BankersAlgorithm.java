// Program to demonstrate 'Bankers Algorithm'.
import java.util.Scanner;

public class BankersAlgorithm {

static int[][] inputMatrix(Scanner scanner, int rows, int cols, String name) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter the " + name + " resources:");
        for (int i = 0; i < rows; i++) {
            System.out.print("Enter p" + (i + 1) + " resource: ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

     static int[] inputVector(Scanner scanner, int size, String name) {
        int[] vector = new int[size];
        System.out.print("Enter the " + name + " resource:  ");
        for (int i = 0; i < size; i++) {
            vector[i] = scanner.nextInt();
        }
        return vector;
    }

     static boolean isSafe(int[] available, int[][] maxMatrix, int[][] allocation) {
        int numProcesses = maxMatrix.length;
        int numResources = available.length;
        
        int[][] need = new int[numProcesses][numResources];
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = maxMatrix[i][j] - allocation[i][j];
            }
        }
        
        int[] work = available.clone();
        boolean[] finish = new boolean[numProcesses];
        StringBuilder safeSequence = new StringBuilder();
        
        int count = 0;
        while (count < numProcesses) {
            boolean progressMade = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i] && canAllocate(need[i], work)) {
                    safeSequence.append("P>").append((i+1) +" ");
                    for (int j = 0; j < numResources; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    progressMade = true;
                    count++;
                    break;
                }
            }
            
            if (!progressMade) {
                return false; 
            }
        }
        
        System.out.println("System is in a safe state.");
        System.out.println("Safe sequence is: " + safeSequence.toString().trim());
        return true;
    }

    private static boolean canAllocate(int[] need, int[] work) {
        for (int i = 0; i < need.length; i++) {
            if (need[i] > work[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int numProcesses = scanner.nextInt();

        System.out.print("Enter number of resources: ");
        int numResources = scanner.nextInt();

        int[][] allocation = inputMatrix(scanner, numProcesses, numResources, "Allocated");
        int[][] maximum = inputMatrix(scanner, numProcesses, numResources, "Maximum");
        int[] available = inputVector(scanner, numResources, "Available");

        if (!isSafe(available, maximum, allocation)) {
            System.out.println("System is not in a safe state.");
        }

        scanner.close();
    }
}

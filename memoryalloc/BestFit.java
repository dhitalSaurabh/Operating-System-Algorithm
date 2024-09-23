// Program to implementation of Best-Fit algorithm of memory allocation. 
package memoryalloc;
import java.util.Scanner;
public class BestFit {
    public static void bestFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        for (int i = 0; i < n; i++) {
            int bestIndex = -1;
            int bestSize = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i] && blockSize[j] < bestSize) {
                    bestIndex = j;
                    bestSize = blockSize[j];
                }
            }
            if (bestIndex != -1) {
                blockSize[bestIndex] -= processSize[i];
                allocation[i] = bestIndex;
            } else {
                allocation[i] = -1;
            }
        }

        
        System.out.println("Process id\t Process size \t Memory Block\t block no");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t"+ blockSize[i]+ "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println();
        }
    }

    // Driver Method
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of blocks:");
        int m = s.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter the block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = s.nextInt();
        }

        System.out.println("Enter the number of processes:");
        int n = s.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter the process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = s.nextInt();
        }

        bestFit(blockSize, m, processSize, n);
        s.close();
    }
}
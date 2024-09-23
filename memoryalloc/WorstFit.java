package memoryalloc;

import java.util.Scanner;
public class WorstFit {
    public static void worstFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];

        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            int maxBlockSize = 0;

            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i] && blockSize[j] > maxBlockSize) {
                    worstIdx = j;
                    maxBlockSize = blockSize[j];
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i) + " is allocated to block " + allocation[i]);
            } else {
                System.out.println("Process " + (i) + " is not allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of blocks: ");
        int m = scan.nextInt();

        int[] blockSize = new int[m];
        System.out.println("Enter the sizes of the blocks:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scan.nextInt();
        }

        System.out.print("Enter the number of processes: ");
        int n = scan.nextInt();

        int[] processSize = new int[n];
        System.out.println("Enter the sizes of the processes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scan.nextInt();
        }

        worstFit(blockSize, m, processSize, n);
        scan.close();
    }
}
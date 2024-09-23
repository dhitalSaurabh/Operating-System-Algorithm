// Program to demonstrate memory allocation with 'First Fit' algorithm.
package memoryalloc;
import java.util.Scanner;
public class firstfit {
    public static void firstFit(int[] blockSize, int m, int[] processSize, int n) {
        for (int i = 0; i < n; i++) {
            boolean allocated = false;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    System.out.println("Process " + (i + 1) + " is allocated to block " + (j + 1));
                    blockSize[j] -= processSize[i];
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                System.out.println("Process " + (i + 1) + " is not allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        for (int i = 0; i < m; i++) {
            System.out.print("Enter block size " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
        }

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process size " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        firstFit(blockSize, m, processSize, n);
        sc.close();
    }
}
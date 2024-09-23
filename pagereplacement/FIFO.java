// Program to demonstrate 'FIFO' page replacement algorithm. 
package pagereplacement;
import java.util.LinkedList;
import java.util.Scanner;

public class FIFO {
    public static void PageReplacement(int[] pages, int numFrames) {
        LinkedList<Integer> frameQueue = new LinkedList<>();
        boolean[] isPageInMemory = new boolean[100]; 
        int pageFaults = 0;

        System.out.println("Page Reference Sequence: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\n");
        for (int page : pages) {
            if (!isPageInMemory[page]) { 
                if (frameQueue.size() == numFrames) {
                    int oldestPage = frameQueue.poll(); 
                    isPageInMemory[oldestPage] = false; 
                }
                frameQueue.add(page); 
                isPageInMemory[page] = true; 
                pageFaults++; 
                System.out.println("Page Fault: " + page + " | Frames: " + frameQueue);
            } else {
                System.out.println("Page Hit: " + page + " | Frames: " + frameQueue);
            }
        }
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();

        System.out.print("Enter the number of pages: ");
        int numPages = scanner.nextInt();

        System.out.print("Enter the page reference string (space-separated): ");
        int[] pages = new int[numPages];
        for (int i = 0; i < numPages; i++) {
            pages[i] = scanner.nextInt();
        }

        PageReplacement(pages, numFrames);

        scanner.close();
    }

    
}

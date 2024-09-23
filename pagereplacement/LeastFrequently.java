package pagereplacement;
// Program to demonstrate 'Least Frequently Used' page replacement algorithm. 
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Page {
    int number;
    int frequency;

    Page(int number) {
        this.number = number;
        this.frequency = 1;
    }
}

public class LeastFrequently {
    public static void pageReplacement(int[] pages, int numFrames) {
        Map<Integer, Page> pageMap = new HashMap<>();
        PriorityQueue<Page> frequencyQueue = new PriorityQueue<>((a, b) -> {
            if (a.frequency == b.frequency) {
                return Integer.compare(a.number, b.number); 
            }
            return Integer.compare(a.frequency, b.frequency);
        });
        int pageFaults = 0;

        System.out.println("Page Reference Sequence: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\n");

        for (int currentPage : pages) {
            if (!pageMap.containsKey(currentPage)) {
                if (pageMap.size() == numFrames) {
                    Page lfuPage = frequencyQueue.poll();
                    pageMap.remove(lfuPage.number);
                    System.out.println("Page Fault: " + currentPage + " | Replacing: " + lfuPage.number);
                } else {
                    System.out.println("Page Fault: " + currentPage);
                }
                Page newPage = new Page(currentPage);
                pageMap.put(currentPage, newPage);
                frequencyQueue.offer(newPage);
                pageFaults++;
            } else {
                Page existingPage = pageMap.get(currentPage);
                existingPage.frequency++;
                frequencyQueue.remove(existingPage); 
                frequencyQueue.offer(existingPage); 
                System.out.println("Page Hit: " + currentPage);
            }

            System.out.print("Current Frames: ");
            for (Page p : pageMap.values()) {
                System.out.print(p.number + "(" + p.frequency + ") ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int numFrames = sc.nextInt();
        if (numFrames <= 0) {
            System.out.println("Number of frames must be positive.");
            return;
        }

        System.out.print("Enter the number of pages: ");
        int numPages = sc.nextInt();
        if (numPages <= 0) {
            System.out.println("Number of pages must be positive.");
            return;
        }

        System.out.print("Enter the page reference string (space-separated): ");
        int[] pages = new int[numPages];
        for (int i = 0; i < numPages; i++) {
            pages[i] = sc.nextInt();
            if (pages[i] < 0) {
                System.out.println("Page numbers must be non-negative.");
                return;
            }
        }

        pageReplacement(pages, numFrames);
        sc.close();
    }
}

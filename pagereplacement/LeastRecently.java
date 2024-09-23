package pagereplacement;
// Program to demonstrate 'Least Recently Used' page replacement algorithm. 

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LeastRecently {
    public static void pageReplacement(int[] pages, int numFrames) {
        Map<Integer, Integer> pageMap = new LinkedHashMap<>(numFrames, 0.75f, true);
        int pageFaults = 0;

        System.out.println("Page Reference Sequence: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\n");

        for (int currentPage : pages) {
            if (!pageMap.containsKey(currentPage)) {
                if (pageMap.size() == numFrames) {
                    Integer lruPage = pageMap.keySet().iterator().next();
                    pageMap.remove(lruPage);
                    System.out.println("Page Fault: " + currentPage + " | Replacing: " + lruPage);
                } else {
                    System.out.println("Page Fault: " + currentPage);
                }
                pageMap.put(currentPage, 0);
                pageFaults++;
            } else {
                System.out.println("Page Hit: " + currentPage);
            }

            System.out.println("Current Frames: " + pageMap.keySet());
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

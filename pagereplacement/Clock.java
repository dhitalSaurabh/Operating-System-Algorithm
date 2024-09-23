package pagereplacement;
// Program to demonstrate 'Clock' page replacement algorithm. 
import java.util.Scanner;

public class Clock {
    public static void pageReplacement(int[] pages, int numFrames) {
        int[] frames = new int[numFrames];
        boolean[] referenced = new boolean[numFrames]; 
        int pageFaults = 0;
        int pointer = 0; 

        for (int i = 0; i < numFrames; i++) {
            frames[i] = -1;
        }

        System.out.println("Page Reference Sequence: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\n");

        for (int currentPage : pages) {
            boolean pageFound = false;

            for (int j = 0; j < numFrames; j++) {
                if (frames[j] == currentPage) {
                    pageFound = true;
                    referenced[j] = true; 
                    System.out.println("Page Hit: " + currentPage + " | Frames: " + getFrames(frames));
                    break;
                }
            }

            if (!pageFound) {
                pageFaults++;
                while (true) {
                    if (frames[pointer] == -1) {
                        frames[pointer] = currentPage;
                        referenced[pointer] = true; 
                        break;
                    } else if (referenced[pointer]) {
                        referenced[pointer] = false; 
                    } else {
                        System.out.println("Page Fault: " + currentPage + " | Replacing: " + frames[pointer]);
                        frames[pointer] = currentPage; 
                        referenced[pointer] = true; 
                        break;
                    }
                    pointer = (pointer + 1) % numFrames;
                }
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    private static String getFrames(int[] frames) {
        StringBuilder sb = new StringBuilder();
        for (int frame : frames) {
            sb.append(frame).append(" ");
        }
        return sb.toString().trim();
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

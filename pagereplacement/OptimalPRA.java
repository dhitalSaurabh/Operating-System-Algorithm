package pagereplacement;
// Program to demonstrate 'Optimal' page replacement algorithm. 
import java.util.Scanner;

public class OptimalPRA {
    public static void pageReplacement(int[] pages, int numFrames) {
        int[] frames = new int[numFrames];
        boolean[] isPageInMemory = new boolean[100]; 
        int pageFaults = 0;

        for (int i = 0; i < numFrames; i++) {
            frames[i] = -1;
        }

        System.out.println("Page Reference Sequence: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\n");

        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            if (!isPageInMemory[currentPage]) { 
                pageFaults++;

                
                int j;
                for (j = 0; j < numFrames; j++) {
                    if (frames[j] == -1) { 
                        frames[j] = currentPage;
                        isPageInMemory[currentPage] = true;
                        break;
                    }
                }

                
                if (j == numFrames) {
                    int pageToReplace = findPageToReplace(pages, frames, i);
                    for (int k = 0; k < numFrames; k++) {
                        if (frames[k] == pageToReplace) {
                            frames[k] = currentPage; 
                            isPageInMemory[pageToReplace] = false; 
                            isPageInMemory[currentPage] = true;
                            break;
                        }
                    }
                }
            }

       
            System.out.print("Frames after referencing page " + currentPage + ": ");
            for (int frame : frames) {
                System.out.print(frame + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    private static int findPageToReplace(int[] pages, int[] frames, int currentIndex) {
        int pageToReplace = -1;
        int farthestIndex = -1;

        for (int frame : frames) {
            int nextIndex = findNextIndex(pages, frame, currentIndex);
            if (nextIndex == -1) {
                return frame;
            } else if (nextIndex > farthestIndex) {
                farthestIndex = nextIndex;
                pageToReplace = frame; 
            }
        }
        return pageToReplace; 
    }

    private static int findNextIndex(int[] pages, int page, int currentIndex) {
        for (int i = currentIndex + 1; i < pages.length; i++) {
            if (pages[i] == page) {
                return i; 
            }
        }
        return -1; 
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
        pageReplacement(pages, numFrames);
        scanner.close();
    }
}

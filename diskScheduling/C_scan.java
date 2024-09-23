package diskScheduling;
// Program to demonstrate 'Circular Scan' disk scheduling algorihtm.
import java.util.Scanner;
import java.util.Arrays;

public class C_scan {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter the number of tracks: ");
        int numTracks = inputScanner.nextInt();

        int[] tracks = new int[numTracks];

        System.out.println("Enter the track numbers:");
        for (int i = 0; i < numTracks; i++) {
            tracks[i] = inputScanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = inputScanner.nextInt();

        int totalMovement = 0;

        Arrays.sort(tracks);

        int currentIndex = 0;
        while (currentIndex < numTracks) {
            if (tracks[currentIndex] >= head) {
                break;
            }
            currentIndex++;
        }

        int[] sequence = new int[numTracks];
        int sequenceIndex = 0;
        while (sequenceIndex < numTracks) {
            sequence[sequenceIndex] = tracks[currentIndex];
            int distance = Math.abs(tracks[currentIndex] - head);
            totalMovement += distance;
            head = tracks[currentIndex];
            System.out.println("Accessing track " + tracks[currentIndex]);

            currentIndex = (currentIndex + 1) % numTracks;
            if (currentIndex == 0) {
                head = 199;
            }
            sequenceIndex++;
        }

        System.out.println("Total movement: " + totalMovement);
        inputScanner.close();
    }
}
package diskScheduling;
// Program to demonstrate 'Scan' disk scheduling algorihtm.

import java.util.Scanner;

public class ScanAlgorithm {
    
    static class Track {
        int number; 
        boolean accessed; 
        
        public Track(int number) {
            this.number = number;
            this.accessed = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of tracks: ");
        int numTracks = scanner.nextInt();
        
        Track[] tracks = new Track[numTracks];
        
        System.out.println("Enter the track numbers:");
        for (int i = 0; i < numTracks; i++) {
            tracks[i] = new Track(scanner.nextInt());
        }
        
        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();
        
        int totalMovement = 0; 
        boolean direction = true;
        
       
        for (int i = 0; i < numTracks; i++) {
            for (int j = i + 1; j < numTracks; j++) {
                if (tracks[i].number > tracks[j].number) {
                    Track temp = tracks[i];
                    tracks[i] = tracks[j];
                    tracks[j] = temp;
                }
            }
        }
        
        int index = 0;
        while (index < numTracks) {
            if (tracks[index].number >= head) {
                direction = true;
            } else {
                direction = false;
            }
            
            while (index < numTracks && (direction ? tracks[index].number >= head : tracks[index].number <= head)) {
                int distance = Math.abs(tracks[index].number - head);
                totalMovement += distance; 
                head = tracks[index].number; 
                System.out.println("Accessing track " + tracks[index].number);
                index++;
            }
            
            if (direction) {
                head = 199;
            } else {
                head = 0; 
            }
        }
        
        System.out.println("Total movement: " + totalMovement);
        scanner.close();
    }
}
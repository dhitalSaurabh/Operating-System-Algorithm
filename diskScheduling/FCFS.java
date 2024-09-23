package diskScheduling;
// Program to demonstrate 'First-Come-First-Served' disk scheduling algorihtm.
import java.util.Scanner;
public class FCFS {
    static class Track {
        int number; 
        boolean accessed; 
        
        public Track(int number) {
            this.number = number;
            this.accessed = false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of tracks: ");
        int numTracks = sc.nextInt();
        
        Track[] tracks = new Track[numTracks];
        
        System.out.println("Enter the track numbers:");
        for (int i = 0; i < numTracks; i++) {
            tracks[i] = new Track(sc.nextInt());
        }
        
        System.out.print("Enter the initial head position: ");
        int head = sc.nextInt();
        
        int totalMovement = 0; 
        
        for (int i = 0; i < numTracks; i++) {
            int distance = Math.abs(tracks[i].number - head);
            totalMovement += distance; 
            head = tracks[i].number; 
            System.out.println("Accessing track " + tracks[i].number);
        }
        
        System.out.println("Total movement: " + totalMovement);
        sc.close();
    }
}
package diskScheduling;
// Program to demonstrate 'Shortest Seek Time First' disk scheduling algorihtm.

import java.util.Scanner;
public class SSTF {
    
    static class DiskTrack {
        int number; 
        boolean accessed; 
        int distance; 
        
        public DiskTrack(int number) {
            this.number = number;
            this.accessed = false;
        }
    }

    public static void calculateDifferences(DiskTrack[] tracks, int head) {
        for (DiskTrack track : tracks) {
            track.distance = Math.abs(track.number - head);
        }
    }

    public static DiskTrack findUnaccessedTrack(DiskTrack[] tracks) {
        DiskTrack shortestTrack = null;
        int shortestDistance = Integer.MAX_VALUE;
        
        for (DiskTrack track : tracks) {
            if (!track.accessed && track.distance < shortestDistance) {
                shortestTrack = track;
                shortestDistance = track.distance;
            }
        }
        
        return shortestTrack;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of tracks: ");
        int numTracks = sc.nextInt();
        
        DiskTrack[] tracks = new DiskTrack[numTracks];
        
        System.out.println("Enter the track numbers:");
        for (int i = 0; i < numTracks; i++) {
            tracks[i] = new DiskTrack(sc.nextInt());
        }
        
        System.out.print("Enter the initial head position: ");
        int head = sc.nextInt();
        
        int totalMovement = 0; 
        
        calculateDifferences(tracks, head);
        
        while (true) {
            DiskTrack nextTrack = findUnaccessedTrack(tracks);
            if (nextTrack == null) {
                break;
            }
            nextTrack.accessed = true;
            System.out.println("Accessing track " + nextTrack.number);
            totalMovement += nextTrack.distance; 
            head = nextTrack.number; 
            calculateDifferences(tracks, head); 
        }
        System.out.println("Total movement: " + totalMovement);
        sc.close();
    }
}
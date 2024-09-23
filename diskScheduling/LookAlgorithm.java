package diskScheduling;
// Program to demonstrate 'Look' disk scheduling algorihtm.
import java.util.*;

class DiskScheduling {

    public static void look(int[] requests, int head, String direction) {
        int seekCount = 0;
        List<Integer> leftRequests = new ArrayList<>();
        List<Integer> rightRequests = new ArrayList<>();
        List<Integer> seekSequence = new ArrayList<>();

        for (int request : requests) {
            if (request < head) {
                leftRequests.add(request);
            } else if (request > head) {
                rightRequests.add(request);
            }
        }

        Collections.sort(leftRequests);
        Collections.sort(rightRequests);

        seekSequence.add(head);

        if (direction.equals("left")) {
            for (int i = leftRequests.size() - 1; i >= 0; i--) {
                seekCount += Math.abs(leftRequests.get(i) - seekSequence.get(seekSequence.size() - 1));
                seekSequence.add(leftRequests.get(i));
            }
            for (int request : rightRequests) {
                seekCount += Math.abs(request - seekSequence.get(seekSequence.size() - 1));
                seekSequence.add(request);
            }
        } else if (direction.equals("right")) {
            for (int request : rightRequests) {
                seekCount += Math.abs(request - seekSequence.get(seekSequence.size() - 1));
                seekSequence.add(request);
            }
            for (int i = leftRequests.size() - 1; i >= 0; i--) {
                seekCount += Math.abs(leftRequests.get(i) - seekSequence.get(seekSequence.size() - 1));
                seekSequence.add(leftRequests.get(i));
            }
        }

        System.out.println("Seek sequence: " + seekSequence);
        System.out.println("Total seek count: " + seekCount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of requests: ");
        int requestSize = sc.nextInt();

        int[] requests = new int[requestSize];
        System.out.println("Enter the requests:");
        for (int i = 0; i < requestSize; i++) {
            requests[i] = sc.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = sc.nextInt();

        System.out.print("Enter the direction (left/right): ");
        String direction = sc.next();

        look(requests, head, direction);
        sc.close();
    }
}
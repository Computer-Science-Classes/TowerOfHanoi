import java.util.Stack;

public class Hanoi {
    private static final int NUM_DISKS = 10;
    private static final int NUM_PEGS = 3;
    private static Stack<Integer>[] pegs;

    public static void main(String[] args) {
        pegs = new Stack[NUM_PEGS];
        for (int i = 0; i < NUM_PEGS; i++) {
            pegs[i] = new Stack<>();
        }
        for (int i = NUM_DISKS; i > 0; i--) {
            pegs[0].push(i);
        }
        loopHanoi();
    }

    private static void loopHanoi() {
        while (true) {
            int diskToMove = 1;
            int currentPeg = findDisk(diskToMove);
            int nextPeg = (currentPeg + 1) % NUM_PEGS;
            moveDisk(diskToMove, nextPeg);

            int smallestDisk = findSmallestDisk(nextPeg);
            if (smallestDisk == -1) {
                break;
            }

            currentPeg = findDisk(smallestDisk);
            if (smallestDisk % 2 == 1) {
                nextPeg = (currentPeg + 1) % NUM_PEGS;
            } else {
                nextPeg = (currentPeg - 1 + NUM_PEGS) % NUM_PEGS;
            }
            moveDisk(smallestDisk, nextPeg);
        }
    }

    private static int findDisk(int disk) {
        for (int i = 0; i < NUM_PEGS; i++) {
            if (!pegs[i].isEmpty() && pegs[i].peek() == disk) {
                return i;
            }
        }
        return -1;
    }

    private static int findSmallestDisk(int excludePeg) {
        int smallestDisk = -1;
        for (int i = 0; i < NUM_PEGS; i++) {
            if (i != excludePeg && !pegs[i].isEmpty()) {
                int topDisk = pegs[i].peek();
                if (smallestDisk == -1 || topDisk < smallestDisk) {
                    smallestDisk = topDisk;
                }
            }
        }
        return smallestDisk;
    }

    private static void moveDisk(int disk, int toPeg) {
        int fromPeg = findDisk(disk);
        pegs[fromPeg].pop();
        pegs[toPeg].push(disk);
        System.out.println(disk + "->" + (toPeg + 1));
    }
}

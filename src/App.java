import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        int numDisks = 3; // Number of disks
        solveHanoi(numDisks);
    }

    private static void solveHanoi(int numDisks) {
        int[] pegs = new int[numDisks]; // Initialize all disks on peg 0
        int totalMoves = (1 << numDisks) - 1;

        for (int i = 1; i <= totalMoves; i++) {
            int disk = getDiskToMove(i);
            int fromPeg = pegs[disk];
            int toPeg = (fromPeg + 1) % 3; // Next peg in cyclic order

            // Check for rule violation (placing a smaller disk on a larger one)
            if (!isValidMove(pegs, disk, toPeg)) {
                toPeg = (toPeg + 1) % 3; // Skip to the next peg
            }

            pegs[disk] = toPeg; // Update the peg of the moved disk
            System.out.println("Move disk " + (disk + 1) + " from peg " + (fromPeg + 1) + " to peg " + (toPeg + 1));
        }
    }

    private static boolean isValidMove(int[] pegs, int disk, int toPeg) {
        for (int d = 0; d < disk; d++) {
            if (pegs[d] == toPeg) {
                return false; // Found a smaller disk on the destination peg
            }
        }
        return true;
    }

    private static int getDiskToMove(int moveNumber) {
        // Gray code logic to determine which disk to move
        int grayCode = moveNumber ^ (moveNumber >> 1);
        int disk = 0;

        while ((grayCode & 1) == 0) {
            disk++;
            grayCode >>= 1;
        }
        return disk;
    }
}

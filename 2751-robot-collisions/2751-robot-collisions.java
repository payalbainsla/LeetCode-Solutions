import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        int[][] robots = new int[n][3]; // pos, health, index
        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i];
            robots[i][1] = healths[i];
            robots[i][2] = i;
        }

        // Sort by position
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);

        Stack<int[]> stack = new Stack<>(); // robots moving right
        List<int[]> survivors = new ArrayList<>();

        for (int[] robot : robots) {
            char dir = directions.charAt(robot[2]);
            if (dir == 'R') {
                stack.push(robot);
            } else {
                // moving left → check collisions
                while (!stack.isEmpty() && robot[1] > 0) {
                    int[] top = stack.peek();
                    if (top[1] < robot[1]) {
                        stack.pop();
                        robot[1]--; // survives but loses 1 health
                    } else if (top[1] == robot[1]) {
                        stack.pop();
                        robot[1] = 0; // both die
                        break;
                    } else {
                        top[1]--; // top survives but loses 1 health
                        robot[1] = 0; // current dies
                        break;
                    }
                }
                if (robot[1] > 0) survivors.add(robot);
            }
        }

        // Add remaining stack robots (moving right survivors)
        while (!stack.isEmpty()) survivors.add(stack.pop());

        // Sort survivors back to original order
        survivors.sort((a, b) -> a[2] - b[2]);

        List<Integer> result = new ArrayList<>();
        for (int[] r : survivors) result.add(r[1]);
        return result;
    }
}

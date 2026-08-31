/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Step 1: linked list ko array mein convert karo
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        
        int n = values.size();
        List<Integer> criticalIndices = new ArrayList<>();
        
        // Step 2: critical points dhundo
        for (int i = 1; i < n - 1; i++) {
            int prev = values.get(i - 1);
            int curr = values.get(i);
            int next = values.get(i + 1);
            
            if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
                criticalIndices.add(i);
            }
        }
        
        // Step 3: agar 2 se kam critical points hain
        if (criticalIndices.size() < 2) {
            return new int[]{-1, -1};
        }
        
        // Step 4: max distance
        int maxDistance = criticalIndices.get(criticalIndices.size() - 1) - criticalIndices.get(0);
        
        // Step 5: min distance (consecutive critical points ke beech)
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < criticalIndices.size(); i++) {
            minDistance = Math.min(minDistance, criticalIndices.get(i) - criticalIndices.get(i - 1));
        }
        
        return new int[]{minDistance, maxDistance};
    }
}
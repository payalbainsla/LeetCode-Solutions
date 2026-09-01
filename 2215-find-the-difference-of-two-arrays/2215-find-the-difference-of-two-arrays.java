class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int x : nums1) {
            set1.add(x);
        }
        for(int y : nums2) {
            set2.add(y);
        }

        for(int x : set1) {
            if(!set2.contains(x)) {
                list1.add(x);
            }
        }

        for(int y : set2) {
            if(!set1.contains(y)) {
                list2.add(y);
            }
        }

         result.add(list1);
        result.add(list2);

        return result;
        
    }
}
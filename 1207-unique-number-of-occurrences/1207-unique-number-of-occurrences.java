class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

    //   step 1: first count frequency
    for(int x : arr) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    // step2 : after that check uniqueness of frequencies

    HashSet<Integer> set = new HashSet<>();
    for(int freq : map.values()) {
        if(!set.add(freq)) { //if frequency is already present 
            return false;
        }
    }
    return true;
    }
}

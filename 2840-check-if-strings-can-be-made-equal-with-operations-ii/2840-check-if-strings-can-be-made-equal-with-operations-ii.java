class Solution {
    public boolean checkStrings(String s1, String s2) {
        char[] even1 = new char[(s1.length() + 1) / 2];
        char[] odd1 = new char[s1.length() / 2];
        char[] even2 = new char[(s2.length() + 1) / 2];
        char[] odd2 = new char[s2.length() / 2];

        for(int i = 0; i < s1.length(); i++) {
            if(i % 2 == 0) {
                even1[i/2] = s1.charAt(i);
                even2[i/2] = s2.charAt(i);
            } else {
                odd1[i/2] = s1.charAt(i);
                odd2[i/2] = s2.charAt(i);
            }
        }

        Arrays.sort(even1);
        Arrays.sort(odd1);
        Arrays.sort(even2);
        Arrays.sort(odd2);

        return Arrays.equals(even1, even2) && Arrays.equals(odd1, odd2);
    }
}
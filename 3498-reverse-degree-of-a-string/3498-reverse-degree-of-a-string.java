class Solution {
    public int reverseDegree(String s) {
        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                            'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < alphabets.length; j++) {
                if (alphabets[j] == c) {
                    int reversePos = 26 - j;      // a(j=0) -> 26, z(j=25) -> 1
                    sum += reversePos * (i + 1);  // string position 1 se shuru
                    break;
                }
            }
        }
        return sum;
    }
}
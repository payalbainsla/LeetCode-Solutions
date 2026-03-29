class Solution {
    public boolean canBeEqual(String s1, String s2) {
       // Agar dono already equal hain
        if (s1.equals(s2)) return true;

        // Allowed swaps: (0,2) aur (1,3)
        // Matlab s1 ke characters ko group-wise compare karna hai

        // Check group 1: positions 0 and 2
        boolean group1 = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                         (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));

        // Check group 2: positions 1 and 3
        boolean group2 = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                         (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));

        return group1 && group2;
      }
    }

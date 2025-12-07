class Solution {
    public int countOdds(int low, int high) {
       //if low is even so we make this odd.
       return (high + 1)/ 2 - low/2; 
    }
}
class Solution {
    public int countCollisions(String directions) {
       char[] arr = directions.toCharArray();
       int n = arr.length;

       int i=0, j=n-1;

       while(i<n && arr[i] == 'L') i++;
       while(j >= 0 && arr[j] == 'R') j--;

       int collisions = 0;

       for(int k=i; k<=j; k++) {
        if(arr[k] == 'L' || arr[k] == 'R') {
            collisions++;
        }
       } 

       return collisions;
    }
}
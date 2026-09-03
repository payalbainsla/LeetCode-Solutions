class Solution {
    public boolean uniformArray(int[] nums1) {
        int SmallOdd=Integer.MAX_VALUE;
        for(int n:nums1){
            if(n%2!=0 && SmallOdd>n){
                SmallOdd=n;
            }
        }
        if(SmallOdd==Integer.MAX_VALUE)return true;
        boolean t1=true,t2=true;
        for(int n:nums1){
            if(n%2==0 && n<=SmallOdd){
                t1=false;
            }
            if(n%2!=0 && n<=SmallOdd){
                t2=false;
            }
        }
        if(t1 || t2)return true;
        return false;
    }
}
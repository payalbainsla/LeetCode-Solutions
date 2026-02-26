class Solution {
    public int numSteps(String s) {
        StringBuilder str = new StringBuilder(s);
        int steps = 0;

        while(str.length() > 1) {
            int lastIndex = str.length()-1;
            if(str.charAt(lastIndex) == '0') {
                divideByTwo(str);
            } else {
                addOne(str);
            }
            steps++;
        }
        return steps;
    }

    private void addOne(StringBuilder s) {
        int i = s.length()-1;

        while(i>=0 && s.charAt(i) == '1') {
            s.setCharAt(i, '0');
            i--;
        }

        if(i < 0) {
            s.insert(0, '1');
        } else {
            s.setCharAt(i, '1');
        }
    }

    private void divideByTwo(StringBuilder s) {
        s.deleteCharAt(s.length()-1);
    }
}
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Step 1: heights array banate hain (histogram style)
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1) {
                    matrix[r][c] += matrix[r-1][c];
                }
            }
        }
        
        int maxArea = 0;
        
        // Step 2: Har row ko sort karo (columns rearrange allowed)
        for (int r = 0; r < rows; r++) {
            int[] heights = matrix[r].clone();
            java.util.Arrays.sort(heights);
            
            // Step 3: Area calculate karo
            for (int i = 0; i < cols; i++) {
                int h = heights[cols - 1 - i]; // descending order
                int area = h * (i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        
        return maxArea;
    }
}

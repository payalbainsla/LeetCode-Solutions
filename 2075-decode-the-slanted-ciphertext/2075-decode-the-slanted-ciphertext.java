class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (encodedText.isEmpty()) return "";

        int n = encodedText.length();
        int cols = n / rows;

        // Build the matrix
        char[][] matrix = new char[rows][cols];
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = encodedText.charAt(idx++);
            }
        }

        // Decode diagonally
        StringBuilder sb = new StringBuilder();
        for (int startCol = 0; startCol < cols; startCol++) {
            int r = 0, c = startCol;
            while (r < rows && c < cols) {
                sb.append(matrix[r][c]);
                r++;
                c++;
            }
        }

        // Trim trailing spaces
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}

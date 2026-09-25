import java.util.*;

class Solution {
    private String expression;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.idx = 0;
        Set<String> result = parseExpr();
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parseExpr() {
        Set<String> result = parseTerm();
        while (idx < expression.length() && expression.charAt(idx) == ',') {
            idx++; // skip ','
            result.addAll(parseTerm());
        }
        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");
        while (idx < expression.length() 
               && expression.charAt(idx) != ',' 
               && expression.charAt(idx) != '}') {
            Set<String> factor = parseFactor();
            Set<String> newResult = new HashSet<>();
            for (String a : result) {
                for (String b : factor) {
                    newResult.add(a + b);
                }
            }
            result = newResult;
        }
        return result;
    }

    private Set<String> parseFactor() {
        Set<String> result;
        if (expression.charAt(idx) == '{') {
            idx++; // skip '{'
            result = parseExpr();
            idx++; // skip '}'
        } else {
            int start = idx;
            while (idx < expression.length() && Character.isLetter(expression.charAt(idx))) {
                idx++;
            }
            result = new HashSet<>();
            result.add(expression.substring(start, idx));
        }
        return result;
    }
}
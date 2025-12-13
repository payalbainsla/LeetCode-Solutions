class Solution {
    static class Coupon {
        String code;
        String businessLine;

        Coupon(String code, String businessLine) {
            this.code = code;
            this.businessLine = businessLine;
        }
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        if(code.length != businessLine.length || code.length != isActive.length) {
            throw new IllegalArgumentException("Input arrays must have the same length");
        }

        Map<String, Integer> order = new HashMap<>();
        order.put("electronics", 0);
        order.put("grocery", 1);
        order.put("pharmacy", 2);
        order.put("restaurant", 3);

        List<Coupon> validCoupons = new ArrayList<>();

        for(int i = 0; i < code.length; i++) {
            if(!isActive[i]) continue;
            if(!order.containsKey(businessLine[i])) continue;
            if(code[i].isEmpty()) continue;
            if(!code[i].matches("[a-zA-Z0-9_]+")) continue;

            validCoupons.add(new Coupon(code[i], businessLine[i]));
        }

        Collections.sort(validCoupons, (a, b) -> {
            int cmp = Integer.compare(order.get(a.businessLine), order.get(b.businessLine));
            if(cmp != 0) return cmp;
            return a.code.compareTo(b.code);
        });

        List<String> res = new ArrayList<>();
        for(Coupon c : validCoupons) {
            res.add(c.code);
        }

        return res;
    }
}
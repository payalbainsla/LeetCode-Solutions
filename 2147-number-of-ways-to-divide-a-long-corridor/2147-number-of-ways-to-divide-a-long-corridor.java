class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;

        int totalSeats = 0;
        for (char c : corridor.toCharArray()) {
            if (c == 'S') totalSeats++;
        }

        if (totalSeats == 0 || totalSeats % 2 != 0) return 0;

        long ways = 1;
        int seatCount = 0;
        int plantsBetween = 0;
        boolean countingPlants = false;

        for (char c : corridor.toCharArray()) {
            if (c == 'S') {
                seatCount++;

                // jab ek section (2 seats) complete ho jaye
                if (seatCount % 2 == 0) {
                    countingPlants = true;
                }
                // jab next section ka first seat aaye
                else if (seatCount > 1) {
                    ways = (ways * (plantsBetween + 1)) % MOD;
                    plantsBetween = 0;
                    countingPlants = false;
                }
            } else {
                if (countingPlants) {
                    plantsBetween++;
                }
            }
        }

        return (int) ways;
    }
}

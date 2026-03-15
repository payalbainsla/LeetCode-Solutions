class Fancy {
    static final int MOD = 1_000_000_007;
    List<Long> seq;
    List<Long> mulAtInsert;
    List<Long> addAtInsert;
    long mul = 1, add = 0;

    public Fancy() {
        seq = new ArrayList<>();
        mulAtInsert = new ArrayList<>();
        addAtInsert = new ArrayList<>();
    }

    public void append(int val) {
        seq.add((long) val);
        mulAtInsert.add(mul);
        addAtInsert.add(add);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= seq.size()) return -1;

        long val = seq.get(idx);
        long mulInsert = mulAtInsert.get(idx);
        long addInsert = addAtInsert.get(idx);

        // Difference in multiplier
        long mulDiff = (mul * modInverse(mulInsert)) % MOD;

        // Apply transformations
        long result = (val * mulDiff) % MOD;
        long addDiff = (add - (addInsert * mulDiff) % MOD + MOD) % MOD;
        result = (result + addDiff) % MOD;

        return (int) result;
    }

    // Modular inverse using Fermat's Little Theorem
    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}

import java.util.Objects;

public class Fraction {
    private final int num, den;
    public Fraction(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        if (den < 0) { num = -num; den = -den; }
        int g = gcd(Math.abs(num), Math.abs(den));
        this.num = num / g;
        this.den = den / g;
    }
    private static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a == 0 ? 1 : a;
    }
    @Override public String toString() { return num + "/" + den; }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fraction other)) return false;
        return num == other.num && den == other.den;
    }
    @Override public int hashCode() { return Objects.hash(num, den); }
}
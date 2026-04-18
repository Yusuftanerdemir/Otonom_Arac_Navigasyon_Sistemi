public class Coordinate {
    private final double x;
    private final double y;
    private final int label; // Sınıf 1 için 1, Sınıf 2 için -1

    public Coordinate(double x, double y, int label) {
        if (label != 1 && label != -1) {
            throw new IllegalArgumentException("Etiketler sadece 1 veya -1 olmalıdır.");
        }
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getLabel() { return label; }
}
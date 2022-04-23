public class InhibitorArc extends Arc {

    private int cardinality = 1;

    public InhibitorArc() { }

    public InhibitorArc(int cardinality) { this.cardinality = cardinality; }

    public int getCardinality() { return cardinality; }

    @Override
    void run() { }
}

public class InhibitorArc extends Arc {

    private int cardinality = 1;

    public InhibitorArc() {
    }

    public InhibitorArc(int cardinality) {
        this.cardinality = cardinality;
    }

    @Override
    void run() {

    }
}

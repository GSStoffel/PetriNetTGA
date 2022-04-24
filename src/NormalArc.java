public class NormalArc extends Arc {

    public NormalArc() {
    }

    public NormalArc(int cardinality) {
        this.cardinality = cardinality;
    }

    @Override
    public void run() {
        if (input instanceof Place) {
            ((Place) input).addToken(cardinality);
        }

        if (output instanceof Place) {
            ((Place) output).subToken(cardinality);
        }
    }
}

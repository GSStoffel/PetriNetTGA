public class NormalArc extends Arc {

    private int cardinality = 1;

    public NormalArc() {
    }

    public NormalArc(int cardinality) {
        this.cardinality = cardinality;
    }

    public int getCardinality() {
        return cardinality;
    }

    @Override
    public void run() {
        if(input instanceof Place){
            ((Place)input).addToken(cardinality);
        }

        if(output instanceof Place){
            ((Place)input).subToken(cardinality);
        }
    }
}

public class NormalArc extends Arc {

    private int cardinality;

    public NormalArc(int cardinality) {
        super();
        this.cardinality = cardinality;
    }

    public NormalArc(Place place, int cardinality) {
        super(place);
        this.cardinality = cardinality;
    }

    public NormalArc(Transition transition, int cardinality) {
        super(transition);
        this.cardinality = cardinality;
    }

    @Override
    public Transition getTransition() {
        return transition;
    }

    @Override
    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    public int getCardinality() {
        return cardinality;
    }

    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }
}

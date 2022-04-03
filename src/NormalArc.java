public class NormalArc extends Arc {

    Transition transition;
    Place place;
    int cardinality;

    public NormalArc(Place place, int cardinality) {
        super(place, cardinality);
    }

    public NormalArc(Transition transition, int cardinality) {
        super(transition, cardinality);
    }

    public Place getPlace() {
        return place;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }
}

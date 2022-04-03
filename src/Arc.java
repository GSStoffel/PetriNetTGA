public class Arc {
    Transition transition;
    Place place;
    int cardinality;

    public Arc() {
    }

    public Arc(Transition transition, int cardinality) {
        this.transition = transition;
        this.cardinality = cardinality;
    }

    public Arc(Place place, int cardinality) {
        this.place = place;
        this.cardinality = cardinality;
    }

}

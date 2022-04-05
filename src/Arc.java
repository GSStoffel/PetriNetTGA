public class Arc {
    protected Transition transition;
    protected Place place;

    public Arc() {
    }

    public Arc(Transition transition) {
        this.transition = transition;
    }

    public Arc(Place place) {
        this.place = place;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}

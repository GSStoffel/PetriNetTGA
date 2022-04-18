import java.util.ArrayList;
import java.util.List;

public class PetriNet {
    List<Place> places = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    List<NormalArc> normalArcs = new ArrayList<>();
    List<ResetArc> resetArcs = new ArrayList<>();
    List<InhibitorArc> inhibitorArcs = new ArrayList<>();

    public PetriNet() {
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<NormalArc> getNormalArcs() {
        return normalArcs;
    }

    public void setNormalArcs(List<NormalArc> normalArcs) {
        this.normalArcs = normalArcs;
    }

    public List<ResetArc> getResetArcs() {
        return resetArcs;
    }

    public void setResetArcs(List<ResetArc> resetArcs) {
        this.resetArcs = resetArcs;
    }

    public List<InhibitorArc> getInibitorArcs() {
        return inhibitorArcs;
    }

    public void setInibitorArcs(List<InhibitorArc> inhibitorArcs) {
        this.inhibitorArcs = inhibitorArcs;
    }
}

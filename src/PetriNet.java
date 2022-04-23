import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetriNet {
    List<Place> places = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    List<NormalArc> normalArcs = new ArrayList<>();
    List<ResetArc> resetArcs = new ArrayList<>();
    List<InhibitorArc> inhibitorArcs = new ArrayList<>();

    public void run(){
        List<Transition> runnableTransitions = getRunnableTransitions();

        if(!runnableTransitions.isEmpty()){
            for(Transition runnableTransition : runnableTransitions) {

                /*
                    Devido a concorrência a transição pode se tornar inativa então é verificado
                    se a mesma ainda está ativa
                 */
                if (runnableTransition.is_runnable()) {
                    List<Arc> inArcList = runnableTransition.getInArcList();
                    List<Arc> outArcList = runnableTransition.getOutArcList();

                    for (Arc arc : inArcList) {
                        if(arc instanceof NormalArc) {
                            while (((Place) arc.getOutput()).getTokens() >= ((NormalArc)arc).getCardinality()) {
                                Place place = (Place) arc.getOutput();
                                List<Arc> placeOutArcList = place.getOutArcList();
                                if (placeOutArcList.size() > 1) {
                                    Random random = new Random();
                                    placeOutArcList.get(random.nextInt(placeOutArcList.size())).run();
                                } else {
                                    arc.run();
                                }
                            }
                        } else {
                            arc.run();
                        }
                    }
                    for (Arc arc : outArcList) {
                        arc.run();
                    }
                }
            }
        }
    }

    public void connect (Place place, Transition transition, Arc arc) {
        place.getOutArcList().add(arc);
        transition.getInArcList().add(arc);
        arc.setOutput(place);
        arc.setInput(transition);
    }

    public void connect (Transition transition, Place place, Arc arc) {
        transition.getOutArcList().add(arc);
        place.getInArcList().add(arc);
        arc.setOutput(place);
        arc.setInput(transition);
    }

    private List<Transition> getRunnableTransitions() {
        List<Transition> runnableTransitions = new ArrayList<>();

        for(Transition transition : transitions) {
            if(transition.is_runnable()) {
                runnableTransitions.add(transition);
            }
        }
        return runnableTransitions;
    }

    public Place getPlaceByLabel(String label) {
        for (Place place : places) {
            if (place.getLabel().equals(label)) {
                return place;
            }
        }
        return null;
    }

    public Transition getTransitionByLabel(String label) {
        for (Transition transition : transitions) {
            if (transition.getLabel().equals(label)) {
                return transition;
            }
        }
        return null;
    }

    public NormalArc getNormalArcByLabel(String label) {
        for (NormalArc arc : normalArcs) {
            if (arc.getLabel().equals(label)) {
                return arc;
            }
        }
        return null;
    }

    public ResetArc getResetArcByLabel(String label) {
        for (ResetArc arc : resetArcs) {
            if (arc.getLabel().equals(label)) {
                return arc;
            }
        }
        return null;
    }

    public InhibitorArc getInhibitorArcByLabel(String label) {
        for (InhibitorArc arc : inhibitorArcs) {
            if (arc.getLabel().equals(label)) {
                return arc;
            }
        }
        return null;
    }
}

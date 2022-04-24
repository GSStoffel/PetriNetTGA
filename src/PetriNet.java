import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetriNet {
    List<Place> places = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    List<Arc> arcs = new ArrayList<>();

    public void run() {
        int ciclo = 1;
        List<Transition> runnableTransitions = getRunnableTransitions();

        while (!runnableTransitions.isEmpty()) {
            System.out.println("\nEstado do ciclo " + ciclo + ": " + this + "\n\n------------------------------");

            for (Transition transition : runnableTransitions) {
                executeTransition(transition);
            }
            runnableTransitions = getRunnableTransitions();
            ciclo++;
        }
        System.out.println("\nEstado do ciclo " + ciclo + ": " + this + "\n\n------------------------------");
        System.out.println("\nNão há mais nenhuma transição habilitada!");
    }

    private void executeTransition(Transition transition) {
        while (transition.is_runnable()) {
            List<Arc> inArcList = transition.getInArcList();
            List<Arc> outArcList = transition.getOutArcList();

            for (Arc arc : inArcList) {
                if (((Place) arc.getOutput()).getOutputNormalArcsRunnable().size() > 1){
                    Place place = (Place) arc.getOutput();
                    List<Arc> outputNormalArcsRunnable = place.getOutputNormalArcsRunnable();

                    Random random = new Random();
                    int index = random.nextInt(outputNormalArcsRunnable.size());
                    System.out.println("index: " + index);
                    Arc arc1 = outputNormalArcsRunnable.get(index);
                    arc1.run();
                    Transition inputTransition = (Transition) arc1.getInput();
                    List<Arc> transitionOutArcList = inputTransition.getOutArcList();
                    for (Arc arcTransitionOutArcList : transitionOutArcList) {
                        arcTransitionOutArcList.run();
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

    public void add(Place place) {
        if (!places.contains(place))
            places.add(place);
    }

    public void add(Transition transition) {
        if (!transitions.contains(transition))
            transitions.add(transition);
    }

    public void add(Arc arc) {
        if (!arcs.contains(arc))
            arcs.add(arc);
    }

    public void connect(Place place, Transition transition, Arc arc) {
        add(place);
        add(transition);
        add(arc);

        place.getOutArcList().add(arc);
        transition.getInArcList().add(arc);

        arc.setOutput(place);
        arc.setInput(transition);
    }

    public void connect(Transition transition, Place place, Arc arc) {
        add(transition);
        add(place);
        add(arc);

        transition.getOutArcList().add(arc);
        place.getInArcList().add(arc);

        arc.setOutput(transition);
        arc.setInput(place);
    }

    private List<Transition> getRunnableTransitions() {
        List<Transition> runnableTransitions = new ArrayList<>();
        for (Transition transition : transitions) {
            if (transition.is_runnable()) {
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

    @Override
    public String toString() {
        StringBuilder networkState = new StringBuilder("\nPlaces: ");
        for (Place place : places) {
            networkState.append(place.toString());
        }
        networkState.append("\nTransitions: ");
        for (Transition transition : transitions) {
            networkState.append(transition.toString());
        }
        return networkState.toString();
    }
}

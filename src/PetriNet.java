import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PetriNet {
    List<Place> places = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    List<Arc> arcs = new ArrayList<>();

    public void run() {
        int ciclo = 0;
        List<Transition> runnableTransitions = getRunnableTransitions();

        while (!runnableTransitions.isEmpty()) {

            new Scanner(System.in).nextLine();

            System.out.println("\nEstado do ciclo " + ciclo + ": " + this + "\n\n------------------------------");

            for (Transition transition : runnableTransitions) {
                while (transition.is_runnable()) {
                    List<Arc> inArcList = transition.getInArcList();
                    List<Arc> outArcList = transition.getOutArcList();

                    for (Arc arc : inArcList) {

                        // Busca arcos de saída dos Places que apontam para a transição, avaliando se há concorrência
                        List<Arc> outputNormalArcsRunnable = ((Place) arc.getOutput()).getOutputNormalArcsRunnable();

                        // Caso haja mais de um arco saindo do mesmo place, há concorrência
                        if (outputNormalArcsRunnable.size() > 1) {

                            // Gera um número alatório entre 0 e a quantidade de arcos concorrentes
                            Random random = new Random();
                            int index = random.nextInt(outputNormalArcsRunnable.size());

                            // Arco escolhido para execução
                            Arc chosenArc = outputNormalArcsRunnable.get(index);
                            chosenArc.run();

                            // Executa as arcos / transições apontadas pelo arco esolhido
                            for (Arc arcFromChosenArc : ((Transition) chosenArc.getInput()).getOutArcList()) {
                                arc.run();
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
            runnableTransitions = getRunnableTransitions();
            ciclo++;
        }
        System.out.println("\nEstado do ciclo " + ciclo + ": " + this + "\n\n------------------------------");
        System.out.println("\nNão há mais nenhuma transição habilitada!");
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

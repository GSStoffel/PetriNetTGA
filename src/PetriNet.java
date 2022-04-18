import java.util.ArrayList;
import java.util.List;

public class PetriNet {
    List<Place> places = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    List<NormalArc> normalArcs = new ArrayList<>();
    List<ResetArc> resetArcs = new ArrayList<>();
    List<InhibitorArc> inhibitorArcs = new ArrayList<>();

    public void run(){
        List<Transition> runnableTransitions = new ArrayList<>();
        
        for(Transition transition : transitions) {
            if(transition.is_runnable()) {
                runnableTransitions.add(transition)
            }
        }
        
        if(!runnableTransitions.isEmpty()){
            for(Transition runnableTransition : runnableTransitions) {
                for (Arc arc : runnableTransition.getInArcList()) {
                    arc.run();
                }
                for (Arc arc : runnableTransition.getOutArcList()) {
                    arc.run();
                }
            }
        }
    }
}

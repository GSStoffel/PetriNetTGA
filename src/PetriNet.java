import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNet {
    List<Place> places = new ArrayList<>();

    Map<String, Place> places_kv = new HashMap<String, Place>();
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

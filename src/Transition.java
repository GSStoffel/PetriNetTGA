import java.util.List;

public class Transition {
    private String label;
    private List<Arc> inArcList;
    private List<Arc> outArcList;

    public Transition() {
    }

    public Transition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean is_runnable(){
        for (Arc arc : inArcList) {
            if (arc instanceof NormalArc){
                if(arc.getInput() instanceof Place) {
                    if(((NormalArc) arc).getCardinality() > ((Place) arc.getInput()).getTokens()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void run(){
        if(is_runnable()){
            for (Arc arc : inArcList) {
                arc.run();
            }
            for (Arc arc : outArcList) {
                arc.run();
            }
        }
    }
}

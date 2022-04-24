import java.util.ArrayList;
import java.util.List;

public class Transition {
    private String label;
    private List<Arc> inArcList = new ArrayList<>();
    private List<Arc> outArcList = new ArrayList<>();
    private Action action;

    public Transition() {
    }

    public Transition(String label) {
        this.label = label;
    }

    public boolean is_runnable() {
        for (Arc arc : inArcList) {
            if (arc instanceof NormalArc) {
                if (arc.getCardinality() > ((Place) arc.getOutput()).getTokens())
                    return false;
            }

            if (arc instanceof InhibitorArc) {
                if (arc.getOutput() instanceof Place) {
                    if (arc.getCardinality() <= ((Place) arc.getOutput()).getTokens())
                        return false;
                }
            }

            if (arc instanceof ResetArc) {
                if (inArcList.size() == 1 && ((Place) arc.getOutput()).getTokens() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Arc> getInArcList() {
        return inArcList;
    }

    public List<Arc> getOutArcList() {
        return outArcList;
    }

    public String getLabel() {
        return label;
    }

    public void executeAction() {
        if (action != null) {
            action.execute();
        }
    }

    @Override
    public String toString() {
        return label + (is_runnable() ? " Habilitado " : " Desabilitado ");
    }
}

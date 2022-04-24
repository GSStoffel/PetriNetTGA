import java.util.ArrayList;
import java.util.List;

public class Place {
    private String label;
    private int tokens;
    private List<Arc> outArcList = new ArrayList<>();
    private List<Arc> inArcList = new ArrayList<>();
    private Action action;

    public Place() {
    }

    public Place(String label) {
        this.label = label;
    }

    public Place(String label, int tokens) {
        this.label = label;
        this.tokens = tokens;
    }

    public int getTokens() {
        return tokens;
    }

    public void addToken(int n) {
        tokens += n;
        executeAction();
    }

    public void subToken(int n) {
        tokens -= n;
        executeAction();
    }

    public void removeAllTokens() {
        tokens = 0;
        executeAction();
    }

    public void executeAction() {
        if (action != null) {
            action.execute();
        }
    }

    public int getTotalOutputNormalArcRunnable() {
        int total = 0;
        for (Arc arc : outArcList) {
            if (arc instanceof NormalArc)
                if (((Transition)arc.getInput()).is_runnable())
                    total++;
        }
        return total;
    }

    public String getLabel() {
        return label;
    }

    public List<Arc> getOutArcList() {
        return outArcList;
    }

    public List<Arc> getInArcList() {
        return inArcList;
    }

    @Override
    public String toString() {
        return label + ": " + tokens + " ";
    }
}

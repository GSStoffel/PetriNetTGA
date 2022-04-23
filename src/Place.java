import java.util.List;

public class Place {
    private String label;
    private int tokens;
    private List<Arc> outArcList;
    private List<Arc> inArcList;
    private Action action;

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
        tokens=0;
        executeAction();
    }

    public void executeAction() {
        if(action != null) {
            action.execute();
        }
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
}

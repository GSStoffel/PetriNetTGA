import java.util.List;

public class Place {
    private String name;
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

}

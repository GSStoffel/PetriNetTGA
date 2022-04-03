import java.util.List;

public class Place {
    private int tokens;
    private List<Arc> arcList;
    private Action action;

    public Place() {
    }

    public Place(int tokens) {
        this.tokens = tokens;
    }

    public Place(int tokens, List<Arc> arcList, Action action) {
        this.tokens = tokens;
        this.arcList = arcList;
        this.action = action;
    }

    public Place(int tokens, List<Arc> arcList) {
        this.tokens = tokens;
        this.arcList = arcList;
    }

    public int getTokens() {
        return tokens;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void executeAction() {
        action.execute();
    }

    public void addToken() {
        tokens++;
    }

    public void subToken() {
        tokens--;
    }
}

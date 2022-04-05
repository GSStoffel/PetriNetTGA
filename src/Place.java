import java.util.List;

public class Place {
    private String name;
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

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void setArcList(List<Arc> arcList) {
        this.arcList = arcList;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void addToken() { tokens++; }

    public void subToken() {
        tokens--;
    }

    public void executeAction() {
        action.execute();
    }

}

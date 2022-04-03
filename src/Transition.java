import java.util.List;

public class Transition {
    private List<Arc> arcList;

    public Transition() {
    }

    public Transition(List<Arc> arcList) {
        this.arcList = arcList;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void setArcList(List<Arc> arcList) {
        this.arcList = arcList;
    }

}

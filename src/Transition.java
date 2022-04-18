import java.util.List;

public class Transition {
    private String name;
    private List<Arc> inArcList;
    private List<Arc> outArcList;

    public boolean is_runnable(){
        for (Arc arc : inArcList) {
            if (arc instanceof NormalArc){
                if(arc.getOutput() instanceof Place) {
                    if(((NormalArc) arc).getCardinality() > ((Place) arc.getInput()).getTokens()){
                        return false;
                    }
                }
            }
            
            if (arc instanceof InhibitorArc){
                if(arc.getOutput() instanceof Place) {
                    if(((InhibitorArc) arc).getCardinality() <= ((Place) arc.getOutput()).getTokens()){
                        return false;
                    }
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
}

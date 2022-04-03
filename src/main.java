import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args){

        Place p2 = new Place(0);
        Arc a2 = new NormalArc(p2, 1);
        Transition t1 = new Transition(new ArrayList<Arc>(Arrays.asList(a2)));
        Arc a1 = new NormalArc(t1, 1);
        Place p1 = new Place(2, new ArrayList<Arc>(Arrays.asList(a1)));


    }
}

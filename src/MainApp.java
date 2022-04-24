public class MainApp {
    public static void main(String[] args) {
        PetriNet petriNet = new PetriNet();

        Place p1 = new Place("P1", 1);
        Place p2 = new Place("P2", 0);
        Place p3 = new Place("P3", 0);

        Transition t1 = new Transition("T1");
        Transition t2 = new Transition("T2");

        petriNet.connect(p1, t1, new NormalArc(1));
        petriNet.connect(p1, t2, new NormalArc(1));
        petriNet.connect(t1, p2, new NormalArc(1));
        petriNet.connect(t2, p3, new NormalArc(1));

        petriNet.run();
    }
}

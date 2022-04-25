/**
 * @author Gabriel Sperb Stoffel e Carlos Leandro Silva Machado
 */

public class MainApp {
    public static void main(String[] args) {
        PetriNet petriNet = PetriJSON.loadJSONFromPetriNet("src/tests/test-refga.json");
        petriNet.getPlaceByLabel("L1").setAction(new HelloWorldAction());
//        petriNet.getPlaceByLabel("L1").setAction(() -> System.out.println("Hello World!"));
        petriNet.run();
    }
}
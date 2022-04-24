public class MainApp {
    public static void main(String[] args) {
        PetriNet petriNet = PetriJSON.loadJSONFromPetriNet("src/tests/test-refga.json");
        //petriNet.getPlaceByLabel("L1").setAction(new HelloWorldAction());
        petriNet.run();
    }
}
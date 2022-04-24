import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MainApp {
    public static void main(String[] args){

        PetriNet petriNet = new PetriNet();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src/tests/test-2-01.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            // ADD PLACES
            JSONArray ja_places = (JSONArray) jsonObject.get("places");
            for (Object ja_place : ja_places) {
                JSONObject jobj_place = (JSONObject) ja_place;
                Place cur_place = new Place(jobj_place.get("label").toString(), ((Long) jobj_place.get("tokens")).intValue());
                petriNet.add(cur_place);
            }

            // ADD TRANSITIONS
            JSONArray ja_transitions = (JSONArray) jsonObject.get("transitions");
            for (Object ja_transition : ja_transitions) {
                JSONObject jobj_transition = (JSONObject) ja_transition;
                Transition cur_transition = new Transition(jobj_transition.get("label").toString());
                petriNet.add(cur_transition);
            }

            // ARC TRANSITION TO PLACE
            JSONArray ja_arc_ttp = (JSONArray) jsonObject.get("arc-transition-to-place");
            for (Object o : ja_arc_ttp) {
                JSONObject jobj_arc = (JSONObject) o;
                String place_label = (String) jobj_arc.get("place");
                String transition_label = (String) jobj_arc.get("transition");

                Arc arc = new NormalArc(((Long) jobj_arc.get("weight")).intValue());

                Place curPlace = null;
                for (Place test_place : petriNet.places) {
                    if (test_place.getLabel().equals(place_label)) {
                        curPlace = test_place;
                        break;
                    }
                }

                Transition curTransition = null;
                for (Transition test_transition : petriNet.transitions) {
                    if (test_transition.getLabel().equals(transition_label)) {
                        curTransition = test_transition;
                        break;
                    }
                }
                petriNet.connect(curTransition, curPlace, arc);
            }

            // ARC PLACE TO TRANSITION
            JSONArray ja_arc_ptt = (JSONArray) jsonObject.get("arc-place-to-transition");
            for (Object o : ja_arc_ptt) {
                JSONObject jobj_arc = (JSONObject) o;
                String place_label = (String) jobj_arc.get("place");
                String transition_label = (String) jobj_arc.get("transition");
                int type = ((Long) jobj_arc.get("type")).intValue();

                Arc arc = switch (type) {
                    case 0 -> new NormalArc(((Long) jobj_arc.get("weight")).intValue());
                    case 1 -> new InhibitorArc(((Long) jobj_arc.get("weight")).intValue());
                    case 2 -> new ResetArc();
                    default -> null;
                };

                Place curPlace = null;
                for (Place test_place : petriNet.places) {
                    if (test_place.getLabel().equals(place_label)) {
                        curPlace = test_place;
                        break;
                    }
                }

                Transition curTransition = null;
                for (Transition test_transition : petriNet.transitions) {
                    if (test_transition.getLabel().equals(transition_label)) {
                        curTransition = test_transition;
                        break;
                    }
                }

                petriNet.connect(curPlace, curTransition, arc);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}
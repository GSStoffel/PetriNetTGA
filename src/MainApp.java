import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainApp {
    public static void main(String[] args){

        PetriNet petriNet = new PetriNet();

        JSONParser parser = new JSONParser();

        // List<Place> places = new ArrayList<>();
        // List<Transition> transitions = new ArrayList<>();
        // List<Arc> normal_arcs = new ArrayList<>();
        // List<Arc> inhibitor_arcs = new ArrayList<>();
        // List<Arc> reset_arcs = new ArrayList<>();

        //try (Reader reader = new FileReader("src/tests/test-01.json")) {
        try (Reader reader = new FileReader("src/tests/test-2-01.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);


            JSONArray ja_places = (JSONArray) jsonObject.get("places");
            for (int i=0; i < ja_places.size(); i++) {
                JSONObject jobj_place = (JSONObject) ja_places.get(i);
                Place cur_place = new Place(jobj_place.get("label").toString(), ((Long) jobj_place.get("tokens")).intValue());
                petriNet.places.add(cur_place);
                //System.out.println(cur_place.getLabel()+", "+cur_place.getTokens());
            }

            JSONArray ja_transitions = (JSONArray) jsonObject.get("transitions");
            for (int i=0; i < ja_transitions.size(); i++) {
                JSONObject jobj_transition = (JSONObject) ja_transitions.get(i);
                Transition cur_transition = new Transition(jobj_transition.get("label").toString());
                petriNet.transitions.add(cur_transition);
                //System.out.println(cur_transition.getLabel());
            }

            JSONArray ja_arc_ttp = (JSONArray) jsonObject.get("arc-transition-to-place");
            for (int i=0; i < ja_arc_ttp.size(); i++) {
                JSONObject jobj_arc = (JSONObject) ja_arc_ttp.get(i);
                String place_label = (String) jobj_arc.get("place");
                String transition_label = (String) jobj_arc.get("transition");

                Arc arc = new NormalArc(((Long)jobj_arc.get("weight")).intValue());

                for(Place test_place:petriNet.places){
                    if (test_place.getLabel().equals(place_label)){
                        arc.input = test_place;
                        break;
                    }
                }

                for(Transition test_transition:petriNet.transitions){
                    if (test_transition.getLabel().equals(transition_label)){
                        arc.output = test_transition;
                        break;
                    }
                }

                petriNet.normalArcs.add((NormalArc) arc);
                //System.out.println(arc.getInput().toString());
            }

            JSONArray ja_arc_ptt = (JSONArray) jsonObject.get("arc-place-to-transition");
            for (int i=0; i < ja_arc_ptt.size(); i++) {
                JSONObject jobj_arc = (JSONObject) ja_arc_ptt.get(i);
                String place_label = (String) jobj_arc.get("place");
                String transition_label = (String) jobj_arc.get("transition");
                int type = ((Long) jobj_arc.get("type")).intValue();

                Arc arc = null;

                switch (type){
                    case 0:
                        arc = new NormalArc(((Long)jobj_arc.get("weight")).intValue());
                        break;
                    case 1:
                        arc = new InhibitorArc(((Long)jobj_arc.get("weight")).intValue());
                        break;
                    case 2:
                        arc = new ResetArc();
                        break;
                }

                for(Place test_place:petriNet.places){
                    if (test_place.getLabel().equals(place_label)){
                        arc.input = test_place;
                        break;
                    }
                }

                for(Transition test_transition:petriNet.transitions){
                    if (test_transition.getLabel().equals(transition_label)){
                        arc.output = test_transition;
                        break;
                    }
                }

                switch (type){
                    case 0:
                        petriNet.normalArcs.add((NormalArc) arc);
                        break;
                    case 1:
                        petriNet.inhibitorArcs.add((InhibitorArc) arc);
                        break;
                    case 2:
                        petriNet.resetArcs.add((ResetArc) arc);
                        break;
                }
                System.out.println(arc.getInput().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

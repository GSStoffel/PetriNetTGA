import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class MainApp {
    public static void main(String[] args){

        Gson gson = new Gson();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/tests/test-01.json"));

            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // SETTINGS
            String str_settings = map.get("settings").toString().replaceAll("\\[", "").replaceAll("\\]","");
            Map<?, ?> settings = gson.fromJson(str_settings, Map.class);
            //System.out.println(settings.get("places").toString());




            // print map entries
            /*for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }*/

            // close reader
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

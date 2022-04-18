import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PetriIN {

	private PetriNet petriNet;
	
	public PetriIN() {
		petriNet = new PetriNet();
	}
	
	public void loadData(String filepath) {
		BufferedReader bfr;
		try {
			bfr = new BufferedReader(new FileReader(filepath));
			String line = bfr.readLine();
			
			int qtd_place = 0;
			int qtd_transition = 0;
			int qtd_arcs = 0;
			
			while (line != null) {
				line = bfr.readLine();
				String[] sline = line.split(" ");
				
				switch (sline[0]) {
				case "0":
					Place place = new Place();
					Transition transition = new Transition();
					break;
					
				case "2":	
					break;	

				default:
					break;
				}
			}
			bfr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

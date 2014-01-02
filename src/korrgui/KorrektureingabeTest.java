package korrgui;

import javax.swing.JFrame;

import korrdata.Klasse;
import korrdata.Korrekturbuch;
import korrdata.Lehrer;

public class KorrektureingabeTest {
	


	public static void main(String[] args) {
Lehrer l = new Lehrer("OStD","Proper","Heinz-Hammel-Schule");
		
		Klasse k = new Klasse("8a", "Mathematik", 2013, l);
				
		Korrekturbuch kb = new Korrekturbuch(k); 
		kb.getPruefungsliste().get(0).getKorrekturliste().setKorrekturListeFromFile();
		
		Korrektureingabe klfr = new Korrektureingabe(kb.getPruefungsliste().get(0));

		klfr.setVisible(true);
		
	}

}

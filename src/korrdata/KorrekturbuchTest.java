package korrdata;

public class KorrekturbuchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Korrekturbuch kb = new Korrekturbuch("8a");
		System.out.println(kb.toString());
		
		//Ausgabe des Namens der ersten Aufgabe der ersten Prüfung
		System.out.println(
		kb.Pruefungsliste.get(0).getAufgabenListe().Aufgabenliste.get(0).getName()
		);
	}

}

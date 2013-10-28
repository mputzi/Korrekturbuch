package korrdata;

import java.util.GregorianCalendar;

public class PruefungTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GregorianCalendar d = new GregorianCalendar(2013,10,28);
		Pruefungsarten.ART art = Pruefungsarten.ART.SA;
				
		Pruefung meinePruefung = new Pruefung(d, art, 1, 35 );
		
		Aufgabe neueAufgabe = new Aufgabe("1a", 3.0f);
		Aufgabe neueAufgabe2 = new Aufgabe("1b", 1.5f);
		Aufgabe neueAufgabe3 = new Aufgabe("2", 2.0f);
		
		AufgabeList aufgabenliste = new AufgabeList();
		aufgabenliste.addToAufgabeList(neueAufgabe);
		aufgabenliste.addToAufgabeList(neueAufgabe2);
		aufgabenliste.addToAufgabeList(neueAufgabe3);
		
		meinePruefung.setAufgabenListe(aufgabenliste);
		
		System.out.println(meinePruefung.toString());
	}

}

package korrdata;

import java.util.GregorianCalendar;

public class PruefungTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Schreiben in Datei
		// Init
		GregorianCalendar d = new GregorianCalendar(2013,10,28);
		Pruefungsarten.ART art = Pruefungsarten.ART.SA;
				
		Pruefung meinePruefung = new Pruefung(d, art, 1, 35 );
		
		Aufgabe neueAufgabe = new Aufgabe("1a", 3.0f);
		Aufgabe neueAufgabe2 = new Aufgabe("1b", 1.5f);
		Aufgabe neueAufgabe3 = new Aufgabe("2", 2.0f);
		Aufgabe neueAufgabe4 = new Aufgabe("3", 7.0f);
		
		AufgabeList aufgabenliste = new AufgabeList();
		aufgabenliste.addToAufgabeList(neueAufgabe);
		aufgabenliste.addToAufgabeList(neueAufgabe2);
		aufgabenliste.addToAufgabeList(neueAufgabe3);
		aufgabenliste.addToAufgabeList(neueAufgabe4);
		
		meinePruefung.setAufgabenListe(aufgabenliste);
		
		// Ausgeben
		System.out.println(meinePruefung.toString());
		// Schreiben
		meinePruefung.writePruefungToCSV("8a_Ma_2013_p1_p.csv");
		
		
		// Schreiben zweiter Datei
		// Init
		GregorianCalendar d2 = new GregorianCalendar(2013,10,28);
		Pruefungsarten.ART art2 = Pruefungsarten.ART.KA;
				
		Pruefung meinePruefung2 = new Pruefung(d2, art2, 2, 32 );
		
		Aufgabe neueAufgabeb = new Aufgabe("1", 5f);
		Aufgabe neueAufgabe2b = new Aufgabe("2", 6f);
		Aufgabe neueAufgabe3b = new Aufgabe("3", 7f);
		Aufgabe neueAufgabe4b = new Aufgabe("4", 2f);
		
		AufgabeList aufgabenliste2 = new AufgabeList();
		aufgabenliste2.addToAufgabeList(neueAufgabeb);
		aufgabenliste2.addToAufgabeList(neueAufgabe2b);
		aufgabenliste2.addToAufgabeList(neueAufgabe3b);
		aufgabenliste2.addToAufgabeList(neueAufgabe4b);
		
		meinePruefung2.setAufgabenListe(aufgabenliste2);
		
		// Ausgeben
		System.out.println(meinePruefung2.toString());
		// Schreiben
		meinePruefung2.writePruefungToCSV("8a_Ma_2013_p2_p.csv");
		
		// Lesen von Datei
		// Init
		Pruefung meinePruefung3 = new Pruefung(d, Pruefungsarten.ART.T, 2, 10 );
		AufgabeList aufgabenliste3 = new AufgabeList();
		meinePruefung3.setAufgabenListe(aufgabenliste3);
		// Lesen
		meinePruefung3.setPruefungFromFile("8a_Ma_2013_p1_p.csv");
		// Ausgeben
		System.out.println(meinePruefung3.toString());
		
		
	}

}

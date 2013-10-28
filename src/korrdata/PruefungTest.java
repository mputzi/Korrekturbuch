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
		meinePruefung.writePruefungToCSV("prueftest.csv");
		
		// Lesen von Datei
		// Init
		Pruefung meinePruefung2 = new Pruefung(d, Pruefungsarten.ART.T, 2, 10 );
		AufgabeList aufgabenliste2 = new AufgabeList();
		meinePruefung2.setAufgabenListe(aufgabenliste2);
		// Lesen
		meinePruefung2.setPruefungFromFile("prueftest.csv");
		// Ausgeben
		System.out.println(meinePruefung2.toString());
		
		
	}

}

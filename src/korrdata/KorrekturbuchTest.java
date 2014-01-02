package korrdata;

public class KorrekturbuchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lehrer l = new Lehrer("OStD","Proper","Heinz-Hammel-Schule");
		
		Klasse k = new Klasse("8a", "Mathematik", 2013, l);
				
		Korrekturbuch kb = new Korrekturbuch(k);
		
		System.out.println(kb.toString());
		
		/*
		//Ausgabe des Fachs des Korrekturbuchs
		System.out.println(kb.getFach());
		
		//Ausgabe des Namens der ersten Aufgabe der ersten Prüfung
		System.out.println(
		kb.Pruefungsliste.get(0).getAufgabenListe().Aufgabenliste.get(0).getName()
		);
		
		
		*/
		
		//kb.neuePruefung(4, 3, 1999, 1, Pruefungsarten.ART.T, 5);
		
		kb.printPruefungen();

		//Speichern des Korrekturbuchs
		//		kb.writeKorrekturBuch();
		
		/*
		System.out.println(kb.getKBKlasse().getSchuelerL().toString());
		System.out.println(kb.getPruefungsliste().get(0).getKb().getKBKlasse().getSchuelerL().toString());
		System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().getSchuelerList().toString());
		*/
		
		/*
		kb.getPruefungsliste().get(0).getKorrekturliste().setErreichtAt(0, 0, 1);
		kb.getPruefungsliste().get(0).getKorrekturliste().setErreichtAt(1, 1, 2);
		kb.getPruefungsliste().get(0).getKorrekturliste().setErreichtAt(2, 2, 3);
		*/
		
		System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().toString());
		
		//kb.getPruefungsliste().get(0).getKorrekturliste().writeKorrekturListe();
		kb.getPruefungsliste().get(0).getKorrekturliste().setKorrekturListeFromFile();
		System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().toString());
		
		System.out.println(kb.getPruefungsliste().get(0).getGesamtPunktzahl());
		
		kb.getPruefungsliste().get(0).getKorrekturliste().printGesamtBEListe();
		kb.getPruefungsliste().get(0).getKorrekturliste().printNoten();
		System.out.println("Durchschnitt: " + kb.getPruefungsliste().get(0).getDurchschnitt());
		System.out.println("1/2 Anteil  : " + kb.getPruefungsliste().get(0).getAnteil12());
		System.out.println("5/6 Anteil  : " + kb.getPruefungsliste().get(0).getAnteil56());
	}

}

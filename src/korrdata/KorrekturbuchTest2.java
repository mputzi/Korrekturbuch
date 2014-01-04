package korrdata;

public class KorrekturbuchTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lehrer l = new Lehrer("StRef","Kumpel","Franz-Lamm-Schule");
		
		Klasse k = new Klasse("12a", "Deutsch", 2010, l);
		
		k.addSchuelerToKlasse("Lucky","Luke");
		k.addSchuelerToKlasse("Dagobert","Duck");
		k.addSchuelerToKlasse("Tick","Duck");
		k.addSchuelerToKlasse("Trick","Duck");
		k.addSchuelerToKlasse("Truck","Duck");
		k.addSchuelerToKlasse("Goofy","");
		k.addSchuelerToKlasse("Mickey","Mouse");
		k.addSchuelerToKlasse("Minnie","Mouse");
		k.addSchuelerToKlasse("Jolly","Jumper");
		k.addSchuelerToKlasse("Rantanplan","");
		k.addSchuelerToKlasse("Joe","Dalton");
		k.addSchuelerToKlasse("William","Dalton");
		k.addSchuelerToKlasse("Jack","Dalton");
		k.addSchuelerToKlasse("Averell","Dalton");
		k.addSchuelerToKlasse("Billy","The Kid");
		k.addSchuelerToKlasse("Jessie","James");
		
		k.writeSchuelerList();
		
		Korrekturbuch kb = new Korrekturbuch(k);
		/*
		System.out.println(kb.toString());
		
		//Ausgabe des Fachs des Korrekturbuchs
		System.out.println(kb.getFach());
		
		//Ausgabe des Namens der ersten Aufgabe der ersten Prüfung
		System.out.println(
		kb.Pruefungsliste.get(0).getAufgabenListe().Aufgabenliste.get(0).getName()
		);
		*/
		
		kb.neuePruefung(4, 3, 2011, 1, Pruefungsarten.ART.T);
		
		Pruefung pr = kb.getPruefungsliste().get(0);
		
		pr.addAufgabeToList(new Aufgabe("1a",3));
		pr.addAufgabeToList(new Aufgabe("1b",5));
		pr.addAufgabeToList(new Aufgabe("2",6));
		pr.update();
		// Aufgabenliste der ersten Prüfung im Korrekturbuch
		//System.out.println(kb.getPruefungsliste().get(0).getAufgabenListe().toString());
		
		pr.writePruefungToCSV();
		// Speichern des Korrekturbuchs
		kb.writeKorrekturBuch();
		
		kb.printPruefungen();
		
		//System.out.println(pr.getGesamtPunktzahl());
    	//kb.writeKorrekturBuch();
		
		/*
		System.out.println(kb.getKBKlasse().getSchuelerL().toString());
		System.out.println(kb.getPruefungsliste().get(0).getKb().getKBKlasse().getSchuelerL().toString());
		System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().getSchuelerList().toString());
		*/
		
    	//System.out.println(kb.getPruefungsliste().get(0).toString());
    	//System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().toString());
		
		KorrekturListe kl = kb.getPruefungsliste().get(0).getKorrekturliste();
		
		kl.setErreichtAt(0, 0, 3);
		kl.setErreichtAt(0, 1, 4);
		kl.setErreichtAt(0, 2, 3.5f);
		kl.setErreichtAt(1, 0, 3);
		kl.setErreichtAt(1, 1, 5);
		kl.setErreichtAt(1, 2, 6);
				
		kl.setAnwesendAtIndex(false, 0);
		
		//System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().toString());
		
		kl.writeKorrekturListe();
		kl.writeAnwesendListe();
		/*
		kb.getPruefungsliste().get(0).getKorrekturliste().setKorrekturListeFromFile();
		kb.getPruefungsliste().get(0).getKorrekturliste().setAnwesendListeFromFile();
		*/
		// System.out.println(kb.getPruefungsliste().get(0).getKorrekturliste().toString());
		
		
		
		//kb.getPruefungsliste().get(0).getKorrekturliste().printGesamtBEListe();
		kl.printNoten();
		System.out.println("Durchschnitt: " + kb.getPruefungsliste().get(0).getDurchschnitt());
		System.out.println("1/2 Anteil  : " + kb.getPruefungsliste().get(0).getAnteil12());
		System.out.println("5/6 Anteil  : " + kb.getPruefungsliste().get(0).getAnteil56());
		
	}

}

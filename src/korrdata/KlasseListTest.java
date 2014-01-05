package korrdata;

public class KlasseListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lehrer grossmeister = new Lehrer("OStD","Proper","Heinz-Hammel-Schule");
		
		Klasse meineKlasse1 = new Klasse("8a", "Mathematik", 2013, grossmeister);
		Klasse meineKlasse2 = new Klasse("8b", "Physik", 2013, grossmeister);
		Klasse meineKlasse3 = new Klasse("8c", "Mathematik", 2012, grossmeister);
		
		KlasseList meineKlassenliste = new KlasseList();
		
		meineKlassenliste.addToKlasseList(meineKlasse1);
		meineKlassenliste.addToKlasseList(meineKlasse2);
		meineKlassenliste.addToKlasseList(meineKlasse3);
		
		System.out.println(meineKlassenliste.toString());
		
		meineKlassenliste.writeKlasseListToCSV("klassenliste.csv");
		
		KlasseList meineKlassenliste2 = new KlasseList();
		meineKlassenliste2.readKlasseListFromCSV("klassenliste.csv");
		
		System.out.println(meineKlassenliste2.toString());
		
		//Nur der Lehrer:
		System.out.println(meineKlassenliste2.getKlassenliste().get(0).getLehrer().toString());
		//Korrekturbuch-Datei:
		System.out.println(meineKlassenliste2.getKlassenliste().get(0).getKorrBuchFilename());
		//Schülerliste-Datei:
		System.out.println(meineKlassenliste2.getKlassenliste().get(0).getSchuelerListFilename());
	
		
	}

}

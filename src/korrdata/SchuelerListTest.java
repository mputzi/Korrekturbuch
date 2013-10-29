package korrdata;

public class SchuelerListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--- Test der Schülerliste ---");
		
		SchuelerList Liste = new SchuelerList();
		//Liste.addToSchuelerList(new Schueler(1,"Martin","Mustermann"));
		Liste.setSchuelerListFromCSV("testcase_13g.csv");
		System.out.println(Liste.toString());
		Liste.writeSchuelerListToCSV("8a_Mathematik_2013_SL.csv");
		
		
		
	}

}

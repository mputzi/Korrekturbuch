package korrdata;

//import java.io.File;


/**
 * Class Klasse
 */
public class Klasse {

	//
	// Fields
	//

	private String klBez = "";
	private String fach = "";
	private int schuljahr = 0;

	private Lehrer lehrer = new Lehrer();

	private String schuelerListFilename = "";
	private SchuelerList schuelerL = new SchuelerList();
	private int schuelerzahl = 0;

	private String korrBuchFilename  ="";
	private Korrekturbuch korrBuch;

	//
	// Constructors
	//
	public Klasse () { };
	//public Klasse ( String bez, String fach ,int sj) { klasseAnlegen(bez,fach,sj);};
	public Klasse ( String bez, String fach ,int sj, Lehrer L) { klasseAnlegen(bez,fach,sj,L);};
	//public Klasse ( String bez, String fach ,int sj, String SLFile) { klasseAnlegen(bez,fach,sj, SLFile);};

	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Set the value of klBez
	 * @param newVar the new value of klBez
	 */
	private void setKlBez ( String newVar ) {
		klBez = newVar;
	}

	/**
	 * Get the value of klBez
	 * @return the value of klBez
	 */
	public String getKlBez ( ) {
		return klBez;
	}

	/**
	 * Set the value of schuljahr
	 * @param newVar the new value of schuljahr
	 */
	private void setSchuljahr ( int newVar ) {
		schuljahr = newVar;
	}

	/**
	 * Get the value of schuljahr
	 * @return the value of schuljahr
	 */
	public int getSchuljahr ( ) {
		return schuljahr;
	}

	/**
	 * Set the value of m_lehrer
	 * @param newVar the new value of m_lehrer
	 */
	public void setLehrer ( Lehrer newVar ) {
		lehrer = newVar;
	}

	/**
	 * Get the value of m_lehrer
	 * @return the value of m_lehrer
	 */
	public Lehrer getLehrer ( ) {
		return lehrer;
	}

	public String getFach() {
		return fach;
	}
	public void setFach(String fach) {
		this.fach = fach;
	}
	
	public int getSchuelerzahl() {
		return schuelerzahl;
	}
	public void setSchuelerzahl(int schuelerzahl) {
		this.schuelerzahl = schuelerzahl;
	}
	public SchuelerList getSchuelerL() {
		return schuelerL;
	}
	public void setSchuelerL(SchuelerList schuelerL) {
		this.schuelerL = schuelerL;
	}
	
	public String getSchuelerListFilename() {
		return schuelerListFilename;
	}
	private void setSchuelerListFilename(String schuelerListFilename) {
		this.schuelerListFilename = schuelerListFilename;
	}
	
	public Korrekturbuch getKorrBuch() {
		return korrBuch;
	}
	public void setKorrBuch(Korrekturbuch korrBuch) {
		this.korrBuch = korrBuch;
	}
	
	public String getKorrBuchFilename() {
		return korrBuchFilename;
	}
	private void setKorrBuchFilename(String korrBuchFilename) {
		this.korrBuchFilename = korrBuchFilename;
	}
	
	//
	// Other methods
	//


	/**
	 * 
	 * @param bez Bezeichnung der Klasse
	 * @param sj Schuljahr
	 * 
	 */
	/*
	public void klasseAnlegen( String bez, String fach, int sj)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setSchuelerListFilename(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
		this.readSchuelerListFromFile();
		this.readSchuelerAnz();

		System.out.println("K: Neue Klasse "+ this.getKlBez() +"/" + this.getFach() + " wird erstellt.");
	}
	 */
	public void klasseAnlegen( String bez, String fach, int sj, Lehrer L)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setLehrer(L);
		
		setSchuelerListFilename(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
				
		this.readSchuelerListFromFile();
		this.readSchuelerAnz();
		System.out.println("K: Neue Klasse "+ this.getKlBez() +"/" + this.getFach() + " wird erstellt.");
	}
	/*
	public void klasseAnlegen( String bez, String fach, int sj, String SLFile)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setSchuelerListFilename(SLFile);
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
		this.readSchuelerListFromFile();
		this.readSchuelerAnz();
		System.out.println("K: Neue Klasse "+ this.getKlBez() +"/" + this.getFach() + " wird erstellt.");
	}
	 */




	/*
public void setKorrBuchFromFile(String filename) {
	//this.korrBuch = korrBuch;
}

public void writeKorrBuchToFile(String filename) {

}*/

	private void readSchuelerListFromFile(){
		SchuelerList SLtmp = new SchuelerList();
		SLtmp.setSchuelerListFromCSV(this.getSchuelerListFilename());
		this.setSchuelerL(SLtmp);
	}

	public void readSchuelerAnz(){
		int anz = 0;
		anz = this.getSchuelerL().getAnz();
		this.setSchuelerzahl(anz);
	}

	public void writeSchuelerList() {
		this.writeSchuelerListToFile(new String(this.getSchuelerListFilename()));
	}

	private void writeSchuelerListToFile(String filename) {
		getSchuelerL().writeSchuelerListToCSV(filename);
	}
	
	public void deleteSchuelerList() {
		this.deleteSchuelerListFile(new String(this.getSchuelerListFilename()));
	}

	private void deleteSchuelerListFile(String filename) {
		getSchuelerL().deleteSchuelerListFile(filename);
	}

	public void addSchuelerToKlasse(String vorname, String nachname){
		this.getSchuelerL().addToSchuelerList(new Schueler(vorname,nachname));
		this.setSchuelerzahl(this.getSchuelerzahl()+1);
	};

	public void addSchuelerToKlasse(int id, String vorname, String nachname){
		this.getSchuelerL().addToSchuelerList(new Schueler(id,vorname,nachname));
		this.setSchuelerzahl(this.getSchuelerzahl()+1);
	};
	/*
	public void removeSchuelerFromKlasse(String vorname, String nachname){
		while(this.getSchuelerL().Schuelerliste.)
		if(this.getSchuelerL().Schuelerliste.c)) {
			int id = this.getSchuelerL().Schuelerliste.
			this.getSchuelerL().removeFromSchuelerList(new Schueler(id, vorname,nachname));
			this.setSchuelerzahl(this.getSchuelerzahl()-1);
		}

	};*/

	public void removeSchuelerFromKlasse(int id, String vorname, String nachname){
		this.getSchuelerL().removeFromSchuelerList(new Schueler(id,vorname,nachname));
		this.setSchuelerzahl(this.getSchuelerzahl()+1);
	};

	public String toString(){
		return new String("K: " + getKlBez() +
				", Fach " 	+ getFach() +
				", Schuljahr " 	+ getSchuljahr() +"/"+ (getSchuljahr()+1) + 
				", Lehrer: " + getLehrer().toString() +
				", Schülerzahl: " + getSchuelerzahl());
	}

}

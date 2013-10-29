package korrdata;

import java.io.File;


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
	private SchuelerList schuelerL;
	private int schuelerzahl = 0;

	private String korrBuchFilename  ="";
	private Korrekturbuch korrBuch;

	//
	// Constructors
	//
	public Klasse () { };
	public Klasse ( String bez, String fach ,int sj) { klasseAnlegen(bez,fach,sj);};
	public Klasse ( String bez, String fach ,int sj, Lehrer L) { klasseAnlegen(bez,fach,sj,L);};
	public Klasse ( String bez, String fach ,int sj, String SLFile) { klasseAnlegen(bez,fach,sj, SLFile);};

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

	//
	// Other methods
	//

	public int getSchuelerzahl() {
		return schuelerzahl;
	}
	public void setSchuelerzahl(int schuelerzahl) {
		this.schuelerzahl = schuelerzahl;
	}
	/**
	 * 
	 * @param bez Bezeichnung der Klasse
	 * @param sj Schuljahr
	 * 
	 */
	public void klasseAnlegen( String bez, String fach, int sj)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setSchuelerListFilename(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
		
		this.readSchuelerAnz();
	}

	public void klasseAnlegen( String bez, String fach, int sj, Lehrer L)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setSchuelerListFilename(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
		setLehrer(L);
		
		this.readSchuelerAnz();
	}

	public void klasseAnlegen( String bez, String fach, int sj, String SLFile)
	{
		setKlBez(bez);
		setSchuljahr(sj);
		setFach(fach);
		setSchuelerListFilename(SLFile);
		setKorrBuchFilename(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
		
		this.readSchuelerAnz();
	}

	public String toString(){
		return new String(getKlBez() +
	", Fach " 	+ getFach() +
	", Schuljahr " 	+ getSchuljahr() +"/"+ (getSchuljahr()+1) + 
	", Lehrer: " + getLehrer().toString() +
	", Schülerzahl: " + getSchuelerzahl());
	}
	public String getFach() {
		return fach;
	}
	public void setFach(String fach) {
		this.fach = fach;
	}
	public Korrekturbuch getKorrBuch() {
		return korrBuch;
	}
	public void setKorrBuch(Korrekturbuch korrBuch) {
		this.korrBuch = korrBuch;
	}

	/*
public void setKorrBuchFromFile(String filename) {
	//this.korrBuch = korrBuch;
}

public void writeKorrBuchToFile(String filename) {

}*/

	public String getSchuelerListFilename() {
		return schuelerListFilename;
	}
	public void setSchuelerListFilename(String schuelerListFilename) {
		this.schuelerListFilename = schuelerListFilename;
	}

	public void writeSchuelerListToFile(String filename) {
		schuelerL.writeSchuelerListToCSV(filename);
	}

	public String getKorrBuchFilename() {
		return korrBuchFilename;
	}
	public void setKorrBuchFilename(String korrBuchFilename) {
		this.korrBuchFilename = korrBuchFilename;
	}

	private void readSchuelerAnz(){
		int anz = 0;
		
		SchuelerList SList = new SchuelerList();
		File f = new File(this.getSchuelerListFilename());
 		if(!f.exists()){ this.setSchuelerzahl(anz);}
 		else{
		
		SList.setSchuelerListFromCSV(this.getSchuelerListFilename());
		
		anz = SList.getAnz();
		
		this.setSchuelerzahl(anz);
 		}
	}

}

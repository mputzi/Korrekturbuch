package korrdata;


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
  
  private String schuelerListFile = "";
  private SchuelerList schuelerL;
  private int schuelerzahl = 0;
  
  private String korrBuchFile  ="";
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
	  setSchuelerListFile(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
	  setKorrBuchFile(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
  }
  
  public void klasseAnlegen( String bez, String fach, int sj, Lehrer L)
  {
	  setKlBez(bez);
	  setSchuljahr(sj);
	  setFach(fach);
	  setSchuelerListFile(new String(bez + "_" + fach + "_" + sj + "_SL.csv"));
	  setKorrBuchFile(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
	  setLehrer(L);
  }
  
  public void klasseAnlegen( String bez, String fach, int sj, String SLFile)
  {
	  setKlBez(bez);
	  setSchuljahr(sj);
	  setFach(fach);
	  setSchuelerListFile(SLFile);
	  setKorrBuchFile(new String(bez + "_" + fach + "_" + sj + "_KB.csv"));
  }

  public String toString(){
	  return new String(getKlBez() + ", Schuljahr " + getSchuljahr() +"/"+ (getSchuljahr()+1) + ", Lehrer: " + getLehrer().toString());
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

public String getSchuelerListFile() {
	return schuelerListFile;
}
public void setSchuelerListFile(String schuelerListFile) {
	this.schuelerListFile = schuelerListFile;
}

public void writeSchuelerListToFile(String filename) {
	schuelerL.writeSchuelerListToCSV(filename);
}

public String getKorrBuchFile() {
	return korrBuchFile;
}
public void setKorrBuchFile(String korrBuchFile) {
	this.korrBuchFile = korrBuchFile;
}

public int read

}

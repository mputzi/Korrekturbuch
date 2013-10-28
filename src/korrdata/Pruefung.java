package korrdata;


import java.util.*;


/**
 * Class Pruefung
 */
public class Pruefung {

  //
  // Fields
  //

  private static final int Aufgabenliste = 0;
  private GregorianCalendar datum;
  private Pruefungsarten.ART artKey;
  private int nummer;
  private int anzTeilnehmer;
  
  private AufgabeList aufgabenListe;
  
    
  //
  // Constructors
  //
  public Pruefung () { };
  public Pruefung (GregorianCalendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz) {
	  datum = prDatum;
	  artKey = prArtKey;
	  nummer = prNummer;
	  anzTeilnehmer = anz;
  };
  public Pruefung (GregorianCalendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz, AufgabeList prListe) {
	  datum = prDatum;
	  artKey = prArtKey;
	  nummer = prNummer;
	  anzTeilnehmer = anz;
	  aufgabenListe = prListe;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of datum
   * @param newVar the new value of datum
   */
  private void setDatum ( GregorianCalendar newVar ) {
    datum = newVar;
  }

  /**
   * Get the value of datum
   * @return the value of datum
   */
  private Date getDatum ( ) {
    return datum;
  }

  /**
   * Set the value of nummer
   * @param newVar the new value of nummer
   */
  private void setNummer ( int newVar ) {
    nummer = newVar;
  }

  /**
   * Get the value of nummer
   * @return the value of nummer
   */
  private int getNummer ( ) {
    return nummer;
  }

  /**
   * Set the value of anzTeilnehmer
   * @param newVar the new value of anzTeilnehmer
   */
  private void setAnzTeilnehmer ( int newVar ) {
    anzTeilnehmer = newVar;
  }

  /**
   * Get the value of anzTeilnehmer
   * @return the value of anzTeilnehmer
   */
  private int getAnzTeilnehmer ( ) {
    return anzTeilnehmer;
  }

  //
  // Other methods
  //

  /**
   * @return       float
   */
  public float getGesamtPunktzahl(  )
  {
	  float gesamt = 0.0f;
	  
	  //for
	  
	  return gesamt;
  }


  /**
   * @return       float
   */
  public float getDurchschnitt(  )
  {	
	  float durch = 0.0f;
	  return durch;
  }


  /**
   * @return       float
   */
  public float getAnteil45(  )
  {
	  float anteil = 0.0f;
	  return anteil;
  }


  /**
   * @return       float
   */
  public float getAnteil12(  )
  {
	  float anteil = 0.0f;
	  return anteil;
  }

public AufgabeList getAufgabenListe() {
	return aufgabenListe;
}

public void setAufgabenListe(AufgabeList aufgabenListe) {
	this.aufgabenListe = aufgabenListe;
}

public boolean addAufgabeToList(Aufgabe a){
	aufgabenListe.addToAufgabeList(a);
	return true;
}


public String toString(){
	  //System.out.println("Hallo!");
	  return new String(nummer+". " + Pruefungsarten.getDesc(artKey) +
			  " vom " + datum.get(Calendar.DAY_OF_MONTH) + "." + (datum.get(Calendar.MONTH)+1) + "." +  datum.get(Calendar.YEAR) + "\n" +
			  			  "Inhalt der Prüfung: " + aufgabenListe + "\n" + 
			            "Anzahl der Teilnehmer: " + anzTeilnehmer);
}

public Pruefungsarten.ART getPrArtKey() {
	return artKey;
}

public void setPrArtKey(Pruefungsarten.ART prArtKey) {
	this.artKey = prArtKey;
}


}

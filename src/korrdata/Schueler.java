package korrdata;


import java.util.*;


/**
 * Class Schueler
 */
public class Schueler {

  //
  // Fields
  //

  private String vorname;
  private String nachname;
  
  //
  // Constructors
  //
  public Schueler () { };
  public Schueler (String v, String n) {
	  setVorname(v);
	  setNachname(n);
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of vorname
   * @param newVar the new value of vorname
   */
  private void setVorname ( String newVar ) {
    vorname = newVar;
  }

  /**
   * Get the value of vorname
   * @return the value of vorname
   */
  public String getVorname ( ) {
    return vorname;
  }

  /**
   * Set the value of nachname
   * @param newVar the new value of nachname
   */
  private void setNachname ( String newVar ) {
    nachname = newVar;
  }

  /**
   * Get the value of nachname
   * @return the value of nachname
   */
  public String getNachname ( ) {
    return nachname;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   * @param        vorname
   * @param        nachname
   */
  public int schuelerAendern( String vorname, String nachname )
  {
	  return 0;
  }

  public String toString(){
	  return new String(getVorname() + " " + getNachname());
  }

}

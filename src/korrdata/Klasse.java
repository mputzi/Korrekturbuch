package korrdata;


import java.util.*;


/**
 * Class Klasse
 */
public class Klasse {

  //
  // Fields
  //

  private String klBez;
  private int schuljahr;

  private Lehrer m_lehrer;
  
  //
  // Constructors
  //
  public Klasse () { };
  public Klasse ( String bez, int sj, Lehrer l ) { klasseAnlegen(bez,sj,l);};
  
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
  private String getKlBez ( ) {
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
  private int getSchuljahr ( ) {
    return schuljahr;
  }

  /**
   * Set the value of m_lehrer
   * @param newVar the new value of m_lehrer
   */
  public void setLehrer ( Lehrer newVar ) {
    m_lehrer = newVar;
  }

  /**
   * Get the value of m_lehrer
   * @return the value of m_lehrer
   */
  public Lehrer getLehrer ( ) {
    return m_lehrer;
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
  public void klasseAnlegen( String bez, int sj, Lehrer l )
  {
	  setKlBez(bez);
	  setSchuljahr(sj);
	  setLehrer(l);
  }

  public String toString(){
	  return new String(getKlBez() + ", Schuljahr " + getSchuljahr() +"/"+ (getSchuljahr()+1) + ", Lehrer: " + getLehrer().toString());
  }

}

package korrdata;


import java.util.*;


/**
 * Class Lehrer
 */
public class Lehrer {

  //
  // Fields
  //

  private String nachname;
  private String amtsbez;
  private String schule;

  public ArrayList<Klasse> klasseList = new ArrayList<Klasse>();
  
  //
  // Constructors
  //
  public Lehrer () { };
  
  /**
   * Set the fields of Lehrer
   * @param a Amtsbezeichnung
   * @param n Nachname
   * @param s Schule
   */
  public Lehrer (String a, String n, String s) {
	  setNachname(n);
	  setAmtsbez(a);
	  setSchule(s);
  };
  
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

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
  private String getNachname ( ) {
    return nachname;
  }

  /**
   * Set the value of amtsbez
   * @param newVar the new value of amtsbez
   */
  private void setAmtsbez ( String newVar ) {
    amtsbez = newVar;
  }

  /**
   * Get the value of amtsbez
   * @return the value of amtsbez
   */
  private String getAmtsbez ( ) {
    return amtsbez;
  }

  /**
   * Set the value of schule
   * @param newVar the new value of schule
   */
  private void setSchule ( String newVar ) {
    schule = newVar;
  }

  /**
   * Get the value of schule
   * @return the value of schule
   */
  private String getSchule ( ) {
    return schule;
  }

  /**
   * Add a Klasse object to the klasseVector List
   */
  public void addKlasse ( Klasse new_object ) {
    klasseList.add(new_object);
  }

  /**
   * Remove a Klasse object from klasseVector List
   */
  public void removeKlasse ( Klasse new_object )
  {
    klasseVector.remove(new_object);
  }

  /**
   * Get the List of Klasse objects held by klasseVector
   * @return List of Klasse objects held by klasseVector
   */
  public ArrayList<Klasse> getKlasseList() {
    return  klasseList;
  }


  //
  // Other methods
  //
  
  public String toString(){
	  return new String(amtsbez + " " + nachname + ", " + schule);
  }

  
}

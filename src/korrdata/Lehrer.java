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
  public String getNachname( ) {
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
  public String getAmtsbez ( ) {
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
  public String getSchule ( ) {
    return schule;
  }


  //
  // Other methods
  //
  
  public String toString(){
	  return new String(amtsbez + " " + nachname + " " + schule);
  }

  public void fromString(String input){
	  String[] strarr = input.split("\\s+");
	  setAmtsbez(strarr[0]);
	  setNachname(strarr[1]);
	  setSchule(strarr[2]);
	  
  }
  
}

package korrdata;

import java.util.*;


/**
 * Class aufgabe
 */
public class Aufgabe {

  //
  // Fields
  //

  private String name;
  private float punkte;
  
  //
  // Constructors
  //
  public Aufgabe () { };
  
  public Aufgabe (float BE) {
	  setPunkte(BE);
  };
  
  public Aufgabe (String bez, float BE){
	  setPunkte(BE);
	  setName(bez);
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of name
   * @param newVar the new value of name
   */
  private void setName ( String newVar ) {
    name = newVar;
  }

  /**
   * Get the value of name
   * @return the value of name
   */
  private String getName ( ) {
    return name;
  }

  /**
   * Set the value of punkte
   * @param newVar the new value of punkte
   */
  private void setPunkte ( float newVar ) {
    punkte = newVar;
  }

  /**
   * Get the value of punkte
   * @return the value of punkte
   */
  private float getPunkte ( ) {
    return punkte;
  }

  //
  // Other methods
  //

}

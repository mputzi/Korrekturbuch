
import java.util.*;


/**
 * Class aufgabe
 */
public class aufgabe {

  //
  // Fields
  //

  private String name;
  private float punkte;
  
  //
  // Constructors
  //
  public aufgabe () { };
  
  public aufgabe (float BE) {
	  setPunkte(BE);
  };
  
  public aufgabe (String bez, float BE){
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

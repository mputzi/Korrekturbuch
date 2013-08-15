

/**
 * Class NSchluessel
 */
public class NSchluessel {

  //
  // Fields
  //

  private int[] noten;
  private boolean grenzePos;
  private float[] grenzen;
  
  //
  // Constructors
  //
  public NSchluessel () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of noten
   * @param newVar the new value of noten
   */
  private void setNoten ( int[] newVar ) {
    noten = newVar;
  }

  /**
   * Get the value of noten
   * @return the value of noten
   */
  private int[] getNoten ( ) {
    return noten;
  }

  /**
   * Set the value of grenzePos
   * @param newVar the new value of grenzePos
   */
  private void setGrenzePos ( boolean newVar ) {
    grenzePos = newVar;
  }

  /**
   * Get the value of grenzePos
   * @return the value of grenzePos
   */
  private boolean getGrenzePos ( ) {
    return grenzePos;
  }

  /**
   * Set the value of grenzen
   * @param newVar the new value of grenzen
   */
  private void setGrenzen ( float[] newVar ) {
    grenzen = newVar;
  }

  /**
   * Get the value of grenzen
   * @return the value of grenzen
   */
  private float[] getGrenzen ( ) {
    return grenzen;
  }

  //
  // Other methods
  //

  /**
   * @return       int
   * @param        anteil
   */
  public int getNote( float anteil = 0 )
  {
  }


}

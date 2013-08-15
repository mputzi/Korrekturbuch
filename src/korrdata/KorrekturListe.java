package korrdata;


import java.util.*;


/**
 * Class KorrekturListe
 */
public class KorrekturListe {

  //
  // Fields
  //

  private bool[] anwesend;
  private float[][] erreicht;

  public SchuelerList m_schueler;
  
  //
  // Constructors
  //
  public KorrekturListe () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of anwesend
   * @param newVar the new value of anwesend
   */
  private void setAnwesend ( bool[] newVar ) {
    anwesend = newVar;
  }

  /**
   * Get the value of anwesend
   * @return the value of anwesend
   */
  private bool[] getAnwesend ( ) {
    return anwesend;
  }

  /**
   * Set the value of erreicht
   * @param newVar the new value of erreicht
   */
  private void setErreicht ( float[][] newVar ) {
    erreicht = newVar;
  }

  /**
   * Get the value of erreicht
   * @return the value of erreicht
   */
  private float[][] getErreicht ( ) {
    return erreicht;
  }

  /**
   * Set the value of m_schueler
   * @param newVar the new value of m_schueler
   */
  public void setSchueler ( SchuelerList newVar ) {
    m_schueler = newVar;
  }

  /**
   * Get the value of m_schueler
   * @return the value of m_schueler
   */
  public SchuelerList getSchueler ( ) {
    return m_schueler;
  }

  //
  // Other methods
  //

  /**
   */
  public void getListGesamtBE(  )
  {
  }


  /**
   */
  public void getListAnteilBE(  )
  {
  }


}

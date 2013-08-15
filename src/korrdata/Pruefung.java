
import java.util.*;


/**
 * Class Pruefung
 */
public class Pruefung {

  //
  // Fields
  //

  private Date datum;
  private int nummer;
  private int anzTeilnehmer;
  
  //
  // Constructors
  //
  public Pruefung () { };
  
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
  private void setDatum ( Date newVar ) {
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
  }


  /**
   * @return       float
   */
  public float getDurchschnitt(  )
  {
  }


  /**
   * @return       float
   */
  public float getAnteil45(  )
  {
  }


  /**
   * @return       float
   */
  public float getAnteil12(  )
  {
  }


}

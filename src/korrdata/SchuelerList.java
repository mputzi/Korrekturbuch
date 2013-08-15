
import java.util.*;


/**
 * Class SchuelerList
 */
public class SchuelerList {

  //
  // Fields
  //

  private int anz;

  public Vector punktelisteVector = new Vector();
  
  //
  // Constructors
  //
  public SchuelerList () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of anz
   * @param newVar the new value of anz
   */
  private void setAnz ( int newVar ) {
    anz = newVar;
  }

  /**
   * Get the value of anz
   * @return the value of anz
   */
  private int getAnz ( ) {
    return anz;
  }

  /**
   * Add a Punkteliste object to the punktelisteVector List
   */
  public void addPunkteliste ( KorrekturListe new_object ) {
    punktelisteVector.add(new_object);
  }

  /**
   * Remove a Punkteliste object from punktelisteVector List
   */
  public void removePunkteliste ( KorrekturListe new_object )
  {
    punktelisteVector.remove(new_object);
  }

  /**
   * Get the List of Punkteliste objects held by punktelisteVector
   * @return List of Punkteliste objects held by punktelisteVector
   */
  public List getPunktelisteList ( ) {
    return (List) punktelisteVector;
  }


  //
  // Other methods
  //

}

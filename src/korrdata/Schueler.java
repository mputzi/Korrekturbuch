package korrdata;



/**
 * Class Schueler
 */
public class Schueler {

  //
  // Fields
  //

  private String vorname;
  private String nachname;
  private int ID;
  
  //
  // Constructors
  //
  public Schueler () { };
  public Schueler (String v, String n) {
	  
	  setVorname(v);
	  setNachname(n);
  };
  public Schueler (int i, String v, String n) {
	  setID(i);
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

  /**
   * Set the value of ID
   * @param newVar the new value of ID
   */
  private void setID ( int newVar ) {
    ID = newVar;
  }

  /**
   * Get the value of ID
   * @return the value of ID
   */
  public int getID ( ) {
    return ID;
  }

  /**
   * Get the value of ID
   * @return the value of ID
   */
  public String getIDString( ) {
    return new String("" + getID());
  }
  
  //
  // Other methods
  //

  /**
   * @return       boolean
   * @param        i
   * @param        vorname
   * @param        nachname
   */
  public boolean schuelerAendern(int i, String vorname, String nachname )
  {
	  this.setID(i); 
	  this.setVorname(vorname);
	  this.setNachname(nachname);
	  return true;	  
  }
  
  /**
   * @return       boolean
   * @param        vorname
   * @param        nachname
   */ 
  public boolean schuelerAendern(String vorname, String nachname )
  {
	  /* this.setID(i); //no ID is being set. */ 
	  this.setVorname(vorname);
	  this.setNachname(nachname);
	  return true;	  
  }

  public String toString3(){
	  return new String(getVorname() + " " + getNachname());
  }
  
  public String toString2(){
	  return new String(getNachname() + ", " + getVorname());
  }
  
  public String toString(){
	  return new String(getID() + ", "+ getVorname() + " " + getNachname());
  }

}

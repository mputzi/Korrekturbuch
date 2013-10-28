package korrdata;

import java.util.*;


/**
 * Class SchuelerList
 */
public class AufgabeList {

  //
  // Fields
  //

  private int anz;

  public ArrayList<Aufgabe> Aufgabenliste = new ArrayList<Aufgabe>();
  
  //
  // Constructors
  //
  public AufgabeList () { };
  
  public AufgabeList (List<Aufgabe> liste) {
	  Aufgabenliste.clear();
	  Aufgabenliste.addAll(liste);
	    
  };
  
  
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
  private void setAnz( int newVar ) {
    anz = newVar;
  }

  /**
   * Get the value of anz
   * @return the value of anz
   */
  public int getAnz( ) {
    return anz;
  }

  /**
   * Add a Schueler to List
   */
  public void addToAufgabeList ( Aufgabe new_object ) {
	if(Aufgabenliste.contains(new_object)){
		System.out.println("Aufgabe bereits in Liste enthalten!");
		return;
	}
    Aufgabenliste.add(new_object);
    setAnz(Aufgabenliste.size());
  }

  /**
   * Remove a Schueler from list
   */
  public void removeFromAufgabeList ( Aufgabe new_object )
  {
    if(Aufgabenliste.contains(new_object)){
    	Aufgabenliste.remove(new_object);
    }
    else{
    	System.out.println("Aufgabe nicht in Liste enthalten.");
    }
  }
  
  public void setAufgabeList (List<Aufgabe> liste)
  {
    Aufgabenliste.clear();
    Aufgabenliste.addAll(liste);
    
    setAnz(Aufgabenliste.size());
  }
  
  
  public String toString(){
	  return new String("Inhalt der Aufgabenliste: " + Aufgabenliste);
  }
  
  
}
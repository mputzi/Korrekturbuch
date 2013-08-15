package korrdata;


import java.util.*;
import com.csvreader.CsvReader;

/**
 * Class SchuelerList
 */
public class SchuelerList {

  //
  // Fields
  //

  private int anz;

  public ArrayList<Schueler> Schuelerliste = new ArrayList<Schueler>();
  
  //
  // Constructors
  //
  public SchuelerList () { };
  
  public SchuelerList (List<Schueler> liste) {
	    Schuelerliste.clear();
	    Schuelerliste.addAll(liste);
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
  public void addToSchuelerList ( Schueler new_object ) {
	if(Schuelerliste.contains(new_object)){
		System.out.println("Schüler bereits in Liste enthalten!");
		return;
	}
    Schuelerliste.add(new_object);
    setAnz(Schuelerliste.size());
  }

  /**
   * Remove a Punkteliste object from punktelisteVector List
   */
  public void removeFromSchuelerList ( Schueler new_object )
  {
    if(Schuelerliste.contains(new_object)){
    	Schuelerliste.remove(new_object);
    }
    else{
    	System.out.println("Schüler nicht in Liste enthalten.");
    }
  }
  
  public void setSchuelerList (List liste )
  {
    Schuelerliste.clear();
    Schuelerliste.addAll(liste);
    
    setAnz(Schuelerliste.size());
  }
  
  public void setSchuelerListFromCSV(File f)
  {
    Schuelerliste.clear();
    
    CsvReader csvSchuelerListe = new CsvReader(f);
    csvSchuelerListe.readHeaders();
    
    
    
    
    Schuelerliste.addAll(liste);
    
    setAnz(Schuelerliste.size());
  }
  
//  /**
//   * Get the List of Punkteliste objects held by punktelisteVector
//   * @return List of Punkteliste objects held by punktelisteVector
//   */
//  public  getPunktelisteList ( ) {
//    return (List) punktelisteVector;
//  }


  //
  // Other methods
  //

}

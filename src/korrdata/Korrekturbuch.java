package korrdata;


import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.csvreader.CsvReader;


/**
 * Class Korrekturbuch
 */
public class Korrekturbuch {

  //
  // Fields
  //
  private int anz;
  public ArrayList<Pruefung> Pruefungsliste = new ArrayList<Pruefung>();
  //
  // Constructors
  //
  public Korrekturbuch () { };
  public Korrekturbuch (List<Pruefung> liste) {
	  Pruefungsliste.clear();
	  Pruefungsliste.addAll(liste);
  };
  //
  // Methods
  //
  /**
   * Add a Pruefung to Korrekturbuch
   */
   public void addToKorrekturbuch ( Pruefung new_object ) {
	if(Pruefungsliste.contains(new_object)){
		System.out.println("Prüfung bereits in Liste enthalten!");
		return;
	}
	Pruefungsliste.add(new_object);
    setAnz(Pruefungsliste.size());
  }

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

  //
  // Other methods
  //
  public String toString(){
	  return new String("Inhalt des Korrekturbuchs: " + Pruefungsliste);
  }
  
  class Filter implements FileFilter{
	  private String klBez;
	  
	  public Filter(String klassenBezeichnung){
		  klBez = klassenBezeichnung;
	  }
	  
	  public boolean accept(File file){
		  String fname = file.getName();
		  return (fname.startsWith(klBez) 
				  && fname.endsWith("p.csv")
				  );
	  }
  }
  
  public void readDirectory(String klassenBezeichnung){
	  File directory = new File(".");
	     
	  File[] files = directory.listFiles(new Filter(klassenBezeichnung));
	 
	  for(int i = 0; i<files.length; i++){
		  System.out.println(files[i].toString());
	  }
	  
  }
  
}

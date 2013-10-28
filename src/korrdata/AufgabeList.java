package korrdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


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
  
  
  public static boolean setAufgabenListeFromFile(String filename){
		 {
			    	  
			    

			  }
		 
		 public void writeAufgabenListeToCSV(String filename)
		  {

		 // before we open the file check to see if it already exists
			    File f = new File(filename);
		 		boolean alreadyExists = f.exists();
		 		
		 		if (alreadyExists){
		 				f.delete();
					}
		 		
		 		try {
		 			// use FileWriter constructor that specifies open for appending
		 			CsvWriter csvOutput = new CsvWriter(new FileWriter(filename, true), ',');
		 					
		 			
		 			/*
		 			// if the file didn't already exist then we need to write out the header line
		 			if (!alreadyExists)
		 			{*/
		 				csvOutput.write("Name");
		 				csvOutput.write("Punkte");
		 				csvOutput.endRecord();
		 			/*}
		 			// else assume that the file already has the correct header line
		 			*/
		 				 			
		 			// write out a few records
		 			for(int i = 0; i < Aufgabenliste.size(); i++){
		 		 			// write out a few records
		 				csvOutput.write(Aufgabenliste.get(i).getName());
		 	 			csvOutput.write(Aufgabenliste.get(i).getPunkteString()); 	 			
		 	 			csvOutput.endRecord();
		 			}
		 			
		 			csvOutput.close();
		 			
		 		} catch (IOException e) {
		 			e.printStackTrace();
		 		}
		 		
				    
		  }
  
  
}
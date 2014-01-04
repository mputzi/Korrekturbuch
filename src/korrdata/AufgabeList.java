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
		System.out.println("AL: Aufgabe bereits in Liste enthalten!");
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
    	System.out.println("AL: Aufgabe nicht in Liste enthalten.");
    }
  }
  
  public void setAufgabeList (List<Aufgabe> liste)
  {
    Aufgabenliste.clear();
    Aufgabenliste.addAll(liste);
    
    this.setAnz(Aufgabenliste.size());
  }
  
  
  public String toString(){
	  return new String("AL: Inhalt der Aufgabenliste: " + Aufgabenliste);
  }
  

  public boolean setAufgabenListeFromFile(String filename){

	  this.Aufgabenliste.clear();
	  Aufgabe tmp;
	  System.out.println("AL: Lese Aufgabenliste"); 

	  try{
		  CsvReader csvAufgabenListe = new CsvReader(filename);
		  csvAufgabenListe.readHeaders();

		  while (csvAufgabenListe.readRecord())
		  {
			  String name			= csvAufgabenListe.get("Name");
			  String be           = csvAufgabenListe.get("Punkte");

			  // perform program logic here
			  System.out.println(name + ", " + be);
			  float beFloat = Float.valueOf(be).floatValue();
			  tmp = new Aufgabe(name, beFloat);
			  this.addToAufgabeList(tmp);
		  }

		  csvAufgabenListe.close();
	  }
	  catch (FileNotFoundException e) {
		  e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }

	  this.setAnz(Aufgabenliste.size());
	  if(this.getAnz()==0) System.out.println("AL: Aufgabenliste leer!");
	  
	  return true;
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
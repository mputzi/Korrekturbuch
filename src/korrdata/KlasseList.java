package korrdata;

import java.util.*;
import java.io.*;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * Class KlasseList
 */
public class KlasseList {

  //
  // Fields
  //

  private int anz;

  public ArrayList<Klasse> Klassenliste = new ArrayList<Klasse>();
  
  //
  // Constructors
  //
  public KlasseList () { };
  
  public KlasseList (List<Klasse> liste) {
	    Klassenliste.clear();
	    Klassenliste.addAll(liste);
	    
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
  public void addToKlasseList ( Klasse new_object ) {
	if(Klassenliste.contains(new_object)){
		System.out.println("Klasse bereits in Liste enthalten!");
		return;
	}
    Klassenliste.add(new_object);
    setAnz(Klassenliste.size());
  }

  /**
   * Remove a Schueler from list
   */
  public void removeFromKlasseList ( Klasse new_object )
  {
    if(Klassenliste.contains(new_object)){
    	Klassenliste.remove(new_object);
    }
    else{
    	System.out.println("Klasse nicht in Liste enthalten.");
    }
  }
  
  public void setKlasseList (List liste )
  {
    Klassenliste.clear();
    Klassenliste.addAll(liste);
    
    setAnz(Klassenliste.size());
  }
  
  public void setKlasseListFromCSV(String filename)
  {
    Klassenliste.clear();
    Klasse tmp;

  
    try{
    CsvReader csvKlasseListe = new CsvReader(filename);
    csvKlasseListe.readHeaders();
    
    while (csvKlasseListe.readRecord())
	{
		String bez			= csvKlasseListe.get("Bezeichnung");
    	String fac     		= csvKlasseListe.get("Fach");
		String sja    		= csvKlasseListe.get("Schuljach");
		
				
		// perform program logic here
		System.out.println(id + ", " + vorname + ", " + nachname);
		int idNumber = Integer.valueOf(id).intValue();
		tmp = new Schueler(idNumber, vorname, nachname);
		addToSchuelerList(tmp);
	}

    csvKlasseListe.close();
    }
    catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
     
    setAnz(Klassenliste.size());
  }
  

  
  public String toString(){
	  return new String("Inhalt der Klassenliste: " + Klassenliste);
  }
  /*
  public void writeSchuelerListToCSV(String filename)
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
 			{*//*
 				csvOutput.write("ID");
 				csvOutput.write("Vorname");
 				csvOutput.write("Name");
 				csvOutput.endRecord();
 			/*}
 			// else assume that the file already has the correct header line
 			*/
 			/*
 			for(int i = 0; i < Schuelerliste.size(); i++){
 			// write out a few records
 				csvOutput.write(Schuelerliste.get(i).getIDString());
 	 			csvOutput.write(Schuelerliste.get(i).getVorname());
 	 			csvOutput.write(Schuelerliste.get(i).getNachname());
 	 			csvOutput.endRecord();
 			}
 			
 			csvOutput.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    
  }
*/
}

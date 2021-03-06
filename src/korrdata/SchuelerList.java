package korrdata;


import java.util.*;
import java.io.*;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

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
  public void addToSchuelerList ( Schueler new_object ) {
	if(Schuelerliste.contains(new_object)){
		System.out.println("Schüler bereits in Liste enthalten!");
		return;
	}
	if(new_object.getID()==0){
		int lastID =0;
	
		if(!Schuelerliste.isEmpty()){
		lastID = Schuelerliste.get(Schuelerliste.size()-1).getID();}
		
		new_object.schuelerAendern(lastID+1, new_object.getVorname(), new_object.getNachname());
	}
    Schuelerliste.add(new_object);
    setAnz(Schuelerliste.size());
  }

  /**
   * Remove a Schueler from list
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
  // not used
  /*
  public void setSchuelerList (List liste )
  {
    Schuelerliste.clear();
    Schuelerliste.addAll(liste);
    
    setAnz(Schuelerliste.size());
  }
  */
  
  public void setSchuelerListFromCSV(String filename)
  {
    Schuelerliste.clear();
    Schueler tmp;

    File f = new File(filename);
    boolean fileExists = f.exists();
		
	if (fileExists){
    
    try{
    CsvReader csvSchuelerListe = new CsvReader(filename);
    csvSchuelerListe.readHeaders();
    
    while (csvSchuelerListe.readRecord())
	{
		String id			= csvSchuelerListe.get("ID");
    	String vorname      = csvSchuelerListe.get("Vorname");
		String nachname     = csvSchuelerListe.get("Name");
				
		// perform program logic here
		System.out.println(id + ", " + vorname + ", " + nachname);
		int idNumber = Integer.valueOf(id).intValue();
		tmp = new Schueler(idNumber, vorname, nachname);
		addToSchuelerList(tmp);
	}

    csvSchuelerListe.close();
    }
    catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	else {System.out.println("SL: Datei existiert nicht" + filename);}
    setAnz(Schuelerliste.size());
  }
  
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
 			{*/
 				csvOutput.write("ID");
 				csvOutput.write("Vorname");
 				csvOutput.write("Name");
 				csvOutput.endRecord();
 			/*}
 			// else assume that the file already has the correct header line
 			*/
 			
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
  
  public void deleteSchuelerListFile(String filename){
	  File f = new File(filename);
		boolean alreadyExists = f.exists();
		
		if (alreadyExists){
				f.delete();
			}
		
  }
  
  public String toString(){
	  return new String("SL: " + Schuelerliste);
  }
  
  

}

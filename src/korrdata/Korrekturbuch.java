package korrdata;


import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import korrdata.Pruefungsarten.ART;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * Class Korrekturbuch
 */
public class Korrekturbuch {

  //
  // Fields
  //
  private String klassenBezeichnung;
  
  private Klasse KBKlasse;
  
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
  
  public Korrekturbuch (String klassenBezeichnung) {
	  this.setKlassenBezeichnung(klassenBezeichnung);
	  if(this.fillKorrekturbuch()){
		  System.out.println("Prüfungen vorhanden!");
	  }
	  else{
		  System.out.println("Keine Prüfungen vorhanden!");
	  }
  };
  
  public Korrekturbuch (Klasse kl) {
	  this.setKlassenBezeichnung(kl.getKlBez());
	  if(this.fillKorrekturbuch()){
		  System.out.println("Prüfungen vorhanden!");
	  }
	  else{
		  System.out.println("Keine Prüfungen vorhanden!");
	  }
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
  
   /**
    * Remove a Pruefung to Korrekturbuch
    */
    public void removeFromKorrekturbuch ( Pruefung new_object ) {
 	if(Pruefungsliste.contains(new_object)){
 		Pruefungsliste.remove(new_object);
 		setAnz(Pruefungsliste.size());
 	}
 	else{
    	System.out.println("Prüfung nicht in Liste enthalten.");
    }
   } 
   
    public void setKorrekturbuch (List<Pruefung> liste )
    {
    	Pruefungsliste.clear();
    	Pruefungsliste.addAll(liste);
      
      setAnz(Pruefungsliste.size());
    }
   
    public boolean setKorrekturbuchFromFiles(File[] files)
    {
    	if (files.length==0){
    		return false;
    	}
    	
    	Pruefungsliste.clear();
    	Pruefung tmp;
    	
    	String lis;
    	GregorianCalendar datum;
    	Pruefungsarten.ART artKey;
    	int nummer;
    	int anzTeilnehmer;
    	
    	
    	for(int i = 0; i<files.length; i++){
    		try{
    			CsvReader csvPruefung = new CsvReader(files[i].toString());
    			csvPruefung.readHeaders();

    			while (csvPruefung.readRecord())
    		  	{
    				lis = "";
    				
    				String num          = csvPruefung.get("Nummer");
    		    	String art          = csvPruefung.get("Art");
    				String yea          = csvPruefung.get("Jahr");
    				String mon          = csvPruefung.get("Monat");
    				String day          = csvPruefung.get("Tag");
    				String anz			= csvPruefung.get("Anzahl");
    				lis					= csvPruefung.get("Listen-Datei");
    						
    				// perform program logic here
    				System.out.println(num + ", " + art + ", " + yea + ", " + mon + ", "+ day + ", " + anz + ", " + lis);
    				int numNumber = Integer.valueOf(num).intValue();
    				ART artNumber = ART.valueOf(art);
    				int yeaNumber = Integer.valueOf(yea).intValue();
    				int monNumber = Integer.valueOf(mon).intValue();
    				int dayNumber = Integer.valueOf(day).intValue();
    				int anzNumber = Integer.valueOf(anz).intValue();
    				
    				datum = new GregorianCalendar(yeaNumber,monNumber,dayNumber);
    				artKey = artNumber;
    				nummer = numNumber;
    				anzTeilnehmer = anzNumber;
    		  		
    		  		tmp = new Pruefung(datum, artKey, nummer, anzTeilnehmer);
    		  		System.out.println("Aufgabeliste: " + lis);
    		  		tmp.setAufgabenListeFromFile(lis);
    		  		  		  		
    		  		addToKorrekturbuch(tmp);
    		  		
    		  	}
    			csvPruefung.close();
   			
    		}
    		catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    	}  
      setAnz(Pruefungsliste.size());
      return true;
    }
    
    public Klasse getKBKlasse() {
		return KBKlasse;
	}

	public void setKBKlasse(Klasse kBKlasse) {
		KBKlasse = kBKlasse;
	}

	public boolean fillKorrekturbuch(){
    	return this.setKorrekturbuchFromFiles(this.readDirectory(getKlassenBezeichnung()));
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
   
   public String getKlassenBezeichnung() {
		return klassenBezeichnung;
	}
   private void setKlassenBezeichnung(String klassenBezeichnung) {
		this.klassenBezeichnung = klassenBezeichnung;
	}
  //
  // Other methods
  //
  public String toString(){
	  return new String("Inhalt des Korrekturbuchs: " + "Klasse "+ 
              this.getKlassenBezeichnung() + Pruefungsliste);
  }
  
  
  // Filter für Dateien
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
  
  // Auslesen der Dateiliste im Verzeichnis
  // Filterung nach Filter-Klasse
  public File[] readDirectory(String klassenBezeichnung){
	  File directory = new File(".");
	     
	  File[] files = directory.listFiles(new Filter(klassenBezeichnung));
	 
	  for(int i = 0; i<files.length; i++){
		  System.out.println(files[i].toString());
	  }
	  
	  return files;
  }
  
  // Speichern der nötigen Informationen des Korrekturbuchs
  public void writeKorrekturbuchToCSV(String filename)
  {

 // before we open the file check to see if it already exists
	    File f = new File(filename);
	    String KBFilename = new String(filename.substring(0, filename.lastIndexOf('.')) + "_aufgaben.csv");
	    
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
 				csvOutput.write("Nummer");
 				csvOutput.write("Art");
 				csvOutput.write("Jahr");
 				csvOutput.write("Monat");
 				csvOutput.write("Tag");
 				csvOutput.write("Anzahl");
 				csvOutput.write("Listen-Datei");
 				csvOutput.endRecord();
 			/*}
 			// else assume that the file already has the correct header line
 			*/
 				 			
 			// write out a few records
 				csvOutput.write("" + this.nummer);
 	 			csvOutput.write("" + this.artKey);
 	 			csvOutput.write("" + this.datum.get(Calendar.YEAR));
 	 			csvOutput.write("" + this.datum.get(Calendar.MONTH));
 	 			csvOutput.write("" + this.datum.get(Calendar.DAY_OF_MONTH));
 	 			csvOutput.write("" + this.anzTeilnehmer);
 	 			
 	 			csvOutput.write(aufgabenFilename);
 	 				 	 			
 	 			csvOutput.endRecord();
 			
 			
 			csvOutput.close();
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		aufgabenListe.writeAufgabenListeToCSV(aufgabenFilename);
    
  }
  
  
  

}
  
  

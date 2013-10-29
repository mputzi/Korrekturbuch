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
  
  private Klasse KBKlasse = new Klasse();
  
  private int prAnz;
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
	  this.setKBKlasse(kl);
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
    setPrAnz(Pruefungsliste.size());
  }
  
   /**
    * Remove a Pruefung to Korrekturbuch
    */
    public void removeFromKorrekturbuch ( Pruefung new_object ) {
 	if(Pruefungsliste.contains(new_object)){
 		Pruefungsliste.remove(new_object);
 		setPrAnz(Pruefungsliste.size());
 	}
 	else{
    	System.out.println("Prüfung nicht in Liste enthalten.");
    }
   } 
   
    public void setKorrekturbuch (List<Pruefung> liste )
    {
    	Pruefungsliste.clear();
    	Pruefungsliste.addAll(liste);
      
      setPrAnz(Pruefungsliste.size());
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
      setPrAnz(Pruefungsliste.size());
      return true;
    }
    
//
  // Accessor methods
  //
    
    public Klasse getKBKlasse() {
		return KBKlasse;
	}

	private void setKBKlasse(Klasse kBKlasse) {
		KBKlasse = kBKlasse;
	}
   /**
    * Set the value of anz
    * @param newVar the new value of anz
    */
   private void setPrAnz( int newVar ) {
     prAnz = newVar;
   }
	/**
    * Get the value of anz
    * @return the value of anz
    */
   public int getPrAnz( ) {
     return prAnz;
   }
   
   public String getKlassenBezeichnung() {
		return klassenBezeichnung;
	}
   private void setKlassenBezeichnung(String klassenBezeichnung) {
		this.klassenBezeichnung = klassenBezeichnung;
	}
   
   public Lehrer getLehrer()
   {
	   return this.getKBKlasse().getLehrer();
   }

   public String getLehrerString()
   {
	   return new String(this.getKBKlasse().getLehrer().toString());
   }
   
   public int getSchuelerAnz(){
	   return this.getKBKlasse().getSchuelerzahl();
   }
   
   public int getSchuljahr(){
	   return this.getKBKlasse().getSchuljahr();
   }
   
   public String getFach()
   {
	   return new String(this.getKBKlasse().getFach());
   }
   
   
  //
  // Other methods
  //
  public String toString(){
	  return new String("Inhalt des Korrekturbuchs:\n" + "Klasse "+ 
              this.getKBKlasse().toString() +
              ", Anzahl der Prüfungen: " + this.getPrAnz() + "\n"
              + Pruefungsliste);
  }
  
  
  public boolean fillKorrekturbuch(){
	return this.setKorrekturbuchFromFiles(this.readDirectory(getKlassenBezeichnung()));
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
  public void writeKorrekturBuch(){
	  Klasse tmp = new Klasse(); 
	  tmp =  this.getKBKlasse();
	  //System.out.println("Schreiben!!");
	  this.writeKorrekturbuchToCSV(new String(
		tmp.getKorrBuchFilename()
		));
  }
  
 private void writeKorrekturbuchToCSV(String filename)
  {

 // before we open the file check to see if it already exists
	    File f = new File(filename);
	   // String KBFilename = new String(filename);
	    
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
 				csvOutput.write("Klasse");
 				csvOutput.write("Fach");
 				csvOutput.write("Schuljahr");
 				csvOutput.write("Lehrer");
 				csvOutput.write("Pruefungszahl");
 				csvOutput.write("Schuelerzahl");
 				csvOutput.endRecord();
 			/*}
 			// else assume that the file already has the correct header line
 			*/
 			
 				Klasse tmp = this.getKBKlasse();
 				
 			// write out a few records
 				csvOutput.write("" + tmp.getKlBez());
 	 			csvOutput.write("" + tmp.getFach());
 	 			csvOutput.write("" + tmp.getSchuljahr());
 	 			csvOutput.write("" + tmp.getLehrer().toString());
 	 			csvOutput.write("" + this.getPrAnz());
 	 			csvOutput.write("" + tmp.getSchuelerzahl());
	 			 				 	 			
 	 			csvOutput.endRecord();

 	 			csvOutput.close();
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    
  }

 public void neuePruefung(int day, int mon, int yea, int nummer, Pruefungsarten.ART art, int teiln){
	 
	 
	 
 }
 
}
  
  

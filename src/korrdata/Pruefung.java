package korrdata;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import korrdata.Pruefungsarten.ART;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * Class Pruefung
 */
public class Pruefung {

  //
  // Fields
  //

  private static final int Aufgabenliste = 0;
  private GregorianCalendar datum;
  private Pruefungsarten.ART artKey;
  private int nummer;
  private int anzTeilnehmer;
  
  private AufgabeList aufgabenListe;
  
    
  //
  // Constructors
  //
  public Pruefung () { };
  public Pruefung (GregorianCalendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz) {
	  datum = prDatum;
	  artKey = prArtKey;
	  nummer = prNummer;
	  anzTeilnehmer = anz;
  };
  public Pruefung (GregorianCalendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz, AufgabeList prListe) {
	  datum = prDatum;
	  artKey = prArtKey;
	  nummer = prNummer;
	  anzTeilnehmer = anz;
	  aufgabenListe = prListe;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of datum
   * @param newVar the new value of datum
   */
  private void setDatum ( GregorianCalendar newVar ) {
    datum = newVar;
  }

  /**
   * Get the value of datum
   * @return the value of datum
   */
  private GregorianCalendar getDatum ( ) {
    return datum;
  }

  /**
   * Set the value of nummer
   * @param newVar the new value of nummer
   */
  private void setNummer ( int newVar ) {
    nummer = newVar;
  }

  /**
   * Get the value of nummer
   * @return the value of nummer
   */
  private int getNummer ( ) {
    return nummer;
  }

  /**
   * Set the value of anzTeilnehmer
   * @param newVar the new value of anzTeilnehmer
   */
  private void setAnzTeilnehmer ( int newVar ) {
    anzTeilnehmer = newVar;
  }

  /**
   * Get the value of anzTeilnehmer
   * @return the value of anzTeilnehmer
   */
  private int getAnzTeilnehmer ( ) {
    return anzTeilnehmer;
  }

  //
  // Other methods
  //

  /**
   * @return       float
   */
  public float getGesamtPunktzahl(  )
  {
	  float gesamt = 0.0f;
	  
	  //for
	  
	  return gesamt;
  }


  /**
   * @return       float
   */
  public float getDurchschnitt(  )
  {	
	  float durch = 0.0f;
	  return durch;
  }


  /**
   * @return       float
   */
  public float getAnteil45(  )
  {
	  float anteil = 0.0f;
	  return anteil;
  }


  /**
   * @return       float
   */
  public float getAnteil12(  )
  {
	  float anteil = 0.0f;
	  return anteil;
  }

public AufgabeList getAufgabenListe() {
	return aufgabenListe;
}

public void setAufgabenListe(AufgabeList aufgabenListe) {
	this.aufgabenListe = aufgabenListe;
}

public boolean addAufgabeToList(Aufgabe a){
	aufgabenListe.addToAufgabeList(a);
	return true;
}


public String toString(){
	  //System.out.println("Hallo!");
	  return new String(nummer+". " + Pruefungsarten.getDesc(artKey) +
			  " vom " + datum.get(Calendar.DAY_OF_MONTH) + "." + (datum.get(Calendar.MONTH)+1) + "." +  datum.get(Calendar.YEAR) + "\n" +
			  			  "Inhalt der Prüfung: " + aufgabenListe + "\n" + 
			            "Anzahl der Teilnehmer: " + anzTeilnehmer);
}

public Pruefungsarten.ART getPrArtKey() {
	return artKey;
}

public void setPrArtKey(Pruefungsarten.ART prArtKey) {
	this.artKey = prArtKey;
}

public boolean setPruefungFromFile(String filename)
	 {
			String lis = "";
			
	
		    try{
		    CsvReader csvPruefung = new CsvReader(filename);
		    csvPruefung.readHeaders();
		    
		    while (csvPruefung.readRecord())
			{
		    	String num          = csvPruefung.get("Nummer");
		    	String art          = csvPruefung.get("Art");
				String yea          = csvPruefung.get("Jahr");
				String mon          = csvPruefung.get("Monat");
				String day          = csvPruefung.get("Tag");
				String anz			= csvPruefung.get("Anzahl");
				lis			= csvPruefung.get("Listen-Datei");
						
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
				
			}
    
		    csvPruefung.close();
		    }
		    catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		    System.out.println(lis);
		    this.aufgabenListe.setAufgabenListeFromFile(lis);
		    return true;

		  }
	 
	 public void writePruefungToCSV(String filename)
	  {

	 // before we open the file check to see if it already exists
		    File f = new File(filename);
		    String aufgabenFilename = new String(filename.substring(0, filename.lastIndexOf('.')) + "_aufgaben.csv");
		    
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

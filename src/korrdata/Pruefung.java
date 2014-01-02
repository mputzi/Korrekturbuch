package korrdata;


import java.io.File;
import java.io.FileNotFoundException;

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

  private Calendar datum;
  private Pruefungsarten.ART artKey;
  private int nummer;
  private int idNum;
  private int anzTeilnehmer;
  
  private Korrekturbuch kb = new Korrekturbuch();
  private AufgabeList aufgabenListe = new AufgabeList();
  private KorrekturListe korrekturliste;
  
  private String prFilename ="";
  
    
  //
  // Constructors
  //
  public Pruefung () { };
  public Pruefung (int idNum, Calendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz) {
	  this.setDatum(prDatum);
	  this.setPrArtKey(prArtKey);
	  this.setNummer(prNummer);
	  this.setAnzTeilnehmer(anz);
	  this.setIdNum(idNum);
	  this.setKorrekturliste(new KorrekturListe(this));
  };
  public Pruefung (int idNum, Calendar prDatum, Pruefungsarten.ART prArtKey, int prNummer, int anz, AufgabeList aListe) {
	  this.setDatum(prDatum);
	  this.setPrArtKey(prArtKey);
	  this.setNummer(prNummer);
	  this.setAnzTeilnehmer(anz);
	  this.setIdNum(idNum);
	  this.setAufgabenListe(aListe);
	  this.setKorrekturliste(new KorrekturListe(this));
  };
  
  // Constructor for use in UI
  public Pruefung (int idNum, Calendar prDatum, Pruefungsarten.ART prArtKey, int prNummer,AufgabeList aListe){
	  this.setDatum(prDatum);
	  this.setPrArtKey(prArtKey);
	  this.setNummer(prNummer);
	  this.setIdNum(idNum);
	  this.setAufgabenListe(aListe);
  // Anzahl der Teilnehmer = Maximum	  
	  this.setAnzTeilnehmer(this.kb.getKBKlasse().getSchuelerzahl());
	  
	  this.setKorrekturliste(new KorrekturListe(this));
  }
  
  
  //
  // Methods
  //

  public void autosetFilename(Korrekturbuch kb){
	  this.setPrFilename(new String(
			  kb.getKlassenBezeichnung() + "_" + kb.getFach() + "_" +
			  kb.getSchuljahr() + "_P" + this.getIdNum() + "pr.csv"
			  ));
  }
  

  //
  // Accessor methods
  //

  
  
  public String getPrFilename() {
	return prFilename;
}
  private void setPrFilename(String prFilename) {
	this.prFilename = prFilename;
}
/**
   * Set the value of datum
   * @param newVar the new value of datum
   */
  private void setDatum ( Calendar newVar ) {
    datum = newVar;
  }

  /**
   * Get the value of datum
   * @return the value of datum
   */
  private Calendar getDatum ( ) {
    return datum;
  }

	public int getIdNum() {
		return idNum;
	}
	public void setIdNum(int idNum) {
		this.idNum = idNum;
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

  public Korrekturbuch getKb() {
	return kb;
}
public void setKb(Korrekturbuch kb) {
	this.kb = kb;
}
public KorrekturListe getKorrekturliste() {
	return korrekturliste;
}
public void setKorrekturliste(KorrekturListe korrekturliste) {
	this.korrekturliste = korrekturliste;
}
/**
   * @return       float
   */
  public float getGesamtPunktzahl(  )
  {
	  float gesamt = 0.0f;
	  
	  for (int i=0; i<this.getAufgabenListe().getAnz(); i++){
		  gesamt += this.getAufgabenListe().Aufgabenliste.get(i).getPunkte();
	  }
	  
	  return gesamt;
  }


  /**
   * @return       float
   */
  public float getDurchschnitt(  )
  {	
	  float durch = 0.0f;
	  int anz = this.getAnzTeilnehmer();
	  int[] nL = this.getKorrekturliste().getNoten();
	  
	  int sum = 0;
	  if (nL.length == anz){
		  for(int i=0;i<anz;i++){
			  sum += nL[i];
		  }
	  }
	  else{
		  System.out.println("PR: Anzahl der Teilnehmer stimmt nicht!");
	  }
	  
	  durch = (float)sum / (float)anz;
	  durch = Math.round(durch*100f) / 100.0f;	  
	  return durch;
  }


  /**
   * @return       float
   */
  public float getAnteil56(  )
  {
	  float anteil = 0.0f;
	  
	  int anz = this.getAnzTeilnehmer();
	  int[] nL = this.getKorrekturliste().getNoten();
	  
	  int sum = 0;
	  if (nL.length == anz){
		  for(int i=0;i<anz;i++){
			  if (nL[i]>4)
			  sum += 1;
		  }
	  }
	  else{
		  System.out.println("PR: Anzahl der Teilnehmer stimmt nicht!");
	  }
	  
	  anteil = (float)sum / (float)anz;
	  anteil = Math.round(anteil*100f) / 100.0f;
	  return anteil;
  }


  /**
   * @return       float
   */
  public float getAnteil12(  )
  {
float anteil = 0.0f;
	  
	  int anz = this.getAnzTeilnehmer();
	  int[] nL = this.getKorrekturliste().getNoten();
	  
	  int sum = 0;
	  if (nL.length == anz){
		  for(int i=0;i<anz;i++){
			  if (nL[i]<3)
			  sum += 1;
		  }
	  }
	  else{
		  System.out.println("PR: Anzahl der Teilnehmer stimmt nicht!");
	  }
	  
	  anteil = (float)sum / (float)anz;
	  anteil = Math.round(anteil*100f) / 100.0f;
	  return anteil;
  }

public AufgabeList getAufgabenListe() {
	return aufgabenListe;
}

public void setAufgabenListe(AufgabeList aufgabenListe) {
	this.aufgabenListe = aufgabenListe;
}

public void setAufgabenListeFromFile(String filename) {
	System.out.println(filename);
	aufgabenListe = new AufgabeList();
	this.aufgabenListe.setAufgabenListeFromFile(filename);
}

public boolean addAufgabeToList(Aufgabe a){
	aufgabenListe.addToAufgabeList(a);
	return true;
}


public String toString(){
	  //System.out.println("Hallo!");
	  return new String("PR:" + this.getIdNum()+"; " + this.getNummer()+". " + Pruefungsarten.getDesc(this.getPrArtKey()) +
			  " vom " + this.getDatum().get(Calendar.DAY_OF_MONTH) + "." + (this.getDatum().get(Calendar.MONTH)+1) + "." +  this.getDatum().get(Calendar.YEAR) + 
			  			  " Inhalt der Prüfung: " + aufgabenListe +  
			            " Anzahl der Teilnehmer: " + this.getAnzTeilnehmer());
}

public Pruefungsarten.ART getPrArtKey() {
	return artKey;
}

public void setPrArtKey(Pruefungsarten.ART prArtKey) {
	this.artKey = prArtKey;
}

public boolean setPruefungFromFile(){
	return this.setPruefungFromFile(this.getPrFilename());
}

public boolean setPruefungFromFile(String filename)
	 {
			String lis = "";
			
	
		    try{
		    CsvReader csvPruefung = new CsvReader(filename);
		    csvPruefung.readHeaders();
		    
		    while (csvPruefung.readRecord())
			{
		    	String idn			= csvPruefung.get("ID");
		    	String num          = csvPruefung.get("Nummer");
		    	String art          = csvPruefung.get("Art");
				String yea          = csvPruefung.get("Jahr");
				String mon          = csvPruefung.get("Monat");
				String day          = csvPruefung.get("Tag");
				String anz			= csvPruefung.get("Anzahl");
				lis			= csvPruefung.get("Listen-Datei");
						
				// perform program logic here
				System.out.println("PR: +++ Neue Prüfung aus Datei: " + filename);
				System.out.println("PR: " + idn + ", " + num + ", " + art + ", " + yea + ", " + mon + ", "+ day + ", " + anz + ", " + lis);
				int idnNumber = Integer.valueOf(idn).intValue();
				int numNumber = Integer.valueOf(num).intValue();
				ART artKey = ART.valueOf(art);
				int yeaNumber = Integer.valueOf(yea).intValue();
				int monNumber = Integer.valueOf(mon).intValue();
				int dayNumber = Integer.valueOf(day).intValue();
				int anzNumber = Integer.valueOf(anz).intValue();
				
				this.setIdNum(idnNumber);
				this.setDatum(new GregorianCalendar(yeaNumber,monNumber,dayNumber));
				this.setPrArtKey(artKey);
				this.setNummer(numNumber);
				this.setAnzTeilnehmer(anzNumber);
								
			}
    
		    csvPruefung.close();
		    }
		    catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		    System.out.println("PR: Aufgabenliste in Datei " + lis);
		    this.aufgabenListe.setAufgabenListeFromFile(lis);
		    this.setKorrekturliste(new KorrekturListe(this));
		    return true;

		  }
	 
	 public void writePruefungToCSV(){
		 if (this.getPrFilename()!=""){
		 this.writePruefungToCSV(this.getPrFilename());}
		 else{
			 System.out.println("PR: Schreiben in Datei nicht erflogreich: Dateiname fehlt.");
		 }
	 }
	 

	 private void writePruefungToCSV(String filename)
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
	 				csvOutput.write("ID");
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
	 				csvOutput.write("" + this.getIdNum());
	 				csvOutput.write("" + this.getNummer());
	 	 			csvOutput.write("" + this.getPrArtKey());
	 	 			csvOutput.write("" + this.getDatum().get(Calendar.YEAR));
	 	 			csvOutput.write("" + this.getDatum().get(Calendar.MONTH));
	 	 			csvOutput.write("" + this.getDatum().get(Calendar.DAY_OF_MONTH));
	 	 			csvOutput.write("" + this.getAnzTeilnehmer());
	 	 			
	 	 			csvOutput.write(aufgabenFilename);
	 	 				 	 			
	 	 			csvOutput.endRecord();
	 			
	 			
	 			csvOutput.close();
	 			
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		
	 		aufgabenListe.writeAufgabenListeToCSV(aufgabenFilename);
	    
	  }


}

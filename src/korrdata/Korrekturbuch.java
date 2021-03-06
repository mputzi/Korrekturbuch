package korrdata;


import java.io.File;
import java.io.FileFilter;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import com.csvreader.CsvReader;
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
  
  private int prAnz = 0;
  private ArrayList<Pruefung> Pruefungsliste = new ArrayList<Pruefung>();
  
  public class PruefungComparator implements Comparator<Pruefung>{
	  @Override
	  public int compare(Pruefung pr1, Pruefung pr2){
		  return (pr1.getIdNum()- pr2.getIdNum());
	  }
  }
  
  //
  // Constructors
  //
  public Korrekturbuch () { };
  
 /*
  public Korrekturbuch (List<Pruefung> liste) {
	  getPruefungsliste().clear();
	  getPruefungsliste().addAll(liste);
	 
	  Collections.sort(getPruefungsliste(),new PruefungComparator());
  };
  
  public Korrekturbuch (String klassenBezeichnung) {
	  this.setKlassenBezeichnung(klassenBezeichnung);
	  if(this.fillKorrekturbuch()){
		  System.out.println("Prüfungen vorhanden!");
	  }
	  else{
		  System.out.println("Keine Prüfungen vorhanden!");
	  }
	  Collections.sort(getPruefungsliste(),new PruefungComparator());
  };
 */
  
  public Korrekturbuch (Klasse kl) {
	  this.setKlassenBezeichnung(kl.getKlBez());
	  this.setKBKlasse(kl);
	  
	  System.out.println("KB: Erstelle Korrekturbuch zur Klasse " + this.getKBKlasse());
	  
	  // Über fillKorrekturbuch werden alle Prüfungen aus Dateien ausgelesen.
	  
	  if(this.fillKorrekturbuch()){
		  System.out.println("Prüfungen vorhanden!");
	  }
	  else{
		  System.out.println("Keine Prüfungen vorhanden!");
	  }
	  Collections.sort(getPruefungsliste(),new PruefungComparator());
  };
  
  //
  // Methods
  //
  /**
   * Add a Pruefung to Korrekturbuch
   */
   public void addToKorrekturbuch ( Pruefung new_object ) {
	if(getPruefungsliste().contains(new_object)){
		System.out.println("KB: Prüfung bereits in Liste enthalten!");
		return;
	}
	getPruefungsliste().add(new_object);
	
    setPrAnz(getPruefungsliste().size());
    Collections.sort(getPruefungsliste(),new PruefungComparator());
  }
  
   /**
    * Remove a Pruefung to Korrekturbuch
    */
    public void removeFromKorrekturbuch ( Pruefung new_object ) {
 	if(getPruefungsliste().contains(new_object)){
 		getPruefungsliste().remove(new_object);
 		setPrAnz(getPruefungsliste().size());
 	}
 	else{
    	System.out.println("Prüfung nicht in Liste enthalten.");
    }
   } 
   //not used
   /*
    public void setKorrekturbuch (List<Pruefung> liste )
    {
    	getPruefungsliste().clear();
    	getPruefungsliste().addAll(liste);
      
      setPrAnz(getPruefungsliste().size());
    }
   */
    public boolean setKorrekturbuchFromFiles(File[] files)
    {
    	if (files.length==0){
    		return false;
    	}
    	
    	getPruefungsliste().clear();
    	    	
    	for(int i = 0; i<files.length; i++){
    		Pruefung tmp = new Pruefung();
    		tmp.setKb(this);
    		tmp.setPruefungFromFile(files[i].toString());
    		addToKorrekturbuch(tmp);
    		
    	}  
      setPrAnz(getPruefungsliste().size());
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
   
   public ArrayList<Pruefung> getPruefungsliste() {
	return Pruefungsliste;
}

public void setPruefungsliste(ArrayList<Pruefung> pruefungsliste) {
	Pruefungsliste = pruefungsliste;
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
   /*
   public Pruefung getPruefungByID(int id){
	   return Pruefungsliste.
   }*/
   
  //
  // Other methods
  //
  public String toString(){
	  return new String("KB: Inhalt des Korrekturbuchs:\n" + "Klasse "+ 
              this.getKBKlasse().toString() +
              ", Anzahl der Prüfungen: " + this.getPrAnz() + "\n"
              + this.getKBKlasse().getSchuelerL().toString() + "\n"
              + this.getPruefungsliste());
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
				  && fname.endsWith("pr.csv")
				  && fname.contains("_P")
				  );
	  }
  }
  
  // Auslesen der Dateiliste im Verzeichnis
  // Filterung nach Filter-Klasse
  public File[] readDirectory(String klassenBezeichnung){
	  System.out.println("KB: Lese Verzeichnis!");
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
 		System.out.println("ww KB: Korrekturbuch " + this.getKBKlasse().getKlBez() + " erfolgreich in Datei geschrieben. ww");
  }

 public Pruefung neuePruefung(int day, int mon, int yea, int nummer, Pruefungsarten.ART art){
	 
	 System.out.println("KB: -+- Neue Prüfung anlegen! -+-");
	 
	 // ID der letzten Prüfung bestimmen
	 int ID = this.getPruefungsliste().size();
	 	 	 
	 /*
	 // Zusammenbau des neuen Dateinamens
	 File[] files = this.readDirectory(getKlassenBezeichnung());
	 String neuPrFilename;
	 String altPrFilename;
	 // letzter benutzter Dateiname
	 altPrFilename = new String(files[0].getName());
	 System.out.println(altPrFilename);
	 // Aufsplitten des letzten alten Dateinamens
	 String[] bausteine = altPrFilename.split("[p_]");
	 // Zusammmenbau des neuen Dateinamens
	 
	 int neuID =  Integer.valueOf(bausteine[4]).intValue()+1;
	 
	 neuPrFilename = new String(bausteine[0] +"_"+ bausteine[1] +
			 "_" + bausteine[2] + "_p"+
			 (neuID) + "_p" + ".csv");
	 */ 

	 Calendar d = new GregorianCalendar(yea,mon,day);
	 //this.setPrAnz(this.getPrAnz()+1);
	 
	 Pruefung tmp = new Pruefung(ID, d, art, nummer, this);
	 //tmp.autosetFilename(this);
	 this.addToKorrekturbuch(tmp);
	 	 
	 System.out.println("KB: "+"Neue Prüfung: " + tmp.getPrFilename() + " erstellt und hinzugefügt.");
	 
	 // Schreiben der neuen Prüfung in neue Datei
	 tmp.writePruefungToCSV();
	 
	 return tmp;
 }
 
/* 
 public void removePruefung(int ID){
	 
	 System.out.println("-- Prüfung löschen! --");
	 Pruefung tmp = this.getPruefungByID(ID);	 
	 this.removeFromKorrekturbuch(tmp);
	 
 }
 */
 
 public void printPruefungen(){
	System.out.println("KB: ----------");
	for(int i = 0; i < this.getPruefungsliste().size(); i++){
	    System.out.println("KB: "+ this.getPruefungsliste().get(i).toString());
	}	
	System.out.println("KB: ----------");	
 }

}
  
  

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

  private ArrayList<Klasse> klassenliste = new ArrayList<Klasse>();
  
  //
  // Constructors
  //
  public KlasseList () { };
  
  public KlasseList (List<Klasse> liste) {
	    getKlassenliste().clear();
	    getKlassenliste().addAll(liste);
	    
  };
  
  
  //
  // Methods
  //


  //
  // Accessor methods
  //
  public ArrayList<Klasse> getKlassenliste() {
		return klassenliste;
	}

	public void setKlassenliste(ArrayList<Klasse> klassenliste) {
		this.klassenliste = klassenliste;
	}
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
	if(getKlassenliste().contains(new_object)){
		System.out.println("Klasse bereits in Liste enthalten!");
		return;
	}
	getKlassenliste().add(new_object);
    setAnz(getKlassenliste().size());
  }

  /**
   * Remove a Schueler from list
   */
  public void removeFromKlasseList ( Klasse new_object )
  {
    if(getKlassenliste().contains(new_object)){
    	getKlassenliste().remove(new_object);
    	setAnz(getKlassenliste().size());
    }
    else{
    	System.out.println("Klasse nicht in Liste enthalten.");
    }
  }
  /*
  private void setKlasseList (List<Klasse> liste )
  {
    getKlassenliste().clear();
    getKlassenliste().addAll(liste);
    
    setAnz(getKlassenliste().size());
  }
  */
  public void readKlasseListFromCSV(String filename)
  {
	getKlassenliste().clear();
    Klasse tmp;
 
    try{
    CsvReader csvKlasseListe = new CsvReader(filename);
    csvKlasseListe.readHeaders();
    
  
    while (csvKlasseListe.readRecord())
	{
		String bez			= csvKlasseListe.get("Bezeichnung");
    	String fac     		= csvKlasseListe.get("Fach");
		String sja    		= csvKlasseListe.get("Schuljahr");
		String leh			= csvKlasseListe.get("Lehrer");
		String slf			= csvKlasseListe.get("Schuelerliste-Datei");
		String kbf			= csvKlasseListe.get("Korrekturbuch-Datei");
				
		// perform program logic here
		// Debugging
		System.out.println(bez + ", " + fac + ", " + sja + ", " +
						   leh + ", " + slf + ", " + kbf);
		
		// internal
		int sjaNumber = Integer.valueOf(sja).intValue();
		Lehrer lehtmp = new Lehrer();
		lehtmp.fromString(leh);
		
		tmp = new Klasse(bez,fac,sjaNumber,lehtmp);
		
		//tmp.setKorrBuchFilename(kbf);
		//tmp.setSchuelerListFilename(slf);
		
		
		addToKlasseList(tmp);
	}

    csvKlasseListe.close();
    }
    catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
     
    setAnz(getKlassenliste().size());
  }
  

  
  public void writeKlasseListToCSV(String filename)
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
 					
 			
 			//if the file didn't already exist then we need to write out the header line
 			//if (!alreadyExists)
 			{
 				csvOutput.write("Bezeichnung");
 				csvOutput.write("Fach");
 				csvOutput.write("Schuljahr");
 				csvOutput.write("Lehrer");
 				csvOutput.write("Schuelerliste-Datei");
 				csvOutput.write("Korrekturbuch-Datei");
 				csvOutput.endRecord();
 			}
 			// else assume that the file already has the correct header line
 			
 			ArrayList<Klasse> kl = getKlassenliste();
 			
 			for(int i = 0; i < kl.size(); i++){
 			// write out a few records
 				csvOutput.write(kl.get(i).getKlBez());
 	 			csvOutput.write(kl.get(i).getFach());
 	 			csvOutput.write("" + kl.get(i).getSchuljahr());
 	 			csvOutput.write(kl.get(i).getLehrer().toString());
 	 			csvOutput.write(kl.get(i).getSchuelerListFilename());
 	 			csvOutput.write(kl.get(i).getKorrBuchFilename());
 	 			
 	 			csvOutput.endRecord();
 			}
 			
 			csvOutput.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    
  }
  
  public String toString(){
	  return new String("Inhalt der Klassenliste: " + getKlassenliste());
  }


}

package korrdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

import korrdata.Pruefungsarten.ART;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;




/**
 * Class KorrekturListe
 */
public class KorrekturListe {

  //
  // Fields
  //
  
  private Pruefung pr;
  
  private int anzAufgaben;
  private int anzSchueler;
	
  private String[] aufgabenL;
  private float[] erreichbar;
  private boolean[] anwesendL;
  private float[][] erreicht;
  private int[] noten;

  private SchuelerList m_schueler;
  
  private String klFilename ="";
  
  //
  // Constructors
  //
  public KorrekturListe () {};
  
  public KorrekturListe (Pruefung parent_pr) {
	  this.setPr(parent_pr);
	  this.autosetFilename();
	  
	  System.out.println("KL: Korrekturliste zur Prüfung " + this.getPr().getIdNum() + " wird erstellt.");
	  System.out.println("KL: " + this.getPr().getKb().toString());
	  
	  this.setSchuelerList(this.getPr().getKb().getKBKlasse().getSchuelerL());
	  //System.out.println(this.getSchuelerNameAt(0));
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  
	  this.setAnzSchueler(this.getSchuelerList().getAnz());
	  this.setAnzAufgaben(this.getPr().getAufgabenListe().getAnz());
	  {
		  float[] erreichbareBE = new float[this.getAnzAufgaben()];
		  for(int i=0; i<this.getAnzAufgaben();i++){
			  erreichbareBE[i]=this.getPr().getAufgabenListe().Aufgabenliste.get(i).getPunkte();
		  }
		  this.setErreichbar(erreichbareBE);
	  }
	  this.fillAnwesendList(true);
	  this.setErreichtL(new float[this.getSchuelerList().getAnz()][this.getPr().getAufgabenListe().getAnz()]);
  };
  
  public KorrekturListe (SchuelerList schuelerL) {
	  this.setSchuelerList(schuelerL);
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  this.setAnzSchueler(this.getSchuelerList().getAnz());
	  this.fillAnwesendList(true);
	  
  };
  public KorrekturListe (SchuelerList schuelerL, AufgabeList aufgabenL) {
	  
	  this.setSchuelerList(schuelerL);
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  this.setAnzSchueler(this.getSchuelerList().getAnz());
	  this.fillAnwesendList(true);
	  this.setAnzAufgaben(aufgabenL.getAnz());
	  this.setErreichtL(new float[this.getSchuelerList().getAnz()][aufgabenL.getAnz()]);
	  
  };
  
  //
  // Methods
  //

  private void autosetFilename(){
	  this.setKlFilename(new String(
			  this.getPr().getKb().getKlassenBezeichnung()  + "_" + this.getPr().getKb().getFach() + "_" +
					  this.getPr().getKb().getSchuljahr() + "_P" + this.getPr().getIdNum() + "kl.csv"
			  ));
  }
  
  //
  // Accessor methods
  //

  /**
   * Set the value of anwesendL
   * @param newVar the new value of anwesendL
   */
  private void setAnwesendList ( boolean[] newVar ) {
    anwesendL = newVar;
  }
  
  private void setAnwesendAtIndex ( boolean newVar, int index ) {
	this.anwesendL[index] = newVar;
  }
  
  
  public void fillAnwesendList (boolean newVar) {
	    for(int i = 0; i< this.getAnwesendList().length; i++){
	    	this.setAnwesendAtIndex(newVar, i);
	    }
	  }

  public Pruefung getPr() {
	return pr;
}
public void setPr(Pruefung pr) {
	this.pr = pr;
}
/**
   * Get the value of anwesendL
   * @return the value of anwesendL
   */
  private boolean[] getAnwesendList ( ) {
    return anwesendL;
  }
  
  public boolean getAnwesendAtIndex(int index){
	  return this.anwesendL[index];
  }
  
  

  /**
   * Set the value of erreicht
   * @param newVar the new value of erreicht
   */
  private void setErreichtL ( float[][] newVar ) {
    erreicht = newVar;
  }

  public void setErreichtAt (int schuelerNum, int aufgNum, float errBE){
	  try{
	  if (schuelerNum <= this.getAnzSchueler()){
		  if (aufgNum <= this.getAnzAufgaben()){
			  if (errBE > this.getErreichbar()[aufgNum]){
				  erreicht[schuelerNum][aufgNum] = errBE;
			  }
			  else{
				  System.out.println("Mehr BE erreicht als erreichbar!");
			  }
		  }
		  else{
			  System.out.println("Aufgabennummer zu groß!");
		  }
	  }
	  else{
		  System.out.println("Schülernummer zu groß!");
	  }
	  }
	  catch(Exception e) {
			e.printStackTrace();
		}
	  
  }
  
  /**
   * Get the value of erreicht
   * @return the value of erreicht
   */
  public float[][] getErreichtL ( ) {
    return erreicht;
  }
  
  public float getErreichtAt(int schuelerNum, int aufgNum){
	   
	  return this.erreicht[schuelerNum][aufgNum];
  }
  
  public String erreichtLtoString(){
	  String str = "Erreichte Punkte \n";
	  for (int i=0; i<this.getAnzSchueler(); i++){
		  for (int j=0; j<this.getAnzAufgaben(); j++){
			  str = str + "("+ i + "," + j + ") -> " + this.erreicht[i][j] + " von " + this.erreichbar[j]  +"\n";
			  // Only for Debugging!!
			  System.out.println("#+- ("+ i + "," + j + ") -> " + this.erreicht[i][j] + " -+#");
			  //System.out.println(str);
		  }
	  }
	  return str;
  }
  /**
   * Set the value of m_schueler
   * @param newVar the new value of m_schueler
   */
  public void setSchuelerList ( SchuelerList newVar ) {
	  System.out.println("KL: SChülerliste in Korrekturliste gesetzt.");
	  System.out.println("KL: " + newVar.toString());
    m_schueler = newVar;
  }

  /**
   * Get the value of m_schueler
   * @return the value of m_schueler
   */
  public SchuelerList getSchuelerList ( ) {
    return m_schueler;
  }

  public int getSchuelerIDAt(int index)
  {
	  return this.getSchuelerList().Schuelerliste.get(index).getID();
  }
  
  public String getSchuelerNameAt(int index)
  {
	  return this.getSchuelerList().Schuelerliste.get(index).getNachname();
  }
  
  public String getSchuelerVornameAt(int index)
  {
	  return this.getSchuelerList().Schuelerliste.get(index).getVorname();
  }
  
  public String[] getAufgabenL() {
		return aufgabenL;
	}
	public void setAufgabenL(String[] aufgabenL) {
		this.aufgabenL = aufgabenL;
	}
	
	public float[] getErreichbar() {
		return erreichbar;
	}
	public void setErreichbar(float[] erreichbar) {
		this.erreichbar = erreichbar;
	}
	public int[] getNoten() {
		return noten;
	}
	public void setNoten(int[] noten) {
		this.noten = noten;
	}

  
  //
  // Other methods
  //

  /**
   */
  public void getListGesamtBE(  )
  {
  }


  /**
   */
  /*
  public float[] getListAnteilBE(  )
  {
	  return new float[];
  }*/

  public int getAnwesendNumber(){
	  int cnt = 0;
	  for (int i=0; i<this.getAnwesendList().length; i++){
		if(this.getAnwesendAtIndex(i)==true) cnt++;  
	  }
	  return cnt;
  }
  
  public String toString(){
	  return new String("++ Korrekturliste zur Prüfung " + this.getPr().toString() + "++\n" + 
			  	"Schüler: " + this.getSchuelerList().toString() + 
			  	"Anwesenheit: " + this.getAnwesendList().toString() + 
			//  	 "Aufgaben: " + this.getAufgabenL().toString() + 
			  	"Gesamtpunktzahl: " + this.getErreichbar().toString() +
			  	"Erreicht: " + this.erreichtLtoString()
			  	);
  }

public int getAnzAufgaben() {
	return anzAufgaben;
}

private void setAnzAufgaben(int anzAufgaben) {
	this.anzAufgaben = anzAufgaben;
}

public int getAnzSchueler() {
	return anzSchueler;
}

private void setAnzSchueler(int anzSchueler) {
	this.anzSchueler = anzSchueler;
}

// Speichern der Korrekturliste
public void writeKorrekturListe(){
	  String fn = this.getKlFilename();
	  
	  if (fn==""){
		  this.setKlFilename("TEST_Korrliste.csv");
	  }  
	
	  //System.out.println("Schreiben!!");
	  this.writeKorrekturListeToCSV(fn);
}

private void writeKorrekturListeToCSV(String filename)
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
				csvOutput.write("Schueler");
				csvOutput.write("Aufgabe");
				csvOutput.write("erreicht");
				csvOutput.endRecord();
			/*}
			// else assume that the file already has the correct header line
			*/
			
			// write out a few records
	 		for(int i = 0; i < this.getAnzSchueler(); i++){
	 			for(int j = 0; j < this.getAnzAufgaben(); j++){
	 				csvOutput.write(""+i);
	 	 			csvOutput.write(""+j);
	 	 			csvOutput.write(""+this.getErreichtAt(i,j));
	 	 			csvOutput.endRecord();
	 			}
	 		}
	 			
	 		csvOutput.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
  
}

private String getKlFilename() {
	return klFilename;
}

private void setKlFilename(String klFilename) {
	this.klFilename = klFilename;
}



public boolean setKorrekturListeFromFile(){
	return this.setKorrekturListeFromFile(this.getKlFilename());
}

private boolean setKorrekturListeFromFile(String filename){
	
	
    try{
    CsvReader csvKorrList = new CsvReader(filename);
    csvKorrList.readHeaders();

	System.out.println("KL: +++ Neue Korrekturliste aus Datei: " + filename);
    
    while (csvKorrList.readRecord())
	{
    	String sch			= csvKorrList.get("Schueler");
    	String auf          = csvKorrList.get("Aufgabe");
    	String err          = csvKorrList.get("erreicht");
		
		// perform program logic here
        // Debugging only
		System.out.println("KL: " + sch + auf + err);
		
		// Umwandeln in interne Formate
		int schNumber = Integer.valueOf(sch).intValue();
		int aufNumber = Integer.valueOf(auf).intValue();
		float errNumber = Float.valueOf(err).floatValue();
		
		this.setErreichtAt(schNumber, aufNumber, errNumber);
						
	}

    csvKorrList.close();
    }
    catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
    //System.out.println("KL: ");
    /*
    this.aufgabenListe.setAufgabenListeFromFile(lis);
    this.setKorrekturliste(new KorrekturListe(this));*/
    
    return true;
}
}


package korrdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

	private AufgabeList aufgabenL;
	private float[] erreichbar;
	private boolean[] anwesendL;
	private float[][] erreicht;
	private int[] noten;
	private float gesamtpunktzahl; 

	private SchuelerList m_schueler;

	private String klFilename ="";
	private String klAnwFilename ="";

	//
	// Constructors
	//
	public KorrekturListe () {};

	public KorrekturListe (Pruefung parent_pr) {
		this.setPr(parent_pr);
		this.autosetFilename();

		System.out.println("KL: Korrekturliste zur Prüfung " + this.getPr().getIdNum() + " wird erstellt.");
		//System.out.println("KL: Korrekturliste zu " + this.getPr().getPruefListString() +" erstellt.");
		//System.out.println("KL: " + this.getPr().getKb().toString());

		this.setSchuelerList(this.getPr().getKb().getKBKlasse().getSchuelerL());
		this.setAnzSchueler(this.getSchuelerList().getAnz());
		//System.out.println(this.getSchuelerNameAt(0));

		this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
		this.fillAnwesendList(true);

		this.setAufgabenL(this.getPr().getAufgabenListe());
		//this.setAnzAufgaben(this.getPr().getAufgabenListe().getAnz());

		float[] erreichbareBE = new float[this.getAnzAufgaben()];
		if(this.getAnzAufgaben()==0){
			System.out.println("KL! Keine Aufgaben in Prüfung!"); 
		}
		for(int i=0; i<this.getAnzAufgaben();i++){
			erreichbareBE[i]=this.getPr().getAufgabenListe().Aufgabenliste.get(i).getPunkte();
			System.out.println("KL: Erreichbare BE in Aufg." + i +" -> " + erreichbareBE[i]);
		}
		
		this.setErreichbar(erreichbareBE);

		this.setGesamtpunktzahl(this.getPr().getGesamtPunktzahl());

		this.setErreichtL(new float[this.getSchuelerList().getAnz()][this.getPr().getAufgabenListe().getAnz()]);
		this.setNoten(new int[this.getSchuelerList().getAnz()]);
	};
/*
	public KorrekturListe (SchuelerList schuelerL) {
		this.setSchuelerList(schuelerL);
		this.setAnzSchueler(this.getSchuelerList().getAnz());
		this.fillAnwesendList(true);

		this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
		this.setNoten(new int[this.getSchuelerList().getAnz()]);

	};
	public KorrekturListe (SchuelerList schuelerL, AufgabeList aufgabenL) {

		this.setSchuelerList(schuelerL);
		this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
		this.setAnzSchueler(this.getSchuelerList().getAnz());
		this.fillAnwesendList(true);
		this.setAnzAufgaben(aufgabenL.getAnz());

		this.setErreichtL(new float[this.getSchuelerList().getAnz()][aufgabenL.getAnz()]);
		this.setNoten(new int[this.getSchuelerList().getAnz()]);
	};
*/
	//
	// Methods
	//

	private void autosetFilename(){
		this.setKlFilename(new String(
				this.getPr().getKb().getKlassenBezeichnung()  + "_" + this.getPr().getKb().getFach() + "_" +
						this.getPr().getKb().getSchuljahr() + "_P" + this.getPr().getIdNum() + "kl.csv"
				));
		this.setKlAnwFilename(new String(
				this.getPr().getKb().getKlassenBezeichnung()  + "_" + this.getPr().getKb().getFach() + "_" +
						this.getPr().getKb().getSchuljahr() + "_P" + this.getPr().getIdNum() + "klan.csv"
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

	public void setAnwesendAtIndex ( boolean newVar, int index ) {
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
	public boolean[] getAnwesendList ( ) {
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
			System.out.println("KL: Aufgabennummer "+aufgNum+", maximal: "+this.getAnzAufgaben());

			if (schuelerNum <= this.getAnzSchueler()){
				if (aufgNum <= this.getAnzAufgaben()){
					if (errBE <= this.getErreichbar()[aufgNum]){
						erreicht[schuelerNum][aufgNum] = errBE;
					}
					else{
						System.out.println("KL: Mehr BE erreicht als erreichbar!" + errBE + " von max " + this.getErreichbar()[aufgNum]);
					}
				}
				else{
					System.out.println("KL: Aufgabennummer "+aufgNum+" zu groß! Maximal: "+this.getAnzAufgaben());
				}
			}
			else{
				System.out.println("KL: Schülernummer "+schuelerNum+" zu groß!");
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
				//System.out.println("#+- ("+ i + "," + j + ") -> " + this.erreicht[i][j] + " -+#");
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

	public AufgabeList getAufgabenL() {
		return aufgabenL;
	}
	public void setAufgabenL(AufgabeList aufgabeList) {
		this.aufgabenL = aufgabeList;
		this.setAnzAufgaben(aufgabeList.getAnz());
		System.out.println("KL: " + this.getAnzAufgaben() + " Aufgaben erfolgreich hinzugefügt.");
	}

	public float[] getErreichbar() {
		return erreichbar;
	}
	public void setErreichbar(float[] erreichbar) {
		this.erreichbar = erreichbar;
	}
	public int[] getNoten() {

		this.calcNoten();
		return noten;
	}
	public void setNoten(int[] noten) {
		this.noten = noten;
	}

	public void calcNoten(){
		float gesBE = this.getGesamtpunktzahl();
		float[] gesBEL = this.getListGesamtBE();
		int[] noten = new int[this.getAnzSchueler()];
		NSchluessel NS = new NSchluessel();

		for(int i=0; i<this.getAnzSchueler();i++){
			float ant = gesBEL[i] / gesBE;

			//debugging only
			// System.out.println("KL: Noten: calc: " + i + " " +ant);

			noten[i] = NS.getNote(ant);
		}
		this.setNoten(noten);
	}

	//debugging only
	public void printNoten(){

		int[] n = this.getNoten();
		for(int i=0; i<this.getAnzSchueler();i++){

			System.out.println("KL: Noten: Schüler: " + i + ": " + (n[i]));
		}
	}

	//
	// Other methods
	//

	/**
	 */
	public float[] getListGesamtBE(  )
	{
		float[] gesamtBEList = new float[this.getAnzSchueler()];
		int i =0;
		while(i<this.getAnzSchueler()){
			float errBESch = 0.0f;

			for(int j=0; j<this.getAnzAufgaben();j++){
				errBESch += this.getErreichtAt(i, j);
			}

			//	  System.out.println("KL: GesBE: Schüler " + i + " hat "+ errBESch + " erreicht.");

			gesamtBEList[i]=errBESch;
			i++;
		}

		return gesamtBEList;
	}

	private String ListGesamtBEString(){
		String str = "";

		float[] GesBEListe = this.getListGesamtBE();

		int i =0;
		while(i<this.getAnzSchueler()){
			str = str + i +": " + GesBEListe[i] + "\n";
			i++;
		}

		return str;
	}

	public void printGesamtBEListe(){
		System.out.println(this.ListGesamtBEString());
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
		return new String("++ Korrekturliste zur Prüfung " + this.getPr().getIdNum() + "++\n" + 
				"Schüler: " + this.getSchuelerList().toString() + "\n" +
				"Anwesenheit: -" + this.getAnwesendListString() + "-\n" +
				"Aufgaben: " + this.getAufgabenL().toString() + "\n" +
				"Gesamtpunktzahl: " + this.getGesamtpunktzahl() +"\n" +
				"Erreicht: " + this.erreichtLtoString() + "++\n" 	);
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
					csvOutput.write(""+this.getSchuelerIDAt(i));
					csvOutput.write(""+j);
					// debug only
					//System.out.println(this.getSchuelerIDAt(i)+"");
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
				String schID			= csvKorrList.get("Schueler");
				String auf          = csvKorrList.get("Aufgabe");
				String err          = csvKorrList.get("erreicht");

				// perform program logic here
				// Debugging only
				//System.out.println("KL: " + schID + auf + err);

				// Umwandeln in interne Formate
				int schIDNumber = Integer.valueOf(schID).intValue();
				int aufNumber = Integer.valueOf(auf).intValue();
				float errNumber = Float.valueOf(err).floatValue();

				this.setErreichtAt(schIDNumber-1, aufNumber, errNumber);

			}

			csvKorrList.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	private String getKlAnwFilename() {
		return klAnwFilename;
	}

	private void setKlAnwFilename(String klAnwFilename) {
		this.klAnwFilename = klAnwFilename;
	}

	// Speichern der Korrekturliste
	public void writeAnwesendListe(){
		String fn = this.getKlAnwFilename();

		if (fn==""){
			this.setKlAnwFilename("TEST_Anwesenheitsliste.csv");
		}  

		//System.out.println("Schreiben!!");
		this.writeAnwesendListeToCSV(fn);
	}

	private void writeAnwesendListeToCSV(String filename)
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
			csvOutput.write("Schueler");
			csvOutput.write("anwesend");
			csvOutput.endRecord();
			/*}
			// else assume that the file already has the correct header line
			 */

			// write out a few records
			for(int i = 0; i < this.getAnzSchueler(); i++){
				csvOutput.write(""+this.getSchuelerIDAt(i));
				csvOutput.write(""+this.getAnwesendAtIndex(i));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean setAnwesendListeFromFile(){
		return this.setAnwesendListeFromFile(this.getKlAnwFilename());
	}

	private boolean setAnwesendListeFromFile(String filename){
		try{
			CsvReader csvAList = new CsvReader(filename);
			csvAList.readHeaders();

			System.out.println("KL: +++ Neue Anwesenheitsliste aus Datei: " + filename);

			while (csvAList.readRecord())
			{
				String schID			= csvAList.get("Schueler");
				String an           = csvAList.get("anwesend");

				// perform program logic here
				// Debugging only
				System.out.println("KL: " + schID + an);

				// Umwandeln in interne Formate
				int schIDNumber = Integer.valueOf(schID).intValue();
				boolean anB = Boolean.valueOf(an).booleanValue();

				this.setAnwesendAtIndex(anB, schIDNumber-1);
			}
			csvAList.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public String getAnwesendListString(){
		String str ="";
		for(int i=0;i<this.getAnwesendList().length;i++){
			if(this.getAnwesendList()[i]){ str+= "1";}
			else str+= "0";
		}
		return str;	
	}

	public float getGesamtpunktzahl() {
		return gesamtpunktzahl;
	}

	public void setGesamtpunktzahl(float gesamtpunktzahl) {
		this.gesamtpunktzahl = gesamtpunktzahl;
	}



}


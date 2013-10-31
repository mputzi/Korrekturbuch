package korrdata;




/**
 * Class KorrekturListe
 */
public class KorrekturListe {

  //
  // Fields
  //
  
  private Pruefung pr;
	
  private String[] aufgabenL;
  private float[] erreichbar;
  private boolean[] anwesendL;
  private float[][] erreicht;
  private int[] noten;

  private SchuelerList m_schueler;
  
  //
  // Constructors
  //
  public KorrekturListe () {};
  
  public KorrekturListe (Pruefung parent_pr) {
	  this.setPr(parent_pr);
	  System.out.println("Korrekturliste zur Prüfung " + this.getPr().getIdNum() + "wird erstellt.");
	  this.setSchuelerList(this.getPr().getKb().getKBKlasse().getSchuelerL());
	  //System.out.println(this.getSchuelerNameAt(0));
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  this.fillAnwesendList(true);
	  this.setErreichtL(new float[this.getSchuelerList().getAnz()][this.getPr().getAufgabenListe().getAnz()]);
  };
  
  public KorrekturListe (SchuelerList schuelerL) {
	  this.setSchuelerList(schuelerL);
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  this.fillAnwesendList(true);
	  
  };
  public KorrekturListe (SchuelerList schuelerL, AufgabeList aufgabenL) {
	  
	  this.setSchuelerList(schuelerL);
	  this.setAnwesendList(new boolean[this.getSchuelerList().getAnz()]);
	  this.fillAnwesendList(true);
	  this.setErreichtL(new float[this.getSchuelerList().getAnz()][aufgabenL.getAnz()]);
	  
  };
  
  //
  // Methods
  //

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

  /**
   * Get the value of erreicht
   * @return the value of erreicht
   */
  private float[][] getErreichtL ( ) {
    return erreicht;
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
			  	"Anwesenheit: " + this.getAnwesendList().toString()  
			  	//+ "Aufgaben: " + this.getAufgabenL().toString() + 
			  	//"Erreichbar: " + this.getErreichbar().toString() +
			  	//"Erreicht: " + this.getErreichtL().toString()
			  	);
  }

}

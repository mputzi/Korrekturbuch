package korrdata;



/**
 * Class NSchluessel
 */
public class NSchluessel {

  //
  // Fields
  //
  public static final float EINS = 0.85f;
  public static final float ZWEI = 0.7f;
  public static final float DREI = 0.55f;
  public static final float VIER = 0.4f;
  public static final float FUENF = 0.2f;
  public static final float SECHS = 0.0f;
	
  private int[] noten;
  private boolean grenzePos;
  private float[] grenzen;
  
  //
  // Constructors
  //
  //public NSchluessel () { };
  public NSchluessel () {
	  int[] notenI = new int[6];
	  for (int i=0; i<notenI.length; i++){
		  notenI[i]=i;
	  }
	  this.setNoten(notenI);
	  
	  float[] grenzenI = {EINS, ZWEI, DREI, VIER, FUENF, SECHS};
	  this.setGrenzen(grenzenI);
	  
	  this.setGrenzePos(false);
  };
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of noten
   * @param newVar the new value of noten
   */
  private void setNoten ( int[] newVar ) {
    noten = newVar;
  }

  /**
   * Get the value of noten
   * @return the value of noten
   */
  private int[] getNoten ( ) {
    return noten;
  }

  /**
   * Set the value of grenzePos
   * @param newVar the new value of grenzePos
   */
  private void setGrenzePos ( boolean newVar ) {
    grenzePos = newVar;
  }

  /**
   * Get the value of grenzePos
   * @return the value of grenzePos
   */
  private boolean getGrenzePos ( ) {
    return grenzePos;
  }

  /**
   * Set the value of grenzen
   * @param newVar the new value of grenzen
   */
  private void setGrenzen ( float[] newVar ) {
    grenzen = newVar;
  }

  /**
   * Get the value of grenzen
   * @return the value of grenzen
   */
  private float[] getGrenzen ( ) {
    return grenzen;
  }

  //
  // Other methods
  //

  /**
   * @return       int
   * @param        anteil
   */
  public int getNote( float anteil )
  {
	  
	  int[] nL = this.getNoten();
	  float[] gL = this.getGrenzen();
	  boolean G = this.getGrenzePos();

	  if(G){
		  for(int i =0; i<nL.length; i++){
			  if(anteil >= gL[i]){
				  return nL[i];
			  }
		  }
	  }
	  else{
		  for(int i =0; i<nL.length; i++){
			  if(anteil > gL[i]){
				  return nL[i];
			  }
		  }
		  return 5;
	  }

	  return -1;
  }
}



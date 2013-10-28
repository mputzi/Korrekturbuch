package korrdata;

import java.util.Set;

import korrdata.Pruefungsarten.ART;

public class PruefungsartenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    System.out.println("--- Test der Pruefungsarten ---");
		System.out.println(Pruefungsarten.toStr());
		System.out.println(Pruefungsarten.getKeys());
		System.out.println(Pruefungsarten.getValues());
			
		System.out.println(Pruefungsarten.getDesc(Pruefungsarten.ART.SA));
	}

}

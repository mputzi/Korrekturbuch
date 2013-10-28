package korrdata;

import java.util.EnumMap;
import java.util.Set;



public static class Pruefungsarten{

	public enum ART {
		SA, ST, T, KA
	}
	
private EnumMap<ART, String> pruefungsMap = new EnumMap<ART, String>(ART.class);

public Pruefungsarten(){
	pruefungsMap.put(ART.SA, "Schulaufgabe");
	pruefungsMap.put(ART.ST, "Stegreifarbeit");
	pruefungsMap.put(ART.T, "Test");
	pruefungsMap.put(ART.KA, "Kurzarbeit");
}

/*
 * Prüfungsarten to String
 * @see java.lang.Object#toString()
 */

public String toString(){
	
	String ret = "";
	Set<ART> enumKeySet = pruefungsMap.keySet();
    
	for(ART art : enumKeySet){
        ret.concat("key : " + art + " value : " + pruefungsMap.get(art) + "\n");
    }
	
	return ret;
}

/*
 * Auslesen der Keys
 */


public Set<ART> getKeys(){
	return pruefungsMap.keySet();
}

/*
 * Auslesen der Beschreibung
 */


public String getDesc(ART a){
	return new String(pruefungsMap.get(a));
}

}


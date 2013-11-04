package korrdata;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Set;



public class Pruefungsarten{

	public static  enum ART {
		SA, ST, T, KA
	}
	
private static EnumMap<ART, String> pruefungsMap = new EnumMap<ART, String>(ART.class);

static {
	pruefungsMap.put(ART.SA, "Schulaufgabe");
	pruefungsMap.put(ART.ST, "Stegreifarbeit");
	pruefungsMap.put(ART.T, "Test");
	pruefungsMap.put(ART.KA, "Kurzarbeit");
}

/*
 * Prüfungsarten to String
 */

public static String Beschreibung(ART art){
	return pruefungsMap.get(art);
}

public static String toStr(){
	
	String ret = "";
	Set<ART> enumKeySet = pruefungsMap.keySet();
    
	for(ART art : enumKeySet){
        String ret2 = ret.concat(new String("key : " + art + " value : " + pruefungsMap.get(art) + "\n"));
        ret = new String(ret2);
    }
	
	return ret;
}

/*
 * Auslesen der Keys
 */


public static Set<ART> getKeys(){
	return pruefungsMap.keySet();
}

/*
 * Auslesen der Beschreibung
 */


public static String getDesc(ART key){
	return new String(pruefungsMap.get(key));
}

public static Collection<String> getValues(){
	return (Collection<String>) pruefungsMap.values();
}




}


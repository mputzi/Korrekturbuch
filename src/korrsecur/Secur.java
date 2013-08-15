package korrsecur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class Secur {
	/**
	 * encrypt a value and generate a keyfile 
	 * if the keyfile is not found then a new one is created
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */

	public static String encrypt(char[] value, File keyFile)
			throws GeneralSecurityException, IOException
			{

		if (!keyFile.exists()) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey sk = keyGen.generateKey();
			FileWriter fw = new FileWriter(keyFile);
			fw.write(byteArrayToHexString(sk.getEncoded()));
			fw.flush();
			fw.close();
			System.out.println("Schlüsseldatei neu erstellt.");
		}
		else{
			System.out.println("Schlüsseldatei war da.");
		}

		SecretKeySpec sks = getSecretKeySpec(keyFile);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());

		System.out.println("Verschlüsselung...");

		String str = new String(value);
		// ACHTUNG Passwort im Klartext ( nur zum Debugging )
		// System.out.print("zu enccrypt: ");
		// System.out.println(str);

		byte[] encrypted = cipher.doFinal(str.getBytes());

		return byteArrayToHexString(encrypted);
			}


	/**
	 * decrypt a value  
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */
	public static String decrypt(String message, File keyFile) 
			throws GeneralSecurityException, IOException 
			{
		if (keyFile.exists()) {

			SecretKeySpec sks = getSecretKeySpec(keyFile);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, sks);
			byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));

			System.out.println("Entschlüsselung...");

			return new String(decrypted);
		}
		else{
			System.out.println("Schlüsseldatei nicht gefunden!!!");
			return "ERROR";
		}
			}


	private static SecretKeySpec getSecretKeySpec(File keyFile) 
			throws NoSuchAlgorithmException, IOException 
			{
		byte [] key = readKeyFile(keyFile);
		SecretKeySpec sks = new SecretKeySpec(key, "AES");
		return sks;
			}

	private static byte [] readKeyFile(File keyFile) 
			throws FileNotFoundException 
			{
		Scanner scanner = 
				new Scanner(keyFile).useDelimiter("\\Z");
		String keyValue = scanner.next();
		scanner.close();
		return hexStringToByteArray(keyValue);
			}


	private static String byteArrayToHexString(byte[] b){
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++){
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++){
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte)v;
		}
		return b;
	}

	/*
	 * ######################################################################
	 * Methoden für die GUI
	 * 
	 * ######################################################################
	 */
	

	public static boolean passCheck(char[] pwd){
		FileReader f;
		int c;
		String inStr = new String("");

		File pwdDat = new File("pwd.dat");
		if(  pwdDat.exists() && pwdDat.canWrite() && pwdDat.isFile()  ) {
			System.out.println("Datei pwd.dat ist da, alles in Ordnung.");
		}
		else if (!pwdDat.exists()) return true;


		try {
			f = new FileReader("pwd.dat");
			while ((c = f.read()) != -1) {

				inStr=inStr+(char)c;
				// debug: Inhalt der Datei auslesen
				// System.out.println(inStr);
			}
			f.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei");
		}
		// debug: fertig gelesener String aus Datei
		// System.out.println(inStr);
		System.out.println("Passwort erfolgreich gelesen.");

		// Umwandeln des gelesenen Strings in Char-Array (zum Verlgeichen)
		// char[] in = inStr.toCharArray();
		// debug: Ausgabe char-Array
		// System.out.println(in);

		File keyDat = new File("key.dat");
		String decpwd = new String("");
		try{
			decpwd = decrypt(inStr, keyDat);
		}
		catch (Exception e) {
			System.err.println("Caught Exception: " +  e.getMessage());	                                 
		}

		// ACHTUNG: Passwort im Klartext ( nur zum Debugging )
		//System.out.println(decpwd);

		// Überprüfung, ob eingegebenes Passwort mit gespeichertem identisch
		if (Arrays.equals(pwd, decpwd.toCharArray())){
			return true;
		} else return false;
	}

	public static boolean passEqualCheck(char[] pwd1, char[] pwd2){

		if (Arrays.equals(pwd1,pwd2)){
			return true;
		}
		else return false;
	}

	/**
	 * writePWD
	 * Methode zum Schreiben der Passwort-Datei
	 * 
	 * @param pwd
	 * @return 0
	 */

	public static int writePWD(char[] pwd){

		File keyDat = new File("key.dat");
		String encpwd = new String("");
		try{
			encpwd = encrypt(pwd, keyDat);
		}
		catch (Exception e) {
			System.err.println("Caught Exception: " +  e.getMessage());	                                 
		}

		System.out.println(encpwd);

		File pwdDat = new File("pwd.dat");
		if(  pwdDat.exists() && pwdDat.canWrite() && pwdDat.isFile()  ) {
			System.out.println("Datei pwd.dat ist da, alles in Ordnung.");

			FileWriter f1;

			try {
				f1 = new FileWriter("pwd.dat");
				f1.write(encpwd);
				f1.flush();
				f1.close();
			} catch (IOException e) {
				System.out.println("Fehler beim Erstellen der Datei");
			}
			return 0;
		}
		else if(pwdDat.exists() && !(pwdDat.canWrite() && pwdDat.isFile())) {
			System.out.println("kein Schreibzugriff oder keine Datei!");
			return 1;
		}
		else {
			System.out.println("Datei pwd.dat existiert nicht!");

			FileWriter f1;

			try {
				f1 = new FileWriter("pwd.dat");
				f1.write(encpwd);
				f1.flush();
				f1.close();
			} catch (IOException e) {
				System.out.println("Fehler beim Erstellen der Datei");
			}

			System.out.println("Datei pwd.dat neu angelegt.");
			return 0;

		}

	}			  

}

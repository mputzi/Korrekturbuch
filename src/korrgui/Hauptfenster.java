package korrgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Hauptfenster {

	private static JFrame frame;
	static Hauptfenster window;
	private JMenu mnDatei, mnKlasse, mnPruefung, mnNeu, mnHilfe;
	private JMenuItem mntmPasswortAendern, mntmBeenden;
	private JMenuItem mntmBearbeiten, mntmOeffnen, mntmNeuAnlegen;
	private JMenuItem mntmSchulaufgabe, mntmStegreifaufgabe, mntmKurzarbeit, mntmTest, mntmPOeffnen;
	private JMenuBar menuBar;
	
	/**
	 * Menüauswahl abfangen und auswerten
	 */
	private ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				
				if (cmd=="passwordchange"){
					// Passwort-Änderungs-Dialog erstellen
					PassChange pwChgDialog = new PassChange(frame);
					// Aufrufenden frame an Passwort-Änderungs-Dialog übermitteln
					pwChgDialog.setLocationRelativeTo(frame);
					// Passwort-Änderungs-Dialog anzeigen
			        pwChgDialog.setVisible(true);					
				}
				if (cmd=="exit")
				{
					frame.setVisible(false);
					frame.dispose();
					System.exit(0);
				}
				
				if (cmd=="classnew"){}
				if (cmd=="classopen")
				{
					/**
					mnPruefung.setVisible(true); // Menü "Prüfung" anzeigen
					mntmBearbeiten.setEnabled(true); // Menüpunkt "Klasse bearbeiten" aktivieren
					**/
				}
				if (cmd=="classedit"){}
				
				if (cmd=="Schulaufgabe"){}
				if (cmd=="Stegreifaufgabe"){}
				if (cmd=="Kurzarbeit"){}
				if (cmd=="Test"){}
				if (cmd=="testopen"){}
				
			}
		};
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Hauptfenster();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Hauptfenster() {
		
		
		initialize();
		mnPruefung.setVisible(false); // Menü "Prüfung" erst möglich, wenn Klasse geöffnet
		mntmBearbeiten.setEnabled(false); // "Klasse bearbeiten" er möglich, wenn Klasse geöffnet
		
	}

	/**
	 * Fensterinhalte festlegen
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Korrekturprogramm");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 436, 21);
		frame.getContentPane().add(menuBar);
		
		/**
		 * Menü "Datei" anlegen mit "Passwort ändern" und "Beenden"
		 */ 
		mnDatei = new JMenu("Datei"); // Menü "Datei" anlegen
		menuBar.add(mnDatei); // Menü zur Menüleiste hinzufügen
		
		mntmPasswortAendern = new JMenuItem("Passwort ändern"); // Menüpunkt "Passwort ändern" erzeugen
		mntmPasswortAendern.setActionCommand("passwordchange"); // bei Auswahl wird "passwordchange" übermittelt
		mntmPasswortAendern.addActionListener(al); // den Menüpunkt zum "ActionListener" hinzufügen
		mnDatei.add(mntmPasswortAendern); // Menüpunkt dem Menü "Datei" hinzufügen
		
		mntmBeenden = new JMenuItem("Beenden"); // siehe oben 
		mntmBeenden.setActionCommand("exit");
		mntmBeenden.addActionListener(al);
		mnDatei.add(mntmBeenden);
		
		/**
		 * Menü "Klasse" anlegen mit Unterpunkten
		 */
		mnKlasse = new JMenu("Klasse");
		menuBar.add(mnKlasse);
		
		mntmOeffnen = new JMenuItem("Öffnen");		
		mntmOeffnen.setActionCommand("classopen");
		mntmOeffnen.addActionListener(al);
		mnKlasse.add(mntmOeffnen);
		
		mntmBearbeiten = new JMenuItem("Bearbeiten");
		mntmBearbeiten.setActionCommand("classedit");
		mntmBearbeiten.addActionListener(al);
		mnKlasse.add(mntmBearbeiten);
		
		mntmNeuAnlegen = new JMenuItem("Neu anlegen");
		mntmNeuAnlegen.setActionCommand("classnew");
		mntmNeuAnlegen.addActionListener(al);
		mnKlasse.add(mntmNeuAnlegen);
		
		/**
		 * Menü "Prüfung" anlegen mit Unterpunkten
		 */
		mnPruefung = new JMenu("Prüfung");
		menuBar.add(mnPruefung);
		
		mnNeu = new JMenu("Neu");
		mnPruefung.add(mnNeu);
		
		mntmSchulaufgabe = new JMenuItem("Schulaufgabe");
		mntmSchulaufgabe.addActionListener(al);
		mnNeu.add(mntmSchulaufgabe);
		
		mntmStegreifaufgabe = new JMenuItem("Stegreifaufgabe");
		mntmStegreifaufgabe.addActionListener(al);
		mnNeu.add(mntmStegreifaufgabe);
		
		mntmKurzarbeit = new JMenuItem("Kurzarbeit");
		mntmKurzarbeit.addActionListener(al);
		mnNeu.add(mntmKurzarbeit);
		
		mntmTest = new JMenuItem("Test");
		mntmTest.addActionListener(al);
		mnNeu.add(mntmTest);
		
		mntmPOeffnen = new JMenuItem("Öffnen");
		mntmPOeffnen.setActionCommand("testopen");
		mntmPOeffnen.addActionListener(al);
		mnPruefung.add(mntmPOeffnen);
		
		
		/**
		 * Menü "Hilfe"
		 */
		mnHilfe = new JMenu("Hilfe");
		mnHilfe.setToolTipText("Hier werden Sie geholfen!");
		menuBar.add(mnHilfe);
		
		
		
		// Passwort-Abfrage bei Programmstart.
		// Passwort-Dialog erstellen
		Passwort pwDialog = new Passwort(frame);
		// Aufrufenden frame an Passwort-Dialog übermitteln
		pwDialog.setLocationRelativeTo(frame);
		// Passwort-Dialog anzeigen
        pwDialog.setVisible(true);
		
	}
}

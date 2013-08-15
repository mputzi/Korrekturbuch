package korrgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class Hauptfenster {

	private static JFrame frame;
	static Hauptfenster window;
	
	private JMenu mnDatei, mnKlasse, mnPruefung, mnNeu, mnHilfe;
	private JMenuItem mntmPasswortAendern, mntmBeenden;
	private JMenuItem mntmBearbeiten, mntmOeffnen, mntmNeuAnlegen, mntmSchliessen;
	private JMenuItem mntmSchulaufgabe, mntmStegreifaufgabe, mntmKurzarbeit, mntmTest, mntmPOeffnen;
	private JMenuBar menuBar;
	
	private JPanel mainpanel;
	private JTextPane welcome;
	
	/**
	 *  In der Variablen class_open wird gespeichert, ob eine Klasse zur Bearbeitung geöffnet ist.
	 *  Nur dann wird überhaupt das Menü Prüfung angezeigt und auch der Menüpunkt Klassebearbeiten.
	**/
	private boolean class_open=false;
	public void set_class_open (boolean arg)
	{
		class_open=arg;
		return;
	}
	public boolean get_class_open ()
	{
		return class_open;
	}
	
	
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
				
				if (cmd=="classnew")
				{
					set_class_open(true); // Klasse geöffnet (neu angelegt)
				}
				if (cmd=="classopen")
				{
					set_class_open(true); // Klasse geöffnet
				}
				if (cmd=="classedit"){}
				if (cmd=="classclose")
				{
					set_class_open(false); // Klasse geschlossen
				}
				
				if (cmd=="Schulaufgabe"){}
				if (cmd=="Stegreifaufgabe"){}
				if (cmd=="Kurzarbeit"){}
				if (cmd=="Test"){}
				if (cmd=="testopen"){}
				
				if (get_class_open()==true)
				{
					mnPruefung.setVisible(true); // Menü "Prüfung" anzeigen
					mntmBearbeiten.setEnabled(true); // Menüpunkt "Klasse bearbeiten" aktivieren
					mntmSchliessen.setEnabled(true);
				}
				if (get_class_open()==false)
				{
					mnPruefung.setVisible(false); // Menü "Prüfung" ausblenden
					mntmBearbeiten.setEnabled(false); // Menüpunkt "Klasse bearbeiten" ausblenden
					mntmSchliessen.setEnabled(false);
				}
				
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
		
		
		initialize(); // Menübar anlegen und Passwortabfrage starten
		mnPruefung.setVisible(false); // Menü "Prüfung" erst möglich, wenn Klasse geöffnet
		mntmBearbeiten.setEnabled(false); // Menüpunkt "Klasse bearbeiten" und "Klasse Schließen" erst möglich, wenn Klasse geöffnet
		mntmSchliessen.setEnabled(false);
		
		set_class_open(false); // Noch keine Klasse geöffnet
		
		mainpanel = new JPanel();
		frame.getContentPane().add(mainpanel, BorderLayout.CENTER);
		
		welcome = new JTextPane();
		welcome.setOpaque(false);
		welcome.setEditable(false);
		welcome.setText("         Herzlich willkommen im Korrektur-Unterstützungs-Programm!\r\n\r\nBitte öffnen Sie eine bestehende Klasse oder legen Sie eine Klasse neu an.");
		mainpanel.add(welcome);
		
		
	}

	/**
	 * Fensterinhalte festlegen
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Korrekturprogramm");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		/**
		 * Menü "Datei" anlegen mit "Passwort ändern" und "Beenden"
		 */ 
		mnDatei = new JMenu("Datei"); // Menü "Datei" anlegen
		menuBar.add(mnDatei); // Menü zur Menüleiste hinzufügen
		
		mntmPasswortAendern = new JMenuItem("Passwort ändern"); // Menüpunkt "Passwort ändern" erzeugen
		mntmPasswortAendern.setActionCommand("passwordchange"); // bei Auswahl wird "passwordchange" übermittelt
		mntmPasswortAendern.addActionListener(al); // "ActionListener" zum Menüpunkt hinzufügen
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
		
		mntmSchliessen = new JMenuItem("Schließen");
		mntmSchliessen.setActionCommand("classclose");
		mntmSchliessen.addActionListener(al);
		mnKlasse.add(mntmSchliessen);
		
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
		// Hauptfenster deaktivieren
		frame.setEnabled(false);
		// Passwort-Dialog erstellen
		Passwort pwDialog = new Passwort(frame);
		// Aufrufenden frame an Passwort-Dialog übermitteln
		pwDialog.setLocationRelativeTo(frame);
		// Passwort-Dialog anzeigen
        pwDialog.setVisible(true);
		
	}
}

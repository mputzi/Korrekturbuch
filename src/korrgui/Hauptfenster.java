package korrgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.Dimension;

public class Hauptfenster {

	/**
	 * mnPruefung, mntmBearbeiten und mntmSchliessen als static, da sonst setActive, ... nicht einfach umsetzbar sind.
	 * 
	 * Also umsetzbar schon mit set- und get-Methoden. Aber so ist es einfacher, wenn auch unsauber :-)
	 */ 
	
	private JFrame frame;
	//private Hauptfenster window;

	
	private static JMenu mnPruefung;
	private static JMenuItem mntmBearbeiten, mntmSchliessen;
	
	private JMenu mnDatei, mnKlasse, mnNeu, mnHilfe;
	private JMenuItem mntmPasswortAendern, mntmBeenden;
	private JMenuItem mntmOeffnen, mntmNeuAnlegen;
	private JMenuItem mntmSchulaufgabe, mntmStegreifaufgabe, mntmKurzarbeit, mntmTest, mntmPOeffnen;
	private JMenuBar menuBar;

	private JPanel mainpanel;
	private JTextPane welcome;


	/**
	 *  In der Variablen class_open wird gespeichert, ob eine Klasse zur Bearbeitung geöffnet ist.
	 *  Nur dann wird überhaupt das Menü Prüfung angezeigt und auch der Menüpunkt Klassebearbeiten.
	 **/
	static private boolean class_open=false;
	static public void set_class_open (boolean arg)
	{
		class_open=arg;
		return;
	}
	static public boolean get_class_open ()
	{
		return class_open;
	}


	/**
	 * Menüauswahl abfangen und auswerten -> "ActionListener"
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
				//set_class_open(true); // Klasse geöffnet (neu angelegt)
				//frame.setEnabled(false); //braucht man nicht mehr, das neues Fenster als modal-geöffnet
				KlasseNeu KlasseNeuDialog = new KlasseNeu(frame); // Klasse Neu Dialog erstellen
				KlasseNeuDialog.setLocationRelativeTo(frame);
				KlasseNeuDialog.setVisible(true); // Dialog anzeigen
			}
			
			
			if (cmd=="classopen")
			{
				//frame.setEnabled(false); //braucht man nicht mehr, das neues Fenster als modal-geöffnet
				KlasseOeffnen KlasseOeffnenDialog = new KlasseOeffnen(frame); // Klasse Öffnen Dialog erstellen
				KlasseOeffnenDialog.setLocationRelativeTo(frame);
				KlasseOeffnenDialog.setVisible(true); // Dialog anzeigen
				
			}
			if (cmd=="classedit"){
				KlasseBearbeiten KlasseBearbeitenDialog = new KlasseBearbeiten(frame); // Klasse Neu Dialog erstellen
				KlasseBearbeitenDialog.setLocationRelativeTo(frame);
				KlasseBearbeitenDialog.setVisible(true); // Dialog anzeigen
			}
			if (cmd=="classclose")
			{
				set_class_open(false); // Klasse geschlossen
			}

			if (cmd=="Schulaufgabe"){}
			if (cmd=="Stegreifaufgabe"){}
			if (cmd=="Kurzarbeit"){}
			if (cmd=="Test"){}
			if (cmd=="testopen"){
				//frame.setEnabled(false);
				PruefungOeffnen PruefungOeffnenDialog = new PruefungOeffnen(frame); // Prüfung Öffnen Dialog erstellen
				PruefungOeffnenDialog.setLocationRelativeTo(frame);
				PruefungOeffnenDialog.setVisible(true); // Dialog anzeigen
			}

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
	 * Fensteraktionen abfangen --> "WindowsListener"
	 * static private wl -> private wl 
	 */
	private static WindowListener wl = new WindowListener(){
		public void windowClosed(WindowEvent arg0) {}
        public void windowActivated(WindowEvent arg0) {
            //System.out.println("Window Activated");
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
        public void windowClosing(WindowEvent arg0) {
        	System.out.println(arg0);
        }
        public void windowDeactivated(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowOpened(WindowEvent arg0) {}
	};
	
	
	/**
	 * Launch the application.
	 * public static void -> public void
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptfenster window = new Hauptfenster();
					window.frame.setVisible(true);
					window.frame.addWindowListener(wl);

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


		/**
		 * Willkommenstext hinzufügen
		 */
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
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.setTitle("Korrekturprogramm");
		frame.setBounds(100, 100, 800, 600);
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


		/**
		 * Passwort-Abfrage bei Programmstart
		 */

		frame.setEnabled(false); // Hauptfenster deaktivieren
		Passwort pwDialog = new Passwort(frame); // Passwort-Dialog erstellen
		pwDialog.setLocationRelativeTo(frame); // Aufrufenden frame an Passwort-Dialog übermitteln
		pwDialog.setVisible(true); // Passwort-Dialog anzeigen

	}
}

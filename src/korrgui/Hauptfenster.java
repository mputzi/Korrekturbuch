package korrgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hauptfenster {

	private static JFrame frame;
	static Hauptfenster window;
	private JMenu mnDatei, mnKlasse, mnPruefung, mnNeu;
	private JMenuItem mntmPasswortAendern, mntmBeenden;
	private JMenuItem mntmBearbeiten, mntmOeffnen, mntmNeuAnlegen;
	private JMenuItem mntmSchulaufgabe, mntmStegreifaufgabe, mntmKurzarbeit, mntmTest;
	private JMenuBar menuBar;
	
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
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cmd = e.getActionCommand();
				System.out.println(cmd);
				/**if (cmd=="OK")
				{
					
				}	**/		
			}
		};
		
		initialize();
		mnPruefung.setEnabled(false);
		mntmBearbeiten.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 436, 21);
		frame.getContentPane().add(menuBar);
		
		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		mntmPasswortAendern = new JMenuItem("Passwort ändern");
		mnDatei.add(mntmPasswortAendern);
		
		mntmBeenden = new JMenuItem("Beenden");
		/**mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cmd = arg0.getActionCommand();
				System.out.println(cmd);
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
				
			}
		});**/
		mnDatei.add(mntmBeenden);
		
		mnKlasse = new JMenu("Klasse");
		menuBar.add(mnKlasse);
		
		mntmOeffnen = new JMenuItem("Öffnen");
		mnKlasse.add(mntmOeffnen);
		mntmOeffnen.setActionCommand("Klasseoeffnen");
		
		mntmBearbeiten = new JMenuItem("Bearbeiten");
		mnKlasse.add(mntmBearbeiten);
		
		mntmNeuAnlegen = new JMenuItem("Neu anlegen");
		mnKlasse.add(mntmNeuAnlegen);
		
		mnPruefung = new JMenu("Prüfung");
		menuBar.add(mnPruefung);
		
		mnNeu = new JMenu("Neu");
		mnPruefung.add(mnNeu);
		
		mntmSchulaufgabe = new JMenuItem("Schulaufgabe");
		mnNeu.add(mntmSchulaufgabe);
		
		mntmStegreifaufgabe = new JMenuItem("Stegreifaufgabe");
		mnNeu.add(mntmStegreifaufgabe);
		
		mntmKurzarbeit = new JMenuItem("Kurzarbeit");
		mnNeu.add(mntmKurzarbeit);
		
		mntmTest = new JMenuItem("Test");
		mnNeu.add(mntmTest);
		
		// Passwort-Abfrage bei Programmstart.
		// Passwort-Dialog erstellen
		Passwort pwDialog = new Passwort(frame);
		// Aufrufenden frame an Passwort-Dialog übermitteln
		pwDialog.setLocationRelativeTo(frame);
		// Passwort-Dialog anzeigen
        pwDialog.setVisible(true);
		
	}
}

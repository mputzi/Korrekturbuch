package korrgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hauptfenster {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptfenster window = new Hauptfenster();
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 436, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmPasswortndern = new JMenuItem("Passwort ändern");
		mnDatei.add(mntmPasswortndern);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");

		mnDatei.add(mntmBeenden);
		
		JMenu mnKlasse = new JMenu("Klasse");
		menuBar.add(mnKlasse);
		
		JMenuItem mntmffnen = new JMenuItem("Öffnen");
		//mntmffnen.setEnabled(false);
		mnKlasse.add(mntmffnen);
		
		
		JMenuItem mntmNeuAnlegen = new JMenuItem("Neu anlegen");
		mnKlasse.add(mntmNeuAnlegen);
		
		JMenu mnPrfung = new JMenu("Prüfung");
		menuBar.add(mnPrfung);
		mnPrfung.setEnabled(false);
		
		JMenu mnNeu = new JMenu("Neu");
		mnPrfung.add(mnNeu);
	}
}

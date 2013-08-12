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
		
		JMenuItem mntmPasswortAendern = new JMenuItem("Passwort ändern");
		mnDatei.add(mntmPasswortAendern);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cmd = arg0.getActionCommand();
				System.out.println(cmd);
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
				
			}
		});

		mnDatei.add(mntmBeenden);
		
		JMenu mnKlasse = new JMenu("Klasse");
		menuBar.add(mnKlasse);
		
		JMenuItem mntmOeffnen = new JMenuItem("Öffnen");
		mnKlasse.add(mntmOeffnen);
		mntmOeffnen.setActionCommand("Klasseoeffnen");
		
		
		JMenuItem mntmNeuAnlegen = new JMenuItem("Neu anlegen");
		mnKlasse.add(mntmNeuAnlegen);
		
		JMenu mnPruefung = new JMenu("Prüfung");
		menuBar.add(mnPruefung);
		mnPruefung.setEnabled(false);
		
		JMenu mnNeu = new JMenu("Neu");
		mnPruefung.add(mnNeu);
		
		JMenuItem mntmSchulaufgabe = new JMenuItem("Schulaufgabe");
		mnNeu.add(mntmSchulaufgabe);
		mntmSchulaufgabe.setEnabled(false);
		
		JMenuItem mntmStegreifaufgabe = new JMenuItem("Stegreifaufgabe");
		mnNeu.add(mntmStegreifaufgabe);
		mntmStegreifaufgabe.setEnabled(true);
		
		JMenuItem mntmKurzarbeit = new JMenuItem("Kurzarbeit");
		mnNeu.add(mntmKurzarbeit);
		mntmKurzarbeit.setEnabled(false);
		
		JMenuItem mntmTest = new JMenuItem("Test");
		mnNeu.add(mntmTest);
		mntmTest.setEnabled(true);
		
		Passwort pwDialog = new Passwort(frame);
		pwDialog.setLocationRelativeTo(frame);
        pwDialog.setVisible(true);
		
	}
}

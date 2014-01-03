package korrgui;

/**
 * Klassenliste muss weiter unten noch durch Pruefungsliste ersetzt werden
 * in Abhängigkeit von der gewählten Klasse
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
//import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import korrdata.KlasseList; //Muss noch auf Pruefungsliste geändert werden
import korrdata.Korrekturbuch;


public class PruefungOeffnen extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Frame aufrufer = new Frame();
	private JPanel contentPanel = new JPanel();
	private boolean auswahl=false; //Prüfung ausgewählt?
	
	private JList Pruefungsliste = new JList(); // Liste der Prüfungen anzeigen
	private DefaultListModel pruef_list = new DefaultListModel();
	
	private JButton okButton;
	private JTextArea Zusammenfassung = new JTextArea();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PruefungOeffnen dialog = new PruefungOeffnen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public PruefungOeffnen(final Frame aufrufer) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.aufrufer = aufrufer;
		initialize();
		
	}
	
	private ActionListener CLASSal = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();
			System.out.println(cmd);
			
			if (cmd=="OK"){
				actionOKButton();
			}
			if (cmd=="Cancel"){
				actionCancelButton();
			}
			
		}
	};
	
	private ListSelectionListener ListSL = new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent arg0) {
			/**
			 * Sobald eine Prüfung aus der Liste (links) ausgewählt ist, wird die entsprechende Zusammenfassung geladen
			 * und im Infofenster (rechts) angezeigt. Außerdem wird festgehalten, dass eine Prüfung ausgewählt wurde.
			 */
			int i = Pruefungsliste.getSelectedIndex();
			Hauptfenster.set_pruef_selected(i);
			set_Zusammenfassung(Hauptfenster.get_kb().getPruefungsliste().get(i).toString());
			
					
			/**
			 * Zusammenfassung wird noch nicht ausgegeben 
			 */
			
			//String outStr = new String(meinePruefungsliste.Klassenliste.get(i).toString());
			//set_Zusammenfassung(outStr);
			
			auswahl=true; //Eine Prüfung wurde ausgewählt
			okButton.setEnabled(auswahl);
		}
	};
	

	
	private void actionOKButton(){
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		PruefungOeffnen.this.setVisible(false);
		PruefungOeffnen.this.dispose();
		//set_Pruef_selected wird bei der Auswahl in der Liste automatisch schon gesetzt, nur bei Cancel wieder auf 99
		
		/**
		 * Hier wird dann die Korrektureingabe gestartet!!
		 */
		
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		PruefungOeffnen.this.setVisible(false);
		PruefungOeffnen.this.dispose();
		Hauptfenster.set_pruef_selected(99);
		
	}
	
	public void set_Zusammenfassung (String arg){
		Zusammenfassung.setText(arg);
	}
	
	
	
	private void initialize() {
		
		// Einbindung der Daten aus der Klassenliste-Datei
				
		//System.out.println("Jetzt kommt die Übergabe der Daten für kb in PruefungOeffnen");
		//System.out.println(Hauptfenster.get_kb());
		
		
		
	    /**
	     * Korrekturbucheinträge noch in linke Liste schreiben (erledigt)
	     * Pruefungsliste als JList
	     */
		
		
		setTitle("Prüfung öffnen");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(150, 200));
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.WEST);
			{
				Pruefungsliste.addListSelectionListener(ListSL);
				Pruefungsliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				Pruefungsliste.setModel(pruef_list);
				//pruef_list.addElement("Test");
				//pruef_list.addElement("Hans");
				int zahl = Hauptfenster.get_kb().getPruefungsliste().size();
				for (int i=0;i<zahl;i++){
					pruef_list.addElement(Hauptfenster.get_kb().getPruefungsliste().get(i).getPruefListString());
				}
				scrollPane.setViewportView(Pruefungsliste);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(250, 200));
			contentPanel.add(scrollPane, BorderLayout.EAST);
			{
				//JTextArea Zusammenfassung = new JTextArea();
				scrollPane.setViewportView(Zusammenfassung);
				Zusammenfassung.setWrapStyleWord(true);
				Zusammenfassung.setLineWrap(true);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(CLASSal);
				buttonPane.add(okButton);
				okButton.setEnabled(false);
				//getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(CLASSal);
				buttonPane.add(cancelButton);
			}
		}		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

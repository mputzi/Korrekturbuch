package korrgui;

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

import korrdata.KlasseList;


public class KlasseOeffnen extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Frame aufrufer = new Frame();
	private JPanel contentPanel = new JPanel();
	private JTextArea Zusammenfassung = new JTextArea();
	private boolean auswahl=false; //Klasse ausgewählt?
	private int auswahl_int; //Nummer der auswählten Klasse
	
	private JList Klassenliste = new JList();
	private DefaultListModel klass_list = new DefaultListModel();
	
	private JButton okButton;
	
	private KlasseList meineKlassenliste = new KlasseList();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KlasseOeffnen dialog = new KlasseOeffnen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public KlasseOeffnen(final Frame aufrufer) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.aufrufer = aufrufer;
		initialize(); // Dialogfenster aufbauen mit "OK"- und "Cancel"-Button
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
			 * Sobald eine Klasse aus der Liste (links) ausgewählt ist, wird die entsprechende Zusammenfassung geladen
			 * und im Infofenster (rechts) angezeigt. Außerdem wird festgehalten, dass eine Klasse ausgewählt wurde.
			 */
			int i = Klassenliste.getSelectedIndex();
			auswahl_int=i;
			
			String outStr = new String(meineKlassenliste.Klassenliste.get(i).toString());
			set_Zusammenfassung(outStr);
			auswahl=true; //Eine Klasse wurde ausgewählt
			okButton.setEnabled(true);
		}
	};
	

	
	private void actionOKButton(){
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseOeffnen.this.setVisible(false);
		KlasseOeffnen.this.dispose();
		//Wenn keine Datei ausgewählt, dann auf false, sonst auf true
		KBMainWin.set_class_open(auswahl);
		KBMainWin.set_class_selected(auswahl_int);
		System.out.println(auswahl_int);
		// Kontrolle wieder an Hauptfenster geben
		//aufrufer.setEnabled(true);
		//aufrufer.setVisible(true);
		KBMainWin.set_kb(meineKlassenliste.Klassenliste.get(auswahl_int));
		//Korrekturbuch zu ausgewählter Klasse beim Öffnen der Klasse gleich auswählen/erstellen
		
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseOeffnen.this.setVisible(false);
		KlasseOeffnen.this.dispose();
		
		//aufrufer.setEnabled(true); // Kontrolle wieder an Hauptfenster geben
		//aufrufer.setVisible(true);
	}
	
	public void set_Zusammenfassung (String arg){
		Zusammenfassung.setText(arg);
	}
	
	
	
	private void initialize() {
		
		// Einbindung der Daten aus der Klassenliste-Datei
		meineKlassenliste.readKlasseListFromCSV("klassenliste.csv");
		
		for (int i = 0; i<meineKlassenliste.Klassenliste.size(); i++){
			klass_list.addElement(meineKlassenliste.Klassenliste.get(i).getKlBez() + " " + meineKlassenliste.Klassenliste.get(i).getFach());
		}
		
		
		setTitle("Klasse öffnen");
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
				Klassenliste.addListSelectionListener(ListSL);
				Klassenliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				Klassenliste.setModel(klass_list);
				//klass_list.addElement("Hans");
				scrollPane.setViewportView(Klassenliste);
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
				Zusammenfassung.setEditable(false);
				Zusammenfassung.setFont(new Font("Tahoma", Font.PLAIN, 12));
				Zusammenfassung.setBorder(UIManager.getBorder("TextField.border"));
				Zusammenfassung.setWrapStyleWord(true);
				Zusammenfassung.setLineWrap(true);
				Zusammenfassung.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				Zusammenfassung.setText("Hier wird (bei Auswahl einer Klasse) eine Zusammenfassung für die gewählte Klasse angezeigt\r\n");
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

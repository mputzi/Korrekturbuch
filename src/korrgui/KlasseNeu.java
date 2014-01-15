package korrgui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import java.util.TreeSet;

import javax.swing.Box;

import java.awt.event.KeyEvent;
import java.io.*;

import korrdata.*;



public class KlasseNeu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static String schuelerListCSVFilename = "schueler.csv";
	Frame aufrufer = new Frame();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KlasseNeu dialog = new KlasseNeu(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public KlasseNeu(final Frame aufrufer) {
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
			if (cmd=="DEL"){
				actionDeleteButton();
			}
			if (cmd=="CSV"){
				actionCSVButton();
			}
			if (cmd=="ADD"){
				actionHinzuButton();
			}
		}
	};
	
	private ListSelectionListener ListSL = new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent arg0){
			btnAusgewLschen.setEnabled(true); //Ein Element der Liste wurde ausgewählt -> somit kann man es auch löschen
		}
	};
	
	
	private KeyListener KeyL = new KeyListener(){
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()>32){
				btnhinzu.setEnabled(true);
			}
			if (e.getKeyCode()==10){
				actionHinzuButton();
			}
		}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
	};
		
	private ListDataListener ListDL = new ListDataListener(){
		public void contentsChanged(ListDataEvent arg0) {
			//
		}

		public void intervalAdded(ListDataEvent arg0) {
			//Beim Update der Liste diese gleich neu sortieren
			name_list.removeListDataListener(ListDL); //Listener deaktivieren, da sonst rekursiver Aufruf!
			name_list_neu = new TreeSet<String>();
			//System.out.print(name_list);System.out.println(name_list_neu);
			for (int i=0; i<name_list.getSize(); i++){
				name_list_neu.add((String) name_list.getElementAt(i));
			};
			//System.out.print(name_list);System.out.println(name_list_neu);
			name_list.clear();
			//System.out.print(name_list);System.out.println(name_list_neu);
			while (name_list_neu.size()!=0){
				name_list.addElement((String) name_list_neu.pollFirst());
			};
			//System.out.print(name_list);System.out.println(name_list_neu);
			name_list.addListDataListener(ListDL);
		}

		public void intervalRemoved(ListDataEvent arg0) {
			//
		}
	};
	
	
	
	
	private JTextField txtBezeichnung;
	private JTextField klasseBezeichnung;
	private JTextField txtFach;
	private JTextField klasseFach;
	private JTextField txtSchuljahr;
	private JTextField klasseSJ;
	private JTextField txtLehrer;
	private JTextField klasseLehrer;
	private JTextField txtSchler;
	private JTextField klasseAmt;
	private JTextField klasseSchule;
	private JTextField txtSchule;
	private JTextField txtAmtsbez;
	private JTextField klasseSchuelerinput;
	private JButton btnImportcsv;
	private JScrollPane scrollPane;
	private JButton btnAusgewLschen;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private JButton btnhinzu;
	private DefaultListModel<String> name_list = new DefaultListModel<String>();
	private JList<String> liste = new JList<String>(name_list);
	private TreeSet<String> name_list_neu;
	
	
	private void actionOKButton(){
		
		// selektierte neue Klasse setzen
			//** falls KlassenID implementiert:
				//Integer newID = new Integer(999);
				// newID bestimmen ... 
				//KBMainWin.set_class_selected(newID);
				//KBMainWin.set_class_open(true);
		KBMainWin.set_class_open(false);
				
		// Schülerliste zur Klasse wird erstellt. 		
		SchuelerList newSL = new SchuelerList();
		Schueler newS;
		
		int sc =0;
		String listname ="";
		String[] nameParts;
		String vorname ="";
		String nachname ="";
		while(!name_list.isEmpty() && sc < name_list.getSize()){
			listname = name_list.get(sc).toString();
			nameParts = listname.split(" ");
			vorname = nameParts[0];
			nachname = nameParts[1];
			
			newS = new Schueler(vorname,nachname);
			newSL.addToSchuelerList(newS);
			
			sc++;
		}
				
		// Auslesen der Textfelder
		String newBez = this.klasseBezeichnung.getText();
		String newFach = this.klasseFach.getText();
		Integer newSJ;
		try{
			newSJ = new Integer(this.klasseSJ.getText());
		}
		catch(NumberFormatException e){
			System.out.println("Eingegebenes Schuljahr keine Zahl!");
			KlasseNeu.this.setVisible(false);
			KlasseNeu.this.dispose();
			return;
		};
		Lehrer newLeh = new Lehrer(this.klasseAmt.getText(),
				this.klasseLehrer.getText(),
				this.klasseSchule.getText());
			
		
		// neue Klasse erstellen
		Klasse kl = new Klasse(newBez,newFach,newSJ,newLeh);
		kl.setSchuelerL(newSL);
		kl.writeSchuelerList();
		kl.readSchuelerAnz();
		
		// Korrekturbuch anlegen
		Korrekturbuch kb = new Korrekturbuch(kl);
		kb.writeKorrekturBuch();
		
		// Klassenliste ergänzen
		KlasseList klL = new KlasseList();
		// auslesen
		klL.readKlasseListFromCSV(KBMainWin.KLISTE);
		// hinzufügen
		klL.addToKlasseList(kl);
		// schreiben
		klL.writeKlasseListToCSV(KBMainWin.KLISTE);
		
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseNeu.this.setVisible(false);
		KlasseNeu.this.dispose();
		// Kontrolle wieder an Hauptfenster geben
		//aufrufer.setEnabled(true);
		//aufrufer.setVisible(true);
		
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseNeu.this.setVisible(false);
		KlasseNeu.this.dispose();
		
		//aufrufer.setEnabled(true); // Kontrolle wieder an Hauptfenster geben
		//aufrufer.setVisible(true);
	}
	
	private void actionDeleteButton(){
		name_list.remove(liste.getSelectedIndex());
		btnAusgewLschen.setEnabled(false);
	}
	
	private void actionHinzuButton(){
		if (klasseSchuelerinput.getText().length()>0){
			name_list.addElement(klasseSchuelerinput.getText());
			liste.setSelectedIndex(name_list.indexOf(klasseSchuelerinput.getText()));
			klasseSchuelerinput.setText("");
			btnhinzu.setEnabled(false);
		}
	}
	
	
	private void actionCSVButton(){
		// Die Datei schueler.csv wird importiert, sofern vorhanden
		
		// Passwort-Änderungs-Dialog erstellen
		SchuelerCSV SLCSVDialog = new SchuelerCSV(this);
		// Aufrufenden frame an Passwort-Änderungs-Dialog übermitteln
		SLCSVDialog.setLocationRelativeTo(this);
		// Passwort-Änderungs-Dialog anzeigen
		SLCSVDialog.setVisible(true);			
		
		
		File datei = new File(getSchuelerListCSVFilename());
		if (datei.exists()){
			try {
			name_list.clear();
			FileReader fr = new FileReader(datei);
			BufferedReader in = new BufferedReader(fr);
			String zeile=null;
			while ((zeile = in.readLine()) != null) {
				name_list.addElement(zeile);
			}
			in.close();
			}
			catch (IOException e) { e.printStackTrace(); }
		}
		btnAusgewLschen.setEnabled(false);
		
	}
	
		
	private void initialize() {
		setTitle("Klasse neu anlegen");
		setBounds(100, 100, 580, 390);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(CLASSal);
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(CLASSal);
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][][][][][][][][]"));
			{
				txtBezeichnung = new JTextField();
				txtBezeichnung.setBorder(null);
				txtBezeichnung.setEditable(false);
				txtBezeichnung.setOpaque(false);
				txtBezeichnung.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtBezeichnung.setText("Bezeichnung der Klasse");
				panel.add(txtBezeichnung, "cell 0 0,growx");
				txtBezeichnung.setColumns(10);
			}
			{
				txtSchuljahr = new JTextField();
				txtSchuljahr.setBorder(null);
				txtSchuljahr.setEditable(false);
				txtSchuljahr.setOpaque(false);
				txtSchuljahr.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtSchuljahr.setText("Schuljahr");
				panel.add(txtSchuljahr, "cell 1 0,growx");
				txtSchuljahr.setColumns(10);
			}
			{
				txtSchler = new JTextField();
				txtSchler.setBorder(null);
				txtSchler.setEditable(false);
				txtSchler.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtSchler.setText("Schüler");
				panel.add(txtSchler, "cell 2 0,alignx center");
				txtSchler.setColumns(10);
			}
			{
				klasseBezeichnung = new JTextField();
				klasseBezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseBezeichnung, "cell 0 1,growx");
				klasseBezeichnung.setColumns(10);
			}
			{
				klasseSJ = new JTextField();
				klasseSJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseSJ, "cell 1 1,growx");
				klasseSJ.setColumns(10);
			}
			{
				klasseSchuelerinput = new JTextField();
				klasseSchuelerinput.addKeyListener(KeyL);
				
				panel.add(klasseSchuelerinput, "flowx,cell 2 1,growx");
				klasseSchuelerinput.setColumns(10);
			}
			{
				verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut, "cell 0 2");
			}
			{
				scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBorder(UIManager.getBorder("ScrollPane.border"));
				panel.add(scrollPane, "cell 2 2 1 10,grow");
				{
					liste.addListSelectionListener(ListSL);
					name_list.addListDataListener(ListDL);
					name_list.addElement("Max Mustermann");
								
					scrollPane.setViewportView(liste);
				}
			}
			{
				txtFach = new JTextField();
				txtFach.setBorder(null);
				txtFach.setEditable(false);
				txtFach.setOpaque(false);
				txtFach.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtFach.setText("Fach");
				panel.add(txtFach, "cell 0 3,growx");
				txtFach.setColumns(10);
			}
			{
				klasseFach = new JTextField();
				klasseFach.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseFach, "flowx,cell 0 4,growx");
				klasseFach.setColumns(10);
			}
			{
				verticalStrut_1 = Box.createVerticalStrut(20);
				panel.add(verticalStrut_1, "cell 0 5");
			}
			{
				txtLehrer = new JTextField();
				txtLehrer.setBorder(null);
				txtLehrer.setEditable(false);
				txtLehrer.setOpaque(false);
				txtLehrer.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtLehrer.setText("Lehrer (Name, Vorname)");
				panel.add(txtLehrer, "cell 0 6,growx");
				txtLehrer.setColumns(10);
			}
			{
				txtAmtsbez = new JTextField();
				txtAmtsbez.setBorder(null);
				txtAmtsbez.setEditable(false);
				txtAmtsbez.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtAmtsbez.setOpaque(false);
				txtAmtsbez.setText("Amtsbez.");
				panel.add(txtAmtsbez, "cell 1 6,growx");
				txtAmtsbez.setColumns(10);
			}
			{
				klasseLehrer = new JTextField();
				klasseLehrer.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseLehrer, "cell 0 7,growx");
				klasseLehrer.setColumns(10);
			}
			{
				klasseAmt = new JTextField();
				klasseAmt.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseAmt, "cell 1 7,growx");
				klasseAmt.setColumns(10);
			}
			{
				verticalStrut_2 = Box.createVerticalStrut(20);
				panel.add(verticalStrut_2, "cell 0 8");
			}
			{
				txtSchule = new JTextField();
				txtSchule.setOpaque(false);
				txtSchule.setEditable(false);
				txtSchule.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtSchule.setBorder(null);
				txtSchule.setText("Schule");
				panel.add(txtSchule, "cell 0 9,growx");
				txtSchule.setColumns(10);
			}
			{
				klasseSchule = new JTextField();
				klasseSchule.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(klasseSchule, "cell 0 10 2 1,growx");
				klasseSchule.setColumns(10);
			}
			{
				btnImportcsv = new JButton("Importiere Schülernamen (CSV)");
				btnImportcsv.setToolTipText("<html>Schüler werden aus 'schueler.csv' hinzugefügt.<br>Diese Datei wird zeilenweise gelesen und muss also nur 'Name Vorname' enthalten.<br>Dabei werden bereits vorhandene Namen überschrieben!</html>");
				btnImportcsv.setEnabled(false);
				File datei = new File("schueler.csv");
				if (datei.exists()){
					btnImportcsv.setEnabled(true);
				}
				btnImportcsv.setActionCommand("CSV");
				btnImportcsv.addActionListener(CLASSal);
				panel.add(btnImportcsv, "cell 0 12,alignx right");
			}
			{
				verticalStrut_3 = Box.createVerticalStrut(20);
				panel.add(verticalStrut_3, "cell 0 11");
			}
			{
				btnAusgewLschen = new JButton("ausgew. löschen");
				btnAusgewLschen.setActionCommand("DEL");
				btnAusgewLschen.addActionListener(CLASSal);
				btnAusgewLschen.setEnabled(false);
				panel.add(btnAusgewLschen, "cell 2 12,alignx center");
			}
			{
				btnhinzu = new JButton("(hinzu!)");
				btnhinzu.setEnabled(false);
				btnhinzu.setActionCommand("ADD");
				btnhinzu.addActionListener(CLASSal);
				panel.add(btnhinzu, "cell 2 1,alignx right");
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public static String getSchuelerListCSVFilename() {
		return schuelerListCSVFilename;
	}


	public static void setSchuelerListCSVFilename(
			String schuelerListCSVFilename) {
		KlasseNeu.schuelerListCSVFilename = schuelerListCSVFilename;
	}
}

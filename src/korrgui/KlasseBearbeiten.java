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
//import org.jdesktop.swingx.JXList;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.Box;
import java.awt.event.KeyEvent;

import korrdata.KlasseList;
import korrdata.Schueler;
import korrdata.SchuelerList;
import java.awt.Window.Type;


public class KlasseBearbeiten extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	Frame aufrufer = new Frame();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KlasseBearbeiten dialog = new KlasseBearbeiten(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public KlasseBearbeiten(final Frame aufrufer) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.aufrufer = aufrufer;
		initialize(); // Dialogfenster aufbauen mit "OK"- und "Cancel"-Button
		
		/**
		 * Testdaten einfügen
		 *
		name_list.addElement("Max Mustermann");
		klasseBezeichnung.setText("8b");
		klasseFach.setText("Physik");
		klasseSJ.setText("2013/14");
		klasseLehrer.setText("Völkl R.");
		klasseAmt.setText("StR");
		klasseSchule.setText("Stiftland-Gymnasium");
		*/
		
		/**
		 * Daten aus gewählter Klasse einfügen
		 */
		lies_Grunddaten();
		lies_Schueler();
		
		
	}
	
	private void lies_Schueler()
	{
		SchuelerList SList = new SchuelerList();
		SList = meineKlassenliste.Klassenliste.get(classselected).getSchuelerL();
		
		String Sname;
		ArrayList<Schueler> SListe = new ArrayList<Schueler>();
		SListe = SList.Schuelerliste;
		
		
		int sizeL = SListe.size();
		for (int i=0;i<sizeL;i++){
			Sname = SListe.get(i).getNachname() + " " + SListe.get(i).getVorname();
			name_list.addElement((String)Sname);
		}
		
		//System.out.println("Neuer Versuch "+SListe);
		//damit ist man in der zugehörigen Schülerliste
		
		
		
	}
	
	private void schreib_Schueler()
	{
		/**
		 * muss noch implementiert werden
		 */
		
		
		
	}
	
	private void schreib_Grunddaten()
	{
		/**
		 * muss noch implementiert werden
		 */
		
				
	}
	
	private void lies_Grunddaten()
	{
		klasseBezeichnung.setText(meineKlassenliste.Klassenliste.get(classselected).getKlBez());
		klasseFach.setText(meineKlassenliste.Klassenliste.get(classselected).getFach());
		klasseSJ.setText(meineKlassenliste.Klassenliste.get(classselected).getSchuljahr()+"");
		klasseLehrer.setText(meineKlassenliste.Klassenliste.get(classselected).getLehrer().getNachname());
		klasseAmt.setText(meineKlassenliste.Klassenliste.get(classselected).getLehrer().getAmtsbez());
		klasseSchule.setText(meineKlassenliste.Klassenliste.get(classselected).getLehrer().getSchule());
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
			if (cmd=="ADD"){
				actionHinzuButton();
			}
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

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	};
	
	private ListSelectionListener ListSL = new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent arg0){
			btnAusgewLschen.setEnabled(true); //Ein Element der Liste wurde ausgewählt -> somit kann man es auch löschen
		}
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
	private JScrollPane scrollPane;
	private JButton btnAusgewLschen;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private JButton btnhinzu;
	private DefaultListModel name_list = new DefaultListModel();
	private JList liste = new JList(name_list);
	private TreeSet<String> name_list_neu; //Dummy-Liste zum Verschieben und sortieren der Daten beim "neu-Hinzufügen"
	
	private JList Klassenliste = new JList();
	private DefaultListModel klass_list = new DefaultListModel();
	private KlasseList meineKlassenliste = new KlasseList();
	private int classselected = KBMainWin.get_class_selected();
	
	private void actionOKButton(){
		
		/**
		 * Hier müssen jetzt noch die veränderten Daten zurückgeschrieben werden
		 */
		schreib_Grunddaten();
		schreib_Schueler();
		
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseBearbeiten.this.setVisible(false);
		KlasseBearbeiten.this.dispose();
		KBMainWin.set_class_open(true); // Eine Klasse ist ja nach wie vor ausgewählt
		// Kontrolle wieder an Hauptfenster geben
		//aufrufer.setEnabled(true);
		//aufrufer.setVisible(true);
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseBearbeiten.this.setVisible(false);
		KlasseBearbeiten.this.dispose();
		
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
	
	
		
	private void initialize() {
		// Einbindung der Daten aus der Klassenliste-Datei
		meineKlassenliste.readKlasseListFromCSV("klassenliste.csv");
		System.out.println(meineKlassenliste.Klassenliste.get(classselected).getKlBez());
		
		setTitle("Klasse bearbeiten");
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
					//name_list.addElement("Max Mustermann");
									
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
}

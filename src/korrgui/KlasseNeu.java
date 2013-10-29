package korrgui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import java.util.Collections;

import javax.swing.Box;


public class KlasseNeu extends JDialog implements ActionListener {

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
	
	/**private ItemListener ListIL = new ItemListener(){
		public void itemStateChanged(ItemEvent arg0){
			btnAusgewLschen.setEnabled(true); //Ein Element der Liste wurde ausgewählt -> somit kann man es auch löschen
		}
	};**/
	
	
	
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
	private JButton btnImportcvs;
	private JScrollPane scrollPane;
	private JButton btnAusgewLschen;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private JButton btnhinzu;
	private JList <String> liste;
	private DefaultListModel <String> name_list;
	
	private void actionOKButton(){
		
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseNeu.this.setVisible(false);
		KlasseNeu.this.dispose();
		// Kontrolle wieder an Hauptfenster geben
		aufrufer.setEnabled(true);
		aufrufer.setVisible(true);
		Hauptfenster.set_class_open(true);
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseNeu.this.setVisible(false);
		KlasseNeu.this.dispose();
		
		aufrufer.setEnabled(true); // Kontrolle wieder an Hauptfenster geben
		aufrufer.setVisible(true);
	}
	
	private void actionDeleteButton(){
		name_list.remove(liste.getSelectedIndex());
		btnAusgewLschen.setEnabled(false);
	}
	
	private void actionHinzuButton(){
		name_list.addElement(klasseSchuelerinput.getText());
		
		liste.setSelectedIndex(name_list.indexOf(klasseSchuelerinput.getText()));
		klasseSchuelerinput.setText("");
		
		
	}
	
	private void actionCSVButton(){
		//CSV-Import-Fenster aufmachen
	}
	
		
	private void initialize() {
		setTitle("Klasse neu anlegen");
		setBounds(100, 100, 577, 377);
		
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
					liste = new JList<String>();
					liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					liste.addListSelectionListener(ListSL);
					name_list = new DefaultListModel<String>();
					liste.setModel(name_list);
					name_list.addElement("Test");
					name_list.addElement("Albert");
					name_list.addElement("Martin");
					name_list.addElement("Berta");
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
				btnImportcvs = new JButton("Importiere Schülernamen (CSV)");
				btnImportcvs.setEnabled(false);
				btnImportcvs.setActionCommand("CSV");
				btnImportcvs.addActionListener(CLASSal);
				panel.add(btnImportcvs, "cell 0 12,alignx right");
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
				btnhinzu = new JButton("(hinzu)");
				btnhinzu.setActionCommand("ADD");
				btnhinzu.addActionListener(CLASSal);
				panel.add(btnhinzu, "cell 2 1,alignx right");
			}
		}
	}
}

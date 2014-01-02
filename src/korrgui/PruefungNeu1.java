package korrgui; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import korrdata.Pruefungsarten;
import korrdata.Pruefungsarten.ART;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PruefungNeu1 extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNR;
	private JTextField textField_1;
	private JTextField txtAusDemFach;
	private JTextField txtFach;
	private JTextField txtVom;
	private JTextField textDatum;
	private JComboBox <ART> comboBox;
	private JTextField txtArt;
	private DefaultTableModel model;
	private JTable table;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PruefungNeu1 dialog = new PruefungNeu1(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PruefungNeu1(final Frame aufrufer) {
		initialize();
	}
	
	public void set_Pruefungsart(String art){
		txtArt.setText("("+art+")");
	}

	private void initialize(){
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Bezeichnung");
		model.addColumn("Punkte");
		JTable table = new JTable(model);
		
		
		setBounds(100, 100, 500, 250);
		//setSize(new Dimension(600, 400));
		setTitle("Prüfung neu anlegen");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		{ // OK- und Cancel-Buttons unten anlegen
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(al);
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(al);
				buttonPane.add(cancelButton);
			}
		}
		{ // Fenster im Fenster
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
			panel.setLayout(fl_panel);
			{
				txtNR = new JTextField();
				txtNR.setText("1");
				panel.add(txtNR);
				txtNR.setColumns(2);
			}
			{
				textField_1 = new JTextField();
				textField_1.setOpaque(false);
				textField_1.setBorder(null);
				textField_1.setText(".");
				panel.add(textField_1);
			}
			{
				comboBox = new JComboBox(Pruefungsarten.ART.values()); // evtl ohne Type-Cast da JComboBox<ART> nicht immer funktioniert
				comboBox.addItemListener(il);
				comboBox.setBorder(UIManager.getBorder("ComboBox.border"));
				comboBox.setBackground(Color.WHITE);
				comboBox.setOpaque(false);
				//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kurzarbeit", "Schulaufgabe", "Stegreifaufgabe", "Test"}));
				
				comboBox.setPreferredSize(new Dimension(45, 20));
				comboBox.setSize(new Dimension(45, 20));
				panel.add(comboBox);
			}
			{
				txtArt = new JTextField();
				txtArt.setBorder(null);
				txtArt.setOpaque(false);
				txtArt.setText("(Schulaufgabe)");
				panel.add(txtArt);
				txtArt.setColumns(8);
			}
			{
				txtAusDemFach = new JTextField();
				txtAusDemFach.setEditable(false);
				txtAusDemFach.setColumns(7);
				txtAusDemFach.setOpaque(false);
				txtAusDemFach.setBorder(null);
				txtAusDemFach.setText("aus dem Fach");
				panel.add(txtAusDemFach);
			}
			{
				txtFach = new JTextField();
				txtFach.setBorder(null);
				txtFach.setDisabledTextColor(Color.BLACK);
				txtFach.setEditable(false);
				txtFach.setText("Mathematik");
				panel.add(txtFach);
				txtFach.setColumns(7);
			}
			{
				txtVom = new JTextField();
				txtVom.setBorder(null);
				txtVom.setEditable(false);
				txtVom.setOpaque(false);
				txtVom.setText("vom");
				panel.add(txtVom);
				txtVom.setColumns(3);
			}
			{
				textDatum = new JTextField();
				textDatum.setText("22.09.2025");
				panel.add(textDatum);
				textDatum.setColumns(6);
			}
		}
		{
			panel_Aufgaben = new JPanel();
			getContentPane().add(panel_Aufgaben, BorderLayout.CENTER);
			panel_Aufgaben.setLayout(new BorderLayout(0, 0));
			{
				panel_1 = new JPanel();
				panel_Aufgaben.add(panel_1, BorderLayout.NORTH);
				{
					anzahl_Aufgaben_gui = new JSpinner();
					panel_1.add(anzahl_Aufgaben_gui);
					anzahl_Aufgaben_gui.setModel(new SpinnerNumberModel(6, 1, 30, 1));
					anzahl_Aufgaben_gui.addChangeListener(cl);
				}
				{
					txtAnzahlDerAufgaben = new JTextField();
					panel_1.add(txtAnzahlDerAufgaben);
					txtAnzahlDerAufgaben.setOpaque(false);
					txtAnzahlDerAufgaben.setHorizontalAlignment(SwingConstants.LEFT);
					txtAnzahlDerAufgaben.setMinimumSize(new Dimension(150, 20));
					txtAnzahlDerAufgaben.setSize(new Dimension(150, 20));
					txtAnzahlDerAufgaben.setPreferredSize(new Dimension(150, 20));
					txtAnzahlDerAufgaben.setText("<- Anzahl der Aufgaben (incl. Teilaufgaben) [z.B. 1a, 1b, 2 = 3 Aufgaben]");
					txtAnzahlDerAufgaben.setEditable(false);
					txtAnzahlDerAufgaben.setColumns(35);
					txtAnzahlDerAufgaben.setBorder(null);
				}
			}
			{
				panel_2 = new JPanel();
				panel_Aufgaben.add(panel_2, BorderLayout.CENTER);
				panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
				{
					scrollPane = new JScrollPane();
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					scrollPane.setPreferredSize(new Dimension(180, 2));
					scrollPane.setMaximumSize(new Dimension(180, 32767));
					panel_2.add(scrollPane);
					{
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						// table.setModel(model);
						for (int i=0;i<6;i++){model.addRow(new Object[]{});}
					
						
						/**table.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null},
									{null, null},
									{null, null},
									{null, null},
									{null, null},
									{null, null},
								},
								new String[] {
									"Bezeichnung", "Punkte"
								}
							) {
								Class[] columnTypes = new Class[] {
									String.class, Float.class
								};
								public Class getColumnClass(int columnIndex) {
									return columnTypes[columnIndex];
								}
							});
						**/
						
						table.getColumnModel().getColumn(0).setPreferredWidth(80);
						table.getColumnModel().getColumn(1).setPreferredWidth(80);
						scrollPane.setViewportView(table);
					}
				}
				{
					txt_erklaerung = new JTextArea();
					txt_erklaerung.setWrapStyleWord(true);
					txt_erklaerung.setBackground(Color.WHITE);
					txt_erklaerung.setEditable(false);
					txt_erklaerung.setFont(new Font("Serif", Font.ITALIC, 11));
					txt_erklaerung.setLineWrap(true);
					txt_erklaerung.setMaximumSize(new Dimension(300, 2147483647));
					txt_erklaerung.setText("Links können die (Teil-)Aufgaben mit der jeweiligen maximalen Punktzahl festgelegt werden.\n\n" +
											"Bei der Punktzahl können auch Dezimalbrüche eingegeben werden. " +
											"Hierbei ist darauf zu achten, dass das Dezimalkomma als ''PUNKT'' eigegeben wird!!");
					panel_2.add(txt_erklaerung);
				}
			}
		}
	}
	
	private ActionListener al = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();
			System.out.println(cmd);
		}
	};
	
	private ItemListener il = new ItemListener(){
		public void itemStateChanged(ItemEvent arg0) {
			set_Pruefungsart(Pruefungsarten.Beschreibung((ART) arg0.getItem()));
		}
	};
	
	private ChangeListener cl = new ChangeListener(){
		@Override
		public void stateChanged(ChangeEvent e){
			int anz = Integer.valueOf(anzahl_Aufgaben_gui.getValue().toString()); // Anzahl der Aufgaben
			int rows = table.getRowCount(); // Anzahl der Zeilen in der Aufgabentabelle
			if (anz>rows) {
				System.out.println("anz>row");
				//model.addRow(new Object[]{});
			}else if (anz<rows) {
				System.out.println("anz<row");
			};
		
			
		}
	};
	
	
	
	
	private JPanel panel_Aufgaben;
	private JTextField txtAnzahlDerAufgaben;
	private JSpinner anzahl_Aufgaben_gui;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTextArea txt_erklaerung;
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

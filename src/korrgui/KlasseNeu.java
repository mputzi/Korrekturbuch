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
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Button;


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
			
		}
	};
	private JTextField txtBezeichnungDerKlasse;
	private JTextField textField;
	private JTextField txtFach;
	private JTextField textField_1;
	private JTextField txtSchuljahr;
	private JTextField textField_2;
	private JTextField txtLehrer;
	private JTextField textField_3;
	private JTextField txtSchler;
	private JTextField textField_4;
	private JTextField txtSchule;
	private JTextField txtSchule_1;
	private JTextField txtAmtsbez;
	private JTextField textField_5;
	private JButton btnImportcvs;
	private JScrollPane scrollPane;
	private JButton btnAusgewLschen;
	private List list;
	private Button button;
	
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
	
		
	private void initialize() {
		setTitle("Klasse neu anlegen");
		setBounds(100, 100, 577, 395);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(CLASSal);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
				txtBezeichnungDerKlasse = new JTextField();
				txtBezeichnungDerKlasse.setBorder(null);
				txtBezeichnungDerKlasse.setEditable(false);
				txtBezeichnungDerKlasse.setOpaque(false);
				txtBezeichnungDerKlasse.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtBezeichnungDerKlasse.setText("Bezeichnung der Klasse");
				panel.add(txtBezeichnungDerKlasse, "cell 0 0,growx");
				txtBezeichnungDerKlasse.setColumns(10);
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
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(textField, "cell 0 1,growx");
				textField.setColumns(10);
			}
			{
				textField_2 = new JTextField();
				textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(textField_2, "cell 1 1,growx");
				textField_2.setColumns(10);
			}
			{
				textField_5 = new JTextField();
				panel.add(textField_5, "flowx,cell 2 1,growx");
				textField_5.setColumns(10);
			}
			{
				scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBorder(UIManager.getBorder("ScrollPane.border"));
				panel.add(scrollPane, "cell 2 2 1 10,grow");
				{
					list = new List();
					list.setMultipleSelections(false);
					scrollPane.setViewportView(list);
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
				textField_1 = new JTextField();
				textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(textField_1, "flowx,cell 0 4,growx");
				textField_1.setColumns(10);
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
				textField_3 = new JTextField();
				textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(textField_3, "cell 0 7,growx");
				textField_3.setColumns(10);
			}
			{
				textField_4 = new JTextField();
				textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(textField_4, "cell 1 7,growx");
				textField_4.setColumns(10);
			}
			{
				txtSchule_1 = new JTextField();
				txtSchule_1.setOpaque(false);
				txtSchule_1.setEditable(false);
				txtSchule_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				txtSchule_1.setBorder(null);
				txtSchule_1.setText("Schule");
				panel.add(txtSchule_1, "cell 0 9,growx");
				txtSchule_1.setColumns(10);
			}
			{
				txtSchule = new JTextField();
				txtSchule.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(txtSchule, "cell 0 10 2 1,growx");
				txtSchule.setColumns(10);
			}
			{
				btnImportcvs = new JButton("Importiere Schülernamen (CVS)");
				btnImportcvs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				panel.add(btnImportcvs, "cell 0 12,alignx right");
			}
			{
				btnAusgewLschen = new JButton("ausgew. löschen");
				panel.add(btnAusgewLschen, "cell 2 12,alignx center");
			}
			{
				button = new Button("hinzu");
				panel.add(button, "cell 2 1,alignx right");
			}
		}
	} 
}

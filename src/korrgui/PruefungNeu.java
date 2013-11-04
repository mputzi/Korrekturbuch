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

public class PruefungNeu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNR;
	private JTextField textField_1;
	private JTextField txtAusDemFach;
	private JTextField txtFach;
	private JTextField txtVom;
	private JTextField textDatum;
	private JComboBox <ART> comboBox;
	private JTextField txtArt;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PruefungNeu dialog = new PruefungNeu(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PruefungNeu(final Frame aufrufer) {
		initialize();
	}
	
	public void set_Pruefungsart(String art){
		txtArt.setText("("+art+")");
	}

	private void initialize(){
		setBounds(100, 100, 600, 400);
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
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
				comboBox = new JComboBox<ART>(Pruefungsarten.ART.values());
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
				txtArt.setColumns(10);
			}
			{
				txtAusDemFach = new JTextField();
				txtAusDemFach.setEditable(false);
				txtAusDemFach.setColumns(9);
				txtAusDemFach.setOpaque(false);
				txtAusDemFach.setBorder(null);
				txtAusDemFach.setText("aus dem Fach");
				panel.add(txtAusDemFach);
			}
			{
				txtFach = new JTextField();
				txtFach.setText("Mathematik");
				panel.add(txtFach);
				txtFach.setColumns(10);
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
				textDatum.setColumns(8);
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
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

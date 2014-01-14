package korrgui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;



public class SchuelerCSV extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcsv;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SchuelerCSV dialog = new SchuelerCSV(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SchuelerCSV(final KlasseNeu klNeuDiag) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("CSV-Dateiname");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setAlwaysOnTop(true);
		this.setFocusable(true);
		setBounds(100, 100, 364, 107);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblCsvdateiname = new JLabel("CSV-Dateiname:");
			contentPanel.add(lblCsvdateiname);
		}
		{
			txtcsv = new JTextField();
			txtcsv.setText(".csv");
			contentPanel.add(txtcsv);
			txtcsv.setColumns(10);
			txtcsv.setCaretPosition(0);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						KlasseNeu.setSchuelerListCSVFilename(txtcsv.getText()); 
											
						SchuelerCSV.this.setVisible(false);
						SchuelerCSV.this.dispose();
						
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// Abbrechen und das Programm verlassen
						// Passwort-Dialog abbauen
						SchuelerCSV.this.setVisible(false);
				        SchuelerCSV.this.dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtcsv, okButton, cancelButton}));
	}

}

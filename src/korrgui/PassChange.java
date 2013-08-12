package korrgui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class PassChange extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordFieldAlt;
	private JPasswordField passwordFieldNeu1;
	private JPasswordField passwordFieldNeu2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PassChange dialog = new PassChange();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PassChange() {
		setTitle("Passwort ändern");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblBisherigesPasswort = new JLabel("Bisheriges Passwort");
			GridBagConstraints gbc_lblBisherigesPasswort = new GridBagConstraints();
			gbc_lblBisherigesPasswort.insets = new Insets(0, 0, 5, 5);
			gbc_lblBisherigesPasswort.gridx = 1;
			gbc_lblBisherigesPasswort.gridy = 1;
			contentPanel.add(lblBisherigesPasswort, gbc_lblBisherigesPasswort);
		}
		{
			passwordFieldAlt = new JPasswordField();
			passwordFieldAlt.setColumns(12);
			passwordFieldAlt.setBackground(new Color(255, 250, 205));
			GridBagConstraints gbc_passwordFieldAlt = new GridBagConstraints();
			gbc_passwordFieldAlt.anchor = GridBagConstraints.WEST;
			gbc_passwordFieldAlt.insets = new Insets(0, 0, 5, 0);
			gbc_passwordFieldAlt.gridx = 3;
			gbc_passwordFieldAlt.gridy = 1;
			contentPanel.add(passwordFieldAlt, gbc_passwordFieldAlt);
		}
		{
			JLabel lblNeuesPasswort = new JLabel("Neues Passwort");
			GridBagConstraints gbc_lblNeuesPasswort = new GridBagConstraints();
			gbc_lblNeuesPasswort.insets = new Insets(0, 0, 5, 5);
			gbc_lblNeuesPasswort.gridx = 1;
			gbc_lblNeuesPasswort.gridy = 3;
			contentPanel.add(lblNeuesPasswort, gbc_lblNeuesPasswort);
		}
		{
			passwordFieldNeu1 = new JPasswordField();
			passwordFieldNeu1.setColumns(12);
			GridBagConstraints gbc_passwordFieldNeu1 = new GridBagConstraints();
			gbc_passwordFieldNeu1.anchor = GridBagConstraints.WEST;
			gbc_passwordFieldNeu1.insets = new Insets(0, 0, 5, 0);
			gbc_passwordFieldNeu1.gridx = 3;
			gbc_passwordFieldNeu1.gridy = 3;
			contentPanel.add(passwordFieldNeu1, gbc_passwordFieldNeu1);
		}
		{
			JLabel lblNeuesPasswort_1 = new JLabel("Neues Passwort");
			GridBagConstraints gbc_lblNeuesPasswort_1 = new GridBagConstraints();
			gbc_lblNeuesPasswort_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNeuesPasswort_1.gridx = 1;
			gbc_lblNeuesPasswort_1.gridy = 4;
			contentPanel.add(lblNeuesPasswort_1, gbc_lblNeuesPasswort_1);
		}
		{
			passwordFieldNeu2 = new JPasswordField();
			passwordFieldNeu2.setColumns(12);
			passwordFieldNeu2.setText("");
			GridBagConstraints gbc_passwordFieldNeu2 = new GridBagConstraints();
			gbc_passwordFieldNeu2.anchor = GridBagConstraints.WEST;
			gbc_passwordFieldNeu2.insets = new Insets(0, 0, 5, 0);
			gbc_passwordFieldNeu2.gridx = 3;
			gbc_passwordFieldNeu2.gridy = 4;
			contentPanel.add(passwordFieldNeu2, gbc_passwordFieldNeu2);
		}
		{
			JLabel lblzurKontrolle = new JLabel("(zur Kontrolle)");
			GridBagConstraints gbc_lblzurKontrolle = new GridBagConstraints();
			gbc_lblzurKontrolle.insets = new Insets(0, 0, 0, 5);
			gbc_lblzurKontrolle.gridx = 1;
			gbc_lblzurKontrolle.gridy = 5;
			contentPanel.add(lblzurKontrolle, gbc_lblzurKontrolle);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
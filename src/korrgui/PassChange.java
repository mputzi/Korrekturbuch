package korrgui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import korrsecur.*;

import java.awt.Window.Type;



public class PassChange extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812077875481168875L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordFieldAlt;
	private JPasswordField passwordFieldNeu1;
	private JPasswordField passwordFieldNeu2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PassChange dialog = new PassChange(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PassChange(final Frame aufrufer) {
		setUndecorated(true);
		setResizable(false);
		
		ActionListener PWDChgAL = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cmd = e.getActionCommand();
				System.out.println("Passwort-Änderungs-Dialog: " + cmd);
				if (cmd=="OK"){
					actionOKButton();		
				}
				
				// Dialog verlassen.
				if (cmd=="Cancel"){
					actionCancelButton();									
 			    }
			}
		};
		
		
		setTitle("Passwort ändern");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 250);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{32, 146, 35, 136, 0};
		gbl_contentPanel.rowHeights = new int[]{32, 19, 35, 19, 19, 15, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			passwordFieldAlt.setToolTipText("Das alte Passwort hatte " + Secur.passLength() + " Zeichen.");
			passwordFieldAlt.setColumns(12);
			passwordFieldAlt.setBackground(new Color(255, 250, 205));
			/*
			StringBuffer sb = new StringBuffer(Secur.passLength());
			for (int i =0;i<Secur.passLength();i++){
				sb.append("a");
			}
			passwordFieldAlt.setText(sb.toString()); */
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
				okButton.addActionListener(PWDChgAL);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(PWDChgAL);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	private void actionOKButton(){
		
		// Ausgabe (debug only)
		// StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append("Eingegebenes Passwort: ");
		// stringBuilder.append(passwordFieldAlt.getPassword());		
		// System.out.println(stringBuilder.toString());
		
		// Vergleiche mit hinterlegtem Passwort
		boolean passCheck = Secur.passCheck(passwordFieldAlt.getPassword());
		// Vergleiche, ob eingegebene neue Passwörter gleich sind.
		boolean passEqualCheck = Secur.passEqualCheck(passwordFieldNeu1.getPassword(),passwordFieldNeu2.getPassword());
		
		
		// Wenn Passwort richtig:
		if(passCheck){
			// Zurück zum aufrufenden Fenster!
			System.out.println("Passwort akzeptiert.");
			
			if(passEqualCheck){
				System.out.println("Eingegebene Passwörter gleich");
			
				// Passwort abspeichern
				Secur.writePWD(passwordFieldNeu1.getPassword());
				// Passwort-Dialog abbauen
				PassChange.this.setVisible(false);
				PassChange.this.dispose();
			}
			else{
				System.out.println("Neue Passwörter nicht identisch!");
			}
			}
		else{
			System.out.println("Passwort falsch");
			
			
		}
		
	}
	
	private void actionCancelButton(){
		
		// Abbrechen und das Programm verlassen
		// Passwort-Dialog abbauen
		PassChange.this.setVisible(false);
        PassChange.this.dispose();
	}
}

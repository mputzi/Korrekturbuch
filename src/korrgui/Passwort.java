package korrgui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Passwort extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField passwortField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Passwort dialog = new Passwort(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Passwort(final Frame aufrufer) {
		ActionListener PWDal = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cmd = e.getActionCommand();
				if (cmd=="OK"){
				System.out.println("Passwort-Dialog: " + cmd);
				// Vergleiche mit hinterlegtem Passwort
				System.out.println("Eingegebenes Passwort: "+
				  passwortField.getText());
				// Wenn Passwort richtig:
				// Zurück zum aufrufenden Fenster!
				System.out.println("Passwort akzeptiert.");
				// Passwort-Dialog abbauen
				Passwort.this.setVisible(false);
		        Passwort.this.dispose();
				}
				// Programm verlassen.
				if (cmd=="Cancel"){
					System.out.println("Passwort-Dialog: " + cmd);
					// Abbrechen und das Programm verlassen
					// Passwort-Dialog abbauen
					Passwort.this.setVisible(false);
			        Passwort.this.dispose();
			        // Versuche das aufrufende Fenster zu schließen!
			        try{
			        	aufrufer.setVisible(false);
			        	aufrufer.dispose();}
			        catch(NullPointerException npe){
			        	System.out.println("kein aufrufender Frame gefunden!");
			        }
					System.exit(0);
				}
			}
		};
		
		
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBackground(Color.RED);
		getContentPane().setBackground(new Color(255, 0, 0));
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 431, 47);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JLabel lblPasswort = new JLabel("Passwort:");
			contentPanel.add(lblPasswort);
		}
		{
			passwortField = new JTextField();
			contentPanel.add(passwortField);
			passwortField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(PWDal);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");	
				cancelButton.addActionListener(PWDal);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

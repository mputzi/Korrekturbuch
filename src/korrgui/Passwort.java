package korrgui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
			Passwort dialog = new Passwort();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Passwort() {
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cmd = e.getActionCommand();
						if (cmd=="OK"){
						System.out.println("Passwort-Dialog: " + cmd);
						// Vergleiche mit hinterlegtem Passwort
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
		
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cmd = e.getActionCommand();
						if (cmd=="Cancel"){
						System.out.println("Passwort-Dialog: " + cmd);
						// Abbrechen und das Programm verlassen
						JButton bt = (JButton)e.getSource();
						JDialog dg = (JDialog)bt.getParent();
						System.out.println(dg);
						
						//dg.setVisible(false);
						//dg.dispose();
						//System.exit(0);
						}
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
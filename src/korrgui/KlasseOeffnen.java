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
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ComponentOrientation;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.Box;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.awt.Panel;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JScrollBar;

public class KlasseOeffnen extends JDialog {

	Frame aufrufer = new Frame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KlasseOeffnen dialog = new KlasseOeffnen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public KlasseOeffnen(final Frame aufrufer) {
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
	
	private void actionOKButton(){
		// Bestätigen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseOeffnen.this.setVisible(false);
		KlasseOeffnen.this.dispose();
		Hauptfenster.set_class_open(true);
		aufrufer.setEnabled(true); // Kontrolle wieder an Hauptfenster geben
		aufrufer.setVisible(true);
		
	}
	
	private void actionCancelButton(){
		// Abbrechen und die Klassenauswahl verlassen
		// Dialog abbauen
		KlasseOeffnen.this.setVisible(false);
		KlasseOeffnen.this.dispose();
		
		aufrufer.setEnabled(true); // Kontrolle wieder an Hauptfenster geben
		aufrufer.setVisible(true);
	}
	
	private void initialize() {
		setTitle("Klasse öffnen");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
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
			Box horizontalBox = Box.createHorizontalBox();
			getContentPane().add(horizontalBox, BorderLayout.CENTER);
			{
				JList list = new JList();
				list.setSize(new Dimension(120, 120));
				list.setPreferredSize(new Dimension(120, 120));
				list.setFixedCellHeight(10);
				list.setAlignmentX(Component.LEFT_ALIGNMENT);
				list.setAlignmentY(Component.TOP_ALIGNMENT);
				list.setMaximumSize(new Dimension(120, 120));
				list.setMinimumSize(new Dimension(120, 120));
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {"Data1", "Data2", "bla", "Data1", "Data2", "bla", "Data1", "Data2", "bla", "Data1", "Data2", "bla"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				horizontalBox.add(list);
			}
		}
	} 
}

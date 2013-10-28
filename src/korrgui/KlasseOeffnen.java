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

public class KlasseOeffnen extends JDialog {

	Frame aufrufer = new Frame();
	private final JPanel contentPanel = new JPanel();
	private JTextArea Zusammenfassung = new JTextArea();
	private JList<String> Klassenliste = new JList<String> ();
	
	

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
		setResizable(false);
		this.aufrufer = aufrufer;
		initialize(); // Dialogfenster aufbauen mit "OK"- und "Cancel"-Button
		//set_Zusammenfassung("Test");
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
	
	public void set_Zusammenfassung (String arg){
		Zusammenfassung.setText(arg);
	}
	
	public void set_Klassenliste (String[] txt){
		//Test
	}
	
	
	private void initialize() {
		setTitle("Klasse öffnen");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(150, 200));
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.WEST);
			{
				//JList Klassenliste = new JList();
				Klassenliste.setModel(new AbstractListModel() {
					String[] values = new String[] {"Tets", "Auto", "Banane", "dksjge", "dsaökfe", "dddddjjj", "Tets", "Auto", "Banane", "dksjge", "dsaökfe", "dddddjjj", "Tets", "Auto", "Banane", "dksjge", "dsaökfe", "dddddjjj"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				Klassenliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(Klassenliste);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(250, 200));
			contentPanel.add(scrollPane, BorderLayout.EAST);
			
			{
				//JTextArea Zusammenfassung = new JTextArea();
				scrollPane.setViewportView(Zusammenfassung);
				Zusammenfassung.setEditable(false);
				Zusammenfassung.setFont(new Font("Tahoma", Font.PLAIN, 12));
				Zusammenfassung.setBorder(UIManager.getBorder("TextField.border"));
				Zusammenfassung.setWrapStyleWord(true);
				Zusammenfassung.setLineWrap(true);
				Zusammenfassung.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				Zusammenfassung.setText("Hier wird (bei Auswahl einer Klasse) eine Zusammenfassung für die gewählte Klasse angezeigt\r\n");
			}
		}
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
	} 
}

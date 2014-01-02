package korrgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.UIManager;

public class Korrektureingabe extends JFrame {

	private JPanel contentPane;
	private JTable table_aufgaben_beschr;
	private JTable table_id;
	private JTable table_BE;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Korrektureingabe frame = new Korrektureingabe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Korrektureingabe() {
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		contentPane.add(splitPane, BorderLayout.SOUTH);
		
		JButton btnFertig = new JButton("Fertig");
		splitPane.setRightComponent(btnFertig);
		
		JButton btnAbbruch = new JButton("Abbruch");
		splitPane.setLeftComponent(btnAbbruch);
		
		JLabel lblEingabeDerErreichten = new JLabel("Eingabe der erreichten Bewertungseinheiten");
		contentPane.add(lblEingabeDerErreichten, BorderLayout.NORTH);
		
		JInternalFrame internalFrame = new JInternalFrame("Korrekturliste");
		contentPane.add(internalFrame, BorderLayout.CENTER);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[249.00,grow][grow]", "[60,grow][]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_1, "cell 0 0,grow");
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		scrollPane_1.setViewportView(horizontalBox_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(150);
		horizontalBox_1.add(horizontalStrut_1);
		
		table_aufgaben_beschr = new JTable();
		table_aufgaben_beschr.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		horizontalBox_1.add(table_aufgaben_beschr);
		table_aufgaben_beschr.setModel(new DefaultTableModel(
			new Object[][] {
				{"Aufgabe"},
				{"erreichbar"},
			},
			new String[] {
				""
			}
		));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_3, "cell 1 0,grow");
		
		Box horizontalBox = Box.createHorizontalBox();
		scrollPane_3.setViewportView(horizontalBox);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 204));
		horizontalBox.add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		
		Component horizontalStrut = Box.createHorizontalStrut(15);
		horizontalBox.add(horizontalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, "cell 0 1,grow");
		
		table_id = new JTable();
		table_id.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		table_id.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"SID", "Anw.", "Name", "Vorname"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Boolean.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_id.getColumnModel().getColumn(0).setResizable(false);
		table_id.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_id.getColumnModel().getColumn(1).setResizable(false);
		table_id.getColumnModel().getColumn(1).setPreferredWidth(15);
		table_id.getColumnModel().getColumn(2).setResizable(false);
		table_id.getColumnModel().getColumn(2).setPreferredWidth(40);
		table_id.getColumnModel().getColumn(3).setResizable(false);
		table_id.getColumnModel().getColumn(3).setPreferredWidth(40);
		scrollPane.setViewportView(table_id);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_2, "cell 1 1,grow");
		
		table_BE = new JTable();
		table_BE.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"0", "1", "2", "3", "4"
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_BE.getColumnModel().getColumn(0).setResizable(false);
		table_BE.getColumnModel().getColumn(1).setResizable(false);
		table_BE.getColumnModel().getColumn(2).setResizable(false);
		table_BE.getColumnModel().getColumn(3).setResizable(false);
		table_BE.getColumnModel().getColumn(4).setResizable(false);
		table_BE.setGridColor(Color.GREEN);
		scrollPane_2.setViewportView(table_BE);
		internalFrame.setVisible(true);
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	
	private getAufgabenFromPr(Pruefung ){
		
		table_BE.setValueAt("!", 0, 0);
	}

}

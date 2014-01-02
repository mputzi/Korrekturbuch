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

public class Korrektureingabe extends JFrame {

	private JPanel contentPane;
	private JTable table_aufgaben;
	private JTable table_id;
	private JTable table_BE;

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
		contentPane.add(splitPane, BorderLayout.SOUTH);
		
		JButton btnFertig = new JButton("Fertig");
		splitPane.setRightComponent(btnFertig);
		
		JButton btnAbbruch = new JButton("Abbruch");
		splitPane.setLeftComponent(btnAbbruch);
		
		JLabel lblEingabeDerErreichten = new JLabel("Eingabe der erreichten Bewertungseinheiten");
		contentPane.add(lblEingabeDerErreichten, BorderLayout.NORTH);
		
		JInternalFrame internalFrame = new JInternalFrame("Korrekturliste");
		contentPane.add(internalFrame, BorderLayout.CENTER);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow][][grow]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_1, "cell 0 0 2 2,grow");
		
		table_aufgaben = new JTable();
		scrollPane_1.setViewportView(table_aufgaben);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, "cell 0 2,grow");
		
		table_id = new JTable();
		scrollPane.setViewportView(table_id);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane_2, "cell 1 2,grow");
		
		table_BE = new JTable();
		scrollPane_2.setViewportView(table_BE);
		internalFrame.setVisible(true);
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

}

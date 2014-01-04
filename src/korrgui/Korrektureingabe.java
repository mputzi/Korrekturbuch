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

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JScrollBar;
/*
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
*/
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;
import javax.swing.Box;

import java.awt.Component;

import javax.swing.UIManager;

import korrdata.AufgabeList;
import korrdata.Pruefung;
import korrdata.KorrekturListe;

import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import korrdata.*;





public class Korrektureingabe extends JFrame {

	private JPanel contentPane;
	private JTable table_aufgaben_beschr;
	private JTable table_id;
	private JTable table_BE;
	private JTable table_aufgaben;
	
	private Pruefung aktPr;
	private AufgabeList al;
	private KorrekturListe kl;
	private JTable table_sum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Korrektureingabe frame = new Korrektureingabe(new Pruefung());
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
	public Korrektureingabe(Pruefung pr) {
		setTitle("Korrekturliste");
		setResizable(false);
		this.setAktPr(pr);
		this.setAl(pr.getAufgabenListe());
		this.setKl(pr.getKorrekturliste());
		
		int schZahl  = this.kl.getAnzSchueler();
		int aufgZahl = this.al.getAnz();
		
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
		lblEingabeDerErreichten.setMinimumSize(new Dimension(0, 0));
		lblEingabeDerErreichten.setMaximumSize(new Dimension(0, 0));
		lblEingabeDerErreichten.setPreferredSize(new Dimension(0, 0));
		contentPane.add(lblEingabeDerErreichten, BorderLayout.NORTH);
		
		JInternalFrame internalFrame = new JInternalFrame("Eingabe der erreichten Punkte");
		contentPane.add(internalFrame, BorderLayout.CENTER);
		internalFrame.getContentPane().setLayout(new MigLayout("", "[249.00,grow][grow][grow]", "[60,grow][grow]"));
		
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
		JScrollBar shbar1 = scrollPane_3.getHorizontalScrollBar();
		internalFrame.getContentPane().add(scrollPane_3, "cell 1 0,grow");
		
		Box horizontalBox = Box.createHorizontalBox();
		scrollPane_3.setViewportView(horizontalBox);
		
		table_aufgaben = new JTable();
		table_aufgaben.setRowSelectionAllowed(false);
		table_aufgaben.setBackground(new Color(255, 255, 204));
		horizontalBox.add(table_aufgaben);
		table_aufgaben.setModel(new DefaultTableModel(
			new Object[2][aufgZahl] ,
			new String[aufgZahl] 
		));
		
		Component horizontalStrut = Box.createHorizontalStrut(15);
		horizontalBox.add(horizontalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		JScrollBar sbar1 = scrollPane.getVerticalScrollBar();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		internalFrame.getContentPane().add(scrollPane, "cell 0 1,grow");
		
		table_id = new JTable();
		table_id.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		table_id.setModel(new DefaultTableModel(
			new Object[schZahl][4] ,
		//	new Object[32][4] ,
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
		JScrollBar sbar2 = scrollPane_2.getVerticalScrollBar();
		JScrollBar shbar2 = scrollPane_2.getHorizontalScrollBar();
		sbar2.setModel(sbar1.getModel());
		shbar2.setModel(shbar1.getModel());
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		internalFrame.getContentPane().add(scrollPane_2, "cell 1 1,grow");
		
		table_BE = new JTable();
		
		   
		
		
		class BEInputTableModel extends AbstractTableModel{
			private String[] columnNames;
			private Object[][] data;
			private Class[] classes;
			
			BEInputTableModel(Object[][] data){
				this.data = data;
			};
			BEInputTableModel(Object[][] data, String[] columnNames){
				this.columnNames = columnNames;
				this.data = data;
			};
			BEInputTableModel(Object[][] data, String[] columnNames, Class[] classes){
				this.columnNames = columnNames;
				this.data = data;
				this.classes = classes;
			};
			
			public int getColumnCount() {
				if( columnNames != null )
					return columnNames.length;
					else
					return -1; 
		    }

		    public int getRowCount() {
		    	if( data != null )
		    		return data.length;
		    		else
		    		return -1;
		    }

		    public String getColumnName(int col) {
		        return columnNames[col];
		    }

		    public Object getValueAt(int row, int col) {
		        return data[row][col];
		    }

		    public Class getColumnClass(int c) {
		        //return getValueAt(0, c).getClass();
		    	return Float.class;
		    }
		    
		    public boolean isCellEditable(int row, int col) {
		        //Note that the data/cell address is constant,
		        //no matter where the cell appears onscreen.
		       // if (col < 2) {
		       //     return false;
		       // } else {
		            return true;
		       // }
		    }

		    /*
		     * Don't need to implement this method unless your table's
		     * data can change.
		     */
		    public void setValueAt(Object value, int row, int col) {
		        data[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }

		}
		
		Class[] flclasses = new Class[aufgZahl];
		for (int i=0; i<flclasses.length; i++) flclasses[i] = Float.class;
		
		String[] tableHead = new String[aufgZahl];
		for (int i=0; i<tableHead.length; i++) tableHead[i] = "BE";
		
		table_BE.setModel(new BEInputTableModel(
			new Object[schZahl][aufgZahl],
		//	new Object[32][aufgZahl],	
			tableHead ,
			flclasses
		));
		
				
		//table_BE.setDefaultEditor(Float.class, new DefaultCellEditor(new JTextField("")));
		//table_BE.setDefaultRenderer(Float.class, new DefaultTableCellRenderer());
			
		
		table_BE.getColumnModel().getColumn(0).setResizable(false);
		table_BE.getColumnModel().getColumn(1).setResizable(false);
		table_BE.getColumnModel().getColumn(2).setResizable(false);
		table_BE.getColumnModel().getColumn(3).setResizable(false);
		table_BE.getColumnModel().getColumn(4).setResizable(false);
		table_BE.setGridColor(Color.GREEN);
		table_BE.getTableHeader().setReorderingAllowed(false);
		scrollPane_2.setViewportView(table_BE);
		
		fillinData(table_aufgaben,table_id,table_BE);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setMinimumSize(new Dimension(100, 22));
		scrollPane_4.setMaximumSize(new Dimension(100, 32767));
		JScrollBar sbar4 = scrollPane_4.getVerticalScrollBar();
		sbar4.setModel(sbar1.getModel());
		
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		internalFrame.getContentPane().add(scrollPane_4, "cell 2 1,grow");
		
		table_sum = new JTable();
		table_sum.setModel(new DefaultTableModel(
			new Object[schZahl][2],
			new String[] {
				"S", "N"
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_sum.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_sum.getColumnModel().getColumn(1).setPreferredWidth(20);
		table_sum.setCellSelectionEnabled(true);
		scrollPane_4.setViewportView(table_sum);


		internalFrame.setVisible(true);
	
		btnFertig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	
	public void fillinData(JTable table_aufg, JTable table_sch, JTable table_BE){
		
		boolean[] anwL = this.getKl().getAnwesendList();
		SchuelerList sL = this.getKl().getSchuelerList();
		KorrekturListe kL = this.getKl();
		
		AufgabeList aL = this.getAl();
		
		for (int i=0; i<aL.getAnz(); i++){
			table_aufg.setValueAt(aL.Aufgabenliste.get(i).getName(), 0, i);
			table_aufg.setValueAt(aL.Aufgabenliste.get(i).getPunkte(), 1, i);
		}
		
		for (int i=0; i<sL.getAnz(); i++){
			table_sch.setValueAt(sL.Schuelerliste.get(i).getIDString(), i, 0);
			table_sch.setValueAt(anwL[i], i, 1);
			table_sch.setValueAt(sL.Schuelerliste.get(i).getNachname(), i, 2);
			table_sch.setValueAt(sL.Schuelerliste.get(i).getVorname(), i, 3);
			
			for (int j=0; j<kl.getAnzAufgaben(); j++){
				table_BE.setValueAt(kL.getErreichtAt(i, j), i, j);
			}
			
			
		}
		
		

		/*
		while(table_aufgaben.getColumnCount()<al.getAnz()){
			table_aufgaben.addColumn(new TableColumn());
			System.out.println("KE: Spalten "+ table_aufgaben.getColumnCount());
		}
		for(int i=0;i<al.getAnz()-1;i++){ 
			String name = al.Aufgabenliste.get(i).getName();
			System.out.println("KE: "+i+" "+name);
			table_aufgaben.setValueAt(name, 0, i);
			
			float punkte = al.Aufgabenliste.get(i).getPunkte();
			System.out.println("KE: "+i+" "+punkte);
			table_aufgaben.setValueAt(punkte, 1, i);
			}
		*/
	}

	public Pruefung getAktPr() {
		return aktPr;
	}

	public void setAktPr(Pruefung aktPr) {
		this.aktPr = aktPr;
	}

	public AufgabeList getAl() {
		return al;
	}

	public void setAl(AufgabeList al) {
		this.al = al;
	}

	public KorrekturListe getKl() {
		return kl;
	}

	public void setKl(KorrekturListe kl) {
		this.kl = kl;
	}

}

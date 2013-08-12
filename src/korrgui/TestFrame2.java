package korrgui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TestFrame2 
extends JFrame
implements ActionListener {
	JComponent menu1, menu2, menu3;
	JMenuBar menubar;
	
	public TestFrame2()
	{
		super("Test");
		//addWindowListener(new WindowClosingAdapter(true));
		menubar = new JMenuBar();
		menu1 = menubar.add(createFileMenu());
		menu2 = menubar.add(createKlasseMenu());
		menu3 = menubar.add(createPruefungMenu());
		setJMenuBar(menubar);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String cmd = event.getActionCommand();
		System.out.println(cmd);
		if (cmd=="Öffnen")
		{
			System.out.println("Test");
			menu3.setEnabled(true);
			
		}
		//JMenuItem mi = (JMenuItem)event.getSource();
		//mi.setEnabled(false);
		//System.out.println(mi);
	}
	
	private JMenu createFileMenu()
	{
		JMenu ret = new JMenu("Datei");
	    ret.setMnemonic('D');
	    JMenuItem mi;
	    //Öffnen
	    mi = new JMenuItem("Passwort ändern", 'P');
	    setCtrlAccelerator(mi, 'P');
	    mi.addActionListener(this);
	    ret.add(mi);
	    ret.addSeparator();
	    //Beenden
	    mi = new JMenuItem("Beenden", 'e');
	    mi.addActionListener(this);
	    ret.add(mi);
	    return ret;
	}
	
	private JMenu createKlasseMenu()
	{
		JMenu ret = new JMenu("Klasse");
	    ret.setMnemonic('K');
	    JMenuItem mi;
	    //Öffnen
	    mi = new JMenuItem("Öffnen", 'f');
	    //setCtrlAccelerator(mi, 'O');
	    mi.addActionListener(this);
	    ret.add(mi);
	    //ret.addSeparator();
	    //Beenden
	    mi = new JMenuItem("neu anlegen", 'n');
	    mi.addActionListener(this);
	    ret.add(mi);
	    return ret;
	}
	
	private JMenu createPruefungMenu()
	{
		JMenu ret = new JMenu("Prüfung");
	    ret.setMnemonic('K');
	    ret.setEnabled(false);
	    JMenuItem mi;
	    //Öffnen
	    mi = new JMenuItem("Neu", 'N');
	    //setCtrlAccelerator(mi, 'O');
	    mi.addActionListener(this);
	    ret.add(mi);
	    //ret.addSeparator();
	    //Beenden
	    return ret;
	}
	
	private void setCtrlAccelerator(JMenuItem mi, char acc)
	{
		KeyStroke ks = KeyStroke.getKeyStroke(acc, Event.CTRL_MASK);
	    mi.setAccelerator(ks);
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TestFrame2 frame = new TestFrame2();
		frame.setLocation(100, 100);
		frame.setSize(300, 200);
		frame.setVisible(true);
	}


}

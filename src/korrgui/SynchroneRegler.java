package korrgui;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
   
class SynchroneRegler extends JFrame  
{  
  public SynchroneRegler()  
  {  
    JScrollPane sp1 = new JScrollPane(new ScrollPanel());  
    JScrollBar sBar1 = sp1.getVerticalScrollBar();  
    JScrollPane sp2 = new JScrollPane(new ScrollPanel());  
    JScrollBar sBar2 = sp2.getVerticalScrollBar();  
    sBar2.setModel(sBar1.getModel()); //<--------------synchronize  
    getContentPane().setLayout(new GridLayout(1,2));  
    getContentPane().add(sp1);  
    getContentPane().add(sp2);  
    setDefaultCloseOperation(EXIT_ON_CLOSE);  
    setSize(400,300);  
    setLocation(400,300);  
  }  
  public static void main(String[] args) {new SynchroneRegler().setVisible(true);}  
}  
class ScrollPanel extends JPanel  
{  
  public ScrollPanel()  
  {  
    setLayout(new GridLayout(50,1));  
    for(int x = 0; x < 50; x++) add(new JLabel(""+(x+1)));  
  }  
}  

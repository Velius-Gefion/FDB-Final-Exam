package grocery;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main_Panel extends JPanel
{
    Grocery grocery;
    JPanel[] panel = new JPanel[8];
    JPanel[] sub_panel = new JPanel[3];
    JButton[] button = new JButton[10];
    JLabel[] label = new JLabel[10];
    JTabbedPane tabbedPane = new JTabbedPane();
    
    Main_Panel(Grocery grocery)
    {
        this.grocery = grocery;
        
        setLayout(new BorderLayout(0, 0));
        //Main Left and Right
        panel[0] = new JPanel();
        panel[1] = new JPanel();
        panel[2] = new JPanel();
        panel[3] = new JPanel();
        panel[4] = new JPanel();
        panel[5] = new JPanel();
        panel[6] = new JPanel();
        panel[7] = new JPanel();
        
        sub_panel[0] = new JPanel();
        sub_panel[1] = new JPanel();
        sub_panel[2] = new JPanel();
        
        //Main Panel
        
        add(panel[0],BorderLayout.WEST);
        add(panel[1],BorderLayout.EAST);
        
        panel[0].setPreferredSize(new Dimension(200,100));
        panel[1].setPreferredSize(new Dimension(590,100));
        
        panel[0].setLayout(new BorderLayout(0, 0));
        panel[1].setLayout(new BorderLayout(0, 0));
        
        panel[0].add(panel[2]);
        panel[1].add(panel[3]);
        
        
        //Left
        panel[2].setBackground(Color.LIGHT_GRAY);
        panel[2].setLayout(null);
        panel[2].add(button[0] = new JButton("Menu"));
        panel[2].add(button[1] = new JButton("Purchase"));
        panel[2].add(label[0] = new JLabel("Grocery Menu"));
        panel[2].add(label[1] = new JLabel("Menu"));
        panel[2].add(label[2] = new JLabel("Purchase"));
        
        label[0].setFont(new java.awt.Font("Segoe UI", 1, 25));
        
        label[1].setFont(new java.awt.Font("Segoe UI", 1, 20));
        label[2].setFont(new java.awt.Font("Segoe UI", 1, 20));
        
        button[0].setBounds(50,100,100,100);
        button[1].setBounds(50,300,100,100);
        
        label[0].setBounds(15,10,200,30);
        label[1].setBounds(70,70,100,30);
        label[2].setBounds(57,270,100,30);
        
        button[0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[0].removeAll();
                panel[0].add(panel[4]);
                panel[0].revalidate();
                panel[0].repaint();
                panel[1].removeAll();
                panel[1].add(panel[5]);
                panel[1].revalidate();
                panel[1].repaint();
            }
        });
        button[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[0].removeAll();
                panel[0].add(panel[6]);
                panel[0].revalidate();
                panel[0].repaint();
                panel[1].removeAll();
                panel[1].add(panel[7]);
                panel[1].revalidate();
                panel[1].repaint();
            }
        });
        
        //Right
        panel[3].setBackground(Color.DARK_GRAY);
        panel[3].setLayout(new BorderLayout(0, 0));
        
        //Form 1.1 panel [2] panel [3]
        
        //Left
        panel[4].setBackground(Color.LIGHT_GRAY);
        panel[4].setLayout(null);
        
        panel[4].add(label[0] = new JLabel("Grocery Menu"));
        label[0].setFont(new java.awt.Font("Segoe UI", 1, 25));
        label[0].setBounds(15,10,200,30);
        
        //Back
        panel[4].add(button[2] = new JButton("Back"));
        //Add
        panel[4].add(button[3] = new JButton("Add Product"));
        //Update
        panel[4].add(button[4] = new JButton("Update Product"));
        //Delete
        panel[4].add(button[5] = new JButton("Delete Product"));
        //Records (Products, Customer, Sales)
        panel[4].add(button[6] = new JButton("Records"));
        
        button[2].setBounds(45,50,110,30);
        button[3].setBounds(100,100,70,70);
        button[4].setBounds(100,190,70,70);
        button[5].setBounds(100,280,70,70);
        button[6].setBounds(100,370,70,70);
        
        button[2].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[0].removeAll();
                panel[0].add(panel[2]);
                panel[0].revalidate();
                panel[0].repaint();
                panel[1].removeAll();
                panel[1].add(panel[3]);
                panel[1].revalidate();
                panel[1].repaint();
            }
        });
        button[3].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Product");
                
                panel[1].removeAll();
                panel[1].add(sub_panel[0]);
                panel[1].revalidate();
                panel[1].repaint();
                
                //create();
            }
        });
        button[4].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update Product");
                
                panel[1].removeAll();
                panel[1].add(sub_panel[0]);
                panel[1].revalidate();
                panel[1].repaint();
                
                //update();
            }
        });
        button[5].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Product");
                
                panel[1].removeAll();
                panel[1].add(sub_panel[0]);
                panel[1].revalidate();
                panel[1].repaint();
                
                //delete();
            }
        });
        
        
        //SubPanel for Different Records
        
        sub_panel[0].setLayout(null);
        sub_panel[1].setLayout(null);
        sub_panel[2].setLayout(null);
        tabbedPane.add("Products",sub_panel[0]);
        tabbedPane.add("Customers",sub_panel[1]);
        tabbedPane.add("Sales",sub_panel[2]);
        
        button[6].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[1].removeAll();
                panel[1].add(tabbedPane);
                panel[1].revalidate();
                panel[1].repaint();
            }
        });
        
        //Right
        panel[5].setLayout(new BorderLayout(0, 0));
        
        
        
        //Form 1.2 panel[4] panel [5]
        
        //Left
        panel[6].setLayout(new BorderLayout(0, 0));
        
        //Right
        panel[7].setLayout(new BorderLayout(0, 0));
        
    }
}
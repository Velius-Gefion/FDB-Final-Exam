package grocery;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class Main_Panel extends JPanel
{
    JPanel[] panel = new JPanel[8];
    JPanel[] sub_panel = new JPanel[3];
    JPanel[] action_panel = new JPanel[2];
    JButton[] button = new JButton[10];
    JLabel[] label = new JLabel[10];
    JTabbedPane tabbedPane = new JTabbedPane();
    JSplitPane[] splitPane = new JSplitPane[2];
    JTextField[] text = new JTextField[5];
    JLabel[] action_label = new JLabel[5];
    JTable product_table, customer_table, sales_table;
    
    Main_Panel(Grocery grocery, Functions function)
    {   
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
        
        action_panel[0] = new JPanel();
        action_panel[1] = new JPanel();
        
        text[0] = new JTextField();
        text[1] = new JTextField();
        text[2] = new JTextField();
        text[3] = new JTextField();
        text[4] = new JTextField();
        
        //Main Panel
        
        add(panel[0],BorderLayout.WEST);
        add(panel[1],BorderLayout.EAST);
        
        panel[0].setPreferredSize(new Dimension(200,100));
        panel[1].setPreferredSize(new Dimension(585,100));
        
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
                
                function.read();
                splitPane[0].setTopComponent(sub_panel[0]);
                splitPane[0].setBottomComponent(action_panel[1]);
                splitPane[0].getTopComponent();
                splitPane[0].getBottomComponent();
                splitPane[0].setDividerLocation(360);
                splitPane[0].setDividerSize(0);
                splitPane[0].setEnabled(false);
                action_panel[0].add(splitPane[0]);
                
                panel[1].add(action_panel[0]);
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
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Add Product");
                
                panel[1].removeAll();
                
                splitPane[0].setTopComponent(sub_panel[0]);
                splitPane[0].setBottomComponent(action_panel[1]);
                splitPane[0].getTopComponent();
                splitPane[0].getBottomComponent();
                splitPane[0].setDividerLocation(360);
                splitPane[0].setDividerSize(0);
                splitPane[0].setEnabled(false);
                action_panel[0].add(splitPane[0]);
                
                panel[1].add(action_panel[0]);
                panel[1].revalidate();
                panel[1].repaint();
                
                if(text[0].getText().matches("") &&
                   text[1].getText().matches("") &&
                   text[2].getText().matches("") &&
                   text[3].getText().matches("") &&
                   text[4].getText().matches(""))
                {
                    JOptionPane.showMessageDialog(null, "Please fill out the form", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    function.create(Integer.parseInt(text[0].getText()),
                             text[1].getText(),
                         Integer.parseInt(text[2].getText()),
                                   text[3].getText(),
                                  Double.parseDouble(text[4].getText()));
                }
            }
        });
        button[4].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Update Product");
                
                panel[1].removeAll();
                
                splitPane[0].setTopComponent(sub_panel[0]);
                splitPane[0].setBottomComponent(action_panel[1]);
                splitPane[0].getTopComponent();
                splitPane[0].getBottomComponent();
                splitPane[0].setDividerLocation(360);
                splitPane[0].setDividerSize(0);
                splitPane[0].setEnabled(false);
                action_panel[0].add(splitPane[0]);
                
                panel[1].add(action_panel[0]);
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
                
                splitPane[0].setTopComponent(sub_panel[0]);
                splitPane[0].setBottomComponent(action_panel[1]);
                splitPane[0].getTopComponent();
                splitPane[0].getBottomComponent();
                splitPane[0].setDividerLocation(360);
                splitPane[0].setDividerSize(0);
                splitPane[0].setEnabled(false);
                action_panel[0].add(splitPane[0]);
                
                panel[1].add(action_panel[0]);
                panel[1].revalidate();
                panel[1].repaint();
                
                //delete();
            }
        });
        
        action_panel[0].setLayout(new BorderLayout(0,0));
        splitPane[0] = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        action_panel[1].setLayout(null);
        action_panel[1].add(text[0]);
        action_panel[1].add(text[1]);
        action_panel[1].add(text[2]);
        action_panel[1].add(text[3]);
        action_panel[1].add(text[4]);
        
        action_panel[1].add(action_label[0] = new JLabel("Product ID"));
        action_panel[1].add(action_label[1] = new JLabel("Description"));
        action_panel[1].add(action_label[2] = new JLabel("Available Quantity"));
        action_panel[1].add(action_label[3] = new JLabel("Unit"));
        action_panel[1].add(action_label[4] = new JLabel("Price"));
        
        action_label[0].setBounds(20,20,65,20);
        action_label[1].setBounds(130,20,100,20);
        action_label[2].setBounds(245,20,140,20);
        action_label[3].setBounds(370,20,50,20);
        action_label[4].setBounds(470,20,100,20);
        
        text[0].setBounds(20,40,65,30);
        text[1].setBounds(90,40,150,30);
        text[2].setBounds(245,40,110,30);
        text[3].setBounds(360,40,50,30);
        text[4].setBounds(415,40,150,30);
        
        //Right
        panel[5].setLayout(new BorderLayout(0, 0));
        
        sub_panel[0].setLayout(new BorderLayout());
        product_table = new JTable(function.product_table);
        JScrollPane scrollPane1 = new JScrollPane(product_table);
        sub_panel[0].add(scrollPane1, BorderLayout.CENTER);
        
        sub_panel[1].setLayout(new BorderLayout());
        customer_table = new JTable(new DefaultTableModel()
        {
            {
                addColumn("Name");
                addColumn("Email");
                addRow(new Object[]{"Data 1", "Data 2"});
            }
        });
        customer_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane2 = new JScrollPane(customer_table);
        sub_panel[1].add(scrollPane2, BorderLayout.CENTER);
        
        sub_panel[2].setLayout(new BorderLayout());
        sales_table = new JTable(new DefaultTableModel()
        {
            {
                addColumn("Date");
                addColumn("Time");
                addColumn("Name");
                addColumn("Email");
                addRow(new Object[]{"Data 1", "Data 2", "Data 3", "Data 4"});
            }
        });
        sales_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane3 = new JScrollPane(sales_table);
        sub_panel[2].add(scrollPane3, BorderLayout.CENTER);
        
        
        customer_table.setDefaultEditor(Object.class, null);
        sales_table.setDefaultEditor(Object.class, null);
        splitPane[1] = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
 
        
        
        button[6].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[1].removeAll();
                
                splitPane[1].setTopComponent(sub_panel[2]);
                splitPane[1].setBottomComponent(sub_panel[0]);
                splitPane[1].getTopComponent();
                splitPane[1].getBottomComponent();
                splitPane[1].setDividerLocation(200);
                splitPane[1].setDividerSize(0);
                splitPane[1].setEnabled(false);
                
                panel[5].add(splitPane[1]);
                
                tabbedPane.add("Customers",sub_panel[1]);
                tabbedPane.add("Sales",panel[5]);
                
                panel[1].add(tabbedPane);
                panel[1].revalidate();
                panel[1].repaint();
            }
        });
        
        //Form 1.2 panel[4] panel [5]
        
        //Left
        panel[6].setLayout(new BorderLayout(0, 0));
        
        //Right
        panel[7].setLayout(new BorderLayout(0, 0));
    }
}
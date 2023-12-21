package grocery;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main_Panel extends JPanel
{
    JPanel[] panel = new JPanel[8];
    JPanel[] sub_panel = new JPanel[3];
    JPanel[] action_panel = new JPanel[2];
    JButton[] button = new JButton[10];
    JLabel[] label = new JLabel[10];
    JTabbedPane[] tabbedPane = new JTabbedPane[2];
    JSplitPane[] splitPane = new JSplitPane[2];
    JTextField[] text = new JTextField[5];
    JLabel[] action_label = new JLabel[5];
    JTable product_table, customer_table, sales_table, checkout_table;
    JButton[] back_button = new JButton[2];
    JButton[] purchase_button = new JButton[3];
    
    Main_Panel(Grocery grocery, Functions function)
    {   
        setLayout(new BorderLayout(0, 0));
        //Main Left and Right
        for(int i = 0;i < 8; i++)
        {
            panel[i] = new JPanel();
            
            if (i < 5)
            {
                text[i] = new JTextField();
                if(i < 3)
                {
                    sub_panel[i] = new JPanel();
                    if (i < 2)
                    {
                        action_panel[i] = new JPanel();
                        tabbedPane[i] = new JTabbedPane();
                    }
                }
            }
        }
        
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
            public void actionPerformed(ActionEvent e)
            {
                function.read_product();
                
                splitPane[0].setTopComponent(sub_panel[0]);
                splitPane[0].setBottomComponent(action_panel[1]);
                splitPane[0].getTopComponent();
                splitPane[0].getBottomComponent();
                splitPane[0].setDividerLocation(360);
                splitPane[0].setDividerSize(0);
                splitPane[0].setEnabled(false);
                action_panel[0].add(splitPane[0]);
                
                change_panel(panel[4], action_panel[0]);
            }
        });
        button[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                function.read_product();
                
                tabbedPane[1].add("Products",sub_panel[0]);
                tabbedPane[1].add("Checkout",panel[7]);
                
                clear();
                change_panel(panel[6], tabbedPane[1]);
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
        panel[4].add(back_button[0] = new JButton("Back"));
        //Add
        panel[4].add(button[3] = new JButton("Add Product"));
        //Update
        panel[4].add(button[4] = new JButton("Update Product"));
        //Delete
        panel[4].add(button[5] = new JButton("Delete Product"));
        //Records (Products, Customer, Sales)
        panel[4].add(button[6] = new JButton("Records"));
        
        back_button[0].setBounds(45,50,110,30);
        button[3].setBounds(100,100,70,70);
        button[4].setBounds(100,190,70,70);
        button[5].setBounds(100,280,70,70);
        button[6].setBounds(100,370,70,70);
        
        back_button[0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                change_panel(panel[2],panel[3]);
            }
        });
        button[3].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {               
                if(text[1].getText().matches("") &&
                   text[2].getText().matches("") &&
                   text[3].getText().matches("") &&
                   text[4].getText().matches(""))
                {
                    JOptionPane.showMessageDialog(null, "Please fill out the form", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(productExists(text[1].getText()))
                {
                    JOptionPane.showMessageDialog(null, "Product already exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    function.create(text[1].getText(),
                                Integer.parseInt(text[2].getText()),
                                          text[3].getText(),
                                         Double.parseDouble(text[4].getText()));
                    clear();
                }
                
                function.read_product();
                product_panel();
                change_panel(null,action_panel[0]);
            }
        });
        button[4].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                if(text[1].getText().matches("") &&
                   text[2].getText().matches("") &&
                   text[3].getText().matches("") &&
                   text[4].getText().matches(""))
                {
                    JOptionPane.showMessageDialog(null, "Please fill out the form", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(productExists(text[1].getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please change something", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    function.update(Integer.parseInt(text[0].getText()),
                             text[1].getText(),
                         Integer.parseInt(text[2].getText()),
                                   text[3].getText(),
                                  Double.parseDouble(text[4].getText()));
                    clear();
                }
                
                function.read_product();
                product_panel();
                change_panel(null,action_panel[0]);
            }
        });
        button[5].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(text[0].getText().matches(""))
                {
                    JOptionPane.showMessageDialog(null, "Please fill out the form", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    function.delete(Integer.parseInt(text[0].getText()));
                    clear();
                }
                
                function.read_product();
                product_panel();
                change_panel(null,action_panel[0]);
            }
        });
        
        action_panel[0].setLayout(new BorderLayout(0,0));
        splitPane[0] = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        action_panel[1].setLayout(null);
        for(int i = 0;i < 5;i++)
        {
            action_panel[1].add(text[i]);
        }
        
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
        function.table_product_function = new DefaultTableModel()
        {
            {
                addColumn("Product ID");
                addColumn("Description");
                addColumn("Quantity");
                addColumn("Unit");
                addColumn("Price");
            }
        };
        product_table = new JTable(function.table_product_function);
        product_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        product_table.getColumnModel().getColumn(1).setPreferredWidth(250);
        product_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        product_table.getColumnModel().getColumn(3).setPreferredWidth(50);
        product_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        product_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane1 = new JScrollPane(product_table);
        sub_panel[0].add(scrollPane1, BorderLayout.CENTER);
        
        sub_panel[1].setLayout(new BorderLayout());
        function.table_customer_function = new DefaultTableModel()
        {
            {
                addColumn("Customer ID");
                addColumn("Name");
                addColumn("Email");
                
                addRow(new Object[]{"Data 1", "Data 2"});
            }
        };
        customer_table = new JTable(function.table_customer_function);
        customer_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane2 = new JScrollPane(customer_table);
        sub_panel[1].add(scrollPane2, BorderLayout.CENTER);
        
        sub_panel[2].setLayout(new BorderLayout());
        function.table_sales_function = new DefaultTableModel()
        {
            {
                addColumn("Date");
                addColumn("Time");
                addColumn("Name");
                addColumn("Email");
                addRow(new Object[]{"Data 1", "Data 2", "Data 3", "Data 4"});
            }
        };
        sales_table = new JTable(function.table_sales_function);
        sales_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        sales_table.getColumnModel().getColumn(1).setPreferredWidth(50);
        sales_table.getColumnModel().getColumn(2).setPreferredWidth(100);
        sales_table.getColumnModel().getColumn(3).setPreferredWidth(250);
        sales_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane3 = new JScrollPane(sales_table);
        sub_panel[2].add(scrollPane3, BorderLayout.CENTER);
        
        
        product_table.setDefaultEditor(Object.class, null);
        customer_table.setDefaultEditor(Object.class, null);
        sales_table.setDefaultEditor(Object.class, null);
        splitPane[1] = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
 
        product_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = product_table.rowAtPoint(e.getPoint());
                int col = product_table.columnAtPoint(e.getPoint());

                if (row >= 0 && col >= 0) {
                    for(int i = 0;i < 5;i++)
                    {
                        text[i].setText(String.valueOf(product_table.getValueAt(row, i)));
                    }
                }
            }
        });
        
        button[6].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                splitPane[1].setTopComponent(sub_panel[2]);
                splitPane[1].setBottomComponent(sub_panel[0]);
                splitPane[1].getTopComponent();
                splitPane[1].getBottomComponent();
                splitPane[1].setDividerLocation(200);
                splitPane[1].setDividerSize(0);
                splitPane[1].setEnabled(false);
                
                panel[5].add(splitPane[1]);
                
                tabbedPane[0].add("Customers",sub_panel[1]);
                tabbedPane[0].add("Sales",panel[5]);
                
                clear();
                change_panel(null, tabbedPane[0]);
            }
        });
        
        //Form 1.2 panel[4] panel [5]
        
        //Left
        panel[6].setLayout(null);
        panel[6].setBackground(Color.LIGHT_GRAY);
        panel[6].add(purchase_button[0] = new JButton("Add"));
        panel[6].add(purchase_button[1] = new JButton("Remove"));
        panel[6].add(purchase_button[2] = new JButton("Checkout"));
        panel[6].add(back_button[1] = new JButton("Back"));
        panel[6].add(label[0] = new JLabel("Grocery Menu"));
        label[0].setFont(new java.awt.Font("Segoe UI", 1, 25));
        label[0].setBounds(15,10,200,30);
        
        back_button[1].setBounds(45,50,110,30);
        purchase_button[0].setBounds(100,100,70,70);
        purchase_button[1].setBounds(100,190,70,70);
        purchase_button[2].setBounds(100,280,70,70);
        
        back_button[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                change_panel(panel[2],panel[3]);
            }
        });
        
        //Right
        panel[7].setBackground(Color.DARK_GRAY);
        panel[7].setLayout(new BorderLayout(0,0));
        
        checkout_table = new JTable(new DefaultTableModel()
        {
            {
                addColumn("Product ID");
                addColumn("Description");
                addColumn("Quantity");
                addColumn("Unit");
                addColumn("Price");
            }
        });
        
        checkout_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        checkout_table.getColumnModel().getColumn(1).setPreferredWidth(250);
        checkout_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        checkout_table.getColumnModel().getColumn(3).setPreferredWidth(50);
        checkout_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        checkout_table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane4 = new JScrollPane(checkout_table);
        panel[7].add(scrollPane4, BorderLayout.CENTER);
        
        
    }
    
    private boolean productExists(String productName)
    {
        for (int row = 0; row < product_table.getRowCount(); row++)
        {
            Object value = product_table.getValueAt(row, 1);
            if (value != null && value.toString().equalsIgnoreCase(productName))
            {
                return true;
            }
        }
        return false;
    }
    
    public void product_panel()
    {
        splitPane[0].setTopComponent(sub_panel[0]);
        splitPane[0].setBottomComponent(action_panel[1]);
        splitPane[0].getTopComponent();
        splitPane[0].getBottomComponent();
        splitPane[0].setDividerLocation(360);
        splitPane[0].setDividerSize(0);
        splitPane[0].setEnabled(false);
        action_panel[0].add(splitPane[0]);
    }
    
    public void change_panel(JPanel panel_1, JPanel panel_2)
    {
        if (panel_1 != null)
        {
            panel[0].removeAll();
            panel[0].add(panel_1);
            panel[0].revalidate();
            panel[0].repaint();
        }
        panel[1].removeAll();
        panel[1].add(panel_2);
        panel[1].revalidate();
        panel[1].repaint();
    }
    
    public void change_panel(JPanel panel_1, JTabbedPane panel_2)
    {
        if (panel_1 != null)
        {
            panel[0].removeAll();
            panel[0].add(panel_1);
            panel[0].revalidate();
            panel[0].repaint();
        }
        panel[1].removeAll();
        panel[1].add(panel_2);
        panel[1].revalidate();
        panel[1].repaint();
    }
    
    public void change_panel(JPanel panel_1, JSplitPane panel_2)
    {
        if (panel_1 != null)
        {
            panel[0].removeAll();
            panel[0].add(panel_1);
            panel[0].revalidate();
            panel[0].repaint();
        }
        panel[1].removeAll();
        panel[1].add(panel_2);
        panel[1].revalidate();
        panel[1].repaint();
    }
    
    public void clear()
    {
        for(int i = 0;i < 5;i++)
        {
            text[i].setText("");
        }
    }
}
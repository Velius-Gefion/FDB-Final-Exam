package grocery;
import static grocery.Main_Panel.isValidEmailFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Functions
{
    Grocery grocery;
    Connection conn;
    DefaultTableModel table_product_function, table_customer_function, table_sales_function, table_checkout_function;
    Session newSession = null;
    MimeMessage mimeMessage = null;
        
    Functions(Grocery grocery)
    {
        this.grocery = grocery;
        
    }
    
    public void connect()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Login_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void validate(String username, String password)
    {   
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE USERNAME = ? AND PASSWORD = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                grocery.show_Main_Panel();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create_product(String productDescription, int productAvailableQuantity, String productUnit, double productPrice)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (PRODUCT_DESCRIPTION, PRODUCT_AVAILABLE_QUANTITY, PRODUCT_UNIT, PRODUCT_PRICE) VALUES (?, ?, ?, ?)");
            stmt.setString(1, productDescription);
            stmt.setInt(2, productAvailableQuantity);
            stmt.setString(3, productUnit);
            stmt.setDouble(4, productPrice);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create_customer(String customerName, String customerEmail)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer (CUSTOMER_NAME, CUSTOMER_EMAIL) VALUES (?, ?)");
            stmt.setString(1, customerName);
            stmt.setString(2, customerEmail);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void read_product()
    {
        try {
            try (Statement statement = conn.createStatement()) {
                String query = "SELECT * FROM products";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    int rowCount = table_product_function.getRowCount();
                    for(int i = rowCount - 1; i >= 0; i--)
                    {
                        table_product_function.removeRow(i);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        table_product_function.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }
    }
    
    public void read_customer()
    {
        try {
            try (Statement statement = conn.createStatement()) {
                String query = "SELECT * FROM customer";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    int rowCount = table_customer_function.getRowCount();
                    for(int i = rowCount - 1; i >= 0; i--)
                    {
                        table_customer_function.removeRow(i);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        table_customer_function.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }
    }
    
    public void read_sales()
    {
        try {
            try (Statement statement = conn.createStatement()) {
                String query = "SELECT * FROM customer";
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    int rowCount = table_sales_function.getRowCount();
                    for(int i = rowCount - 1; i >= 0; i--)
                    {
                        table_sales_function.removeRow(i);
                    }

                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        table_sales_function.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }
    }
    
    public void update(int productId, String productDescription, int productAvailableQuantity, String productUnit, double productPrice)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET PRODUCT_DESCRIPTION = ?, PRODUCT_AVAILABLE_QUANTITY = ?, PRODUCT_UNIT = ?, PRODUCT_PRICE = ? WHERE PRODUCT_ID = ?");
            stmt.setString(1, productDescription);
            stmt.setInt(2, productAvailableQuantity);
            stmt.setString(3, productUnit);
            stmt.setDouble(4, productPrice);
            stmt.setInt(5, productId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int productId)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE PRODUCT_ID = ?");
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUpServerProperties()
    {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        newSession = Session.getDefaultInstance(properties,null);
        
    }

    public void sendEmail() throws MessagingException
    {
        String fromUser = "veliusgefion@gmail.com";
        String fromUserPassword = "sfnc zvda ecxg nnvs";
        String emailHost = "smtp.gmail.com";
        try (Transport transport = newSession.getTransport("smtp"))
        {
            transport.connect(emailHost, fromUser, fromUserPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        }
        
        JOptionPane.showOptionDialog(null, "Email sent successfully.", "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{},
                null);
    }
     
    protected MimeMessage draftEmail(String name, String email, String[][] order) throws AddressException, MessagingException, IOException
    {
        String emailSubject = "Sales Order";
        String orderList = "";
        double total = 0;
        for (String[] order1 : order)
        {
            for (int j = 0; j < order[0].length; j++)
            {
                if(j == 0)
                {
                    orderList += "\n<tr>";
                }
                orderList += "<td>" + order1[j] + "</td>";
                if(j == 4)
                {
                    orderList += "\n</tr>";
                    total += (Integer.valueOf(order1[2]) * Double.valueOf(order1[j]));
                }
            }
        }
        String emailBody = 
                "<html>Good Day " + name + ",<br><br>"
                + """
                Here are the list of items you have bought<br><br>
                <table style=\"width:100%\">
                    <tr>
                        <th>Product ID</th>
                        <th>Product Description</th>
                        <th>Quantity</th>
                        <th>Product Unit</th>
                        <th>Price</th>
                    </tr>
                """
                + orderList
                + """
                    <tr>
                        <th></th><th></th><th></th>
                        <th>TOTAL AMOUNT</th>
                """
                +      "<th>" + total + "</th>"
                + """
                    </tr>
                </table>
                <br><br>Thank you for purchasing with us!
                </html>
                """;
        mimeMessage = new MimeMessage(newSession);
        
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"text/html");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        
        JOptionPane firstOptionPane = new JOptionPane("This might take a while",
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        
        JDialog dialog = firstOptionPane.createDialog("Automatic Close Dialog");
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
        Timer timer;
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
        return mimeMessage;
    }
    
    public void purchase(JTable checkout_table,JTable customer_table)
    {
        try {
            String name = "";
            String email = "";
            String[][] order = new String[checkout_table.getRowCount()][5];

            while (true) {
                name = JOptionPane.showInputDialog("Please enter your name:");
                if(name == null)
                {
                    break;
                }
                else if(name.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill out the window", "Error", JOptionPane.ERROR_MESSAGE);
                }

                while (true)
                {
                    email = JOptionPane.showInputDialog("Please enter your email address:");
                    if(email == null || !isValidEmailFormat(email))
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        break;
                    }
                }

                if(email != null)
                {
                    break;
                }
            }

            String [] customerRow = new String [3];
            boolean found = false;
            read_customer();
            if(!("".equals(name)) && !("".equals(email)))
            {
                for(int i = 0;i < customer_table.getRowCount();i++)
                {
                    for(int j = 0;j < 3;j++)
                    {
                        customerRow[j] = String.valueOf(customer_table.getValueAt(i, j));
                    }

                    if(customerRow[2].matches(email))
                    {
                        found = true;
                    }
                }

                if(!found)
                {
                    System.out.println("Email saved into the database");
                    create_customer(name, email);
                }

                for(int i = 0;i < checkout_table.getRowCount();i++)
                {
                    System.out.println(checkout_table.getRowCount());
                    for(int j = 0;j < 5;j++)
                    {
                        order[i][j] = String.valueOf(checkout_table.getValueAt(i, j));
                    }
                }

                setUpServerProperties();
                draftEmail(name, email, order);
                sendEmail();
            }
        }
        catch (MessagingException | IOException ex)
        {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    public void report() throws FileNotFoundException, IOException
    {
        /*try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Products");
            int rowNum = 0;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("PRODUCT_ID"));
                row.createCell(1).setCellValue(rs.getString("PRODUCT_DESCRIPTION"));
                row.createCell(2).setCellValue(rs.getInt("PRODUCT_AVAILABLE_QUANTITY"));
                row.createCell(3).setCellValue(rs.getString("PRODUCT_UNIT"));
                row.createCell(4).setCellValue(rs.getDouble("PRODUCT_PRICE"));
            }
            FileOutputStream outputStream = new FileOutputStream("products.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }*
    }
    
    public void sendEmail()
    {
        try {
            String to = customerEmail;
            String subject = "List of products available";
            String body = "Here is a list of products available:\n";
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                body += rs.getString("PRODUCT_DESCRIPTION") + " - " + rs.getDouble("PRODUCT_PRICE") + "\n";
            }
            //EmailSender.sendEmail(to, subject, body);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}

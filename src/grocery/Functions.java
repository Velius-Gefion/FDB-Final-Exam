package grocery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Functions
{
    Grocery grocery;
    Connection conn;
    DefaultTableModel table_product_function, table_customer_function, table_sales_function, table_checkout_function;
    
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
    
    public void create(String productDescription, int productAvailableQuantity, String productUnit, double productPrice)
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
    /*
    public void purchase()
    {
        // Purchase transaction
        customerName = JOptionPane.showInputDialog(null, "Enter customer name:");
        customerEmail = JOptionPane.showInputDialog(null, "Enter customer email:");
        // Show list of products and ask for quantity
        // Subtract quantity from PRODUCT_AVAILABLE_QUANTITY in the database
        totalAmount = 0.0;
        // Compute change and display total amount and change
    }
    
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

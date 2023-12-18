package grocery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Functions
{
    
    
    Grocery grocery;
    private String username;
    private String password;
    private int productId;
    private String productDescription;
    private int productAvailableQuantity;
    private String productUnit;
    private double productPrice;
    private String customerName;
    private String customerEmail;
    private double totalAmount;
    private Connection conn;
    
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
    
    public void create(int productId, String productDescription, int productAvailableQuantity, String productUnit, double productPrice)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (PRODUCT_ID, PRODUCT_DESCRIPTION, PRODUCT_AVAIABLE_QUANTITY, PRODUCT_UNIT, PRODUCT_PRICE) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, productId);
            stmt.setString(2, productDescription);
            stmt.setInt(3, productAvailableQuantity);
            stmt.setString(4, productUnit);
            stmt.setDouble(5, productPrice);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(int productId, String productDescription, int productAvailableQuantity, String productUnit, double productPrice)
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET PRODUCT_DESCRIPTION = ?, PRODUCT_AVAIABLE_QUANTITY = ?, PRODUCT_UNIT = ?, PRODUCT_PRICE = ? WHERE PRODUCT_ID = ?");
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
        // Subtract quantity from PRODUCT_AVAIABLE_QUANTITY in the database
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
                row.createCell(2).setCellValue(rs.getInt("PRODUCT_AVAIABLE_QUANTITY"));
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

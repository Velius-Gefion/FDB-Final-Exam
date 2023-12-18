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
        this.username = username;
        this.password = password;
        
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
    /*
    public void create()
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (product_id, product_description, product_available_quantity, product_unit, product_price) VALUES (?, ?, ?, ?, ?)");
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
    
    public void update()
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET product_description = ?, product_available_quantity = ?, product_unit = ?, product_price = ? WHERE product_id = ?");
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
    
    public void delete()
    {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE product_id = ?");
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void purchase()
    {
        // Purchase transaction
        customerName = JOptionPane.showInputDialog(null, "Enter customer name:");
        customerEmail = JOptionPane.showInputDialog(null, "Enter customer email:");
        // Show list of products and ask for quantity
        // Subtract quantity from product_available_quantity in the database
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
                row.createCell(0).setCellValue(rs.getInt("product_id"));
                row.createCell(1).setCellValue(rs.getString("product_description"));
                row.createCell(2).setCellValue(rs.getInt("product_available_quantity"));
                row.createCell(3).setCellValue(rs.getString("product_unit"));
                row.createCell(4).setCellValue(rs.getDouble("product_price"));
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
                body += rs.getString("product_description") + " - " + rs.getDouble("product_price") + "\n";
            }
            //EmailSender.sendEmail(to, subject, body);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}

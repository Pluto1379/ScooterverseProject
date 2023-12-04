import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class sqlFunctions {
    private String dbUrl = "jdbc:mysql://localhost/Scooter_Rental";
    private String username = "root";
    private String password = "cs157scooter";
    private Connection conn = null;
    private static PreparedStatement ps = null;

    public void login(String username, String password){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "select * from customer where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                System.out.println("Username: "+rs.getString("username")+", First Name: "+rs.getString("firstName"));
                System.out.println("Login Process Was Successful");
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void createAccount(String fn, String ln, String ln, String username, String password, boolean admin){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);
            String statement = "insert into customer (firstName, lastName, LicenceNmber, username, password, admin) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,fn);
            preparedStatement.setString(2, ln);
            preparedStatement.setString(3, ln);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setBoolean(6, admin);
            
            
            int result = preparedStatement.executeUpdate();

            if(result != 0)
            	System.out.println("Your Account Was Created Succesfully");
            else
            	System.out.println("This Account Already Exists");


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
  
    public void returnScooter(String username, int scooterID){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            // Execute a query
            String statement = "update rental set scooterReturned = 1 where username = ? and scooterID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2, scooterID);
            int result = preparedStatement.executeUpdate();

            if (result != 0){
                System.out.println("Your Rental Scooter Was Returned Successfully" );
            }
            else {
                System.out.println("Scooter Has Already Been Returned.");
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void deleteScooter(int scooterID){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "delete from scooter where scooterID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,scooterID);
            boolean result = preparedStatement.execute();

            System.out.println("Scooter deleted!" );


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void addScooter(String scooterType, String brand, String model){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "insert into scooter (scooterType, brand, model) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,scooterType);
            preparedStatement.setString(2, brand);
            preparedStatement.setString(3, model);
            boolean result = preparedStatement.execute();

            System.out.println("Scooter inserted!" );


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void deleteCustomer(String username){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "delete from customer where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            boolean result = preparedStatement.execute();

            System.out.println("Customer deleted!" );


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void updateFee(String scooterType, int fee){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "update fee set price = ? where scooterType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,fee);
            preparedStatement.setString(2, scooterType);

            boolean result = preparedStatement.execute();

            System.out.println("Fee updated!" );


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void getFee(String scooterType){
        Statement st = null;
        ResultSet rs = null;
        double scooterFee = 0;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "select price from fee where scooterType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, scooterType);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                scooterFee = rs.getDouble("fee");
            }

            System.out.println("Here Is The Price For The Scooter: " + scooterFee);


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void rentScooter(String username, int scooterID, int time){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "insert into rental (username, scooterID, returnDate) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, scooterID);
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.DATE, time);
            java.sql.Date returnDate = new java.sql.Date(cl.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(3, returnDate);

            preparedStatement.execute();

            System.out.println("Scooter Was Successfully Rented");


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    public void getMostExpensiveScooter(){
        Statement st = null;
        ResultSet rs = null;
        int inventoryId;
        int maxPrice;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);
            String statement = "select inventory.inventoryID, MAX(price) \n"
            		+ "from inventory join scooter inner join fee on scooter.scooterType = fee.scooterType \n"
            		+ "where scooter.scooterID in\n"
            		+ "		(select scooterID \n"
            		+ "		from inventory as i\n"
            		+ "where scooterID in (select i.scooterID where i.inventoryID = inventory.inventoryID)) group by inventory.invnetoryID;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                inventoryId = rs.getInt("inventoryID");
                maxPrice = rs.getInt("MAX(price)");
                System.out.println(" The most expensive scooter is " + maxPrice + " at inventoryID " + inventoryId);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void getReturnInfo(int scooterID) {
        Statement st = null;
        ResultSet rs = null;
        String inventoryID = "";
        Calendar cl = Calendar.getInstance();
        java.sql.Date returnDate = new java.sql.Date(cl.getTimeInMillis());

        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "Select inventoryID, returnDate from rental, inventory where rental.scooterID = store.scooterID and rental.scooterID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1, scooterID);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                inventoryID = rs.getString("inventoryID");
                returnDate = rs.getDate("returnDate");
                System.out.println("Scooter " + scooterID + " Must Be Returned " + inventoryID +
                        " By " + returnDate.toString());
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void getScooterRentedAtInventory(String inventoryId){
        Statement st = null;
        ResultSet rs = null;
        String scooterType;
        int count;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "SELECT scooterType, count(*) as count\n"
            		+ "  	FROM scooter AS s, rental AS r, inventory AS i\n"
            		+ "  	WHERE s.ScooterID = r.ScooterID and i.ScooterID =  s.ScooterID and i.inventoryID = ? \n"
            		+ "and r.scooterReturned = false\n"
            		+ " 	GROUP BY s.scooterType\n"
            		+ "HAVING count(*) > 0;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, inventoryId);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                scooterType = rs.getString("scooterType");
                count = rs.getInt("count");
                System.out.println("Scooter Type: " + scooterType + " And The Count Is: " + count);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void changeReturnDate(String username, int scooterID, int time) {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "update rental set returnDate = ? where username = ? and scooterID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(2, username);
            preparedStatement.setInt(3, scooterID);
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.DATE, time);
            java.sql.Date returnDate = new java.sql.Date(cl.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(1, returnDate);

            preparedStatement.execute();

            System.out.println("Scooter Return Date Was Updated");


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void getCustomerDataAsIn(String username){
        Statement st = null;
        ResultSet rs = null;
        List<String> customerDetails = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "select username as CustomerDataInIn \n"
            		+ "from customer\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select licenceNumber \n"
            		+ "from customer\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select firstName\n"
            		+ "from customer\n"
            		+ "where username = ?\n"
            		+ "union\n"
            		+ "select lastName\n"
            		+ "from customer\n"
            		+ "where username = ?;\n"
            		+ "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, username);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                customerDetails.add(rs.getString("CustomerDataInIn"));
            }
            
            System.out.println("Username: " + customerDetails.get(0));
            System.out.println("License Number: " + customerDetails.get(1));
            System.out.println("First Name: " + customerDetails.get(2));
            System.out.println("Last Name: " + customerDetails.get(3));

            
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    

    public void getScooters(String inventoryID) {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.print("Scooters at store " + inventoryID + ": ");
            String statement = "SELECT s.scooterID, s.scooterType, r.username " +
                    "FROM scooter AS s " +
                    "LEFT JOIN rental AS r " +
                    "ON s.scooterID = r.scooterID and scooterReturned = false " +
                    "INNER JOIN " +
                    "inventory as i " +
                    "ON s.scooterID = i.scooterID " +
                    "Where i.inventoryID = ? " +
                    "Order by s.scooterID";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, inventoryID);

            rs = preparedStatement.executeQuery("SELECT s.ScooterID, s.scooterType, r.username "+
                    "FROM scooter AS s " +
                    "LEFT JOIN rental AS r " +
                    "ON s.scooterID = r.scooterID and scooterReturned = false " +
                    "INNER JOIN " +
                    "inventory as i " +
                    "ON s.scooterID = i.scooterID " +
                    "Where i.inventoryID =  " + inventoryID + " " +
                    "Order by s.scooterID");

            while (rs.next()) {
                System.out.println("\nScooter ID = " + rs.getString("ScooterID") + " Scooter Type = " + rs.getString("scooterType")
                        + " Username = " + rs.getString("username"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void insertScooter(String scooterType, int fee){
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "insert into fee (scooterType, price) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, scooterType);
            preparedStatement.setInt(2, fee);
            preparedStatement.execute();

            System.out.println("Scooter inserted!");


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(st!=null)
                    st.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void addInventory(String inventoryID, int scooterID) {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "insert into inventory (inventoryID, scooterID) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, inventoryID);
            preparedStatement.setInt(2, scooterID);
            boolean result = preparedStatement.execute();

            System.out.println("Inventory added");


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void log(String date) {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            String statement = "CALL log(?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, date);
            boolean result = preparedStatement.execute();

            if(!result)
            	System.out.println("Log Entry Successful!");


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
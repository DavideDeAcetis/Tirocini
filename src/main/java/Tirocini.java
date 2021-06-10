import java.sql.*;

class Tirocini {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tirocini?noAccessToProcedureBodies=true&serverTimezone=Europe/Rome", "root", "ciao123");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from legale");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRORE: " + e);
        }
    }
}

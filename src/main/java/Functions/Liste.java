package Functions;

import Utility.Colors;

import java.sql.*;

public class Liste {
    public static void lista_aziende (Connection con) throws SQLException {
        System.out.println(Colors.CYAN + "Lista delle aziende con offerte di tirocinio ancora aperte:" + Colors.RESET );
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_aziende_con_offerte()}");
        /*stmt.registerOutParameter(1, Types.INTEGER);*/
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        System.out.println("ID - Nome azienda");
        System.out.println("-------------------");
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + " - " +
                    rs.getString(2)
            );
        }
    }

    public static void lista_tirocini_attivi (Connection con) throws SQLException {
        System.out.println(Colors.CYAN + "Lista dei tirocini attualmente in svolgimento:" + Colors.RESET );
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_tirocini_attivi()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        System.out.println("ID - Titolo offerta - Nome azienda - Studente - Tutor universitario - Tutor aziendale - Inizio - Fine");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + " - " +
                    rs.getString(2) + " - " +
                    rs.getString(3) + " - " +
                    rs.getString(4) + " - " +
                    rs.getString(5) + " - " +
                    rs.getString(6) + " - " +
                    rs.getString(7) + " - " +
                    rs.getString(8)
            );
        }
    }
}

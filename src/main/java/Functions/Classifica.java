package Functions;

import Utility.Colors;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Classifica implements Colors {
    public static void classifica_aziende_tirocini(Connection con) throws SQLException {
        System.out.println(CYAN + "Classifica delle aziende con il maggior numero di tirocini svolti:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL classifica_aziende()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        if (rs.next()) {
            System.out.println("Posizione - Nome azienda - Numero tirocini");
            System.out.println("------------------------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3)
                );
            } while (rs.next());
        }
    }

    public static void classifica_aziende_gradimento(Connection con) throws SQLException {
        System.out.println(CYAN + "Classifica delle aziende in base al gradimento degli studenti:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL classifica_aziende_feedback()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        if (rs.next()) {
            System.out.println("Posizione - Nome azienda - Feedback medio");
            System.out.println("-----------------------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3)
                );
            } while (rs.next());
        }
    }

    public static void classifica_aziende_gradimento_tirocini(Connection con) throws SQLException {
        System.out.println(CYAN + "Classifica delle aziende in base al gradimento dei tirocini:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL classifica_aziende_feedback_tirocini()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        if (rs.next()) {
            System.out.println("Posizione - Nome azienda - Feedback medio tirocini");
            System.out.println("--------------------------------------------------  ");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3)
                );
            } while (rs.next());
        }
    }

    public static void classifica_tutor_universitari(Connection con) throws SQLException {
        System.out.println(CYAN + "Classifica dei tutor universitari pi?? richiesti:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL classifica_tutor()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        if (rs.next()) {
            System.out.println("Tirocini - Nome - Cognome");
            System.out.println("-------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3)
                );
            } while (rs.next());
        }
    }
}

package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.Scanner;

public class Liste implements Colors {
    public static void lista_aziende(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista delle aziende con offerte di tirocinio ancora aperte:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_aziende_con_offerte()}");
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

    public static void lista_tirocini_attivi(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista dei tirocini attualmente in svolgimento:" + RESET);
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

    public static void lista_storico_tirocini_studente(Connection con) throws SQLException {
        System.out.println(CYAN + "Storico dei tirocini \nInserire la " + RED + "matricola" + CYAN + " dello studente." + RESET);
        System.out.println(CYAN + "inserisci" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." + RESET);
        System.out.println("--------------------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Statement stmt1 = con.createStatement();
        ResultSet rs2 = stmt1.executeQuery("SELECT concat(s.nome, s.cognome) FROM studente s WHERE s.matricola = " + input + ";");
        if(!rs2.next()){
            System.out.println(RED + "Lo studente con matricola " + input + " non esiste." + RESET);
            return;
        }

        CallableStatement stmt2 = con.prepareCall("{CALL lista_storico_tirocini_studente(?)}");
        stmt2.setString(1, input);
        stmt2.execute();
        ResultSet rs = stmt2.getResultSet();
        if(rs.next()){
            System.out.println(CYAN + "Ecco la lista dei tirocini conclusi da " + rs2.getString(1) + RESET);
            System.out.println("Titolo offerta - Nome azienda - Inizio - Fine");
            System.out.println("----------------------------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3) + " - " +
                                rs.getString(4)
                );
            }
            while (rs.next());
        } else {
            System.out.println(CYAN + "Lo studente non ha ancora svolto o conluso alcun tirocinio." + RESET);
        }
    }
}

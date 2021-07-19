package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Liste implements Colors {
    private static class StudenteInesistenteException extends Throwable {
    }

    public static void lista_aziende(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista delle aziende con offerte di tirocinio ancora aperte:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_aziende_con_offerte()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        if (rs.next()) {
            System.out.println("ID - Nome azienda");
            System.out.println("-------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2)
                );
            } while (rs.next());
        }
    }

    public static void lista_tirocini_attivi(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista dei tirocini attualmente in svolgimento:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_tirocini_attivi()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            System.out.println("ID - Titolo offerta - Nome azienda - Studente - Tutor universitario - Tutor aziendale - Inizio - Fine");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            do {
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
            } while (rs.next());
        } else {
            System.out.println(RED + "Attualmente non ci sono tirocini in svolgimento." + RESET);
        }
    }

    public static void lista_tirocini_scaduti(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista dei tirocini scaduti:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL lista_tirocini_scaduti()}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            System.out.println("ID - Titolo offerta - Nome azienda - Studente - Tutor universitario - Tutor aziendale - Inizio - Fine");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            do {
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
            } while (rs.next());
        } else {
            System.out.println(RED + "Attualmente non ci sono tirocini scaduti." + RESET);
        }
    }

    public static int lista_storico_tirocini_studente(Connection con) throws SQLException {
        System.out.println(CYAN + "Storico dei tirocini." + RESET);
        System.out.println(CYAN + "Inserisci" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." + RESET);
        System.out.println("--------------------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        Statement stmt1 = con.createStatement();
        ResultSet rs1;
        boolean inputOk = false;
        while (!inputOk) {
            System.out.println(GREEN + "Inserire matricola dello studente:" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                if (!(input.length() == 6)) {
                    throw new InputMismatchException();
                }
                rs1 = stmt1.executeQuery("SELECT concat(s.nome, s.cognome) FROM studente s WHERE s.matricola = " + input + ";");
                if (!rs1.next()) {
                    throw new StudenteInesistenteException();
                }
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: La matricola deve contenere solo cifre numeriche." + RESET);
            } catch (InputMismatchException e) {
                System.out.println(RED + "ERRORE: La matricola deve contenere 6 cifre numeriche." + RESET);
            } catch (StudenteInesistenteException e) {
                System.out.println(RED + "Lo studente con matricola " + input + " non esiste." + RESET);
            }
        }
        inputOk = false;

        CallableStatement stmt2 = con.prepareCall("{CALL lista_storico_tirocini_studente(?)}");
        stmt2.setString(1, input);
        stmt2.execute();
        ResultSet rs2 = stmt2.getResultSet();
        if (rs2.next()) {
            System.out.println(CYAN + "Ecco la lista dei tirocini conclusi da " + rs2.getString(1) + RESET);
            System.out.println("Nome studente - Matricola - Titolo offerta - Nome azienda - Inizio - Fine");
            System.out.println("-------------------------------------------------------------------------");
            do {
                System.out.println(
                        rs2.getString(1) + " - " +
                                rs2.getString(2) + " - " +
                                rs2.getString(3) + " - " +
                                rs2.getString(4) + " - " +
                                rs2.getString(5) + " - " +
                                rs2.getString(6)
                );
            }
            while (rs2.next());
        } else {
            System.out.println(CYAN + "Lo studente non ha ancora svolto o conluso alcun tirocinio." + RESET);
        }
        return 0;
    }
}

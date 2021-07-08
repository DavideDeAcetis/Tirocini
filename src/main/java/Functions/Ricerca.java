package Functions;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import Utility.Colors;

public class Ricerca implements Colors {
    public static void ricerca_offerte(Connection con) throws SQLException {
        System.out.println(CYAN + "Lista delle offerte attualmente disponibili:" + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL ricerca_offerte(null,null,null,null,null)}");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            System.out.println("ID - Data creazione - Titolo - Sede - Durata - Posti disponibili - Cfu - Obiettivi");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3) + " - " +
                                rs.getString(4) + " - " +
                                rs.getString(5) + " - " +
                                rs.getString(6) + " - " +
                                rs.getString(8) + " - " +
                                rs.getString(7)
                );
            } while (rs.next());
        } else {
            System.out.println(RED + "Attualmente non ci sono offerte disponibili." + RESET);
        }
    }

    public static int ricerca_offerte_con_criteri(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu di ricerca per le offerte, inserire i criteri di ricerca desiderati;" +
                "\nlasciare vuoti i campi che non sono di interesse." +
                "\ninserisci" + Colors.RED + " 0 " + Colors.CYAN + "in qualsiasi momento per annullare l'operazione." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{CALL ricerca_offerte(?,?,?,?,?)}");

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        System.out.println(GREEN + "Inserire parole chiave:" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else if (input.equalsIgnoreCase("")){
            stmt.setString(1, null);
        } else {
            stmt.setString(1, input);
        }


        System.out.println(GREEN + "Inserire città di interesse:" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else if (input.equalsIgnoreCase("")){
            stmt.setString(2, null);
        } else {
            stmt.setString(2, input);
        }

        while (!inputOk) {
            System.out.println(GREEN + "Inserire la durata minima (in ore) del tirocinio:" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("")){
                    stmt.setString(3, null);
                } else {
                    stmt.setString(3, input);
                }
                    inputOk = true;
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: Inserire un numero." + Colors.RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(GREEN + "Inserire la durata massima (in ore) del tirocinio:" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("")){
                    stmt.setString(4, null);
                } else {
                    stmt.setString(4, input);
                }
                    inputOk = true;
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: Inserire un numero." + Colors.RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(GREEN + "Inserire il numero di cfu desiderati:" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("")){
                    stmt.setString(5, null);
                } else {
                    stmt.setString(5, input);
                }
                    inputOk = true;
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: Inserire un numero." + Colors.RESET);
            }
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            System.out.println("ID - Data creazione - Titolo - Sede - Durata - Posti disponibili - Cfu - Obiettivi");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            do {
                System.out.println(
                        rs.getString(1) + " - " +
                                rs.getString(2) + " - " +
                                rs.getString(3) + " - " +
                                rs.getString(4) + " - " +
                                rs.getString(5) + " - " +
                                rs.getString(6) + " - " +
                                rs.getString(8) + " - " +
                                rs.getString(7)
                );
            } while (rs.next());
        } else {
            System.out.println(RED + "Attualmente non ci sono offerte disponibili che soddisfano questi criteri." + RESET);
        }
        return 0;
    }
}

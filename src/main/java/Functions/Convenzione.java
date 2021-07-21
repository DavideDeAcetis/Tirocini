package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Convenzione implements Colors {
    public static void convenziona(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;
        ArrayList<Integer> aziende_id = new ArrayList<>();
        PreparedStatement stmt;

        stmt = con.prepareCall("{CALL controllo_convenzione()}");
        stmt.execute();

        PreparedStatement stmt1 = con.prepareStatement("SELECT a.id AS id, a.nome AS nome FROM azienda a WHERE a.verificato = 0 AND a.data_convenzione IS NULL");
        ResultSet rs2 = stmt1.executeQuery();

        if (rs2.next()) {
            System.out.println(CYAN + "Scegli l'azienda da convenzionare:" + RESET);
            System.out.println("Nome azienda");
            System.out.println("------------");
            int count = 1;
            do {
                System.out.println(
                        count++ + ") " +
                                rs2.getString("nome")
                );
                aziende_id.add(rs2.getInt("id"));
            } while (rs2.next());

            stmt = con.prepareCall("{CALL convenziona(?,?)}");
            while (!inputOk) {
                input = scanner.nextLine();
                if (Integer.parseInt(input) == 0) {
                    System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                    return;
                }
                try {
                    if (Integer.parseInt(input) - 1 < aziende_id.size()) {
                        stmt.setInt(1, aziende_id.get(Integer.parseInt(input) - 1));
                        inputOk = true;
                    } else {
                        System.out.println(RED + "ERRORE: Inserire una cifra valida." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                }
            }

            inputOk = false;
            while (!inputOk) {
                System.out.println(GREEN + "Inserire il numero di protocollo della convenzione." + RESET);
                input = scanner.nextLine();
                if (Integer.parseInt(input) == 0) {
                    System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                    return;
                }
                if (input.length() <= 45) {
                    stmt.setString(2, input);
                    inputOk = true;
                } else {
                    System.out.println(RED + "ERRORE: Inserire massimo 45 caratteri." + RESET);
                }
            }
            stmt.execute();
            System.out.println(GREEN + "Azienda convenzionata con successo." + RESET);
            stmt.close();
        } else {
            System.out.println(RED + "Non ci sono aziende da convenzionare." + RESET);
        }
    }

    public static void verifica(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;
        ArrayList<Integer> aziende_id = new ArrayList<>();
        PreparedStatement stmt;

        PreparedStatement stmt1 = con.prepareStatement("SELECT a.id AS id, a.nome AS nome FROM azienda a WHERE a.verificato = 0");
        ResultSet rs2 = stmt1.executeQuery();

        if (rs2.next()) {
            System.out.println(CYAN + "Scegli l'azienda da verificare:" + RESET);
            System.out.println("Nome azienda");
            System.out.println("------------");
            int count = 1;
            do {
                System.out.println(
                        count++ + ") " +
                                rs2.getString("nome")
                );
                aziende_id.add(rs2.getInt("id"));
            } while (rs2.next());

            stmt = con.prepareCall("{CALL verifica_azienda(?)}");

            while (!inputOk) {
                input = scanner.nextLine();
                try {
                    if (Integer.parseInt(input) == 0) {
                        System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                        return;
                    }
                    if (Integer.parseInt(input) - 1 < aziende_id.size()) {
                        stmt.setInt(1, aziende_id.get(Integer.parseInt(input) - 1));
                        inputOk = true;
                    } else {
                        System.out.println(RED + "ERRORE: Inserire una cifra valida." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                }
            }
            stmt.execute();
            System.out.println(GREEN + "Azienda convenzionata con successo." + RESET);
            stmt.close();
        }
    }
}

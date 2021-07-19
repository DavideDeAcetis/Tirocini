package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Feedback implements Colors {
    public static int inserisci_feedback_azienda(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'inserimento dei feedback per l'azienda. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obbligatori." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt;

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;
        ArrayList<Integer> lista_id_aziende = new ArrayList<>();
        int studente_id;

        System.out.println(GREEN + "Inserisci matricola studente" + RED + "*" + RESET);
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            PreparedStatement stmt1 = con.prepareStatement("SELECT s.id FROM studente s WHERE s.matricola = ?");
            stmt1.setString(1, input);
            ResultSet rs2 = stmt1.executeQuery();
            rs2.next();
            studente_id = rs2.getInt(1);

            stmt = con.prepareCall("{CALL lista_aziende_senza_feedback(?)}");
            stmt.setString(1, input);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                System.out.println(CYAN + "Scegli l'azienda a cui dare il feedback:" + RESET);
                System.out.println("Azienda");
                System.out.println("-------");
                int count = 1;
                do {
                    System.out.println(
                            count++ + ") " +
                                    rs.getString(2)
                    );
                    lista_id_aziende.add(rs.getInt("id"));
                } while (rs.next());
            } else {
                System.out.println(RED + "Non ci sono aziende per cui inserire il feedback" + RESET);
                return 0;
            }
        }

        while (!inputOk) {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (Integer.parseInt(input) - 1 < lista_id_aziende.size()) {
                    stmt = con.prepareCall("{CALL aggiungi_feedback_azienda(?,?,?)}");
                    stmt.setInt(1, studente_id);
                    stmt.setInt(2, lista_id_aziende.get(Integer.parseInt(input) - 1));

                    while (!inputOk) {
                        System.out.println(GREEN + "Inserisci il feedback:" + RED + "*" + RESET);
                        input = scanner.nextLine();
                        try {
                            if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 5) {
                                stmt.setInt(3, Integer.parseInt(input));
                            } else {
                                throw new InputMismatchException();
                            }
                            inputOk = true;
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                        } catch (InputMismatchException e) {
                            System.out.println(RED + "ERRORE: Inserire un numero tra 0 e 5." + RESET);
                        }
                        inputOk = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
            }
        }

        stmt.execute();
        stmt.close();
        System.out.println(CYAN + "Feedback aggiunto con successo!" + RESET);
        return studente_id;
    }

    public static int inserisci_feedback_tirocinio(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'inserimento dei feedback per i tirocini. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obbligatori." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt;

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;
        ArrayList<String> lista_id_tirocini = new ArrayList<>();
        int studente_id;

        System.out.println(GREEN + "Inserisci matricola studente" + RED + "*" + RESET);
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            PreparedStatement stmt1 = con.prepareStatement("SELECT s.id FROM studente s WHERE s.matricola = ?");
            stmt1.setString(1, input);
            ResultSet rs2 = stmt1.executeQuery();
            rs2.next();
            studente_id = rs2.getInt(1);

            stmt = con.prepareCall("{CALL lista_tirocini_senza_feedback(?)}");
            stmt.setString(1, input);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                System.out.println(CYAN + "Scegli il tirocinio a cui dare il feedback:" + RESET);
                System.out.println("Titolo");
                System.out.println("-------");
                int count = 1;
                do {
                    System.out.println(
                            count++ + ") " +
                                    rs.getString(2)
                    );
                    lista_id_tirocini.add(rs.getString("id"));
                } while (rs.next());
            } else {
                System.out.println(RED + "Non ci sono tirocini per cui inserire il feedback" + RESET);
                return 0;
            }
        }

        while (!inputOk) {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (Integer.parseInt(input) - 1 < lista_id_tirocini.size()) {
                    stmt = con.prepareCall("{CALL aggiungi_feedback_tirocinio(?,?,?)}");
                    stmt.setInt(1, studente_id);
                    stmt.setString(2, lista_id_tirocini.get(Integer.parseInt(input) - 1));

                    while (!inputOk) {
                        System.out.println(GREEN + "Inserisci il feedback:" + RED + "*" + RESET);
                        input = scanner.nextLine();
                        try {
                            if (input.equalsIgnoreCase("0")) {
                                return -1;
                            }
                            if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 5) {
                                stmt.setString(3, input);
                            } else {
                                throw new InputMismatchException();
                            }
                            inputOk = true;
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                        } catch (InputMismatchException e) {
                            System.out.println(RED + "ERRORE: Inserire un numero tra 0 e 5." + RESET);
                        }
                        inputOk = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
            }
        }
        stmt.execute();
        stmt.close();
        System.out.println(CYAN + "Feedback aggiunto con successo!" + RESET);
        return studente_id;
    }
}

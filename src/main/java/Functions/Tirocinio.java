package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tirocinio implements Colors {
    public static int attivazione_tirocinio(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'attivazione di una tirocinio. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obligatori." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{? = CALL attiva_tirocinio(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        stmt.execute();
        int studente_id = stmt.getInt(1);
        stmt.close();
        System.out.println(CYAN + "Registrazione completata con successo!" + RESET);
        return studente_id;
    }

    public static int chiusura_tirocinio(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per la chiusura di un tirocinio. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obligatori." + RESET);
        System.out.println("---------------------------------------------------------------- ");
        CallableStatement stmt;

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;
        int id_tirocinio = 0;

        System.out.println(GREEN + "Inserisci matricola studente" + RED + "*" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt = con.prepareCall("{CALL lista_tirocini_attivi_studente(?)}");
            stmt.setString(1, input);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                System.out.println("Titolo - Azienda - Data di inizio - Data di fine");
                System.out.println("------------------------------------------------");
                int count = 1;
                do {
                    System.out.println(
                            count++ + ") " +
                                    rs.getString(4) + " - " +
                                    rs.getString(5) + " - " +
                                    rs.getString(6) + " - " +
                                    rs.getString(7)
                    );
                    id_tirocinio = rs.getInt("id");
                } while (rs.next());
            } else {
                System.out.println(RED + "Non ci sono tirocini attivi per questo studente." + RESET);
                return 0;
            }
        }

        while (!inputOk) {
            System.out.println(GREEN + "Chiudere questo tirocinio? (Y/N)" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("y")) {
                    stmt = con.prepareCall("{? = CALL chiudi_tirocinio(?,?,?,?,?)}");
                    stmt.setInt(2, id_tirocinio);

                    while (!inputOk) {
                        System.out.println(GREEN + "Inserisci le ore di tirocinio svolte:" + RED + "*" + RESET);
                        input = scanner.nextLine();
                        try {
                            if (input.equalsIgnoreCase("0")) {
                                return -1;
                            }
                            Integer.parseInt(input);
                            stmt.setString(3, input);
                            inputOk = true;
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                        }
                        inputOk = true;
                    }
                    inputOk = false;

                    while (!inputOk) {
                        System.out.println(GREEN + "Inserisci eventuali rimborsi:" + RED + "*" + RESET);
                        input = scanner.nextLine();
                        try {
                            if (input.equalsIgnoreCase("0")) {
                                return -1;
                            }
                            Float.parseFloat(input);
                            stmt.setString(4, input);
                            inputOk = true;
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                        }
                        inputOk = true;
                    }
                    inputOk = false;

                    while (!inputOk) {
                        System.out.println(GREEN + "Inserisci un giudizio dell'azienda per le prestazioni dello studente:" + RED + "*" + RESET);
                        input = scanner.nextLine();
                        try {
                            if (input.equalsIgnoreCase("0")) {
                                return -1;
                            }
                            int giudizio = Integer.parseInt(input);
                            if (giudizio >= 0 && giudizio <= 5) {
                                stmt.setString(5, input);
                            } else {
                                throw new GiudizioErratoException();
                            }
                            inputOk = true;
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "ERRORE: Inserire solo cifre numeriche." + RESET);
                        } catch (GiudizioErratoException e) {
                            System.out.println(RED + "ERRORE: Inserire un valore numerico tra 0 e 5." + RESET);
                        }
                        inputOk = true;
                    }
                    inputOk = false;

                    System.out.println(GREEN + "Inserisci una descrizione del lavoro svolto dallo studente:" + RED + "*" + RESET);
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("0")) {
                        return -1;
                    } else {
                        stmt.setString(6, input);
                    }
                } else if ((input.equalsIgnoreCase("n"))) {
                    return -1;
                } else {
                    throw new InputMismatchException();
                }
                stmt.execute();
                stmt.close();
                System.out.println(CYAN + "Tirocinio chiuso correttamente." + RESET);

                inputOk = true;
            } catch (InputMismatchException e) {
                System.out.println(RED + "ERRORE: Input non valido, il formato deve essere: Y oppure N." + RESET);
            }
        }
        return 0;
    }
    private static class GiudizioErratoException extends Throwable { }
}

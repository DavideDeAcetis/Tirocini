package Functions;

import Utility.Colors;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Offerta implements Colors {
    private static class NoTirociniAttiviException extends Throwable {
    }

    public static int aggiungi_offerta(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'inserimento di una nuova offerta di tirocinio, inserisci" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obbligatori." + RESET);
        System.out.println(RED + "ATTENZIONE: " + CYAN + "L'azienda deve essere prima verificata per poter aggiungere un'offerta di tirocinio." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        CallableStatement stmt = con.prepareCall("{? = CALL controllo_azienda(?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        while (!inputOk) {
            System.out.println(GREEN + "Inserisci la partita IVA dell'azienda:" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Long.parseUnsignedLong(input);
                if (input.length() == 11) {
                    stmt.setString(2, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: La partitaIVA deve contenere 11 numeri." + RESET);
            } catch (InputMismatchException e) {
                System.out.println(RED + "ERRORE: La partitaIVA deve contenere 11 caratteri." + RESET);
            }
        }
        inputOk = false;

        stmt.execute();
        String azienda_id = stmt.getString(1);
        System.out.println(CYAN + "L'azienda selezionata è verificata e può proseguire con l'operazione." + RESET);
        stmt.close();

        stmt = con.prepareCall("{? = CALL aggiungi_offerta(?,?,?,?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        stmt.setString(2, azienda_id);

        System.out.println(GREEN + "Inserisci il titolo dell'offerta:" + RED + "*" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(3, input);
        }

        System.out.println(GREEN + "Inserisci la sede dove verrà svolto il tirocinio:" + RED + "*" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(4, input);
        }

        while (!inputOk) {
            System.out.println(GREEN + "Inserisci la durata (in ore) del tirocinio:" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                stmt.setString(5, input);
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: Inserire solo numeri." + RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(GREEN + "Inserisci i posti disponibili:" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                stmt.setString(6, input);
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: Inserire solo numeri." + RESET);
            }
        }
        inputOk = false;

        System.out.println(GREEN + "Inserisci gli obiettivi del tirocinio:" + RED + "*" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(7, input);
        }
        while (!inputOk) {
            System.out.println(GREEN + "Inserisci il numero di CFU:" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                stmt.setString(8, input);
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: Inserire solo numeri." + RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(GREEN + "Inserisci la modalità di svolgimento del tirocinio (d = a distanza, p = in presenza, m = mista):" + RED + "*" + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equals("d") || input.equals("p") || input.equals("m")) {
                    stmt.setString(9, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e) {
                System.out.println(RED + "ERRORE: Input non corretto." + RESET);
            }
        }
        inputOk = false;

        System.out.println(GREEN + "Inserisci eventuali facilitazioni: " + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(10, input);
        }
        System.out.println(GREEN + "Inserisci una descrizione dell'offerta:" + RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(11, input);
        }

        stmt.execute();
        int offerta_id = stmt.getInt(1);
        stmt.close();
        System.out.println(CYAN + "Offerta inserita con successo!" + RESET);
        return offerta_id;
    }

    public static int annulla_offerta(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'annullamento di un' offerta di tirocinio, inserisci" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean inputOk = false;

        while (!inputOk) {
            System.out.println(CYAN + "Inserisci la partita IVA dell'azienda che ha creato l'offerta: " + RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Long.parseUnsignedLong(input);
                if (!(input.length() == 11)) {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERRORE: La partitaIVA deve contenere 11 numeri." + RESET);
            } catch (InputMismatchException e) {
                System.out.println(RED + "ERRORE: La partitaIVA deve contenere 11 caratteri." + RESET);
            }
        }
        inputOk = false;
        int id_offerta = -1;

        try {
            con.setAutoCommit(false);   //in modo da eseguire una transazione

            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT count(*) FROM offerta o, azienda a WHERE o.azienda_id = a.id AND a.partita_iva = " + input + " AND o.visibile = 1");
            rs2.next();
            int rows = rs2.getInt(1);
            int[] id_list = new int[rows];
            rs2.close();

            ResultSet rs1 = stmt.executeQuery("SELECT o.id, o.titolo FROM offerta o, azienda a WHERE o.azienda_id = a.id AND a.partita_iva = " + input + " AND o.visibile = 1");
            int count = 1;
            while (rs1.next()) {
                id_list[count - 1] = rs1.getInt(1);
                System.out.println(RED + count++ + ") " + GREEN + rs1.getString(1) + " - " + rs1.getString(2) + RESET);
            }

            if (rows != 0) {
                while (!inputOk) {
                    System.out.println(CYAN + "Inserisci il numero corrispondente al titolo dell'offerta da annullare: " + RESET);
                    System.out.println("---------------------------------------------------------------- ");
                    input = scanner.nextLine();
                    try {
                        if (input.equalsIgnoreCase("0")) {
                            return -1;
                        }
                        if (!(Integer.parseInt(input) <= rows)) {
                            throw new InputMismatchException();
                        }
                        inputOk = true;
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "ERRORE: Inserire un numero corretto." + RESET);
                    } catch (InputMismatchException e) {
                        System.out.println(RED + "ERRORE: Inserire un numero nel range 1 - " + rows + "." + RESET);
                    }
                }
            } else {
                throw new NoTirociniAttiviException();
            }
            inputOk = false;

            id_offerta = id_list[Integer.parseInt(input) - 1];
            CallableStatement cstmt = con.prepareCall("CALL annullamento_offerta(?)");
            cstmt.setInt(1, id_offerta);
            cstmt.execute();
            stmt.close();
            rs1.close();

            System.out.println(CYAN + "L'offerta selezionata è stata annullata con successo!" + RESET);

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            // in case of exception, rollback the transaction
            con.rollback();
        } catch (NoTirociniAttiviException e) {
            System.out.println(RED + "Questa azienda non ha tirocini attivi." + RESET);
            return 0;
        }
        return id_offerta;
    }
}

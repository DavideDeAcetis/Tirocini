package Functions;

import Utility.Colors;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Registrazione {
    public static int registrazione_studente(Connection con) throws SQLException {
        System.out.println(Colors.CYAN + "Menu di registrazione di uno studente, inserisci" + Colors.RED + " 0 " + Colors.CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + Colors.RED + "*" + Colors.CYAN + " sono obbligatori." + Colors.RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{? = CALL registrazione_studente(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci nome" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(2, input);
        }

        System.out.println(Colors.GREEN + "Inserisci cognome" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(3, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci la data di nascita (aaaa/mm/gg):" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    dateFormat.setLenient(false);
                    dateFormat.parse(input.trim());
                    stmt.setString(4, input);
                    inputOk = true;
                }
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: La data inserita non è corretta, il formato deve essere: (aaaa/mm/gg)." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci la città di nascita:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(5, input);
        }

        System.out.println(Colors.GREEN + "Inserisci la provincia di nascita:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(6, input);
        }

        System.out.println(Colors.GREEN + "Inserisci la provincia di residenza:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(7, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Lo studente ha eseguito il corso sulla sicurezza? (Y/N):" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("y")) {
                    stmt.setString(8, "1");
                } else if ((input.equalsIgnoreCase("n"))) {
                    stmt.setString(8, "0");
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e) {
                System.out.println(Colors.RED + "ERRORE: Input non valido, il formato deve essere: Y oppure N." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci email:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(9, input);
        }

        System.out.println(Colors.GREEN + "Inserisci telefono: " + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(10, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci matricola:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                if (input.length() == 6) {
                    stmt.setString(11, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + "ERRORE: La matricola deve contenere solo cifre numeriche." + Colors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(Colors.RED + "ERRORE: La matricola deve contenere 6 cifre numeriche." + Colors.RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci codice fiscale:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                if (input.length() == 16) {
                    stmt.setString(12, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e){
                System.out.println(Colors.RED + "ERRORE: Il codice fiscale deve contenere 16 caratteri." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci nome del corso di laurea:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(13, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Lo studente è portatore di handicap? (Y/N):" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else if (input.equalsIgnoreCase("y")) {
                    stmt.setString(14, "1");
                } else if ((input.equalsIgnoreCase("n"))) {
                    stmt.setString(14, "0");
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e) {
                System.out.println(Colors.RED + "ERRORE: Input non valido, il formato deve essere: Y oppure N." + Colors.RESET);
            }
        }

        stmt.execute();
        int studente_id = stmt.getInt(1);
        stmt.close();
        System.out.println(Colors.CYAN + "Registrazione completata con successo!" + Colors.RESET);
        return studente_id;
    }

    public static int registrazione_azienda(Connection con) throws SQLException {
        System.out.println(Colors.CYAN + "Menu di registrazione di un'azienda, inserisci" + Colors.RED + " 0 " + Colors.CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + Colors.RED + "*" + Colors.CYAN + " sono obbligatori." + Colors.RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{? = CALL registrazione_azienda(?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci il nome dell'azienda:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(2, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci partita IVA:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Long.parseUnsignedLong(input);
                if (input.length() == 11) {
                    stmt.setString(3, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (NumberFormatException | InputMismatchException e){
                System.out.println(Colors.RED + "ERRORE: La partitaIVA deve contenere 11 numeri." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci il foro competente:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(4, input);
        }
        System.out.println(Colors.GREEN + "Inserisci la sede legale:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(5, input);
        }
        System.out.println(Colors.GREEN + "Inserisci il nome del rappresentante legale:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(6, input);
        }
        System.out.println(Colors.GREEN + "Inserisci il cognome del rappresentante legale:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(7, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci il codice fiscale del rappresentante legale:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                if (input.length() == 16) {
                    stmt.setString(8, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e){
                System.out.println(Colors.RED + "ERRORE: Il codice fiscale deve contenere 16 caratteri." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci il nome del responsabile dei tirocini:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(9, input);
        }

        System.out.println(Colors.GREEN + "Inserisci il cognome del responsabile dei tirocini:" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(10, input);
        }

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci il codice fiscale del responsabile dei tirocini:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                if (input.length() == 16) {
                    stmt.setString(11, input);
                } else {
                    throw new InputMismatchException();
                }
                inputOk = true;
            } catch (InputMismatchException e){
                System.out.println(Colors.RED + "ERRORE: Il codice fiscale deve contenere 16 caratteri." + Colors.RESET);
            }
        }

        System.out.println(Colors.GREEN + "Inserisci un recapito telefonico del responsabile dei tirocini:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(12, input);
        }

        System.out.println(Colors.GREEN + "Inserisci una e-mail del responsabile dei tirocini:" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(13, input);
        }

        stmt.execute();
        int studente_id = stmt.getInt(1);
        stmt.close();
        System.out.println(Colors.CYAN + "Registrazione completata con successo!" + Colors.RESET);
        return studente_id;
    }
}

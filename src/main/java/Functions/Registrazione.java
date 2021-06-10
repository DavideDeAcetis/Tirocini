package Functions;

import java.sql.*;
import java.util.Scanner;
import Utility.Colors;

public class Registrazione {
    public static int registrazione_studente(Connection con) throws SQLException {
        System.out.println(Colors.CYAN + "Menu di registrazione di uno studente, inserisci" + Colors.RED + " 0 " + Colors.CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + Colors.RED + "*" + Colors.CYAN + "sono obligatori." + Colors.RESET );
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{? = CALL registrazione_studente(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(Colors.GREEN + "Inserisci nome" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(2, input);
        }
        System.out.println(Colors.GREEN + "Inserisci cognome" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(3, input);
        }
        System.out.println(Colors.GREEN + "Inserisci la data di nascita (aaaa/mm/gg)" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(4, input);
        }
        System.out.println(Colors.GREEN + "Inserisci la città di nascita" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(5, input);
        }
        System.out.println(Colors.GREEN + "Inserisci la provincia di nascita" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(6, input);
        }
        System.out.println(Colors.GREEN + "Inserisci la provincia di residenza" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(7, input);
        }
        System.out.println(Colors.GREEN + "Lo studente ha eseguito il corso sulla sicurezza? (Y/N)" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else if (input.equalsIgnoreCase("y")){
            stmt.setString(8, "1");
        } else if ((input.equalsIgnoreCase("n"))){
            stmt.setString(8, "0");
        }
        System.out.println(Colors.GREEN + "Inserisci email" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(9, input);
        }
        System.out.println(Colors.GREEN + "Inserisci telefono: " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(10, input);
        }
        System.out.println(Colors.GREEN + "Inserisci matricola" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(11, input);
        }
        System.out.println(Colors.GREEN + "Inserisci codice fiscale" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(12, input);
        }
        System.out.println(Colors.GREEN + "Inserisci nome del corso di laurea" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else {
            stmt.setString(13, input);
        }
        System.out.println(Colors.GREEN + "Lo studente è portatore di handicap? (Y/N)" + Colors.RED + "*" + Colors.GREEN +  ": " + Colors.RESET );
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("0")) {
            return -1;
        } else if (input.equalsIgnoreCase("y")){
            stmt.setString(14, "1");
        } else if ((input.equalsIgnoreCase("n"))){
            stmt.setString(14, "0");
        }

        stmt.execute();
        int studente_id = stmt.getInt(1);
        stmt.close();
        return studente_id;
    }
}

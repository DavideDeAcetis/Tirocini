package Functions;

import Utility.Colors;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tirocinio implements Colors {
    public static int attivazione_tirocinio(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per l'attivazione di una tirocinio. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obbligatori." + RESET);
        System.out.println("---------------------------------------------------------------- ");

        CallableStatement stmt = con.prepareCall("{? = CALL attiva_tirocinio(?,?,?,?,?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputOk = false;

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Selezionare l'offerta per cui si desidera attivare il tirocinio" + Colors.RED + "*" + Colors.RESET);
            CallableStatement stmt1 = con.prepareCall("{CALL ricerca_offerte('', '',0 ,10000 ,10000 )}");
            stmt1.execute();
            ResultSet rs = stmt1.getResultSet();
            if (rs.next()) {
                System.out.println("Numero da inserire - Titolo - Sede - Durata - Posti disponibili - Obiettivi - CFU");
                System.out.println("-------------------------");
                do {
                    System.out.println(
                            rs.getString(1) + "    -    " +
                                    rs.getString(3) + " - " +
                                    rs.getString(4) + " - " +
                                    rs.getString(5) + " - " +
                                    rs.getString(6) + " - " +
                                    rs.getString(7) + " - " +
                                    rs.getString(8)
                    );
                } while (rs.next());
            }
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("0")) {
                return -1;
            } //controlla se si sia inserito un numero valido
                stmt1.execute();
                rs = stmt1.getResultSet();
                if (rs.next()) {
                    do {
                       if(rs.getString(1).equalsIgnoreCase(input)) {
                           inputOk = true;
                           stmt.setString(6, input);
                       }
                    } while (rs.next());

            }
                if(!inputOk) {
                    System.out.println(Colors.GREEN + "Dato selezionato non valido, scegliere uno dei numeri proposti" + Colors.RESET);
                }
        }
        inputOk=false;

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci matricola studente:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                if (input.length() == 6) {
                    stmt.setString(5, input);
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
            System.out.println(Colors.GREEN + "Inserisci codice fiscale tutor aziendale:" + Colors.RED + "*" + Colors.RESET);
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
        String matricolaTu = null;
        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci matricola tutor universitario:" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                }
                Integer.parseInt(input);
                if (input.length() == 6) {
                    matricolaTu=input;
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
        String querySelect= "SELECT * FROM tirocini.tutor_universitario WHERE matricola = ?";
        PreparedStatement stmt2 = con.prepareStatement(querySelect);
        stmt2.setString(1,matricolaTu);
        ResultSet rs= stmt2.executeQuery();
        if(!(rs.next())){
            System.out.println(Colors.GREEN +"Tutor universitario non registrato. Vuoi registrare il tutor universitario? 1 continuare 0 annullare"+ Colors.RESET);
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("1")){
                CallableStatement stmt3 = con.prepareCall("{? = CALL registra_tutor_universitario(?,?,?,?,?,?)}");
                stmt3.registerOutParameter(1, Types.INTEGER);

                System.out.println(Colors.GREEN + "Inserisci nome" + Colors.RED + "*" + Colors.RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    stmt3.setString(2, input);
                }
                System.out.println(Colors.GREEN + "Inserisci cognome" + Colors.RED + "*" + Colors.RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    stmt3.setString(3, input);
                }
                while (!inputOk) {
                    System.out.println(Colors.GREEN + "Inserisci codice fiscale:" + Colors.RED + "*" + Colors.RESET);
                    input = scanner.nextLine();
                    try {
                        if (input.equalsIgnoreCase("0")) {
                            return -1;
                        }
                        if (input.length() == 16) {
                            stmt3.setString(5, input);
                        } else {
                            throw new InputMismatchException();
                        }
                        inputOk = true;
                    } catch (InputMismatchException e){
                        System.out.println(Colors.RED + "ERRORE: Il codice fiscale deve contenere 16 caratteri." + Colors.RESET);
                    }
                }
                inputOk = false;
                stmt3.setString(4, matricolaTu);
                System.out.println(Colors.GREEN + "Inserisci email tutor universitario:" + Colors.RED + "*" + Colors.RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    stmt3.setString(7, input);
                }
                System.out.println(Colors.GREEN + "Inserisci telefono tutor universitario " + Colors.RESET);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    stmt3.setString(6, input);
                }
                stmt3.execute();
                int tutor_id = stmt3.getInt(1);
                stmt3.close();
                System.out.println(CYAN + "Registrazione tutor universitario completata con successo!" + RESET);
                stmt.setString(7, matricolaTu);
            }

        }else {
            stmt.setString(7, matricolaTu);
        }



        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci la data di inizio tirocinio (aaaa/mm/gg):" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    dateFormat.setLenient(false);
                    dateFormat.parse(input.trim());
                    stmt.setString(2, input);
                    inputOk = true;
                }
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: La data inserita non è corretta, il formato deve essere: (aaaa/mm/gg)." + Colors.RESET);
            }
        }
        inputOk = false;

        while (!inputOk) {
            System.out.println(Colors.GREEN + "Inserisci la data di fine tirocinio (aaaa/mm/gg):" + Colors.RED + "*" + Colors.RESET);
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("0")) {
                    return -1;
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    dateFormat.setLenient(false);
                    dateFormat.parse(input.trim());
                    stmt.setString(3, input);
                    inputOk = true;
                }
            } catch (Exception e) {
                System.out.println(Colors.RED + "ERRORE: La data inserita non è corretta, il formato deve essere: (aaaa/mm/gg)." + Colors.RESET);
            }
        }
        inputOk = false;

        System.out.println(Colors.GREEN + "Inserisci le ore effettive di tirocinio" + Colors.RED + "*" + Colors.RESET);
        input = scanner.nextLine();
            if (input.equalsIgnoreCase("0")) {
                return -1;
            } else {

                stmt.setString(4, input);
            }


        stmt.execute();
        int studente_id = stmt.getInt(1);
        stmt.close();
        System.out.println(CYAN + "Attivazione del tirocinio completata con successo!" + RESET);
        return studente_id;

    }


    public static int chiusura_tirocinio(Connection con) throws SQLException {
        System.out.println(CYAN + "Menu per la chiusura di un tirocinio. " +
                "\nInserire" + RED + " 0 " + CYAN + "in qualsiasi momento per annullare la procedura." +
                "\nI campi contrassegnati con " + RED + "*" + CYAN + " sono obbligatori." + RESET);
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

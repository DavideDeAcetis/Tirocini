import java.sql.*;
import java.util.Scanner;

import Functions.Liste;
import Functions.Offerte;
import Utility.Colors;
import Functions.Registrazione;
import  Utility.Stampa;

class Tirocini implements Colors{
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tirocini?noAccessToProcedureBodies=true&serverTimezone=Europe/Rome", "root", "ciao123");

            boolean exit = false;
            while (!exit) {
                Stampa.StampaMenu();
                Scanner scanner = new Scanner(System.in);
                int input_azione = scanner.nextInt();

                switch (input_azione) {
                    case 0:     //Uscita dal programma
                        exit = true;
                        break;
                    case 1:     //Registrazione studente
                        try {
                            int studente_id = Registrazione.registrazione_studente(con);
                            if (studente_id == -1){
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e){
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 2:     //Registrazione azienda
                        try {
                            int azienda_id = Registrazione.registrazione_azienda(con);
                            if (azienda_id == -1){
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e){
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 3:     //Inserimento di una nuova offerta di tirocinio
                        try {
                            int offerta_id = Offerte.aggiungi_offerta(con);
                            if (offerta_id == -1){
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e){
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 4:     //Annullamento di un'offerta di tirocinio
                        try {
                            int offerta_id = Offerte.annulla_offerta(con);
                            if (offerta_id == -1){
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 5:
                        // Perform "original number" case.
                        break;
                    case 6:
                        // Perform "encrypt number" case.
                        break;
                    case 7:
                        // Perform "decrypt number" case.
                        break;
                    case 8:
                        // Perform "quit" case.
                        break;
                    case 9:
                        // Perform "original number" case.
                        break;
                    case 10:
                        // Perform "encrypt number" case.
                        break;
                    case 11:
                        // Perform "decrypt number" case.
                        break;
                    case 12:

                        break;
                    case 13:    //lista aziende con offerte aperte
                        Liste.lista_aziende(con);
                        break;
                    case 14:    //lista tirocini in corso
                        Liste.lista_tirocini_attivi(con);
                        break;
                    case 15:
                        // Perform "decrypt number" case.
                        break;
                    case 16:

                        break;
                    default:
                        System.out.println(RED + "ERRORE: Input non valido" + RESET);
                }

                if (!exit) {
                    System.out.println(GREEN + "Premere" + RED + " 1 " + GREEN + "per continuare o" + RED + " 0 " + GREEN + "per uscire" + RESET);
                    if (scanner.nextInt() == 0) {
                        exit = true;
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(RED + "ERRORE: " + e + RESET);
        }
    }
}

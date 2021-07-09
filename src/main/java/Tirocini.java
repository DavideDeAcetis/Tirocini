import java.sql.*;
import java.util.Scanner;

import Functions.*;
import Utility.Colors;
import Utility.Stampa;

class Tirocini implements Colors {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tirocini?noAccessToProcedureBodies=true&serverTimezone=Europe/Rome", "root", "ciao123");

            boolean exit = false;
            while (!exit) {
                Stampa.StampaMenu();
                Scanner scanner = new Scanner(System.in);
                int input_azione = scanner.nextInt();
                int status;

                switch (input_azione) {
                    case 0:     //Uscita dal programma
                        exit = true;
                        break;
                    case 1:     //Registrazione studente
                        try {
                            status = Registrazione.registrazione_studente(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 2:     //Registrazione azienda
                        try {
                            status = Registrazione.registrazione_azienda(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 3:     //Inserimento di una nuova offerta di tirocinio
                        try {
                            status = Offerta.aggiungi_offerta(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 4:     //Annullamento di un'offerta di tirocinio
                        try {
                            status = Offerta.annulla_offerta(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 5:     //Ricerca offerte
                        try {
                            Ricerca.ricerca_offerte(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 6:     //Ricerca offerte con criteri
                        try {
                            Ricerca.ricerca_offerte_con_criteri(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 7:     //Classifica offerte per numero di tirocini
                        try {
                            Classifica.classifica_aziende_tirocini(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 8:     //Classsifica offerte per gradimento
                        try {
                            //TODO: DA FARE LA PROCEDURE IN SQL
                            Classifica.classifica_aziende_gradimento(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 9:     //Calssifica tutor universitari per tirocini
                        try {
                            Classifica.classifica_tutor_universitari(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 10:
                        // Perform "encrypt number" case.
                        break;
                    case 11:        //chiusura di un tirocinio
                        try {
                            status = Tirocinio.chiusura_tirocinio(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 12:       //Inserimento feedback per un'azienda.
                        try {
                            status = Feedback.inserisci_feedback_azienda(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                            e.printStackTrace(System.out);
                        }
                        break;
                    case 13:    //Ricerca tirocini di uno studente
                        try {
                            status = Liste.lista_storico_tirocini_studente(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 14:    //lista aziende con offerte aperte
                        try {
                            Liste.lista_aziende(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 15:    //lista tirocini in corso
                        try {
                            Liste.lista_tirocini_attivi(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 16:    //lista tirocini scaduti
                        try {
                            Liste.lista_tirocini_scaduti(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
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
            e.printStackTrace(System.out);
        }
    }
}

import Functions.*;
import Utility.Colors;
import Utility.Stampa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

class Tirocini implements Colors {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tirocini?noAccessToProcedureBodies=true&serverTimezone=Europe/Rome", "root","ciao123")){
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
                            Classifica.classifica_aziende_gradimento(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 9:
                        try {
                            Classifica.classifica_aziende_gradimento_tirocini(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 10:     //Calssifica tutor universitari per tirocini
                        try {
                            Classifica.classifica_tutor_universitari(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 11:  //attivazione tirocinio
                        try {
                            status = Tirocinio.attivazione_tirocinio(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                            break;
                    case 12:        //chiusura di un tirocinio
                        try {
                            status = Tirocinio.chiusura_tirocinio(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 13:       //Inserimento feedback per un azienda.
                        try {
                            status = Feedback.inserisci_feedback_azienda(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 14:       //Inserimento feedback per un tirocinio.
                        try {
                            status = Feedback.inserisci_feedback_tirocinio(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 15:    //Ricerca tirocini di uno studente
                        try {
                            status = Liste.lista_storico_tirocini_studente(con);
                            if (status == -1) {
                                System.out.println(RED + "Operazione annullata dall'utente." + RESET);
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 16:    //lista aziende con offerte aperte
                        try {
                            Liste.lista_aziende(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 17:    //lista tirocini in corso
                        try {
                            Liste.lista_tirocini_attivi(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 18:    //lista tirocini scaduti
                        try {
                            Liste.lista_tirocini_scaduti(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 19:    //convenziona un'azienda
                        try {
                            Convenzione.convenziona(con);
                        } catch (Exception e) {
                            System.out.println(RED + "ERRORE: " + e + RESET);
                        }
                        break;
                    case 20:    //verifica un'azienda
                        try {
                            Convenzione.verifica(con);
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
        } catch (Exception e) {
            System.out.println(RED + "ERRORE: " + e + RESET);
        }
    }
}

package Utility;

public class Stampa implements Colors {
    public static void StampaMenu() {
        System.out.println(CYAN + "Inserisci il numero corrispondente all'azione che vuoi eseguire: " + RESET);
        System.out.println("---------------------------------------------------------------- ");
        System.out.println(
                        GREEN + "0: "   + RESET + "ESCI" +
                        GREEN + "\n1: " + RESET + "Registra uno studente." +
                        GREEN + "\n2: " + RESET + "Registra un'azienda." +
                        GREEN + "\n3: " + RESET + "Inserisci offerta." +
                        GREEN + "\n4: " + RESET + "Annulla offerta." +
                        GREEN + "\n5: " + RESET + "Ricerca tirocini in corso" +
                        GREEN + "\n6: " + RESET + "Ricerca tirocini con criteri" +
                        GREEN + "\n7: " + RESET + "Classifica delle aziende" +
                        GREEN + "\n8: " + RESET + "Classifica dei tutor universitari" +
                        GREEN + "\n9: " + RESET + "Attiva tirocinio." +
                        GREEN + "\n10: " + RESET + "Chiudi tirocinio." +
                        GREEN + "\n11: " + RESET + "Inserisci feedback sul tirocinio e sull'azienda." +
                        GREEN + "\n12: " + RESET + "Lista tirocini svolti da uno studente." +
                        GREEN + "\n13: " + RESET + "Lista delle aziende con offerte di tirocinio ancora aperte." +
                        GREEN + "\n14: " + RESET + "Lista dei tirocini in corso." +
                        GREEN + "\n15: " + RESET + "Lista dei tirocini che sono giunti a scadenza"
        );
    }
}

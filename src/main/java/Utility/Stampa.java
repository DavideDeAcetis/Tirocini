package Utility;

public class Stampa implements Colors {
    public static void StampaMenu() {
        System.out.println(CYAN + "Inserisci il numero corrispondente all'azione che vuoi eseguire: " + RESET);
        System.out.println("---------------------------------------------------------------- ");
        System.out.println(
                        GREEN + "0\t: " + RESET + "ESCI" +
                        GREEN + "\n1\t: " + RESET + "Registra uno studente." +
                        GREEN + "\n2\t: " + RESET + "Registra un'azienda." +
                        GREEN + "\n3\t: " + RESET + "Inserisci offerta." +
                        GREEN + "\n4\t: " + RESET + "Annulla offerta." +
                        GREEN + "\n5\t: " + RESET + "Ricerca offerte" +
                        GREEN + "\n6\t: " + RESET + "Ricerca offerte con criteri" +
                        GREEN + "\n7\t: " + RESET + "Classifica delle aziende per numero di tirocini" +
                        GREEN + "\n8\t: " + RESET + "Classifica delle aziende per gradimento" +
                        GREEN + "\n9\t: " + RESET + "Classifica dei tutor universitari" +
                        GREEN + "\n10\t: " + RESET + "Attiva tirocinio." +
                        GREEN + "\n11\t: " + RESET + "Chiudi tirocinio." +
                        GREEN + "\n12\t: " + RESET + "Inserisci feedback sul tirocinio e sull'azienda." +
                        GREEN + "\n13\t: " + RESET + "Lista tirocini svolti da uno studente." +
                        GREEN + "\n14\t: " + RESET + "Lista delle aziende con offerte di tirocinio ancora aperte." +
                        GREEN + "\n15\t: " + RESET + "Lista dei tirocini in corso." +
                        GREEN + "\n16\t: " + RESET + "Lista dei tirocini che sono giunti a scadenza"
        );
    }
}

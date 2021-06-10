package Utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stampa {
    public static void StampaMenu() {
        System.out.println(Colors.CYAN + "Inserisci il numero corrispondente all'azione che vuoi eseguire: " + Colors.RESET);
        System.out.println("---------------------------------------------------------------- ");
        System.out.println(
                Colors.GREEN + "0: " + Colors.RESET + "ESCI" +
                        Colors.GREEN + "\n1: " + Colors.RESET + "Registra uno studente." +
                        Colors.GREEN + "\n2: " + Colors.RESET + "Registra un'azienda." +
                        Colors.GREEN + "\n3: " + Colors.RESET + "Inserisci offerta." +
                        Colors.GREEN + "\n4: " + Colors.RESET + "Annulla offerta." +
                        Colors.GREEN + "\n5: " + Colors.RESET + "Ricerca tirocini" +
                        Colors.GREEN + "\n6: " + Colors.RESET + "Ricerca tirocini con criteri" +
                        Colors.GREEN + "\n7: " + Colors.RESET + "Classifica delle aziende" +
                        Colors.GREEN + "\n8: " + Colors.RESET + "Classifica dei tutor universitari" +
                        Colors.GREEN + "\n9: " + Colors.RESET + "Attiva tirocinio." +
                        Colors.GREEN + "\n10: " + Colors.RESET + "Chiudi tirocinio." +
                        Colors.GREEN + "\n11: " + Colors.RESET + "Inserisci feedback sul tirocinio e sull'azienda." +
                        Colors.GREEN + "\n12: " + Colors.RESET + "Lista tirocini svolti da uno studente." +
                        Colors.GREEN + "\n13: " + Colors.RESET + "Lista delle aziende con offerte di tirocinio ancora aperte." +
                        Colors.GREEN + "\n14: " + Colors.RESET + "Lista dei tirocini in corso." +
                        Colors.GREEN + "\n15: " + Colors.RESET + "Lista dei tirocini che sono giunti a scadenza"
        );
    }
}

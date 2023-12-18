package com.cleva.embauche;

import java.io.IOException;

public class EmbaucheCleva {

    public static void main(String[] args) {
        try {
            Carte carte = new Carte("C:\\Outils\\projects\\CLEVATEST\\com\\cleva\\embauche\\ressources\\carte.txt");
           // carte.deplacerPersonnage(6, 9, "OONOOOSSO");
            carte.deplacerPersonnageStream(6, 9, "OONOOOSSO");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

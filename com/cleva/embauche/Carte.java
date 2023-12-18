package com.cleva.embauche;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Carte {
    private char[][] matrice;
    private int nombreLines;
    private int nombreColones;

    public Carte(String filePath) throws IOException {
        chargerCarte(filePath);
    }

    /**
     * Utilisation d'une expression régulière pour vérifier si les directions sont valides (N, S, E, O)
     *
     * @param directions
     * @return
     */
    private boolean isValidDirections(String directions) {
        String pattern = "^[NSOE]+$";
        return Pattern.matches(pattern, directions);
    }

    private void chargerCarte(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        //lecture de dimension de la carte
        String line = reader.readLine();
        nombreColones = line.length();
        nombreLines = 0;

        //lecture du fichier pour determiner le nombre de lignes
        while (line != null) {
            nombreLines++;
            line = reader.readLine();
        }

        // reinitialisation du lecteur pour lire à partir du debut
        reader.close();
        reader = new BufferedReader(new FileReader(filePath));

        // initialisation de la carte
        matrice = new char[nombreLines][nombreColones];
        int currentRow = 0;

        //lecture du fichier pour remplir la carte
        line = reader.readLine();
        while (line != null) {
            for (int i = 0; i < nombreColones; i++) {
                matrice[currentRow][i] = line.charAt(i);
            }
            currentRow++;
            line = reader.readLine();
        }
    }

    /**
     * Version Optimiser
     */
    public void deplacerPersonnageStream(int startX, int startY, String directions) {
        if (isValidDirections(directions)) {
            Coordonnes position = new Coordonnes(startX, startY);

            directions.chars()
                    .mapToObj(direction -> (char) direction)
                    .forEach(direction -> deplacerSelonDirection(position, direction));

            System.out.println("Le personnage se trouve en (" + position.getX() + "," + position.getY() + ")");
        } else {
            System.out.println("Les directions fournies ne sont pas valides.");
            throw new RuntimeException("Les directions fournies ne sont pas valides");
        }
    }

    /**
     * Dans le contexte de ce problème, les valeurs de delta (-1, 0, 1) sont utilisées pour représenter
     * les déplacements dans les différentes directions.
     * Voici comment cela fonctionne : cf case
     *
     * @param position
     * @param direction
     */
    private void deplacerSelonDirection(Coordonnes position, char direction) {
        switch (direction) {
            case 'N':
                /**
                 * Nord (N): Le personnage se déplace vers le haut, donc le déplacement vertical (deltaY) est -1,
                 * et le déplacement horizontal (deltaX) est 0.*/
                deplacerVers(position, 0, -1);
                break;
            case 'S':
                /**
                 * Sud (S): Le personnage se déplace vers le bas, donc le déplacement vertical (deltaY) est 1,
                 * et le déplacement horizontal (deltaX) est 0.*/
                deplacerVers(position, 0, 1);
                break;
            case 'E':
                /**
                 * Est (E): Le personnage se déplace vers la droite, donc le déplacement horizontal (deltaX) est 1,
                 * et le déplacement vertical (deltaY) est 0.
                 */
                deplacerVers(position, 1, 0);
                break;
            case 'O':
                /**
                 * Ouest (O): Le personnage se déplace vers la gauche, donc le déplacement horizontal (deltaX) est -1,
                 * et le déplacement vertical (deltaY) est 0.
                 */
                deplacerVers(position, -1, 0);
                break;
        }
    }

    /**
     * @param coordonnes fournie
     * @param deltaX     changeant selon le point cardinal
     * @param deltaY     changeant selon le point cardinal
     */
    private void deplacerVers(Coordonnes coordonnes, int deltaX, int deltaY) {
        int x = coordonnes.getX() + deltaX;
        int y = coordonnes.getY() + deltaY;

        if (estPositionValide(x, y)) {
            coordonnes.deplacer(deltaX, deltaY);
        }
    }

    /**
     * est utilisée pour vérifier si une position spécifiée sur la carte est valide et
     * vérifie plusieurs conditions pour déterminer si cette position est à l'intérieur des limites de la carte
     * et si la case correspondante est accessible (représentée par un espace ' ' dans la carte).
     *
     * @param x la coordonnée verticale
     * @param y la coordonnée horizontale
     * @return
     */
    private boolean estPositionValide(int x, int y) {
        return x >= 0 && x < nombreColones && y >= 0 && y < nombreLines && matrice[y][x] == ' ';
    }

    /*
    public void deplacerPersonnage(int startX, int startY, String directions) {
        int x = startX;
        int y = startY;
        if (!isValidDirections(directions))
            throw new RuntimeException("Les directions fournies ne sont pas valides");

        for (char direction : directions.toCharArray()) {
            switch (direction) {
                case 'N':
                    if (y - 1 >= 0 && map[y - 1][x] == ' ') {
                        y--;
                    }
                    break;
                case 'S':
                    if (y + 1 < nombreLines && map[y + 1][x] == ' ') {
                        y++;
                    }
                    break;
                case 'E':
                    if (x + 1 < nombreColones && map[y][x + 1] == ' ') {
                        x++;
                    }
                    break;
                case 'O':
                    if (x - 1 >= 0 && map[y][x - 1] == ' ') {
                        x--;
                    }
                    break;
            }
        }
        System.out.println("Le personnage se trouve en (" + x + "," + y + ")");
    }*/
}

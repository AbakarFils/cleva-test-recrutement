package com.cleva.embauche;

public class Coordonnes {
    private int x;
    private int y;

    public Coordonnes(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void deplacer(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}

package Alex.PO2.Organisms;

import Alex.PO2.Komentator;
import Alex.PO2.Swiat;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;


public abstract class Organizm {

    protected Swiat World;
    protected Organizm[][] organizmy;

    protected int sila;
    protected int inicjatywa;
    protected int[] polozenie = new int[2];

    protected char symbol;
    protected String title;
    protected Color COLOR;

    protected int tour_life = 0;
    protected int step = 1;
    protected boolean alive = true;

    public Organizm(Swiat my_world, int x, int y){
        World = my_world;

        this.polozenie[0] = x;
        this.polozenie[1] = y;

        organizmy = World.getOrganizmy();

        COLOR = Color.BLACK;
    }


    public int getSila(){
        return this.sila;
    }
    public int getInicjatywa(){
        return this.inicjatywa;
    }
    public int getTourLife(){
        return this.tour_life;
    }
    public int getStep(){
        return this.step;
    }
    public int[] getPolozenie(){
        return this.polozenie;
    }
    public char getSymbol(){
        return this.symbol;
    }
    public String getTitle(){
        return title;
    }
    public Color getColor(){
        return this.COLOR;
    }


    public void setTourLife(){
        this.tour_life++;
    }
    public void setTourLife(int num){
        this.tour_life = num;
    }
    public void setNewPolozenie(int x, int y){
        this.polozenie[0] = x;
        this.polozenie[1] = y;
    }
    public void setInicjatywa(int num){
        this.inicjatywa = num;
    }
    public void setSila(int sila){
        this.sila = sila;
    }
    public void setTitle(String val){
        this.title = val;
    }
    public void setCzlowiekDirection(int dir){
    }

    public String generateData2Save(){
        String out = this.symbol + " " + this.polozenie[0] + " "
                + this.polozenie[1] + " " + this.sila + " " + this.inicjatywa + " " + this.tour_life;

        return out;
    }

    public abstract void akcja();
    public abstract void akcja(int x, int y);
    public abstract void kolizja(int x, int y);


    protected int getDirection() {
        return (int) (Math.random() * 100 % 4 + 1);
    }
    protected int getRandomDir(int[] seq){
        int zeroes = 0;
        int direction = -1;

        Arrays.sort(seq);
        seq = reverse_array(seq, seq.length);

        for (int k:seq)
            if (k == 0)
                zeroes++;

        if(zeroes != 4){
            Random rand = new Random();
            int ri = rand.nextInt(seq.length-zeroes);

            direction = seq[ri];
        }

        return direction;
    }

    protected int[] getMoveCoord(){
        int direction = getDirection();

        return getMoveCoord(direction);

    }
    protected int[] getMoveCoord(int direction){
        int[] coordinates = new int[2];

        if (direction == 1) { // Right (1,0)
            coordinates = setNewPosition(1, 0, this.step);
        }
        else if (direction == 2) { // Left (-1,0)
            coordinates = setNewPosition(-1, 0, this.step);
        }
        else if (direction == 3) { // UP (0,1)
            coordinates = setNewPosition(0, 1, this.step);
        }
        else if (direction == 4) { // DOWN (0,-1)
            coordinates = setNewPosition(0, -1, this.step);

        }

        return coordinates;
    }

    protected int[] setNewPosition(int x, int y, int distance){
        int[] new_coord = new int[2];

        int d = distance;

        int w_width = World.getSize()[0];
        int w_height = World.getSize()[1];


        if (x == 1 && y == 0) {

            new_coord[0] = this.polozenie[0] + d;
            new_coord[1] = this.polozenie[1];

        }else if (x == -1 && y == 0) {
            new_coord[0] = this.polozenie[0] - d;
            new_coord[1] = this.polozenie[1];
        }
        else if (x == 0 && y == 1) {
            new_coord[0] = this.polozenie[0];
            new_coord[1] = this.polozenie[1] - d;
        }
        else if (x == 0 && y == -1) {
            new_coord[0] = this.polozenie[0];
            new_coord[1] = this.polozenie[1] + d;
        }


        if (new_coord[0] >= w_width) {
            new_coord[0] -= 2 * d;
        }
        if (new_coord[0] < 0) {
            new_coord[0] += 2 * d;
        }
        if (new_coord[1] >= w_height) {
            new_coord[1] -= 2 * d;
        }
        if (new_coord[1] < 0) {
            new_coord[1] += 2 * d;
        }

        return new_coord;
    }

    protected int[] findFreePlace(){
        //Organizm[][] organizmy = World.getOrganizmy();

        int tX, tY, step, w_w, w_h;

        int[] dir = new int[4];

        for (int i = 0; i < 4; i++) {
            dir[i] = 0;
        }


        tX = getPolozenie()[0];
        tY = getPolozenie()[1];

        step = this.step;

        w_w = World.getSize()[0];
        w_h = World.getSize()[1];


        if (((tY - step) >= 0) && (organizmy[tY - step][tX] == null)) {
            dir[0] = 3;
        }

        if (((tY + step) < w_h) && (organizmy[tY + step][tX] == null)) {
            dir[1] = 4;
        }

        if (((tX - step) >= 0) && (organizmy[tY][tX - step] == null)) {
            dir[2] = 2;
        }

        if (((tX + step) < w_w) && (organizmy[tY][tX + step] == null)) {
            dir[3] = 1;
        }

//					        3	4	  2		1
        return dir; // dir (UP, DOWN, LEFT, RIGHT)
    }

    protected void rozmnozSie() {
        int[] mozliwosci = findFreePlace();

        int direction = getRandomDir(mozliwosci);


        if (direction != -1) {
            int[] coordinates = getMoveCoord(direction);

            int X = coordinates[0];
            int Y = coordinates[1];

            Komentator.newRecord(this.title + " rozmnożył się! Gratulacje!");
            organizmy[Y][X] = createNewOrganizm(X, Y);

        }
    }

    protected boolean checkOrganizm(Organizm o1, Organizm o2){
        if (o1 != null && o2 != null){
            return o1.getClass() == o2.getClass();
        }
        
        return false;

    }

    protected abstract boolean czyOdbilAtak(Organizm atakujacy);
    protected abstract Organizm createNewOrganizm(int x, int y);


    private static int[] reverse_array(int a[], int n)
    {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        return b;
    }


}

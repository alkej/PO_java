package Alex.PO2;//import com.sun.org.apache.xpath.internal.operations.Or;

import Alex.PO2.Organisms.Animals.*;
import Alex.PO2.Organisms.Organizm;
import Alex.PO2.Organisms.Plants.*;
import Alex.PO2.Utils.OrganizmComparator;
import Alex.PO2.Utils.OrganizmyList;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;

public class Swiat {

    private String localDir = System.getProperty("user.dir");

    private int width, height;

    private Organizm[][] moje_organizmy;
    private PriorityQueue<Organizm> kolejkaRuchu;
    private Czlowiek Sasha;

    private int tourNumber = 0;
    private boolean gameOver = false;

    public Swiat(int width, int height){

        this.width = width;
        this.height = height;

        moje_organizmy = new Organizm[height][width];
        kolejkaRuchu = new PriorityQueue<>(new OrganizmComparator());

        dodajNaPoczatek();

        Sasha = new Czlowiek(this, width/2, height/2);
        Sasha.setTitle("Sasha");
        moje_organizmy[height/2][width/2] = Sasha;


    }
    public Swiat (String path){

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {

            this.width = Integer.parseInt(reader.readLine());
            this.height = Integer.parseInt(reader.readLine());
            this.tourNumber = Integer.parseInt(reader.readLine());

            moje_organizmy = new Organizm[height][width];
            kolejkaRuchu = new PriorityQueue<>(new OrganizmComparator());


            String line;
            while((line = reader.readLine()) != null){

                String[] data = line.split(" ");

                String symbol = data[0];
                int X = Integer.parseInt(data[1]);
                int Y = Integer.parseInt(data[2]);
                int sila = Integer.parseInt(data[3]);
                int inicjatywa = Integer.parseInt(data[4]);
                int life_tour = Integer.parseInt(data[5]);

                if (symbol.equals("C")){
                    Sasha = new Czlowiek(this, X, Y);
                    Sasha.setSila(sila);
                    Sasha.setInicjatywa(inicjatywa);
                    Sasha.setTourLife(life_tour);
                    int special_val = Integer.parseInt(data[6]);
                    int step_val = Integer.parseInt(data[7]);
                    Sasha.setSpecial(special_val);
                    Sasha.setStep(step_val);
                    moje_organizmy[Y][X] = Sasha;

                }else{
                    initOrganizmy(X, Y,OrganizmyList.valueOf(symbol).getNumber());
                    moje_organizmy[Y][X].setSila(sila);
                    moje_organizmy[Y][X].setInicjatywa(inicjatywa);
                    moje_organizmy[Y][X].setTourLife(life_tour);
                }

            }
        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + path);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Buffer error: " + path);
            ex.printStackTrace();
        }
    }

    public Organizm[][] getOrganizmy(){
        return moje_organizmy;
    }
    public int[] getSize(){
        int[] size = new int[2];
        size[0] = width;
        size[1] = height;
        return size;
    }
    public int getTourNumber(){
        return tourNumber;
    }
    public boolean getGameOver(){
        return gameOver;
    }

    public void setCzlowiekDirection_world(int dir){
        if (Sasha != null){
            Sasha.setCzlowiekDirection(dir);
        }
    }

    public boolean wykonajTure(){
        if (!gameOver) {
            if (tourNumber > 0) {
                Komentator.newRecord("Zaczeła się tura Nr. " + tourNumber);
            }

            while (kolejkaRuchu.size() > 0) {
                Organizm organizm = kolejkaRuchu.remove();
                if (moje_organizmy[organizm.getPolozenie()[1]][organizm.getPolozenie()[0]] != null) {
                    organizm.setTourLife();
                    organizm.akcja();
                }
            }
            tourNumber++;
            updateQueue();
        }
        return !gameOver;
    }
    public void initOrganizmy(int X, int Y, int num){
        if (num == OrganizmyList.Zolw.getNumber()){
            moje_organizmy[Y][X] = new Zolw(this, X, Y);
        }else if (num == OrganizmyList.Wilk.getNumber()){
            moje_organizmy[Y][X] = new Wilk(this, X, Y);
        }else if (num == OrganizmyList.Wilcze_jagody.getNumber()){
            moje_organizmy[Y][X] = new Wilcze_jagody(this, X, Y);
        }else if (num == OrganizmyList.Trawa.getNumber()){
            moje_organizmy[Y][X] = new Trawa(this, X, Y);
        }else if (num == OrganizmyList.Owca.getNumber()){
            moje_organizmy[Y][X] = new Owca(this, X, Y);
        }else if (num == OrganizmyList.Mlecz.getNumber()){
            moje_organizmy[Y][X] = new Mlecz(this, X, Y);
        }else if (num == OrganizmyList.Guarana.getNumber()){
            moje_organizmy[Y][X] = new Guarana(this, X, Y);
        }else if (num == OrganizmyList.Barszcz.getNumber()){
            moje_organizmy[Y][X] = new Barszcz(this, X, Y);
        }else if (num == OrganizmyList.Antylopa.getNumber()){
            moje_organizmy[Y][X] = new Antylopa(this, X, Y);
        }else if (num == OrganizmyList.Lis.getNumber()){
            moje_organizmy[Y][X] = new Lis(this, X, Y);
        }
    }
    public void zabijOrganizm(int x, int y){
        if (moje_organizmy[y][x] instanceof Czlowiek){
            Sasha = null;
            gameOver = true;
            Komentator.newRecord("GAME OVER!");
        }
        kolejkaRuchu.remove(moje_organizmy[y][x]);
        moje_organizmy[y][x] = null;

    }
    public boolean tryToActivateSpecial(){
        boolean result = false;
        if (Sasha != null)
            result = Sasha.activateUmejetnosc();
        return result;
    }

    public boolean SaveGame(){
        String path = localDir + "/saves/";

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String game_name = dateFormat.format(date).
                replace(" ", "_").
                replace(":", "_").
                replace("/", "_");

        String game_path = path + game_name + ".txt";

        try(PrintWriter writer = new PrintWriter(game_path)){

            writer.println(this.width);
            writer.println(this.height);
            writer.println(this.tourNumber);

            for (int i = 0; i < height; i++) {
                for (int k = 0; k < width; k++) {
                    if (moje_organizmy[i][k] != null){
                        writer.println(moje_organizmy[i][k].generateData2Save());
                    }
                }
            }

        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + game_path);
            ex.printStackTrace();
            return false;
        }


        String games_info_path = path + "games.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(games_info_path, true))){
            writer.write(game_name);
            writer.newLine();

        }catch (FileNotFoundException ex){
            System.out.println("Not Found: " + games_info_path + "games.txt");
            ex.printStackTrace();
            return false;
        }catch (IOException ex){
            System.out.println("Error with BufferedWriter");
            ex.printStackTrace();
            return false;
        }


        return true;
    }




    private void dodajNaPoczatek(){
        int rX = 0, rY = 0;
        boolean running;

        for (int i = 1; i < 10 + width*height/10; i++) {
            Random rand = new Random();

            running = true;
            while (running){
                rX = rand.nextInt(width);
                rY = rand.nextInt(height);

                if(moje_organizmy[rY][rX] == null)
                    running = false;
            }
//            moje_organizmy[rY][rX] =
//                    OrganizmyList.createOrganizm(OrganizmyList.valueOf(OrganizmyList.getStringByValue(i%(10+1))),
//                    this, rX, rY);
            initOrganizmy(rX, rY, i%(10+1));
        }

    }

    private void updateQueue(){
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if (moje_organizmy[i][k] != null){
                    kolejkaRuchu.add(moje_organizmy[i][k]);
                }
            }
        }
    }

    private void rysujSwiat(){
        for (int i = 0; i < height; i++){
            for (int k = 0; k < width; k++){
                if (moje_organizmy[i][k] != null){
                    System.out.print(moje_organizmy[i][k].getSymbol());
                }else{
                    System.out.print("+");
                }
            }
            System.out.println();
        }
        System.out.println("\n----------------------------------------\n");

    }

}


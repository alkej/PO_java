package Alex.PO2.Organisms.Animals;

import Alex.PO2.Swiat;

import java.awt.*;

public class Czlowiek extends Zwierze {

    public Czlowiek(Swiat my_world, int x, int y){
        super(my_world, x, y);

        this.sila = 5;
        this.inicjatywa = 4;

        this.symbol = 'C';

        this.COLOR = Color.CYAN;
    }

    public void setCzlowiekDirection(int dir){
        this.direction = dir;
    }

    @Override
    public void akcja() {
        int direction = this.direction;
        if (direction != 0){
            checkSpecial();

            int[] coordinates = getMoveCoord(direction);

            this.direction = 0; //set direction to def value;

            int X = coordinates[0];
            int Y = coordinates[1];

            super.akcja(X, Y);
        }


    }

    public boolean activateUmejetnosc(){
        if (this.special == 0){
            this.special = 6;
            return true;
        }
        return false;
    }

    public int getSpecialVal(){
        return this.special;
    }

    public void setSpecial(int special){
        this.special = special;
    }

    public void setStep(int step){
        this.step = step;
    }


    public String generateData2Save(){
        String out = super.generateData2Save() +  " " + this.special + " " + this.step;

        return out;
    }

    private void endSpecial(){
        this.step = 1;
        this.special = -1;
    }

    private void checkSpecial(){
        if (this.special > 0) {
            if (this.special > 3) {
                this.step = 2;
            } else {
                int my_rn = (int)(Math.random() * 100);

                if (my_rn >= 50) {
                    this.step = 2;
                } else {
                    this.step = 1;
                }

            }

            this.special--;

            if (this.special == 1) {
                endSpecial();
            }

        }

        if (this.special < 0) {
            this.special--;
        }
        if (this.special == -6) {
            this.special = 0;
        }
    }







    private int direction;
    private int special;


}

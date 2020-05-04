/* Ocean.java */

import sun.security.provider.SHA;

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

    /**
     *  Do not rename these constants.  WARNING:  if you change the numbers, you
     *  will need to recompile Test4.java.  Failure to do so will give you a very
     *  hard-to-find bug.
     */

    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

    /**
     *  Define any variables associated with an Ocean object here.  These
     *  variables MUST be private.
     */
    private int width;
    private int height;
    private int starveTime;

    private Animal[][] ocean;


    /**
     *  The following methods are required for Part I.
     */

    /**
     *  Ocean() is a constructor that creates an empty ocean having width i and
     *  height j, in which sharks starve after starveTime timesteps.
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     */

    public Ocean(int i, int j, int starveTime) {
        // Your solution here.
        this.width = i;
        this.height = j;
        this.starveTime = starveTime;

        this.ocean = new Animal[this.height][this.width];
        for(int p = 0; p < this.height; p++) {
            for(int q = 0; q < this.width; q++) {
                this.ocean[p][q] = new Animal(EMPTY, 0);
            }
        }
    }

    /**
     *  width() returns the width of an Ocean object.
     *  @return the width of the ocean.
     */

    public int width() {
        // Replace the following line with your solution.
        return this.width;
    }

    /**
     *  height() returns the height of an Ocean object.
     *  @return the height of the ocean.
     */

    public int height() {
        // Replace the following line with your solution.
        return this.height;
    }

    /**
     *  starveTime() returns the number of timesteps sharks survive without food.
     *  @return the number of timesteps sharks survive without food.
     */

    public int starveTime() {
        // Replace the following line with your solution.
        return this.starveTime;
    }

    /**
     *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a fish in.
     *  @param y is the y-coordinate of the cell to place a fish in.
     */

    public void addFish(int x, int y) {
        // Your solution here.
        ocean[y][x].setKind(FISH);
        ocean[y][x].setLifeTime(this.starveTime());
    }

    /**
     *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
     *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */

    public void addShark(int x, int y) {
        // Your solution here.
        ocean[y][x].setKind(SHARK);
        ocean[y][x].setLifeTime(this.starveTime());
    }

    /**
     *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
     *  a fish, and SHARK if it contains a shark.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */

    public int cellContents(int x, int y) {
        // Replace the following line with your solution.
        return ocean[y][x].getKind();
    }

    /**
     *  timeStep() performs a simulation timestep as described in README.
     *  @return an ocean representing the elapse of one timestep.
     */

    public int aroundFish(int x, int y, Animal[][] transferedOcean) { // x is width; y is height
        x++;
        y++;
        int up = y - 1;
        int bottom = y + 1;
        int left = x - 1;
        int right = x + 1;
        int numOfFish = 0;
//        System.out.println(up + " "+ bottom + ' ' + left + " " + right);
        for(int i = left; i <= right; i++) {
            for(int j = up; j <= bottom; j++) {

//                System.out.print(transferedOcean[i][j].getKind() + " ");
                if(transferedOcean[i][j].getKind() == FISH) {
                    numOfFish++;
                }
            }
//            System.out.println();
        }

        return numOfFish;
    }

    public int aroundShark(int x, int y, Animal[][] transferedOcean) { // x is width; y is height
        x++;
        y++;
        int up = y - 1;
        int bottom = y + 1;
        int left = x - 1;
        int right = x + 1;
        int numOfShark = 0;
        for(int p = left; p <= right; p++) {
            for(int q = up; q <= bottom; q++) {
                if(transferedOcean[p][q].getKind() == SHARK) {
                    numOfShark++;
                }
            }
        }

        return numOfShark;
    }

    public Ocean timeStep() {
        // Replace the following line with your solution.
        Ocean newOcean = new Ocean(this.width(), this.height(), starveTime);
        Animal[][] tmp = new Animal[height()+2][width()+2];
        System.out.println(width() + " " + height());
        for(int i = 1; i <= height(); i++) {
            for (int j = 1; j <= width(); j++) {
                //System.out.println((i-1) + " " + (j-1));
                tmp[i][j] = this.ocean[i - 1][j - 1];
            }
        }
        for(int i = 1; i <= width(); i++) {
            tmp[0][i] = this.ocean[height()-1][i-1];
            tmp[height()+1][i] = this.ocean[0][i-1];
        }
        for(int i = 1; i <= height(); i++) {
            tmp[i][0] = this.ocean[i-1][width()-1];
            tmp[i][width()+1] = this.ocean[i-1][0];
        }
        tmp[0][0] = this.ocean[height()-1][width()-1];
        tmp[0][width()+1] = this.ocean[height()-1][0];
        tmp[height()+1][0] = this.ocean[0][width()-1];
        tmp[height()+1][width()+1] = this.ocean[0][0];

//        tmp[0][0] = new Animal();
//        tmp[0][width()+1] = new Animal();
//        tmp[height()+1][0] = new Animal();
//        tmp[height()+1][width()+1] = new Animal();

//        for(int i = 1; i <= width(); i++){
//
//            this.ocean[0][i].setKind(this.ocean[height][i].getKind());
//        }
//        for(int i = 1; i <= height(); i++) {
//            this.ocean[i][0] = this.ocean[i][width()];
//        }
//        this.ocean[0][0] = this.ocean[width()][height()];
//        this.ocean[0][width()+1] = this.ocean[height()][1];
//
//        this.ocean[height()+1][0] = this.ocean[1][width()];
//        this.ocean[height()+1][width()+1] = this.ocean[1][1];





        for(int p = 0; p < height(); p++) {
            for(int q = 0; q < width(); q++) {
                int numOfFish = aroundFish(p, q, tmp);
                int numOfShark = aroundShark(p, q, tmp);
                //System.out.println(numOfFish + ' ' + numOfShark);

                if(this.ocean[p][q].getKind() == FISH) {
                    numOfFish--;
                    if(numOfFish >= 0 && numOfShark == 0) {
                        if(this.ocean[p][q].getLifeTime() == 0){
                            newOcean.ocean[p][q].setKind(EMPTY);
                            newOcean.ocean[p][q].setLifeTime(0);
                        } else {
                            System.out.println("Reserve this one!!!!! " + p + " " + q + tmp[p+1][q+1].getKind() + " " +tmp[p+1][q+1].getLifeTime());

                            newOcean.ocean[p][q].setKind(tmp[p+1][q+1].getKind());
                            newOcean.ocean[p][q].setLifeTime(tmp[p+1][q+1].getLifeTime()-1);
                        }
                    }
                    if(numOfShark == 1) {
                        newOcean.ocean[p][q].setKind(EMPTY);
                        newOcean.ocean[p][q].setLifeTime(0);
                    }
                    if(numOfShark > 1) {
                        newOcean.ocean[p][q].setKind(SHARK);
                        newOcean.ocean[p][q].setLifeTime(starveTime());
                    }
                    System.out.print(newOcean.ocean[p][q].getLifeTime());
                }
                if(this.ocean[p][q].getKind() == EMPTY) {
                    if(numOfFish >= 2 && numOfShark <= 1) {
                        newOcean.ocean[p][q].setKind(FISH);
                        newOcean.ocean[p][q].setLifeTime(starveTime());
                    }
                    if(numOfFish >= 2 && numOfShark >= 2) {
                        newOcean.ocean[p][q].setKind(SHARK);
                        newOcean.ocean[p][q].setLifeTime(starveTime());
                    }
                    if(numOfFish < 2) {
                        newOcean.ocean[p][q].setKind(EMPTY);
                        newOcean.ocean[p][q].setLifeTime(0);
                    }
                    System.out.print(newOcean.ocean[p][q].getLifeTime());
                }
                if(this.cellContents(q, p) == SHARK) {
                    numOfShark--;
                    if(numOfShark == 0 && numOfFish == 0){
                        if(this.ocean[p][q].getLifeTime() == 0) {
                            newOcean.ocean[p][q].setKind(EMPTY);
                            newOcean.ocean[p][q].setLifeTime(0);
                        } else {
                            System.out.println("Reserve this one!!!!! " + p + " " + q);
                            newOcean.ocean[p][q].setKind(this.ocean[p][q].getKind());
                            newOcean.ocean[p][q].setLifeTime(this.ocean[p][q].getLifeTime()-1);
                        }
                    }
                    for (int i = p+1-1; i <= p+1+1; i++) {
                        for(int j = q+1-1; j <= q+ 1+1; j++) {
                            if(tmp[i][j].getKind() == FISH) {
                                if(i == 0 && j != 0 && j != (width()+1)) {
                                    System.out.println("It is eaten" + (height()-1) + " " + (j-1));
                                    newOcean.ocean[height()-1][j-1].setKind(EMPTY);
                                    newOcean.ocean[height()-1][j-1].setLifeTime(0);
                                }
                                if(i == (height()+1) && j != 0 && j != (width()+1)) {
                                    System.out.println("It is eaten" + 0 + " " + (j-1));
                                    newOcean.ocean[0][j-1].setKind(EMPTY);
                                    newOcean.ocean[0][j-1].setLifeTime(0);
                                }
                                if(j == 0 && i != 0 && i != (height()+1)) {
                                    System.out.println("It is eaten" + (i-1) + " " + (width()-1));
                                    newOcean.ocean[i-1][width()-1].setKind(EMPTY);
                                    newOcean.ocean[i-1][width()-1].setLifeTime(0);
                                }
                                if(j == (width()+1) && i != 0 && i != (height()+1)) {
                                    System.out.println("It is eaten" + (i-1) + " " + 0);
                                    newOcean.ocean[i-1][0].setKind(EMPTY);
                                    newOcean.ocean[i-1][0].setLifeTime(0);
                                }
                                if(i == 0 && j == 0) {
                                    System.out.println("It is eaten" + (width()-1) + " " + (height()-1));
                                    newOcean.ocean[width()-1][height()-1].setKind(EMPTY);
                                    newOcean.ocean[width()-1][height()-1].setLifeTime(0);
                                }
                                if(i == 0 && j == width()+1) {
                                    System.out.println("It is eaten" + (height()-1) + " " + 0);
                                    newOcean.ocean[height()-1][0].setKind(EMPTY);
                                    newOcean.ocean[height()-1][0].setLifeTime(0);
                                }
                                if(i == height()+1 && j == 0) {
                                    System.out.println("It is eaten" + 0 + " " + (width()-1));
                                    newOcean.ocean[0][width()-1].setKind(EMPTY);
                                    newOcean.ocean[0][width()-1].setLifeTime(0);
                                }
                                if(i == height()+1 && j == width()+1) {
                                    System.out.println("It is eaten" + 0 + " " + 0);
                                    newOcean.ocean[0][0].setKind(EMPTY);
                                    newOcean.ocean[0][0].setLifeTime(0);
                                }
                                newOcean.ocean[p][q].setKind(SHARK);
                                newOcean.ocean[p][q].setLifeTime(starveTime());
                            }
                        }
                    }
                    System.out.print(newOcean.ocean[p][q].getLifeTime() );
                }
            }
        }
        return newOcean;
    }

    /**
     *  The following method is required for Part II.
     */

    /**
     *  addShark() (with three parameters) places a shark in cell (x, y) if the
     *  cell is empty.  The shark's hunger is represented by the third parameter.
     *  If the cell is already occupied, leave the cell as it is.  You will need
     *  this method to help convert run-length encodings to Oceans.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     *  @param feeding is an integer that indicates the shark's hunger.  You may
     *         encode it any way you want; for instance, "feeding" may be the
     *         last timestep the shark was fed, or the amount of time that has
     *         passed since the shark was last fed, or the amount of time left
     *         before the shark will starve.  It's up to you, but be consistent.
     */

    public void addShark(int x, int y, int feeding) {
        // Your solution here.
    }

    /**
     *  The following method is required for Part III.
     */

    /**
     *  sharkFeeding() returns an integer that indicates the hunger of the shark
     *  in cell (x, y), using the same "feeding" representation as the parameter
     *  to addShark() described above.  If cell (x, y) does not contain a shark,
     *  then its return value is undefined--that is, anything you want.
     *  Normally, this method should not be called if cell (x, y) does not
     *  contain a shark.  You will need this method to help convert Oceans to
     *  run-length encodings.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */

    public int sharkFeeding(int x, int y) {
        // Replace the following line with your solution.
        return 0;
    }

}
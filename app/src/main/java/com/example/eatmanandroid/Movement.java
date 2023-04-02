package com.example.eatmanandroid;

import android.content.Intent;
import android.content.Context;


public class Movement {
    //private instance vars
    private Eatman eatman;

    private Mouse mouse0;
    private Mouse mouse1;
    private Mouse mouse2;
    private Mouse mouse3;

    private int blockSize;
    private short [][] currentMap;
    private int swipeDir;
    private boolean pelletEaten;

    public Movement(final short [][] curMap, final int blockSize){
        currentMap = curMap;
        this.blockSize = blockSize;
        eatman = new Eatman(blockSize);
        mouse0 = new Mouse(blockSize);
        mouse1 = new Mouse(blockSize);
        mouse2 = new Mouse(blockSize);
        mouse3 = new Mouse(blockSize);

        swipeDir = 4;
        pelletEaten = false;
    }

    //public getters
    public Eatman getEatman(){ return eatman; }
    public Mouse getMouse0(){ return mouse0; }
    public Mouse getMouse1(){ return mouse1; }
    public Mouse getMouse2(){ return mouse2; }
    public Mouse getMouse3(){ return mouse3; }
    public int getSwipeDir(){ return swipeDir; }


    //checks to see if game over happens
    public void checkPlayerDeath() throws PlayerDeathException{
        if(((mouse0.getXPos()/blockSize) == (eatman.getXPos()/blockSize))&&
                ((mouse0.getYPos()/blockSize) == (eatman.getYPos()/blockSize)) ||
                ((mouse1.getXPos()/blockSize) == (eatman.getXPos()/blockSize)) &&
                        ((mouse1.getYPos()/blockSize) == (eatman.getYPos()/blockSize)) ||
                ((mouse2.getXPos()/blockSize) == (eatman.getXPos()/blockSize)) &&
                        ((mouse2.getYPos()/blockSize) == (eatman.getYPos()/blockSize)) ||
                ((mouse3.getXPos()/blockSize) == (eatman.getXPos()/blockSize)) &&
                        ((mouse3.getYPos()/blockSize) == (eatman.getYPos()/blockSize))){
            throw new PlayerDeathException("Eatman and Mouse collision");
        }
    }

    //METHODS FOR PELLET REMOVAL
    //returns the updated map with the pellet removed
    public short[][] updateMap(){
        pelletEaten = false;
        return currentMap;
    }
    //to see if we need to update the map if a pellet was removed
    public boolean needMapRefresh(){ return pelletEaten; }
    //private method to change the map when the pellet is eaten
    private void pelletWasEaten(int x, int y, short value){
        currentMap[x][y] = value;
        pelletEaten = true;
    }

    //move eatman
    public void moveEatman(){
        short ch;
        int nextDirection = eatman.getNextDir();
        int xPosEatman = eatman.getXPos();
        int yPosEatman = eatman.getYPos();

        // This was based on the non-Android Eatman legacy project for CS56
        // Check if xPos and yPos of eatman is both a multiple of block size
        if ( (xPosEatman % blockSize == 0) && (yPosEatman  % blockSize == 0) ) {

            // When eatman goes through tunnel on
            // the right reappear at left tunnel
            if (xPosEatman >= blockSize * 17) {
                xPosEatman= 0;
                eatman.setXPos(0);
            }

            // Is used to find the number in the level array in order to
            // check wall placement, pellet placement, and candy placement
            ch = currentMap[yPosEatman / blockSize][xPosEatman / blockSize];

            // If there is a pellet, eat it
            if ((ch & 16) != 0) {

                // Toggle pellet so it won't be drawn anymore
                pelletWasEaten(yPosEatman / blockSize, xPosEatman / blockSize, (short) (ch ^ 16));
            }

            // Checks for direction buffering
            if (!((nextDirection == 3 && (ch & 1) != 0) ||
                    (nextDirection == 1 && (ch & 4) != 0) ||
                    (nextDirection == 0 && (ch & 2) != 0) ||
                    (nextDirection == 2 && (ch & 8) != 0))) {
                eatman.setCurDir(nextDirection);
                swipeDir = nextDirection;
            }

            // Checks for wall collisions
            if ((swipeDir == 3 && (ch & 1) != 0) ||
                    (swipeDir == 1 && (ch & 4) != 0) ||
                    (swipeDir == 0 && (ch & 2) != 0) ||
                    (swipeDir == 2 && (ch & 8) != 0)) {
                swipeDir = 4;
            }
        }

        // When eatman goes through tunnel on
        // the left reappear at right tunnel
        if (xPosEatman < 0) {
            xPosEatman = blockSize * 17;
            eatman.setXPos(blockSize * 17);
        }
    }

    //call method after we have moved and drawn eatman
    public void updateEatman(){
        // Depending on the direction move the position of eatman
        if (swipeDir == 0) {
            eatman.setYPos(eatman.getYPos() + -blockSize/15);
        } else if (swipeDir == 1) {
            eatman.setXPos(eatman.getXPos() + blockSize/15);
        } else if (swipeDir == 2) {
            eatman.setYPos(eatman.getYPos() + blockSize/15);
        } else if (swipeDir == 3) {
            eatman.setXPos(eatman.getXPos() + -blockSize/15);
        }
    }


    //move mouse
    public void moveMouse0() {
        short ch;
        int mouseDir = mouse0.getDir();
        int xPosMouse = mouse0.getXPos();
        int yPosMouse = mouse0.getYPos();
        int xDis = eatman.getXPos() - xPosMouse;
        int yDis = eatman.getYPos() - yPosMouse;


        if ((xPosMouse % blockSize == 0) && (yPosMouse % blockSize == 0)) {
            ch = currentMap[yPosMouse / blockSize][xPosMouse / blockSize];

            if (xPosMouse >= blockSize * 17) {
                xPosMouse = 0;
                mouse0.setXPos(0);
            }
            if (xPosMouse < 0) {
                xPosMouse = blockSize * 17;
                mouse0.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 1;
                        mouse0.setDir(1);
                    } else {
                        mouseDir = 2;
                        mouse0.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    mouseDir = 1;
                    mouse0.setDir(1);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse0.setDir(2);
                } else {
                    mouseDir = 3;
                    mouse0.setDir(3);
                }
            }


            // Move mouse right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            mouseDir = 1;
                            mouse0.setDir(1);
                        } else {
                            mouseDir = 0;
                            mouse0.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        mouseDir = 1;
                        mouse0.setDir(1);
                    } else if ((ch & 2) == 0) {
                        mouseDir = 0;
                        mouse0.setDir(0);
                    } else {
                        mouseDir = 2;
                        mouse0.setDir(2);
                    }
                }
            }


            // Move mouse left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse0.setDir(3);
                    } else {
                        mouseDir = 2;
                        mouse0.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse0.setDir(3);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse0.setDir(2);
                } else {
                    mouseDir = 1;
                    mouse0.setDir(1);
                }
            }


            // Move mouse left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse0.setDir(3);
                    } else {
                        mouseDir = 0;
                        mouse0.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse0.setDir(3);
                } else if ((ch & 2) == 0) {
                    mouseDir = 0;
                    mouse0.setDir(0);
                } else {
                    mouseDir = 2;
                    mouse0.setDir(2);
                }
            }


            // Handles wall collisions
            if ((mouseDir == 3 && (ch & 1) != 0) ||
                    (mouseDir == 1 && (ch & 4) != 0) ||
                    (mouseDir == 0 && (ch & 2) != 0) ||
                    (mouseDir == 2 && (ch & 8) != 0)) {
                mouseDir = 4;
                mouse0.setDir(4);
            }
        }



        if (mouse0.getDir() == 0) {
            mouse0.setYPos(mouse0.getYPos() + -blockSize/20);
        } else if (mouse0.getDir() == 1) {
            mouse0.setXPos(mouse0.getXPos() + blockSize/20);
        } else if (mouse0.getDir() == 2) {
            mouse0.setYPos(mouse0.getYPos() + blockSize/20);
        } else if (mouse0.getDir() == 3) {
            mouse0.setXPos(mouse0.getXPos() + -blockSize/20);
        }
    }

    public void moveMouse1() {
        short ch;
        int mouseDir = mouse1.getDir();
        int xPosMouse = mouse1.getXPos();
        int yPosMouse = mouse1.getYPos();
        int xDis = eatman.getXPos() - xPosMouse;
        int yDis = eatman.getYPos() - yPosMouse;


        if ((xPosMouse % blockSize == 0) && (yPosMouse % blockSize == 0)) {
            ch = currentMap[yPosMouse / blockSize][xPosMouse / blockSize];

            if (xPosMouse >= blockSize * 17) {
                xPosMouse = 0;
                mouse1.setXPos(0);
            }
            if (xPosMouse < 0) {
                xPosMouse = blockSize * 17;
                mouse1.setXPos(blockSize * 17);
            }


            // Move mouse right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 1;
                        mouse1.setDir(1);
                    } else {
                        mouseDir = 2;
                        mouse1.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    mouseDir = 1;
                    mouse1.setDir(1);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse1.setDir(2);
                } else {
                    mouseDir = 3;
                    mouse1.setDir(3);
                }
            }


            // Move mouse right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            mouseDir = 1;
                            mouse1.setDir(1);
                        } else {
                            mouseDir = 0;
                            mouse1.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        mouseDir = 1;
                        mouse1.setDir(1);
                    } else if ((ch & 2) == 0) {
                        mouseDir = 0;
                        mouse1.setDir(0);
                    } else {
                        mouseDir = 2;
                        mouse1.setDir(2);
                    }
                }
            }


            // Move mouse left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse1.setDir(3);
                    } else {
                        mouseDir = 2;
                        mouse1.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse1.setDir(3);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse1.setDir(2);
                } else {
                    mouseDir = 1;
                    mouse1.setDir(1);
                }
            }


            // Move mouse left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse1.setDir(3);
                    } else {
                        mouseDir = 0;
                        mouse1.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse1.setDir(3);
                } else if ((ch & 2) == 0) {
                    mouseDir = 0;
                    mouse1.setDir(0);
                } else {
                    mouseDir = 2;
                    mouse1.setDir(2);
                }
            }


            // Handles wall collisions
            if ((mouseDir == 3 && (ch & 1) != 0) ||
                    (mouseDir == 1 && (ch & 4) != 0) ||
                    (mouseDir == 0 && (ch & 2) != 0) ||
                    (mouseDir == 2 && (ch & 8) != 0)) {
                mouseDir = 4;
                mouse1.setDir(4);
            }
        }



        if (mouse1.getDir() == 0) {
            mouse1.setYPos(mouse1.getYPos() + -blockSize/20);
        } else if (mouse1.getDir() == 1) {
            mouse1.setXPos(mouse1.getXPos() + blockSize/20);
        } else if (mouse1.getDir() == 2) {
            mouse1.setYPos(mouse1.getYPos() + blockSize/20);
        } else if (mouse1.getDir() == 3) {
            mouse1.setXPos(mouse1.getXPos() + -blockSize/20);
        }
    }

    public void moveMouse2() {
        short ch;
        int mouseDir = mouse2.getDir();
        int xPosMouse = mouse2.getXPos();
        int yPosMouse = mouse2.getYPos();
        int xDis = eatman.getXPos() - xPosMouse;
        int yDis = eatman.getYPos() - yPosMouse;


        if ((xPosMouse % blockSize == 0) && (yPosMouse % blockSize == 0)) {
            ch = currentMap[yPosMouse / blockSize][xPosMouse / blockSize];

            if (xPosMouse >= blockSize * 17) {
                xPosMouse = 0;
                mouse2.setXPos(0);
            }
            if (xPosMouse < 0) {
                xPosMouse = blockSize * 17;
                mouse2.setXPos(blockSize * 17);
            }


            // Move mouse right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 1;
                        mouse2.setDir(1);
                    } else {
                        mouseDir = 2;
                        mouse2.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    mouseDir = 1;
                    mouse2.setDir(1);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse2.setDir(2);
                } else {
                    mouseDir = 3;
                    mouse2.setDir(3);
                }
            }


            // Move mouse right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            mouseDir = 1;
                            mouse2.setDir(1);
                        } else {
                            mouseDir = 0;
                            mouse2.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        mouseDir = 1;
                        mouse2.setDir(1);
                    } else if ((ch & 2) == 0) {
                        mouseDir = 0;
                        mouse2.setDir(0);
                    } else {
                        mouseDir = 2;
                        mouse2.setDir(2);
                    }
                }
            }


            // Move mouse left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse2.setDir(3);
                    } else {
                        mouseDir = 2;
                        mouse2.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse2.setDir(3);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse2.setDir(2);
                } else {
                    mouseDir = 1;
                    mouse2.setDir(1);
                }
            }


            // Move mouse left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse2.setDir(3);
                    } else {
                        mouseDir = 0;
                        mouse2.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse2.setDir(3);
                } else if ((ch & 2) == 0) {
                    mouseDir = 0;
                    mouse2.setDir(0);
                } else {
                    mouseDir = 2;
                    mouse2.setDir(2);
                }
            }


            // Handles wall collisions
            if ((mouseDir == 3 && (ch & 1) != 0) ||
                    (mouseDir == 1 && (ch & 4) != 0) ||
                    (mouseDir == 0 && (ch & 2) != 0) ||
                    (mouseDir == 2 && (ch & 8) != 0)) {
                mouseDir = 4;
                mouse2.setDir(4);
            }
        }



        if (mouse2.getDir() == 0) {
            mouse2.setYPos(mouse2.getYPos() + -blockSize/20);
        } else if (mouse2.getDir() == 1) {
            mouse2.setXPos(mouse2.getXPos() + blockSize/20);
        } else if (mouse2.getDir() == 2) {
            mouse2.setYPos(mouse2.getYPos() + blockSize/20);
        } else if (mouse2.getDir() == 3) {
            mouse2.setXPos(mouse2.getXPos() + -blockSize/20);
        }
    }

    public void moveMouse3() {
        short ch;
        int mouseDir = mouse3.getDir();
        int xPosMouse = mouse3.getXPos();
        int yPosMouse = mouse3.getYPos();
        int xDis = eatman.getXPos() - xPosMouse;
        int yDis = eatman.getYPos() - yPosMouse;


        if ((xPosMouse % blockSize == 0) && (yPosMouse % blockSize == 0)) {
            ch = currentMap[yPosMouse / blockSize][xPosMouse / blockSize];

            if (xPosMouse >= blockSize * 17) {
                xPosMouse = 0;
                mouse3.setXPos(0);
            }
            if (xPosMouse < 0) {
                xPosMouse = blockSize * 17;
                mouse3.setXPos(blockSize * 17);
            }


            // Move mouse right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 1;
                        mouse3.setDir(1);
                    } else {
                        mouseDir = 2;
                        mouse3.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    mouseDir = 1;
                    mouse3.setDir(1);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse3.setDir(2);
                } else {
                    mouseDir = 3;
                    mouse3.setDir(3);
                }
            }


            // Move mouse right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            mouseDir = 1;
                            mouse3.setDir(1);
                        } else {
                            mouseDir = 0;
                            mouse3.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        mouseDir = 1;
                        mouse3.setDir(1);
                    } else if ((ch & 2) == 0) {
                        mouseDir = 0;
                        mouse3.setDir(0);
                    } else {
                        mouseDir = 2;
                        mouse3.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse3.setDir(3);
                    } else {
                        mouseDir = 2;
                        mouse3.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse3.setDir(3);
                } else if ((ch & 8) == 0) {
                    mouseDir = 2;
                    mouse3.setDir(2);
                } else {
                    mouseDir = 1;
                    mouse3.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        mouseDir = 3;
                        mouse3.setDir(3);
                    } else {
                        mouseDir = 0;
                        mouse3.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    mouseDir = 3;
                    mouse3.setDir(3);
                } else if ((ch & 2) == 0) {
                    mouseDir = 0;
                    mouse3.setDir(0);
                } else {
                    mouseDir = 2;
                    mouse3.setDir(2);
                }
            }


            // Handles wall collisions
            if ((mouseDir == 3 && (ch & 1) != 0) ||
                    (mouseDir == 1 && (ch & 4) != 0) ||
                    (mouseDir == 0 && (ch & 2) != 0) ||
                    (mouseDir == 2 && (ch & 8) != 0)) {
                mouseDir = 4;
                mouse3.setDir(4);
            }
        }



        if (mouse3.getDir() == 0) {
            mouse3.setYPos(mouse3.getYPos() + -blockSize/20);
        } else if (mouse3.getDir() == 1) {
            mouse3.setXPos(mouse3.getXPos() + blockSize/20);
        } else if (mouse3.getDir() == 2) {
            mouse3.setYPos(mouse3.getYPos() + blockSize/20);
        } else if (mouse3.getDir() == 3) {
            mouse3.setXPos(mouse3.getXPos() + -blockSize/20);
        }
    }

    public void tryDeath(Context context){
        try{
            checkPlayerDeath();
        } catch (PlayerDeathException e){
            Intent failedIntent = new Intent(context, FailedLevelActivity.class);
            context.startActivity(failedIntent);
        }
    }
}//Movement

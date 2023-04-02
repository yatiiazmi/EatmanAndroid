package com.example.eatmanandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by colerogers on 7/25/16.
 */
public class Eatman {
    //private instance vars
    private int xPos, yPos, curDir, nextDir;

    //create a new Pacman object at starting position
    public Eatman(final int blockSize) {

        //Eatman is at the 8 position of length in the array x-dir
        //e.g. curMap[8,y]
        xPos = 8 * blockSize;

        //Pacman is at the 13 position of height in the array y-dir
        //e.g. curMap[x,13]
        yPos = 13 * blockSize;

        curDir = 2;
        nextDir = 4;
    }

    //public getters
    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getCurDir() {
        return curDir;
    }

    public int getNextDir() {
        return nextDir;
    }

    //public setters
    public void setXPos(int xPos) { this.xPos = xPos; }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public void setCurDir(int curDir) {
        this.curDir = curDir;
    }

    public void setNextDir(int nextDir) {
        this.nextDir = nextDir;
    }

    // This was based on the non-Android Eatman legacy project for CS56
    // Method that draws eatman based on his viewDirection
    public void drawEatman(BitmapImages bitmap, Canvas canvas, Movement movement, Paint paint, Context context, int currentEatmanFrame) {
        //move pacman
        movement.moveEatman();

        //draw eatman
        switch (this.getCurDir()) {
            case (0):
                canvas.drawBitmap(bitmap.getEatmanUp()[currentEatmanFrame], this.getXPos(), this.getYPos(), paint);
                break;
            case (1):
                canvas.drawBitmap(bitmap.getEatmanRight()[currentEatmanFrame], this.getXPos(), this.getYPos(), paint);
                break;
            case (3):
                canvas.drawBitmap(bitmap.getEatmanLeft()[currentEatmanFrame], this.getXPos(), this.getYPos(), paint);
                break;
            default:
                canvas.drawBitmap(bitmap.getEatmanDown()[currentEatmanFrame], this.getXPos(), this.getYPos(), paint);
                break;
        }

        //update eatman
        movement.updateEatman();
        //check if there is a collision
        movement.tryDeath(context);
    }
}